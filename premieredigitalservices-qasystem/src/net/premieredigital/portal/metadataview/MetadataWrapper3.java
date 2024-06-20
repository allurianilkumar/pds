package net.premieredigital.portal.metadataview;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.admin.services.UserAddDeliveryMethods;
import net.premieredigital.portal.admin.services.UserDeleteService;
import net.premieredigital.portal.masteringview.AddItemToMasteringGridView;
import net.premieredigital.portal.masteringview.ClearAllEntriesForTitleInMastering;
import net.premieredigital.portal.masteringview.DeleteTitleFromMasteringGrid;
import net.premieredigital.portal.masteringview.SynchronizationBetweenMasteringViewAndTasksView;
import net.premieredigital.portal.masteringview.UserViewAndEditTitleInTheMasteringGrid;

import org.testng.annotations.Test;

public class MetadataWrapper3 extends BaseTest{

	@Test(groups="ExcludeSafari", description="4.7 Synchronization Between Mastering View And Tasks View")
    public void synchronizationBetweenMasteringViewAndTasksView()  throws Exception {
		SynchronizationBetweenMasteringViewAndTasksView synchronizationBetweenMasteringViewAndTasksView = new SynchronizationBetweenMasteringViewAndTasksView(this);
		synchronizationBetweenMasteringViewAndTasksView.synchTaskViewAndMasteringGridView();
    }
	@Test(groups="ExcludeSafari", description="4.1 User Shall Be Able To Add Mastering Grid View")
    public void addItemToMasteringGridView()  throws Exception {
		AddItemToMasteringGridView addItemToMasteringGridView = new AddItemToMasteringGridView(this);
		addItemToMasteringGridView.addItemToMasteringGridView();
    }
	@Test(groups = "ExcludeSafari",description="4.3 Delete A Title From Mastering Grid View")
    public void deleteTitleFromMasteringGrid()  throws Exception {
		DeleteTitleFromMasteringGrid deleteTitleFromMasteringGrid = new DeleteTitleFromMasteringGrid(this);
		deleteTitleFromMasteringGrid.deleteTitleFromMasteringGrid();
    }
}
