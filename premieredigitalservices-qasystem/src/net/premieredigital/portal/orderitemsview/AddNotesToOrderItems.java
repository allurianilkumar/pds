package net.premieredigital.portal.orderitemsview;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderItemConfiguration;
import net.premieredigital.portal.pom.OrderItemPage;
import net.premieredigital.portal.pom.OrderPage;

public class AddNotesToOrderItems {
	private OrderItemConfiguration orderItemConfig;
	private OrderItemPage orderItemPage;
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private boolean nextTr;
	private BaseTest baseTest;

	public AddNotesToOrderItems(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	
	public void preCondition() throws Exception {
		nextTr = false;
		orderItemConfig = new OrderItemConfiguration();
		this.orderConfig = new OrderConfiguration();
		orderPage = new OrderPage(this.baseTest.getWebDriver(), 
				this.baseTest.getPageConfiguration(), orderConfig);
		//new LoginPage(this.webDriver.get(), this.pageConfig).login();
		this.orderItemPage = new OrderItemPage(this.baseTest.getWebDriver(), 
				this.baseTest.getPageConfiguration(), 
				new OrderItemConfiguration(), orderConfig);
	}
	//@Test(groups = "ExcludeSafari",description="5.2 Sync Between Order Item View and Task View")
	public void addNotesToOrderItems() throws Exception{
		this.baseTest.setImplicitWait(5);
		this.preCondition();
		// Place Order
		orderPage.placeOrder();
		orderPage.addSameRequirementToAll();
		
		// Add Notes
		orderItemPage.addNotes();
	}
}
