package net.premieredigital.portal.admin.services;

import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.ServiceAdminConfiguration;
import net.premieredigital.portal.pom.ServiceAdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class UserAddService {
	private ServiceAdminConfiguration serviceAdminConfig;
	private ServiceAdminPage serviceAdmin;
	private BaseTest baseTest;
	public UserAddService(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void postCondition() {
		serviceAdmin.deleteService();
	}
	public void preCondition() throws Exception {
		baseTest.setImplicitWait(5);
		this.serviceAdminConfig = new ServiceAdminConfiguration();
		//new LoginPage(this.webDriver.get(), this.pageConfig).login();
		this.serviceAdmin = new ServiceAdminPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.serviceAdminConfig);
			}
	//2.3.1
	//@Test(description="2.3.1 User shall be able to add a service")
	public void userSuccessfulAddService() throws Exception {
		this.preCondition();
		serviceAdmin.addService();
		ExpectedConditions.textToBePresentInElementLocated(By.tagName("a"),serviceAdminConfig.getServiceName());
	}
	//@Test(description="2.3.2 User shall not be able to add service")
	public void userUnsuccessfulAddServiceDuplicateEntry() {
		serviceAdmin.addService();
		serviceAdmin.addService();
		baseTest.getWebDriver().findElement(By.className("duplicate")).equals("Service name already available.");
	}
}
