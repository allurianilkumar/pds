package net.premieredigital.portal.admin.providers;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.ProviderAdminConfiguration;
import net.premieredigital.portal.pom.ProviderAdminPage;

import org.testng.annotations.Test;


public class UserAddContracts {
	private ProviderAdminConfiguration providerAdminConfig;
	private ProviderAdminPage providerAdmin;
	private BaseTest baseTest;
	public UserAddContracts(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void postCondition() {
		//providerAdmin.deleteProvider();
	}

	public void preCondition() {
		try {
			this.providerAdminConfig = new ProviderAdminConfiguration();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//new LoginPage(webDriver.get(), this.pageConfig).login();
		this.providerAdmin = new ProviderAdminPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.providerAdminConfig);
	}
	//2.2.4
	//@Test(groups="ExcludeSafari",description="2.2.2 User shall be able to add contracts")
	public void userShouldBeAbleToAddContracts() {
		this.preCondition();
		//providerAdmin.createProvider();
		providerAdmin.addContracts();
//		fluentlyWait(new FluentWait<By>(By.className("content")));
//		Assert.assertTrue(webDriver.get().findElement(By.className("content")).getText().contains("text.txtSept. 11, 2014Sept. 23, 2014Sept. 11, 2014"));
//		providerAdmin.deleteProvider();
	}
	//2.2.5
	//@Test(description="2.2.5 User shall not be able to add contracts")
	public void userUnsuccessfulAddContract() {
		providerAdmin.createProvider();
		providerAdmin.unsuccessfulAddContract();
//		fluentlyWait(new FluentWait<By>(By.className("content")));
//		Assert.assertTrue(webDriver.get().findElement(By.className("content")).getText().contains("Mapping value: This field is required"));
		
//		providerAdmin.deleteProvider();
	}
}
