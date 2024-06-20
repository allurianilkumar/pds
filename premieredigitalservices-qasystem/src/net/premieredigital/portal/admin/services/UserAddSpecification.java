package net.premieredigital.portal.admin.services;

import java.util.List;
import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.pom.BasePage;
import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.ServiceAdminConfiguration;
import net.premieredigital.portal.pom.ServiceAdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.base.Predicate;

public class UserAddSpecification {
	private ServiceAdminConfiguration serviceAdminConf;
	private ServiceAdminPage serviceAdmin;
	private BaseTest baseTest;
	public UserAddSpecification(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void postCondition() {
		serviceAdmin.deleteService();
	}

	public void preCondition() throws Exception {
		this.serviceAdminConf = new ServiceAdminConfiguration();
		//new LoginPage(baseTest.getWebDriver(), this.pageConfig).login();
		this.serviceAdmin = new ServiceAdminPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.serviceAdminConf);
	}
	//2.3.7
	//@Test(groups = "ExcludeSafari" , description="2.3.7 User shall be able to add specification")
	public void successfulAddSpecificationTest() throws Exception {
		this.preCondition();
		baseTest.setImplicitWait(5);
		serviceAdmin.addService();
		serviceAdmin.addSpecification();
		Thread.sleep(5000);
		WebElement adminUser = baseTest.getWebDriver().findElement(By.id("admin_user"));
		WebElement mainContenet = adminUser.findElement(By.className("main-content"));
		//baseTest.fluentlyWait(new FluentWait<By>(By.className("service_specs")));
//		new WebDriverWait(webDriver.get(),30).until(ExpectedConditions.presenceOfElementLocated(By.className("service_specs")));
		WebElement table = mainContenet.findElement(By.tagName("table"));
		baseTest.fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement body = table.findElement(By.tagName("tbody"));
		baseTest.fluentlyWait(new FluentWait<By>(By.tagName("td")));
		List<WebElement> tds = body.findElements(By.tagName("td"));
		Boolean confirm;
		String split_name[] = serviceAdminConf.getFilePath().split("/");
		int size = split_name.length;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		confirm = tds.get(0).getText().contains(split_name[size-1]);
		Assert.assertTrue(confirm);
		confirm = tds.get(1).getText().contains(serviceAdminConf.getCategory());
		Assert.assertTrue(confirm);
		confirm = tds.get(2).getText().contains(serviceAdminConf.getPublicationDate());
		Assert.assertTrue(confirm);
		confirm = tds.get(3).getText().contains(serviceAdminConf.getUploadedDate());
		Assert.assertTrue(confirm);
	}
}
