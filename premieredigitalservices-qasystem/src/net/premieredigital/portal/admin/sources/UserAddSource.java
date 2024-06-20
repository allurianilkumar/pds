package net.premieredigital.portal.admin.sources;


import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.SourceAdminConfiguration;
import net.premieredigital.portal.pom.SourceAdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserAddSource extends BaseTest{
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
	//test 2.7.1
	//@Test(groups="ExcludeSafari",description="2.7.1 User shall be able to add a source")
	public void userSuccessfulAddSource() {
		sourceAdmin.addSource();
		ExpectedConditions.textToBePresentInElementLocated(By.tagName("a"),sourceAdminConfig.getName());
			}
	//@Test(groups="ExcludeSafari",description="2.7.2 User shall not be able to add source due to duplicate entry")
	public void userUnsuccessfulAddSourceDuplicateEntry() {
		sourceAdmin.addSource();
		sourceAdmin.addDuplicateSource();
		ExpectedConditions.textToBePresentInElementLocated(By.className("errors"),"Duplicate entry '" + sourceAdminConfig.getName() +"' for key 'name'");
	}
}
