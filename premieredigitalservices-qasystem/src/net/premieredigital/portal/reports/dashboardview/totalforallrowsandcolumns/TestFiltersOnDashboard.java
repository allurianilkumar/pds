package net.premieredigital.portal.reports.dashboardview.totalforallrowsandcolumns;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
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

public class TestFiltersOnDashboard {
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
	public TestFiltersOnDashboard(BaseTest baseTest) {
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
	//7.1.6
	//@Test(description="7.1.6 Test Filters on Dashboard")
	public void testFiltersOnDashboard() throws Exception{
		this.preCondition();
		baseTest.getWebDriver().get(dashboardPage.getPageUrl());
		Thread.sleep(2000);
		baseTest.fluentlyWait(new FluentWait<By>(By.linkText("Work In Progress")));
		baseTest.getWebDriver().findElement(By.linkText("Work In Progress")).click();
		Thread.sleep(2000);
		baseTest.fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
		WebElement elm1 = baseTest.getWebDriver().findElement(By.className("main-section-loader"));
		dashboardPage.waitForCssToChange(elm1);
		Thread.sleep(6000);
		int workinProgresssTotal = dashboardPage.getCountDashboardTableTotal(2);
		new Select(baseTest.getWebDriver().findElement(By.name("provider_id"))).selectByVisibleText(dashboardConfig.getProviderName());
		Thread.sleep(1000);
		baseTest.fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
        WebElement elm = baseTest.getWebDriver().findElement(By.className("main-section-loader"));
        dashboardPage.waitForCssToChange(elm);
        Thread.sleep(1000);
		int providerTotal = dashboardPage.getCountDashboardTableTotal(2);
		if(providerTotal <= workinProgresssTotal){
	        new Select(baseTest.getWebDriver().findElement(By.name("service_id"))).selectByVisibleText(dashboardConfig.getServiceName());
	        Thread.sleep(1000);
	        baseTest.fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
	        elm = baseTest.getWebDriver().findElement(By.className("main-section-loader"));
	        dashboardPage.waitForCssToChange(elm);
	        Thread.sleep(1000);
			int serviceTotal = dashboardPage.getCountDashboardTableTotal(2);
			if(serviceTotal <= providerTotal){
				baseTest.fluentlyWait(new FluentWait<By>(By.id("completed_from_filter")));
		        baseTest.getWebDriver().findElement(By.id("completed_from_filter")).click();
		        dashboardPage.setCustomeMonth("back",3);
				Thread.sleep(1000);
				baseTest.fluentlyWait(new FluentWait<By>(By.id("completed_through_filter")));
				baseTest.getWebDriver().findElement(By.id("completed_through_filter")).click();
				dashboardPage.setCustomeMonth("next",1);
				baseTest.fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
		        elm = baseTest.getWebDriver().findElement(By.className("main-section-loader"));
		        dashboardPage.waitForCssToChange(elm);
				int completedDateTotal = dashboardPage.getCountDashboardTableTotal(2);
				if(completedDateTotal <= serviceTotal){
					Assert.assertTrue(true);
				}else{
					Assert.assertTrue(false);
				}
			}else{
				Assert.assertTrue(false);
			}
		}else{
			Assert.assertTrue(false);
		}
	}

}
