package net.premieredigital.portal.tasksview;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.admin.billingparties.UserShallBeAbleToAddBillingParty;
import net.premieredigital.portal.admin.others.SuccessfulLoginTest;
import net.premieredigital.portal.admin.services.UserAddService;
import net.premieredigital.portal.admin.services.UserAddSpecification;
import net.premieredigital.portal.masteringview.AddItemToMasteringGridView;
import net.premieredigital.portal.masteringview.SynchronizationBetweenMasteringViewAndTasksView;
import net.premieredigital.portal.masteringview.UserRemoveTaskAndAddTaskInMasteringGrid;
import net.premieredigital.portal.masteringview.UserViewAndEditTitleInTheMasteringGrid;

import org.testng.annotations.Test;

public class TaskWrapper extends BaseTest{
	@Test(groups = "ExcludeSafari",description="8.1 Updating Statuses in Tasks View")
    public void updateStatusInTasksView()  throws Exception {
    	UpdateStatusInTasksView updateStatusInTasksView = new UpdateStatusInTasksView(this);
    	updateStatusInTasksView.updateStatus();
    }
	@Test(description="1.1 Successful login to the system",priority=1)
    public void successfulLoginTest()  throws Exception {
		SuccessfulLoginTest successfulLoginTest = new SuccessfulLoginTest(this);
		successfulLoginTest.successfulLoginTest();
    }
    @Test(description="2.3.1 User shall be able to add a service")
    public void userAddService() throws Exception {
    	UserAddService userAddService = new UserAddService(this);
    	userAddService.userSuccessfulAddService();
    }
	@Test(groups = "ExcludeSafari",description="4.8 User Shall Be Able To Remove Task And Add Task In Mastering Grid")
    public void userRemoveTaskAndAddTaskInMasteringGrid()  throws Exception {
		UserRemoveTaskAndAddTaskInMasteringGrid userRemoveTaskAndAddTaskInMasteringGrid = new UserRemoveTaskAndAddTaskInMasteringGrid(this);
		userRemoveTaskAndAddTaskInMasteringGrid.addAndRemoveTaskInMasteringGridView();
    }
}
