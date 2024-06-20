package net.premieredigital.portal.orderitemsview;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.admin.billingparties.UserShallBeAbleToAddBillingParty;
import net.premieredigital.portal.admin.services.UserAddSpecification;
import net.premieredigital.portal.masteringview.AddAndEditQcNoteonMasteringVideoPage;
import net.premieredigital.portal.masteringview.UserViewAndEditTitleInTheMasteringGrid;
import net.premieredigital.portal.tasksview.SaveDefaultSearchInTasksView;

import org.testng.annotations.Test;

public class OrderItemWrapper extends BaseTest {

	@Test(groups = "ExcludeSafari",description="5.2 Sync Between Order Item View and Task View")
    public void syncBetweenOrderItemViewAndTaskView()  throws Exception {
		SyncBetweenOrderItemViewAndTaskView syncBetweenOrderItemViewAndTaskView = new SyncBetweenOrderItemViewAndTaskView(this);
		syncBetweenOrderItemViewAndTaskView.syncBetweenOrderItemViewAndTaskView();
    }
	@Test(groups = "ExcludeSafari",description="8.2 Save Default Search in Tasks View")
    public void saveDefaultSearchInTasksView()  throws Exception {
    	SaveDefaultSearchInTasksView saveDefaultSearchInTasksView = new SaveDefaultSearchInTasksView(this);
    	saveDefaultSearchInTasksView.saveDefaultTaskSearch();
    }
    @Test(groups = "ExcludeSafari",description="5.3 Updating Action Buttons in Order Item View")
    public void UpdatingActionButtonsInOrderItemView()  throws Exception {
        net.premieredigital.portal.orderitemsview.UpdatingActionButtonsInOrderItemView updatingActionButtonsInOrderItemView = new net.premieredigital.portal.orderitemsview.UpdatingActionButtonsInOrderItemView(this);
        updatingActionButtonsInOrderItemView.updatingActionButtonsInOrderItemView();
    }
    @Test(groups = "ExcludeSafari" , description="2.3.7 User shall be able to add specification")
    public void userAddSpecification() throws Exception {
    	UserAddSpecification userAddSpecification = new UserAddSpecification(this);
    	userAddSpecification.successfulAddSpecificationTest();
    }
}
