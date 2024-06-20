package net.premieredigital.portal.ordersview;

import java.util.List;
import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderPage;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PlaceAnOrder {
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private BaseTest baseTest;
	public PlaceAnOrder(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void preCondition() throws Exception {
		baseTest.setImplicitWait(5);
		this.orderConfig = new OrderConfiguration();
		this.orderPage = new OrderPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderConfig);
	}
	//6.1
	//@Test(groups = "ExcludeSafari",description="6.1 User shall be able to place an order")
	public void placeAnOrder() throws Exception {
		this.preCondition();
		try {
			orderPage.placeOrder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Assert.assertTrue(baseTest.getWebDriver().getPageSource().contains(orderConfig.getTitle1()));
		Assert.assertTrue(baseTest.getWebDriver().getPageSource().contains(orderConfig.getTitle3()));
		//Assert.assertTrue(baseTest.getWebDriver().getPageSource().contains(orderConfig.getTitle3()));
		//orderPage.addRequirement();
	}
}
