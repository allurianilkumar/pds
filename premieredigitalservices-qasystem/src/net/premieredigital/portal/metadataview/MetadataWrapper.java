package net.premieredigital.portal.metadataview;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.admin.billingparties.UserShallBeAbleToAddBillingParty;
import net.premieredigital.portal.admin.billingparties.UserShallBeAbleToEditBillingParty;
import net.premieredigital.portal.admin.ratings.UserAddRatingSystem;
import net.premieredigital.portal.admin.services.UserAddDeliveryMethods;
import net.premieredigital.portal.admin.services.UserAddSpecification;
import net.premieredigital.portal.admin.services.UserDeleteSpecification;
import net.premieredigital.portal.masteringview.UserRemoveTaskAndAddTaskInMasteringGrid;
import net.premieredigital.portal.masteringview.UserViewAndEditTitleInTheMasteringGrid;
import net.premieredigital.portal.orderitemsview.SaveDefaultSearchInOrderItemView;
import net.premieredigital.portal.ordersview.PlaceAnOrder;
import net.premieredigital.portal.ordersview.othertest.GlobalSearch;

import org.testng.annotations.Test;

public class MetadataWrapper extends BaseTest{

	@Test(description="9.2 Synchronize Metadata View With Customized Metadata Layout For iTunes Service")
    public void syncMetadataViewWithCustomizedMetadataLayoutForiTunes()  throws Exception {
    	SyncMetadataViewWithCustomizedMetadataLayoutForiTunes syncMetadataViewWithCustomizedMetadataLayoutForiTunes = new SyncMetadataViewWithCustomizedMetadataLayoutForiTunes(this);
    	syncMetadataViewWithCustomizedMetadataLayoutForiTunes.syncMetadataViewWithCustomizedMetadataLayoutiTunes();
    }
    @Test(description="2.6.1 User shall be able to add a rating system")
    public void userAddRatingSystem() throws Exception {
    	UserAddRatingSystem userAddRatingSystem = new UserAddRatingSystem(this);
    	userAddRatingSystem.userAddRatingSystem();
    }
    @Test(groups="ExcludeSafari" ,description="4.6 User Shall Be Able To View And Edit Title In The Mastering Grid")
    public void userViewAndEditTitleInTheMasteringGrid()  throws Exception {
		UserViewAndEditTitleInTheMasteringGrid userViewAndEditTitleInTheMasteringGrid = new UserViewAndEditTitleInTheMasteringGrid(this);
		userViewAndEditTitleInTheMasteringGrid.viewAndEditTitleInMasteringGrid();
    }
	@Test(groups = "ExcludeSafari",description="5.4 Save Default Search in Order Item View")
    public void saveDefaultSearchInOrderItemView()  throws Exception {
    	SaveDefaultSearchInOrderItemView saveDefaultSearchInOrderItemView = new SaveDefaultSearchInOrderItemView(this);
    	saveDefaultSearchInOrderItemView.saveDefaultSearch();
	}
    @Test(groups = "ExcludeSafari",description="6.1 User shall be able to place an order")
    public void placeAnOrder()  throws Exception {
        PlaceAnOrder placeAnOrder = new PlaceAnOrder(this);
        placeAnOrder.placeAnOrder();
    }
}
