package net.premieredigital.portal.admin.sources;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.SourceAdminConfiguration;
import net.premieredigital.portal.pom.SourceAdminPage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserDeleteSource extends BaseTest{
	private SourceAdminConfiguration sourceAdminConfig;
	private SourceAdminPage sourceAdmin;

	public void postCondition() {
		sourceAdmin.deleteSource();
	}

	public void preCondition() throws Exception {
		this.sourceAdminConfig = new SourceAdminConfiguration();
		//new LoginPage(this.webDriver.get(), this.pageConfig).login();
		this.sourceAdmin = new SourceAdminPage(this.webDriver.get(),this.pageConfig, new SourceAdminConfiguration());
			}
	//Test 2.7.4
	//@Test(groups="ExcludeSafari",description="2.7.4 User shall be able to delete a source")
	public void userSuccessfulDeleteSource() {
		sourceAdmin.addSource();
		sourceAdmin.deleteSource();
		Boolean isPresent = webDriver.get().findElements(By.linkText(sourceAdminConfig.getName())).size()>0;
		Assert.assertFalse(isPresent);
	}
}
