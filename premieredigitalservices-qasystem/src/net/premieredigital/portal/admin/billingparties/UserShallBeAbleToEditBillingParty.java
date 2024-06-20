package net.premieredigital.portal.admin.billingparties;	

import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.BillingAdminConfiguration;
import net.premieredigital.portal.pom.BillingAdminPage;
import net.premieredigital.portal.pom.LoginPage;

import org.testng.annotations.Test;

	public class UserShallBeAbleToEditBillingParty {
		private BaseTest baseTest;
		public UserShallBeAbleToEditBillingParty(BaseTest baseTest) {
			this.baseTest = baseTest;
		}
			//2.2.6
			//@Test(description= "2.4.2 User shall be able to edit billing party")
			public void userShallBeAbleToEditBillingParty() throws Exception{
				baseTest.setImplicitWait(5);
				//new LoginPage(webDriver.get(), this.pageConfig).login();
				baseTest.getWebDriver().get(baseTest.getPageConfiguration().getRootURL()+"/admin/billing_parties");
				BillingAdminPage billingAdmin = new BillingAdminPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), new BillingAdminConfiguration());
				billingAdmin.addBillingParty();
				billingAdmin.editBillingParty();
			}

		}


