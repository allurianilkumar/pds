package net.premieredigital.portal.metadataview;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.admin.services.UserAddDeliveryMethods;
import net.premieredigital.portal.masteringview.ClearAllEntriesForTitleInMastering;
import net.premieredigital.portal.masteringview.UserRemoveTaskAndAddTaskInMasteringGrid;
import net.premieredigital.portal.masteringview.UserViewAndEditTitleInTheMasteringGrid;
import net.premieredigital.portal.orderitemsview.SaveDefaultSearchInOrderItemView;
import net.premieredigital.portal.ordersview.PlaceAnOrder;
import net.premieredigital.portal.ordersview.SyncBetweenTasksRequirementOrderItem;
import net.premieredigital.portal.ordersview.othertest.GlobalSearch;

import org.testng.annotations.Test;

public class MetadataWrapper2 extends BaseTest{
	@Test(groups = "ExcludeSafari",description="4.4 Clear All Fields For A Title In Mastering Grid")
    public void clearAllEntriesForTitleInMastering()  throws Exception {
		ClearAllEntriesForTitleInMastering clearAllEntriesForTitleInMastering = new ClearAllEntriesForTitleInMastering(this);
		clearAllEntriesForTitleInMastering.clearTitleInMasteringGrid();
    }
    @Test(groups = "ExcludeSafari", description="6.3 Updates To Statuses Synch Between Tasks View, Requirements Page, and Order Items View")
    public void syncBetweenTasksRequirementOrderItem()  throws Exception {
    	SyncBetweenTasksRequirementOrderItem syncBetweenTasksRequirementOrderItem = new SyncBetweenTasksRequirementOrderItem(this);
    	syncBetweenTasksRequirementOrderItem.syncBetweenTasksRequirementOrderItem();
    }
}
