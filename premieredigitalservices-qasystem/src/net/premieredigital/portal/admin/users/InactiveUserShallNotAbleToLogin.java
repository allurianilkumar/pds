package net.premieredigital.portal.admin.users;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.ProviderAdminConfiguration;
import net.premieredigital.portal.pom.ProviderAdminPage;
import net.premieredigital.portal.pom.ServiceAdminConfiguration;
import net.premieredigital.portal.pom.ServiceAdminPage;
import net.premieredigital.portal.pom.UserAdminConfiguration;
import net.premieredigital.portal.pom.UserAdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

	public class InactiveUserShallNotAbleToLogin {
		private UserAdminConfiguration userAdminConfig;
      	private UserAdminPage userAdmin;
      	private ProviderAdminPage providerAdmin;
      	private ProviderAdminConfiguration providerAdminConfig;
      	private ServiceAdminPage serviceAdmin;
      	private ServiceAdminConfiguration serviceAdminConfig;
        private BaseTest baseTest;
        public InactiveUserShallNotAbleToLogin(BaseTest baseTest) {
      		this.baseTest = baseTest;
        }
    	/*public void postCondition() {
			new LoginPage(this.webDriver.get(), this.pageConfig).login();
      		ProviderAdminPage providerAdmin = null;
			try {
				providerAdmin = new ProviderAdminPage(this.webDriver.get(), this.pageConfig, new PrviderAdminConfiguration());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ServiceAdminPage serviceAdmin =new ServiceAdminPage(this.webDriver.get(), this.pageConfig, serviceAdminConfig);
      		providerAdmin.deleteProvider();					
			serviceAdmin.deleteService();
    	}*/

    	public void preCondition() throws Exception {
    		//new LoginPage(this.webDriver.get(), this.pageConfig).login();
    		baseTest.getWebDriver().get(baseTest.getPageConfiguration().getRootURL()+"/admin/");
    		this.serviceAdminConfig = new ServiceAdminConfiguration();
    		/*this.providerAdminConfig = new ProviderAdminConfiguration();
    		ProviderAdminPage providerAdmin = new ProviderAdminPage(this.webDriver.get(), this.pageConfig, this.providerAdminConfig);
			ServiceAdminPage serviceAdmin =new ServiceAdminPage(this.webDriver.get(), this.pageConfig, serviceAdminConfig);
    		providerAdmin.createProvider();
    		serviceAdmin.addService();
    		System.out.println("preCondition executed");*/
    	}
		//2.1.2 Inactive user shall not be able to login
				//@Test(description="2.1.2 Inactive user shall not be able to login")
				public void inactiveUserShallNoteAbleToLoginTest() throws Exception{
					this.preCondition();
					new LoginPage(baseTest.getWebDriver(),baseTest.getPageConfiguration()).login();
					baseTest.setImplicitWait(5);
					//new LoginPage(this.webDriver.get(), this.pageConfig).login();
					UserAdminPage uAdminPage = new UserAdminPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), new UserAdminConfiguration(), serviceAdminConfig);
					uAdminPage.addUser();
					//check that user already exists message is shown.
					Assert.assertNotEquals(baseTest.getWebDriver().findElement(By.className("messages")).getText(), "User already exists.");
					uAdminPage.updateUser();
					uAdminPage.signOut();
					uAdminPage.userLogin();
					Assert.assertEquals(baseTest.getWebDriver().findElement(By.className("error")).getText(),"Invalid username / password.");
					//adminUserLogin();
				}
			}
