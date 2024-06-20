package net.premieredigital.portal.admin.providers;

import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.ProviderAdminConfiguration;
import net.premieredigital.portal.pom.ProviderAdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import com.google.common.base.Predicate;

public class UserDeleteProviderTest extends BaseTest {
	private ProviderAdminConfiguration providerAdminConfig;
	private ProviderAdminPage providerAdmin;

//	@AfterMethod
//	public void postCondition() {
//		providerAdmin.deleteProvider();
//	}

	public void preCondition() {
		this.providerAdminConfig = new ProviderAdminConfiguration();
		new LoginPage(webDriver.get(), this.pageConfig).login();
		this.providerAdmin = new ProviderAdminPage(webDriver.get(),this.pageConfig, this.providerAdminConfig);
			}
	//2.2.8
	//@Test(description="2.2.8 User shall be able to delete a provider")
	public void userDeleteProvider() {
		providerAdmin.createProvider();
		providerAdmin.deleteProvider();
		webDriver.get().navigate().refresh();
		 FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(webDriver.get());
	        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
	        fluentWait.withTimeout(10000, TimeUnit.MILLISECONDS);
	        fluentWait.ignoring(TimeoutException.class);
	        fluentWait.until(new Predicate<WebDriver>() {
	            public boolean apply(WebDriver driver) {
	                try {	  	
	                  return driver.findElements(By.linkText(pageConfig.getProviderName().toLowerCase().replaceAll(" ", ""))).size()==0;
	                } catch (NoSuchElementException ex) {
	                    return false;
	                }
	                catch (TimeoutException ex) {
	                	return true;
	                }
	            }
	        });
		/*if (this.pageConfig.getBrowserName() == "safari") {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
		Boolean isPresent = webDriver.get().findElements(By.linkText(pageConfig.getProviderName().toLowerCase().replaceAll(" ", ""))).size()>0;
		Assert.assertFalse(isPresent);
	}
}
