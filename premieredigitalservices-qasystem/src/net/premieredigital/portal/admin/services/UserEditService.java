package net.premieredigital.portal.admin.services;

import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.ServiceAdminConfiguration;
import net.premieredigital.portal.pom.ServiceAdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.base.Predicate;

public class UserEditService {
	private ServiceAdminConfiguration serviceAdminConf;
	private ServiceAdminPage serviceAdmin;
	private BaseTest baseTest;
	public UserEditService(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void postCondition() {
		serviceAdmin.deleteService();
	}

	public void preCondition() throws Exception {
		this.serviceAdminConf = new ServiceAdminConfiguration();
		//new LoginPage(this.webDriver.get(), this.pageConfig).login();
		this.serviceAdmin = new ServiceAdminPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.serviceAdminConf);
	}
	//2.3.3
	//@Test(description="2.3.3 User shall be able to edit a service")
	public void successfulEditService() throws Exception {
		this.preCondition();
		baseTest.setImplicitWait(5);
		serviceAdmin.addService();
		serviceAdmin.editService();
		FluentWait<By> fluentWait = new FluentWait<By>(By.name("notes"));
        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(2000, TimeUnit.MILLISECONDS);
        fluentWait.until(new Predicate<By>() {
            public boolean apply(By by) {
                try {
                    return baseTest.getWebDriver().findElements(by).size() > 0;
                } catch (NoSuchElementException ex) {
                    return false;
                }
            }
        });
//		new WebDriverWait(webDriver.get(),30).until(ExpectedConditions.presenceOfElementLocated(By.name("notes")));
		ExpectedConditions.textToBePresentInElementLocated(By.name("name"), serviceAdminConf.getServiceName());
		ExpectedConditions.textToBePresentInElementLocated(By.name("notes"), serviceAdminConf.getAddress());
		ExpectedConditions.textToBePresentInElementLocated(By.name("price_notes"), serviceAdminConf.getPriceNotes());
		ExpectedConditions.textToBePresentInElementLocated(By.name("art_work_description"), serviceAdminConf.getDescription());
//		if (this.pageConfig.getBrowserName() != "safari") {
//			Assert.assertTrue(new Select(driver.findElement(By.name("packaging_method"))).getFirstSelectedOption().getText().equals(serviceAdminConf.getPackagingMethod()));
//			Assert.assertTrue(new Select(driver.findElement(By.name("packaging.file_renaming"))).getFirstSelectedOption().getText().equals(serviceAdminConf.getFileRenaming()));
//			ExpectedConditions.textToBePresentInElementLocated(By.name("packaging.transcode_hot_folder"), serviceAdminConf.getTranscodeFolder());
//			Assert.assertTrue(new Select(driver.findElement(By.name("delivery_method"))).getFirstSelectedOption().
//					getText().equals(serviceAdminConf.getDeliveryMethod()));
//		}
		Boolean isChecked;
		isChecked = baseTest.getWebDriver().findElement(By.name("automatic_delivery")).isSelected();
		Assert.assertTrue(isChecked);
		isChecked = baseTest.getWebDriver().findElement(By.name("allows_delivery_resume")).isSelected();
		Assert.assertTrue(isChecked);
	}
}
