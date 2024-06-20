package net.premieredigital.portal.admin;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.admin.billingparties.UserShallBeAbleToAddBillingParty;
import net.premieredigital.portal.admin.billingparties.UserShallBeAbleToEditBillingParty;
import net.premieredigital.portal.admin.others.SuccessfulLoginTest;
import net.premieredigital.portal.admin.providers.UserAddContracts;
import net.premieredigital.portal.admin.providers.UserAddProvider;
import net.premieredigital.portal.admin.providers.UserModifyProviderInfo;
import net.premieredigital.portal.admin.ratings.UserAddRatingSystem;
import net.premieredigital.portal.admin.services.UserAddDeliveryMethods;
import net.premieredigital.portal.admin.services.UserAddService;
import net.premieredigital.portal.admin.services.UserAddSpecification;
import net.premieredigital.portal.admin.services.UserDeleteService;
import net.premieredigital.portal.admin.services.UserDeleteSpecification;
import net.premieredigital.portal.admin.services.UserEditService;
import net.premieredigital.portal.admin.users.InactiveUserShallNotAbleToLogin;
import net.premieredigital.portal.admin.users.UserShallBeAbleToAddUser;
import net.premieredigital.portal.masteringview.AddAndEditQcNoteonMasteringVideoPage;
import net.premieredigital.portal.masteringview.UserViewAndEditTitleInTheMasteringGrid;
import net.premieredigital.portal.metadataview.SyncMetadataViewWithCustomizedMetadataLayoutForAmazon;
import net.premieredigital.portal.orderitemsview.TestFiltersOnOrderItemsView;
import net.premieredigital.portal.orderitemsview.UpdateStatusInOrderItemView;

import org.testng.annotations.Test;

public class AdminWrapper extends BaseTest {     
	@Test(description="1.6 Invalid username during forgot password",priority=4)
	public void invalidEmailDuringForgotPasswordTest() throws Exception {
		SuccessfulLoginTest successfulLoginTest = new SuccessfulLoginTest(this);
		successfulLoginTest.invalidEmailDuringForgotPasswordTest();
	}
    //User Shall be able to add a user
    @Test(description= "2.1.1 User shall be able to add a user",priority=5)
    public void userShallBeAbleToAddUser() throws Exception {
    	UserShallBeAbleToAddUser userShallBeAbleToAddUser = new UserShallBeAbleToAddUser(this);
    	userShallBeAbleToAddUser.userShallBeAbleToAddUserTest();
    }
    //Inactive user shall not be able to login
    @Test(description="2.1.2 Inactive user shall not be able to login",priority=6)
    public void inactiveUserShallNotAbleToLogin() throws Exception {
    	InactiveUserShallNotAbleToLogin inactiveUserShallNotAbleToLogin = new InactiveUserShallNotAbleToLogin(this);
    	inactiveUserShallNotAbleToLogin.inactiveUserShallNoteAbleToLoginTest();
    	
    }
    @Test(description="2.2.1 User shall be able to add a provider")
    public void  userAddProvider() throws Exception {
    	UserAddProvider userAddProvider = new UserAddProvider(this);
    	userAddProvider.successfulAddProviderTest();
    }
	@Test(groups="ExcludeSafari",description="2.2.2 User shall be able to add contracts")
    public void userAddContracts()  throws Exception {
    	UserAddContracts userAddContracts = new UserAddContracts(this);
    	userAddContracts.userShouldBeAbleToAddContracts();
    }
    @Test(groups = "ExcludeSafari",description="2.2.3 User shall be able to edit provider information")
    public void userModifyProviderInfo() throws Exception {
    	UserModifyProviderInfo userModifyProviderInfo= new UserModifyProviderInfo(this);
    	userModifyProviderInfo.userShouldBeAbleToModifyProviderInfo();
    }
	@Test(description="1.5 Forgot Password",priority=3)
	public void forgotPasswordTest() throws Exception {
		SuccessfulLoginTest successfulLoginTest = new SuccessfulLoginTest(this);
		successfulLoginTest.forgotPasswordTest();
	}
	@Test(description="2.3.4 User shall be able to delete a service")
	public void userDeleteService() throws Exception {
		UserDeleteService userDeleteService = new UserDeleteService(this);
		userDeleteService.successfulDeleteService();
	}
	@Test(groups="ExcludeSafari", description="4.10 Add and Edit QC Note on Mastering Video Page")
    public void AddAndEditQcNoteonMasteringVideoPage()  throws Exception {
		AddAndEditQcNoteonMasteringVideoPage addAndEditQcNoteonMasteringVideoPage = new AddAndEditQcNoteonMasteringVideoPage(this);
		addAndEditQcNoteonMasteringVideoPage.addAndEditQcNoteonMasteringVideoPage();
    }
}
