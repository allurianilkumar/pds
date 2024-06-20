package net.premieredigital.portal.ordersview;

import java.util.List;
import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderItemConfiguration;
import net.premieredigital.portal.pom.OrderItemPage;
import net.premieredigital.portal.pom.OrderPage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.base.Predicate;

public class SyncBetweenTasksRequirementOrderItem {
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private OrderItemPage orderItemPage;
	private BaseTest baseTest;
	public SyncBetweenTasksRequirementOrderItem(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void postCondition() {
	//
	}

	public void preCondition() throws Exception {
		this.orderConfig = new OrderConfiguration();
		//new LoginPage(baseTest.getWebDriver(), this.pageConfig).login();
		this.orderPage = new OrderPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), this.orderConfig);
		this.orderItemPage = new OrderItemPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), new OrderItemConfiguration(), this.orderConfig);
	}
	//@Test(groups = "ExcludeSafari", description="6.3 Updates To Statuses Synch Between Tasks View, Requirements Page, and Order Items View")
	public void syncBetweenTasksRequirementOrderItem()  throws Exception {
		this.preCondition();
		orderPage.placeOrder();
		orderPage.addSameRequirementToAll();
//		orderPage.addRequirement();
		orderPage.syncOrderItemViewOnFeature();
		orderItemPage.checkOrderItemViewGemStatus();
		FluentWait<By> fluentWait = new FluentWait<By>(By.name("status"));
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
//		new WebDriverWait(baseTest.getWebDriver(),30).until(ExpectedConditions.presenceOfElementLocated(By.name("status")));
		String selected_option = new Select(baseTest.getWebDriver().findElement(By.name("status"))).getFirstSelectedOption().getText();
		//System.out.println("selected_option:" + selected_option);
		Assert.assertTrue(selected_option.trim().equals("Ingest - Ready to Begin"));
		orderPage.syncTasksView();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement tbody = baseTest.getWebDriver().findElement(By.tagName("tbody"));
		List<WebElement> tds = tbody.findElements(By.tagName("td"));
		WebElement status = tds.get(7);
		//System.out.println(status.findElement(By.className("status")).getText());
		Assert.assertTrue(status.findElement(By.className("status")).getText().equals("Ingest - Ready to Begin"));
		orderPage.syncAllTasksInRequirementPage();
		orderPage.modifyStatusInTasksView();
		orderItemPage.checkOrderItemViewGemStatus();

		selected_option = new Select(baseTest.getWebDriver().findElement(By.name("status"))).getFirstSelectedOption().getText();
		if (selected_option != null) {
			Assert.assertTrue(selected_option.trim().equals("Ingest - In Progress"));
		} else {
			Assert.assertTrue(false);
		}

		String status_string = orderPage.checkRequirementPageStatus();
		if (status_string != null) {
			Assert.assertTrue(status_string.trim().equals("Ingest - In Progress"));
		} else {
			Assert.assertTrue(false);
		}
	}
}
