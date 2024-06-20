package net.premieredigital.portal.admin.services;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.ProviderAdminConfiguration;
import net.premieredigital.portal.pom.ProviderAdminPage;
import net.premieredigital.portal.pom.ServiceAdminConfiguration;
import net.premieredigital.portal.pom.ServiceAdminPage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserAddDeliveryMethods {
	private ServiceAdminConfiguration serviceAdminConfig;
	private ProviderAdminConfiguration providerAdminConfig;
	private ServiceAdminPage serviceAdmin;
	private ProviderAdminPage providerAdmin;
	private BaseTest baseTest;
	public UserAddDeliveryMethods(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void postCondition() {
		serviceAdmin.deleteService();
	}
	public void preCondition() throws Exception {
		this.serviceAdminConfig = new ServiceAdminConfiguration();
		this.providerAdminConfig = new ProviderAdminConfiguration();
		//new LoginPage(baseTest.getWebDriver(), this.pageConfig).login();
		this.serviceAdmin = new ServiceAdminPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.serviceAdminConfig);
		this.providerAdmin = new ProviderAdminPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(),this.providerAdminConfig);
	}
	//2.3.5
	//@Test(description="2.3.5 User shall be able to add delivery methods per provider")
	public void successfulAddDeliveryMethods() throws Exception {
		this.preCondition();
		baseTest.setImplicitWait(5);
		this.providerAdmin.createProvider();
		serviceAdmin.addService();
		serviceAdmin.addDeliveryMethods();
		Assert.assertFalse(baseTest.getWebDriver().findElement(By.className("provider_id")).equals(baseTest.getPageConfiguration().getProviderName()));
//		serviceAdmin.deleteService();
	}
	//@Test(description="2.3.6 User shall not be able to add delivery methods per provider")
	public void unsuccessfulAddDeliveryMethods() {
		baseTest.setImplicitWait(5);
		serviceAdmin.addService();
		serviceAdmin.addDeliveryMethodsFail();
		Boolean isPresent = baseTest.getWebDriver().findElements(By.linkText("Add Provider")).size()>0;
		Assert.assertFalse(isPresent);
//		serviceAdmin.deleteService();
	}
}
