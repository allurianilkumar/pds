package net.premieredigital.portal.admin.providers;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.ProviderAdminConfiguration;
import net.premieredigital.portal.pom.ProviderAdminPage;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class UserAddProvider {
	private ProviderAdminConfiguration providerAdminConfig;
	private ProviderAdminPage providerAdmin;
	private BaseTest baseTest;
	public UserAddProvider(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void postCondition() {
		providerAdmin.deleteProvider();
	}

	public void preCondition() {
		baseTest.setImplicitWait(5);
		this.providerAdminConfig = new ProviderAdminConfiguration();
		//new LoginPage(webDriver.get(), this.pageConfig).login();
		this.providerAdmin = new ProviderAdminPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(),this.providerAdminConfig);
		}
	//2.2.1
	//@Test(description="2.2.1 User shall be able to add a provider")
	public void successfulAddProviderTest() {
		this.preCondition();
		providerAdmin.createProvider();
//		providerAdmin.deleteProvider();
	}
	//2.2.4
	//@Test(description="2.2.4 User shall not be able to add provider due to duplicate entry")
	public void unsuccessfulAddProviderDuplicateEntry() {
		providerAdmin.createProvider();
		providerAdmin.createProvider();
		baseTest.getWebDriver().findElement(By.className("duplicate")).equals("Provider already available");
//		providerAdmin.deleteProvider();
	}
}
