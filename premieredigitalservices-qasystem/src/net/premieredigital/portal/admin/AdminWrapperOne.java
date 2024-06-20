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
import net.premieredigital.portal.masteringview.UserViewAndEditTitleInTheMasteringGrid;
import net.premieredigital.portal.metadataview.SyncMetadataViewWithCustomizedMetadataLayoutForAmazon;
import net.premieredigital.portal.orderitemsview.TestFiltersOnOrderItemsView;
import net.premieredigital.portal.orderitemsview.UpdateStatusInOrderItemView;

import org.testng.annotations.Test;

public class AdminWrapperOne extends BaseTest {
   @Test(description="9.1 Synchronize Metadata View With Customized Metadata Layout For Amazon Service")
    public void syncMetadataViewWithCustomizedMetadataLayoutForAmazon()  throws Exception {
        SyncMetadataViewWithCustomizedMetadataLayoutForAmazon syncMetadataViewWithCustomizedMetadataLayoutForAmazon =
                new SyncMetadataViewWithCustomizedMetadataLayoutForAmazon(this);
        syncMetadataViewWithCustomizedMetadataLayoutForAmazon.syncMetadataViewWithCustomizedMetadataLayoutAmazon();
    }
	@Test(groups = "ExcludeSafari",description="5.1 Update status in order items view")
	public void updateStatusInOrderItemView()  throws Exception {
		UpdateStatusInOrderItemView updateStatusInOrderItemView = new UpdateStatusInOrderItemView(this);
		updateStatusInOrderItemView.updateStatus();
	}
	@Test(description="1.3 Unsuccessful login to the system",priority=2)
	public void unsuccessfulLoginTest() throws Exception {
		SuccessfulLoginTest successfulLoginTest = new SuccessfulLoginTest(this);
		successfulLoginTest.unsuccessfulLoginTest();
	}
	@Test(description= "2.4.2 User shall be able to edit billing party")
    public void userShallBeAbleToEditBillingParty()  throws Exception {
		UserShallBeAbleToEditBillingParty userShallBeAbleToEditBillingParty = new UserShallBeAbleToEditBillingParty(this);
		userShallBeAbleToEditBillingParty.userShallBeAbleToEditBillingParty();
    }
	@Test(description= "2.4.1 User shall be able to add billing party")
    public void userShallBeAbleToAddBillingParty()  throws Exception {
		UserShallBeAbleToAddBillingParty userShallBeAbleToAddBillingParty = new UserShallBeAbleToAddBillingParty(this);
		userShallBeAbleToAddBillingParty.userShallBeAbleToAddBillingParty();
    }
}
