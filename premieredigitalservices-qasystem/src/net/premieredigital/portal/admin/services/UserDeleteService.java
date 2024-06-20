package net.premieredigital.portal.admin.services;

import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.ServiceAdminConfiguration;
import net.premieredigital.portal.pom.ServiceAdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.base.Predicate;

public class UserDeleteService {
	private ServiceAdminConfiguration serviceAdminConf;
	private ServiceAdminPage serviceAdmin;
	private BaseTest baseTest;
	public UserDeleteService(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void postCondition() {
		//serviceAdmin.deleteService();
	}

	public void preCondition() throws Exception {
		baseTest.setImplicitWait(5);
		this.serviceAdminConf = new ServiceAdminConfiguration();
		//new LoginPage(webDriver.get(), this.pageConfig).login();
		this.serviceAdmin = new ServiceAdminPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.serviceAdminConf);
	}
	//2.3.4
	//@Test(description="2.3.4 User shall be able to delete a service")
	public void successfulDeleteService() throws Exception {
		this.preCondition();
		serviceAdmin.addService();
		serviceAdmin.deleteService();
		baseTest.getWebDriver().navigate().refresh();
	    FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(baseTest.getWebDriver());
        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(10000, TimeUnit.MILLISECONDS);
        try {
        	fluentWait.until(new Predicate<WebDriver>() {
                public boolean apply(WebDriver newDriver) {
                    try {
                    	System.out.println("Fluentwait delete");
                    	             	  	
                        return newDriver.findElements(By.linkText(serviceAdminConf.getServiceName())).size()==0;
                    } catch (NoSuchElementException ex) {
                        return false;
                    }
                    catch (TimeoutException ex) {
                    	return true;
                    }
                }
            });
        } catch(Exception ex) {
        }
		Boolean isPresent = baseTest.getWebDriver().findElements(By.linkText(serviceAdminConf.getServiceName())).size()>0;
		Assert.assertFalse(isPresent);
	}
}
