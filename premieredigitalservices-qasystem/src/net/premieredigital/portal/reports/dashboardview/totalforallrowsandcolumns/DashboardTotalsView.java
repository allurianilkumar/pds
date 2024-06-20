package net.premieredigital.portal.reports.dashboardview.totalforallrowsandcolumns;

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
import org.openqa.selenium.WebElement;

import java.util.List;

public class DashboardTotalsView {
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
	private int[] dashboardMasteringTotal=new int[5];
	private BaseTest baseTest;
	public DashboardTotalsView(BaseTest baseTest) {
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
		this.masteringPage = new MasteringPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderItemConfig, this.orderConfig, this.masteringConfig);
		this.dashboardPage = new DashboardPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(),this.serviceConfig,this.dashboardConfig, this.orderItemPage, this.orderConfig);
	}
	//7.1.3
	//@Test(description="7.1.3 Checking Dashboard Totals for all statuses")
	public void dashboardTotalsView() throws Exception{
		this.preCondition();
		dashboardPage.renderDashboardView();
		dashboardPage.clickWorkInProgress();
		/*     Total In Work In Progress    */
		//7.1.3.1 Total In Work In Progress
		dashboardClientServices(false);
		 /*    Client Services    */
		dashboardPage.clickSubOption("Client Services");
		//7.1.3.2 Total In Client Services, Provider View
		dashboardClientServices(false);
		//7.1.3.3 Total In Client Services, Service View
		dashboardClientServices(true);
		 /*    Needs Source     */
		//dashboardPage.clickSubOption("Needs Source");
		//7.1.3.4 Total In Client Services, Needs Source, Provider View
		dashboardClientServices(false);
		//7.1.3.5 Total In Client Services, Needs Source, Service View
		dashboardClientServices(true);
		/*      Needs Review    */
		dashboardPage.clickSubOption("Needs Review");
		//7.1.3.6 Total In Client Services, Needs Review, Provider View
//		dashboardClientServices(false);
		//7.1.3.7 Total In Client Services, Needs Review, Service View
//		dashboardClientServices(true);
		 /*     On Hold         */
//		dashboardPage.clickSubOption("On Hold");
		//7.1.3.8 Total In Client Services, On Hold, Provider View
//		dashboardClientServices(false);
		//7.1.3.9 Total In Client Services, On Hold, Service View
//		dashboardClientServices(true);
		/*      Not Started     */
		dashboardPage.clickSubOption("Not Started");
		//7.1.3.10 Total In Client Services, Not Yet Started, Provider View
		dashboardClientServices(false);
		//7.1.3.11 Total In Client Services, Not Yet Started, Service View
		dashboardClientServices(true);
		  /* Total In Mastering */
		//7.1.3.12 Total In Mastering
		dashboardMasteringSubDepartment("Mastering");
//		dashboardMasteringSubDepartment("Ingest");
		dashboardMasteringSubDepartment("Conformance");
		dashboardMasteringSubDepartment("QC");
		dashboardMasteringSubDepartment("Repairs");
		dashboardMasteringSubDepartment("Finalization");
		/*  "Total In Fulfillment" */
		dashboardPage.clickSubOption("Fulfillment");
		//7.1.3.13 Total In Fulfillment
		dashboardClientServices(false);
	}
	public void dashboardClientServices(boolean clickable)throws Exception{
		if(clickable){
			dashboardPage.clickSwitch();
			Thread.sleep(1000);
		}
		List<WebElement> tableRow = dashboardPage.getTableRow();
		List<WebElement> tableColumn = dashboardPage.getTableColumn();
		int rows = tableRow.size(),columns = tableColumn.size();
		if(rows >= 2){
			String[][] table = dashboardPage.getTableArrayList(tableRow,tableColumn);
			for(int i=0;i<rows;i++){
				int sum =0,titleValue=0;
				for(int j=2;j<columns;j++){
					if(j!=2){	
						sum+= Integer.parseInt(table[i][j]);	
					}
					else
					 titleValue=Integer.parseInt(table[i][j]);
				}
				Assert.assertEquals(titleValue,sum);
			}
			for(int k=1;k<columns;k++){
				int sumValue =0,titleValueColumn=0;
				for(int l=0;l<rows;l++){
					if(l!= rows-1){	
					    sumValue += Integer.parseInt(table[l][k]);
					}
					else
					 titleValueColumn=Integer.parseInt(table[l][k]);
				}
				Assert.assertEquals(sumValue,titleValueColumn);
			}
		}
		Thread.sleep(1000);
	}
	public void dashboardMasteringSubDepartment(String link)throws Exception{
		dashboardPage.clickSubOption(link);
		List<WebElement> tableRow = dashboardPage.getTableRow();
		List<WebElement> tableColumn = dashboardPage.getTableColumn();
		int rows = tableRow.size(),columns = tableColumn.size();
		if(rows >= 2){
			String[][] table = dashboardPage.getTableArrayList(tableRow,tableColumn);
			for(int i=0;i<rows;i++){
				int sum =0,titleValue=0;
				for(int j=2;j<columns;j++){
					if(j!=2)
					 sum+= Integer.parseInt(table[i][j]);
					else
					 titleValue=Integer.parseInt(table[i][j]);
				}
				Assert.assertEquals(titleValue,sum);
				if(link.equals("Mastering") && i<(rows-1)){
					dashboardMasteringTotal[i]=titleValue;
				}
				if((!link.equals("Mastering")) && (i==(rows-1))){
					checkMasteringSubValues(link,titleValue);
				}
			}
			for(int k=1;k<columns;k++){
				int sumValue =0,titleValueColumn=0;
				for(int l=0;l<rows;l++){
					if(l!= rows-1)
					 sumValue += Integer.parseInt(table[l][k]);
					else
					 titleValueColumn=Integer.parseInt(table[l][k]);
				}
				Assert.assertEquals(sumValue,titleValueColumn);
			}
		}
		Thread.sleep(1000);
	}
	public void checkMasteringSubValues(String subDepartmentName,int titleValue){
	      switch (subDepartmentName) {
	          case "Ingest":
	        	  Assert.assertEquals(dashboardMasteringTotal[0],titleValue);
	              break;
	          case "Conformance":
	        	  Assert.assertEquals(dashboardMasteringTotal[1],titleValue);;
	              break;
	          case "QC":
	        	  Assert.assertEquals(dashboardMasteringTotal[2],titleValue);;
	              break;
	          case "Repairs":
	        	  Assert.assertEquals(dashboardMasteringTotal[3],titleValue);;
	              break;
	          case "Finalization":	        	  
	        	  Assert.assertEquals(dashboardMasteringTotal[4],titleValue);;
	              break;
	          default: System.out.println("not matching Sub Department");
	      }
	}
}
