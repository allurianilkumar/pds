package net.premieredigital.portal.pom;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Predicate;

public class LoginPage extends BasePage {
	
	private PageConfiguration pageConfig = null;
	
	public String getPageURL() {
		return pageConfig.getRootURL() + "/";
	}
	public LoginPage(WebDriver driver, PageConfiguration pageConfig) {
		this.driver = driver;
		this.pageConfig = pageConfig;
	}
	public void clientLogin(){
	    this.driver.get(getPageURL());
	    fluentlyWait(new FluentWait<By>(By.id("id_username")));
	    this.driver.findElement(By.id("id_username")).sendKeys(pageConfig.getClientUserName());
	    this.driver.findElement(By.id("id_password")).sendKeys(pageConfig.getPassword());
	    this.driver.findElement(By.name("submit")).click();
	    setImplicitWait(5);
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void login() {
	    this.driver.get(getPageURL());
	    fluentlyWait(new FluentWait<By>(By.id("id_username")));
	    this.driver.findElement(By.id("id_username")).sendKeys(pageConfig.getUsername());
	    this.driver.findElement(By.id("id_password")).sendKeys(pageConfig.getPassword());
	    this.driver.findElement(By.name("submit")).click();
	    setImplicitWait(5);
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	    FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
//        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
//        fluentWait.withTimeout(10000, TimeUnit.MILLISECONDS);
//        fluentWait.until(new Predicate<WebDriver>() {
//            public boolean apply(WebDriver elm) {
//                try {
//                	System.out.println("Fluentwait user");             	  	
//                    return elm.findElement(By.className("username")) != null;
//                } catch (NoSuchElementException ex) {
//                	System.out.println("Fluentwait no such element");
//                    return false;
//                }
//            }
//        });
        
//	    if(this.pageConfig.getBrowserName() != null && this.pageConfig.getBrowserName() == "safari") {
//		    try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//	    }
	}
	public void invalidLogin() {
		this.driver.get(getPageURL());
		fluentlyWait(new FluentWait<By>(By.id("id_username")));
		this.driver.findElement(By.id("id_username")).sendKeys("nouser");
		this.driver.findElement(By.id("id_password")).sendKeys("nopassword");
		this.driver.findElement(By.name("submit")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void logout() {
		/*System.out.println("Loggingout ************");
		new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.className("username")));
		driver.findElement(By.className("username")).click();			
		new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
		driver.findElement(By.linkText("Logout")).click();*/
		driver.get(pageConfig.getRootURL()+"/logout/");
	}
}
