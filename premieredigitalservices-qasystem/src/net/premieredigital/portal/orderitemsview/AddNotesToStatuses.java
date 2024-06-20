package net.premieredigital.portal.orderitemsview;

import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderItemConfiguration;
import net.premieredigital.portal.pom.OrderItemPage;
import net.premieredigital.portal.pom.OrderPage;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNotesToStatuses{
	private OrderItemConfiguration orderItemConfig;
	private OrderItemPage orderItemPage;
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private boolean nextTr;
	private BaseTest baseTest;

	public AddNotesToStatuses(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	
	public void preCondition() throws Exception {
		nextTr = false;
		orderItemConfig = new OrderItemConfiguration();
		this.orderConfig = new OrderConfiguration();
		orderPage = new OrderPage(this.baseTest.getWebDriver(),this.baseTest.getPageConfiguration(), this.orderConfig);
		//new LoginPage(this.webDriver.get(), this.pageConfig).login();
		this.orderItemPage = new OrderItemPage(this.baseTest.getWebDriver(),this.baseTest.getPageConfiguration(),new OrderItemConfiguration(),this.orderConfig);
	}
	//@Test(groups = "ExcludeSafari",description="5.2 Sync Between Order Item View and Task View")
	public void addNotesToStatuses() throws Exception{
		this.baseTest.setImplicitWait(5);
		this.preCondition();
		// Place Order
		orderPage.placeOrder();
		orderPage.addSameRequirementToAll();
		
		// Add Notes
		orderItemPage.addNotesForFeature();
	}
}



