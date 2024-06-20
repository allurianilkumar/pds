package net.premieredigital.portal.admin.billingparties;
import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.BillingAdminConfiguration;
import net.premieredigital.portal.pom.BillingAdminPage;
import net.premieredigital.portal.pom.LoginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

		public class UserShallBeAbleToAddBillingParty {
			private BaseTest baseTest;
			public UserShallBeAbleToAddBillingParty(BaseTest baseTest) {
				this.baseTest = baseTest;
			}
			//2.2.6
			//@Test(description= "2.4.1 User shall be able to add billing party")
			public void userShallBeAbleToAddBillingParty() throws Exception{
				baseTest.setImplicitWait(5);
				//new LoginPage(webDriver.get(), this.pageConfig).login();
				BillingAdminConfiguration config = new BillingAdminConfiguration();
				BillingAdminPage billingAdmin = new BillingAdminPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), config);
				billingAdmin.addBillingParty();
				ExpectedConditions.textToBePresentInElementLocated(By.linkText(config.getName()), config.getName());
			}

		}

