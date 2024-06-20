package net.premieredigital.portal.admin.providers;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.ProviderAdminConfiguration;
import net.premieredigital.portal.pom.ProviderAdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserEditMapping extends BaseTest {
	private ProviderAdminConfiguration providerAdminConfig;
	private ProviderAdminPage providerAdmin;
	
	public void postCondition() {
		//providerAdmin.deleteProvider();
	}

	public void preCondition() {
		this.providerAdminConfig = new ProviderAdminConfiguration();
		new LoginPage(webDriver.get(), this.pageConfig).login();
		this.providerAdmin = new ProviderAdminPage(webDriver.get(),this.pageConfig, this.providerAdminConfig);
	
	}
	//2.2.6
	//@Test(groups="ExcludeSafari",description = "2.2.6 User shall be able to edit mapping")
	public void userSuccessfullyEditMapping() {
		setImplicitWait(5);
		//providerAdmin.createProvider();
		providerAdmin.editMapping();
		ExpectedConditions.textToBePresentInElementLocated(By.className("content"), "Mapping Saved");
//		providerAdmin.deleteProvider();
	}
	//2.2.7
	//@Test(groups="ExcludeSafari",description = "2.2.7 User shall not be able to edit mapping")
	public void userUnsuccessfulEditMapping() {
		//providerAdmin.createProvider();
		providerAdmin.unsuccessfulEditMapping();
		ExpectedConditions.textToBePresentInElementLocated(By.className("content"), "Cannot add contract");
//		providerAdmin.deleteProvider();
	}
}
