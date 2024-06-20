package net.premieredigital.portal.orderitemsview;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.admin.billingparties.UserShallBeAbleToAddBillingParty;
import net.premieredigital.portal.masteringview.AddAndEditQcNoteonMasteringVideoPage;
import net.premieredigital.portal.tasksview.AddTaskNotes;

import org.testng.annotations.Test;

public class OrderItemsTaskNoteWrapper extends BaseTest {
	@Test(groups = "ExcludeSafari",description="5.13 Add Notes in Order Items")
	public void addNotesInOrderItems()  throws Exception {
		AddNotesInOrderItems addNotesInOrderItems=new AddNotesInOrderItems(this);
		addNotesInOrderItems.addNotesInOrderItems();
	}
	@Test(groups = "ExcludeSafari",description="5.14 Add Notes in Order Item Tasks")
	public void addNotesInOrderItemTasks()  throws Exception {
		AddNotesForOrderItemTasks addNotesForOrderItemTasks = new AddNotesForOrderItemTasks(this);
		addNotesForOrderItemTasks.addNotesForOrderItemTasks();
	}
}
