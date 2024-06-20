package net.premieredigital.portal.ordersview;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.admin.billingparties.UserShallBeAbleToAddBillingParty;
import net.premieredigital.portal.admin.billingparties.UserShallBeAbleToEditBillingParty;
import net.premieredigital.portal.admin.services.UserAddDeliveryMethods;
import net.premieredigital.portal.admin.services.UserAddSpecification;
import net.premieredigital.portal.admin.services.UserDeleteSpecification;
import net.premieredigital.portal.admin.services.UserEditService;
import net.premieredigital.portal.masteringview.AddAndEditQcNoteonMasteringVideoPage;
import net.premieredigital.portal.orderitemsview.TestFiltersOnOrderItemsView;
import net.premieredigital.portal.orderitemsview.UpdateStatusInOrderItemView;
import net.premieredigital.portal.ordersview.othertest.GlobalSearch;
import net.premieredigital.portal.tasksview.AddTaskNotes;

import org.testng.annotations.Test;

public class OrdersWrapper extends BaseTest{
    //@Test(groups = "ExcludeSafari",description="6.2 User shall be able to search and view an order")
    public void searchAndViewAnOrder()  throws Exception {
        SearchAndViewAnOrder searchAndViewAnOrder = new SearchAndViewAnOrder(this);
        searchAndViewAnOrder.searchAndViewAnOrder();
    }
    //@Test(groups = "ExcludeSafari",description="11.1 Global Search")
    public void globalSearch()  throws Exception {
    	GlobalSearch globalSearch = new GlobalSearch(this);
    	globalSearch.globalSearch();
    }
    @Test(groups = "ExcludeSafari",description="2.3.8 User shall be able to delete a specification")
    public void UserDeleteSpecification() throws Exception {
    	UserDeleteSpecification userDeleteSpecification = new UserDeleteSpecification(this);
    	userDeleteSpecification.userDeleteSpecification();
    }
    @Test(description="2.3.3 User shall be able to edit a service")
    public void userEditService() throws Exception {
    	UserEditService userEditService =new UserEditService(this);
    	userEditService.successfulEditService();
    }
    @Test(description="2.3.5 User shall be able to add delivery methods per provider")
    public void userAddDeliveryMethods() throws Exception {
    	UserAddDeliveryMethods userAddDeliveryMethods=  new UserAddDeliveryMethods(this);
    	userAddDeliveryMethods.successfulAddDeliveryMethods();
    }
	@Test(groups = "ExcludeSafari",description="8.3 Add Task Notes")
	public void addTaskNotes() throws Exception{
		AddTaskNotes addTaskNotes = new AddTaskNotes(this);
		addTaskNotes.addTaskNotes();
	}
}
