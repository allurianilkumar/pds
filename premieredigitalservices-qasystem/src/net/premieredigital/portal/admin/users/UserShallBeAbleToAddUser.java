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
import org.testng.annotations.Test;
              public class UserShallBeAbleToAddUser {	
            	  private UserAdminConfiguration userAdminConfig;
              	private UserAdminPage userAdmin;
              	private ProviderAdminPage providerAdmin;
              	private ProviderAdminConfiguration providerAdminConfig;
              	private ServiceAdminPage serviceAdmin;
              	private ServiceAdminConfiguration serviceAdminConfig;
                private BaseTest baseTest;
                public UserShallBeAbleToAddUser(BaseTest baseTest) {
              		this.baseTest = baseTest;
                }
//            	public void postCondition(){
//            		this.providerAdminConfig = new ProviderAdminConfiguration();
//              		//new LoginPage(this.webDriver.get(), this.pageConfig).login();
//              		ProviderAdminPage providerAdmin = null;
//					try {
//						providerAdmin = new ProviderAdminPage(this.webDriver.get(), this.pageConfig, this.providerAdminConfig);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					ServiceAdminPage serviceAdmin =new ServiceAdminPage(this.webDriver.get(), this.pageConfig, serviceAdminConfig);
//					providerAdmin.deleteProvider();
//              		serviceAdmin.deleteService();
//              	}

            	public void preCondition() throws Exception {
					baseTest.setImplicitWait(5);
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
			//2.2.6
				//@Test(description= "2.1.1 User shall be able to add a user")
				public void userShallBeAbleToAddUserTest() throws Exception{
					baseTest.setImplicitWait(5);
					UserAdminPage uAdminPage = new UserAdminPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), new UserAdminConfiguration(), serviceAdminConfig);
					this.userAdminConfig = new UserAdminConfiguration();
					uAdminPage.addUser();
					ExpectedConditions.textToBePresentInElementLocated(By.className("messages"),"User successfully added.");
					uAdminPage.signOut();
					uAdminPage.userLogin();
					ExpectedConditions.textToBePresentInElementLocated(By.className("username"),userAdminConfig.getEmail());
					uAdminPage.signOut();
					//this.adminUserLogin();
				}
		}


