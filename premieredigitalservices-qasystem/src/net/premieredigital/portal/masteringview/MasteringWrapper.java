package net.premieredigital.portal.masteringview;
import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.admin.billingparties.UserShallBeAbleToAddBillingParty;
import net.premieredigital.portal.admin.billingparties.UserShallBeAbleToEditBillingParty;
import net.premieredigital.portal.admin.ratings.UserAddRatingSystem;
import net.premieredigital.portal.admin.services.UserAddDeliveryMethods;
import net.premieredigital.portal.admin.services.UserAddSpecification;
import net.premieredigital.portal.admin.services.UserDeleteSpecification;
import net.premieredigital.portal.metadataview.OrderItemMetadataCrewAndCast;

import org.testng.annotations.Test;

public class MasteringWrapper extends BaseTest{
	@Test(groups="ExcludeSafari", description="4.2 Find A Title In Mastering Grid View")
    public void findTitleInMasteringGridView()  throws Exception {
		FindTitleInMasteringGridView findTitleInMasteringGridView = new FindTitleInMasteringGridView(this);
		findTitleInMasteringGridView.findTitleInMasteringGrid();
    }
	@Test(groups="ExcludeSafari", description="4.5 Save Modifications In Mastering Grid")
    public void SaveModificationsInMasteringGrid()  throws Exception {
		SaveModificationsInMasteringGrid saveModificationsInMasteringGrid = new SaveModificationsInMasteringGrid(this);
		saveModificationsInMasteringGrid.saveModificationsInMasteringGrid();
    }
	@Test(groups="ExcludeSafari", description="9.3 Order Item Metadata - Crew and Cast")
    public void orderItemMetadataCrewAndCast()  throws Exception {
    	OrderItemMetadataCrewAndCast orderItemMetadataCrewAndCast = new OrderItemMetadataCrewAndCast(this);
    	orderItemMetadataCrewAndCast.orderItemMetadataCrewAndCast();
    }
}
