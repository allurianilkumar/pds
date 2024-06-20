package net.premieredigital.portal.ordersview;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderPage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.base.Predicate;

public class SearchAndViewAnOrder {
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private BaseTest baseTest;

	public SearchAndViewAnOrder(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	
	
	public void postCondition() {
//		orderPage.deleteOrder();
	}

	public void preCondition() throws Exception {
		this.orderConfig = new OrderConfiguration();
		this.orderPage = new OrderPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), this.orderConfig);
	}
	//6.2
	//@Test(groups = "ExcludeSafari", description="6.2 User shall be able to search and view an order")
	public void searchAndViewAnOrder() throws Exception{
		this.preCondition();
		orderPage.placeOrder();
		orderPage.searchOrder();
		Assert.assertTrue(baseTest.getWebDriver().getPageSource().contains(orderConfig.getOrderNumber()));
		orderPage.viewOrder();
		ArrayList<String> newTab = new ArrayList<String>(baseTest.getWebDriver().getWindowHandles());
		baseTest.getWebDriver().close();
		baseTest.getWebDriver().switchTo().window(newTab.get(1));
		FluentWait<By> fluentWait = new FluentWait<By>(By.className("sortable"));
        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(2000, TimeUnit.MILLISECONDS);
        fluentWait.until(new Predicate<By>() {
            public boolean apply(By by) {
                try {
                  return baseTest.getWebDriver().findElements(by).size() > 0;
                } catch (NoSuchElementException ex) {
                  return false;
                }
            }
        });
//		new WebDriverWait(baseTest.getWebDriver(),10).until(ExpectedConditions.presenceOfElementLocated(By.className("sortable")));
		Assert.assertTrue(baseTest.getWebDriver().getPageSource().contains(orderConfig.getTitle1()));
		Assert.assertTrue(baseTest.getWebDriver().getPageSource().contains(orderConfig.getTitle2()));
		Assert.assertTrue(baseTest.getWebDriver().getPageSource().contains(orderConfig.getTitle3()));
	}
}
