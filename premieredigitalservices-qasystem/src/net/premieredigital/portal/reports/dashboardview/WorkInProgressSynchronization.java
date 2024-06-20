package net.premieredigital.portal.reports.dashboardview;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.DashboardConfiguration;
import net.premieredigital.portal.pom.DashboardPage;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.MasteringConfiguration;
import net.premieredigital.portal.pom.MasteringPage;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderItemConfiguration;
import net.premieredigital.portal.pom.OrderItemPage;
import net.premieredigital.portal.pom.OrderPage;
import net.premieredigital.portal.pom.ServiceAdminConfiguration;
import net.premieredigital.portal.pom.ServiceAdminPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

public class WorkInProgressSynchronization {
	private OrderItemConfiguration orderItemConfig;
	private OrderItemPage orderItemPage;
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private MasteringPage masteringPage;
	private MasteringConfiguration masteringConfig;
	private ServiceAdminPage servicePage;
	private ServiceAdminConfiguration serviceConfig;
	private DashboardPage dashboardPage;
	private DashboardConfiguration dashboardConfig;
	private BaseTest baseTest;
	public WorkInProgressSynchronization(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void postCondition() {
//		orderPage.deleteOrder();
	}
	
	public void preCondition() throws Exception {
		masteringConfig = new MasteringConfiguration();
		orderItemConfig = new OrderItemConfiguration();
		serviceConfig = new ServiceAdminConfiguration();
		orderConfig = new OrderConfiguration();
		dashboardConfig = new DashboardConfiguration();
		orderPage = new OrderPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), this.orderConfig);
		//new LoginPage(baseTest.getWebDriver(), baseTest.getPageConfiguration()).login();
		this.servicePage = new ServiceAdminPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), this.serviceConfig);
		this.orderPage = new OrderPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(),this.orderConfig);
		this.orderItemPage = new OrderItemPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), this.orderItemConfig, this.orderConfig);
		this.masteringPage = new MasteringPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderItemConfig, this.orderConfig, this.masteringConfig);
		this.dashboardPage = new DashboardPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(),this.serviceConfig,this.dashboardConfig, this.orderItemPage, this.orderConfig);
	}
	//7.1.2
	//@Test(groups = "ExcludeSafari",description="7.1.2 Synchronization of dashboard items: Client Services,Mastering and Fulfillment")
	public void workInProgressSynchronization() throws Exception{
		this.preCondition();
		dashboardPage.fillDashboard();
		Thread.sleep(1000);
		String masteringTotal = dashboardPage.clickDesiredLinkAndReadTotal("Mastering");
		String clientServicesTotal = dashboardPage.clickDesiredLinkAndReadTotal("Client Services");
		String fulfillmentTotal = dashboardPage.clickDesiredLinkAndReadTotal("Fulfillment");
		//System.out.println("masteringTotal: "+masteringTotal);
		//System.out.println("clientServicesTotal: "+clientServicesTotal);
		//System.out.println("fulfillmentTotal: "+fulfillmentTotal);
		orderPage.placeOrder();
		//orderPage.addRequirement();
		orderPage.addSameRequirementToAll();
		// 7.1.2.3 Fulfillment Is Updated When Action Buttons Are Modified In Order Item View
		orderItemPage.fillOrderItemPage();
		orderItemPage.updateActionButtons();
		Assert.assertEquals(baseTest.getWebDriver().findElement(By.className("overall-status")).getText().trim(), "Fulfillment - Delivery - Default Status");
		dashboardPage.fillDashboard();
		String fulfillmentCheckTotal = dashboardPage.clickDesiredLinkAndReadTotal("Fulfillment");
		//System.out.println("fulfillmentCheckTotal: "+fulfillmentCheckTotal);
		Assert.assertTrue(Integer.parseInt(fulfillmentCheckTotal.replaceAll(",", "")) > Integer.parseInt(fulfillmentTotal.replaceAll(",", "")));
		// 7.1.2.2 Mastering is Updated When Status Of Item Is Changes In Mastering Grid
		masteringPage.addExistingTitle(orderConfig.getTitle3());
		masteringPage.modifyStatus(dashboardConfig.getModifiedStatus());
		orderItemPage.changeStatusForFeature(dashboardConfig.getModifiedStatus());
		dashboardPage.fillDashboard();
		Thread.sleep(2000);
		String masteringCheckTotal = dashboardPage.clickDesiredLinkAndReadTotal("Mastering");
		//System.out.println("masteringCheckTotal: "+masteringCheckTotal);
		Assert.assertTrue(Integer.parseInt(masteringCheckTotal.replaceAll(",", "")) > Integer.parseInt(masteringTotal.replaceAll(",", "")));
		// 7.1.2.1 Client Services is Updated When Requirements are Added To Newly Added Items
		dashboardPage.fillDashboard();
		Thread.sleep(2000);
		String clientServicesCheckTotal = dashboardPage.clickDesiredLinkAndReadTotal("Client Services");
		//System.out.println("clientServicesCheckTotal: "+clientServicesCheckTotal);
		Assert.assertTrue(Integer.parseInt(clientServicesCheckTotal.replaceAll(",", "")) > Integer.parseInt(clientServicesTotal.replaceAll(",", "")));
	}
}
