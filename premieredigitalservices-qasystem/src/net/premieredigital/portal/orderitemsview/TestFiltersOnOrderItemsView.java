package net.premieredigital.portal.orderitemsview;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderItemConfiguration;
import net.premieredigital.portal.pom.OrderItemPage;
import net.premieredigital.portal.pom.OrderPage;

public class TestFiltersOnOrderItemsView {
	private OrderItemConfiguration orderItemConfig;
	private OrderItemPage orderItemPage;
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private BaseTest baseTest;
	public TestFiltersOnOrderItemsView(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void postCondition() {
//		orderPage.deleteOrder();
	}
	public void preCondition() throws Exception {
		orderItemConfig = new OrderItemConfiguration();
		this.orderConfig = new OrderConfiguration();
		orderPage = new OrderPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderConfig);
		//new LoginPage(this.baseTest.getWebDriver(), this.pageConfig).login();
		this.orderItemPage = new OrderItemPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), new OrderItemConfiguration(), this.orderConfig);
	}
	//Test 5.11
	//@Test(groups = "ExcludeSafari",description="5.11 Test Filters on Order Items View") 
	public void testFiltersOnOrderItemsView() throws Exception{
		this.preCondition();
		baseTest.getWebDriver().get(baseTest.getPageConfiguration().getRootURL() + "/home");
		int totalOrderItems = orderItemPage.getTotlaOrderItemResutl();
		baseTest.getWebDriver().findElement(By.className("titles"));
		new Select(baseTest.getWebDriver().findElement(By.name("provider_id"))).selectByVisibleText(orderConfig.getProviderName());
		orderItemPage.waitUntilTableisLoaded();
		int providerOrderItemTotalResult = orderItemPage.getTotlaOrderItemResutl();
		if(providerOrderItemTotalResult <= totalOrderItems){
			new Select(baseTest.getWebDriver().findElement(By.name("service_id"))).selectByVisibleText(orderConfig.getService());
			int serviceWithPOrderItemTotalResult = orderItemPage.getTotlaOrderItemResutl();
			if(serviceWithPOrderItemTotalResult <= providerOrderItemTotalResult){
				baseTest.getWebDriver().findElement(By.id("completed_from_filter")).click();
				orderItemPage.waitUntilTableisLoaded();
				orderPage.setCustomeMonth("back",3);
				Thread.sleep(1000);
				baseTest.getWebDriver().findElement(By.id("completed_through_filter")).click();
				orderItemPage.waitUntilTableisLoaded();
				orderPage.setCustomeMonth("next",1);
				int completedDateTotal = orderItemPage.getTotlaOrderItemResutl();
					if(completedDateTotal <= serviceWithPOrderItemTotalResult){
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
