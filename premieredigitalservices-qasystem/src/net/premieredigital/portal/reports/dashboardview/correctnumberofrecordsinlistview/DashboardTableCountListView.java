package net.premieredigital.portal.reports.dashboardview.correctnumberofrecordsinlistview;

import org.testng.Assert;
import org.testng.annotations.Test;

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

public class DashboardTableCountListView {

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
	private int numberOfRecords;
	private BaseTest baseTest;
	public DashboardTableCountListView(BaseTest baseTest) {
		this.baseTest = baseTest;
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
		this.masteringPage = new MasteringPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderItemConfig, this.orderConfig, this.masteringConfig);
		this.dashboardPage = new DashboardPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(),this.serviceConfig,this.dashboardConfig, this.orderItemPage, this.orderConfig);
	}
	// 7.1.1
	//@Test(description="7.1.1 Dashboard View for Finished Orders")
	public void dashboardTableCountListView() throws Exception {
		this.preCondition();
		dashboardPage.renderDashboardView();
		// 7.1.4.1 Assert Number Of Records Displayed In Client Services, Needs Source
//		assertTableRecordsForClientServicesInNeedsSource("Needs Source","Lionsgate","iTunes","Total");
		// 7.1.4.2 Assert Number Of Records Displayed In Client Services, Needs Review
		assertTableRecordsForClientServices("Needs Review","NBCUniversal","Total");
		// 7.1.4.3 Assert Number Of Records Displayed In Client Services, On Hold
		assertTableRecordsForClientServices("On Hold","Eros","Total");
		// 7.1.4.4 Assert Number Of Records Displayed In Client Services, Not Yet Started
		assertTableRecordsForClientServices("Not Started","iTunes","Past Due");
		// 7.1.4.5 Assert Number Of Records Displayed In Mastering, Repairs, Ready To Begin
		assertTableRecordsForMasteringAndFulfillment("Mastering","Repairs","Ready To begin","Titles");
		// 7.1.4.6 Assert Number Of Records Displayed In Fulfillment, Delivery
		assertTableRecordsForMasteringAndFulfillment("Fulfillment","Delivery","Ready To Begin","Titles");
	}
	public void assertTableRecordsForClientServicesInNeedsSource(String link,String tableRowLink,String tableRowSubLink,String tableColumnSubLink) throws InterruptedException{
		dashboardPage.clickInnerLink(link);
		dashboardPage.findProviderOrServiceTableLinkClick(tableRowLink);
		dashboardPage.clickSwitch();
		int rows = dashboardPage.calculateNumberOfRows();
		int columns = dashboardPage.claculateNumberOfColumns();
		int row = dashboardPage.getRowNumber(tableRowSubLink,rows);
		int column = dashboardPage.getColumnNumber(tableColumnSubLink,columns);
		int value = dashboardPage.readValueFromDesiredRowAndColumn(row, column);
		if(value!=0)
			numberOfRecords = dashboardPage.getNumberOfRecords(row,column);
		else
			numberOfRecords =0;
		Assert.assertEquals(value, numberOfRecords);
	}
	public void assertTableRecordsForClientServices(String link,String tableRowLink,String tableColumnSubLink) throws InterruptedException{
		dashboardPage.clickInnerLink(link);
		if(link == "Not Started")
		dashboardPage.clickSwitch();
		int rows = dashboardPage.calculateNumberOfRows();
		int columns = dashboardPage.claculateNumberOfColumns();
		int row = dashboardPage.getRowNumber(tableRowLink,rows);
		int column = dashboardPage.getColumnNumber(tableColumnSubLink,columns);
		int value = dashboardPage.readValueFromDesiredRowAndColumn(row, column);
		if(value!=0)
			numberOfRecords = dashboardPage.getNumberOfRecords(row,column);
		else
			numberOfRecords=0;
		Assert.assertEquals(value, numberOfRecords);
	}
	public void assertTableRecordsForMasteringAndFulfillment(String link,String tableRowLink,String tableRowSubLink,String tableColumnSubLink) throws InterruptedException{
		dashboardPage.clickDesiredLink(link);
		dashboardPage.findProviderOrServiceTableLinkClick(tableRowLink);
		int rows = dashboardPage.calculateNumberOfRows();
		int columns = dashboardPage.claculateNumberOfColumns();
		int row = dashboardPage.getRowNumber(tableRowSubLink,rows);
		int column = dashboardPage.getColumnNumber(tableColumnSubLink,columns);
		int value = dashboardPage.readValueFromDesiredRowAndColumn(row, column);
		if(value!=0)
			numberOfRecords = dashboardPage.getNumberOfRecords(row,column);
		else
			numberOfRecords=0;
		Assert.assertEquals(value, numberOfRecords);
	}
}
