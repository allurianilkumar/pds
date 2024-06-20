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
import org.openqa.selenium.By;

public class PackagedVsNonPackagedFilter {
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
	public PackagedVsNonPackagedFilter(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
    public void postCondition() {
//        orderPage.deleteOrder();
    }
    public void preCondition() throws Exception {
        masteringConfig = new MasteringConfiguration();
        orderItemConfig = new OrderItemConfiguration();
        serviceConfig = new ServiceAdminConfiguration();
        orderConfig = new OrderConfiguration();
        dashboardConfig = new DashboardConfiguration();
        orderPage = new OrderPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), orderConfig);
        //new LoginPage(baseTest.getWebDriver(), baseTest.getPageConfiguration()).login();
        this.servicePage = new ServiceAdminPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), this.serviceConfig);
        this.orderPage = new OrderPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(),orderConfig);
        this.orderItemPage = new OrderItemPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), this.orderItemConfig, this.orderConfig);
        this.masteringPage = new MasteringPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderItemConfig, orderConfig, this.masteringConfig);
        this.dashboardPage = new DashboardPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(),this.serviceConfig,this.dashboardConfig, this.orderItemPage, this.orderConfig);
    }
    //7.1.5
    //@Test(groups = "ExcludeSafari",description="7.1.5 Packaged vs Non-Packaged Filter")
    public void packagedVsNonPackagedFilter() throws Exception{
    	this.preCondition();
        dashboardPage.fillNonPackagedData();
        dashboardPage.clickWorkInProgress();
        boolean resultForZero = dashboardPage.testTableValue(0);
        //System.out.println("this result is"+resultForZero);
        Assert.assertTrue(resultForZero);
        dashboardPage.fillPackagedData();
        dashboardPage.clickWorkInProgress();
        boolean resultForOne = dashboardPage.testTableValue(0);
        //System.out.println("this result is"+resultForOne);
        Assert.assertFalse(resultForOne);
    }
}
