	package net.premieredigital.portal.pom;
	import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

	public class UserAdminPage extends BasePage {
		private PageConfiguration pageConfig = null;
		private UserAdminConfiguration userAdminConf =null;
		private ServiceAdminConfiguration serviceAdminConf =null;
		private String first_name,last_name,email,role,provider,services,password;
		public UserAdminPage(WebDriver driver, PageConfiguration pageConfig, UserAdminConfiguration userAdminConf, ServiceAdminConfiguration serviceAdminConf){
			this.driver=driver;
			this.pageConfig=pageConfig;
			this.userAdminConf = userAdminConf;
			this.serviceAdminConf = serviceAdminConf;
			
		}
		public void addUser(){
			driver.get(pageConfig.getRootURL()+"/admin/");
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			assertPageTitle("Users");
			fluentlyWait(new FluentWait<By>(By.name("first_name")));
			driver.findElement(By.name("first_name")).sendKeys(userAdminConf.getFirstName());	
			driver.findElement(By.name("last_name")).sendKeys(userAdminConf.getLastName());
			driver.findElement(By.name("email")).sendKeys(userAdminConf.getEmail());
			new Select(driver.findElement(By.name("role"))).selectByVisibleText(userAdminConf.getRole());	
			new Select(driver.findElement(By.name("providers_list"))).selectByVisibleText(userAdminConf.getProvider());
			new Select(driver.findElement(By.name("services_list"))).selectByVisibleText(userAdminConf.getServices());		
			driver.findElement(By.name("submit")).click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void updateUser(){
	        fluentlyWait(new FluentWait<By>(By.className("username")));
	        fluentlyWait(new FluentWait<By>(By.linkText(userAdminConf.getFirstName() +" Tester")));
			driver.findElement(By.linkText(userAdminConf.getFirstName() +" Tester")).click();
			fluentlyWait(new FluentWait<By>(By.name("is_active")));

			if (driver.findElement(By.name("is_active")).isSelected() )
			{
			     driver.findElement(By.name("is_active")).click();
			     try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			driver.findElement(By.name("submit")).click();
			fluentlyWait(new FluentWait<By>(By.name("is_active")));
		}
		public void signOut(){
			//setImplicitWait(5);
//	        fluentlyWait(new FluentWait<By>(By.className("collapsed")));	        
//	        driver.findElement(By.className("collapsed")).click();
//			//driver.findElement(By.xpath("//div[@id='header']/div/span[2]/span/span")).click();	
//	        fluentlyWait(new FluentWait<By>(By.linkText("Logout")));
//	        driver.findElement(By.linkText("Logout")).click();
	        driver.get(pageConfig.getRootURL()+"/logout/");
		}
		public void userLogin(){				 
			    this.driver.findElement(By.id("id_username")).sendKeys(userAdminConf.getEmail());
			    this.driver.findElement(By.id("id_password")).sendKeys(userAdminConf.getPassword());
			    this.driver.findElement(By.name("submit")).click();			    
			    fluentlyWait(new FluentWait<By>(By.className("username")));
			}
		public String getPageURL() {
			return pageConfig.getRootURL() + "/admin/";
		}
		}
