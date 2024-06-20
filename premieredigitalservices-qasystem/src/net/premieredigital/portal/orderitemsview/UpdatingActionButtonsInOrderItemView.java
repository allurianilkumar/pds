package net.premieredigital.portal.orderitemsview;

import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderItemConfiguration;
import net.premieredigital.portal.pom.OrderItemPage;
import net.premieredigital.portal.pom.OrderPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdatingActionButtonsInOrderItemView {
	private OrderItemConfiguration orderItemConfig;
	private OrderItemPage orderItemPage;
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private BaseTest baseTest;
	public UpdatingActionButtonsInOrderItemView(BaseTest baseTest) {
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
	//Test 5.3
	//@Test(groups = "ExcludeSafari",description="5.3 Updating Action Buttons in Order Item View")
	public void updatingActionButtonsInOrderItemView() throws Exception{
		this.preCondition();
		orderPage.placeOrder();
		orderPage.addSameRequirementToAll();
//		orderPage.addRequirement();
		orderItemPage.fillOrderItemPage();
		Thread.sleep(5000);
		orderItemPage.clickFinish();
		Thread.sleep(10000);
		//System.out.println(baseTest.getWebDriver().findElement(By.className("overall-status")).getText());
		Assert.assertEquals(baseTest.getWebDriver().findElement(By.className("overall-status")).getText().trim(), "Fulfillment - Complete");
		baseTest.getWebDriver().findElement(By.linkText("Feat")).click();
		Thread.sleep(10000);
		checkStatus("Finished");
		baseTest.getWebDriver().findElement(By.linkText("Trlr")).click();
		Thread.sleep(10000);
		checkStatus("Finished");
		baseTest.getWebDriver().findElement(By.linkText("Art")).click();
		Thread.sleep(10000);
		checkStatus("Finished");
		baseTest.getWebDriver().findElement(By.linkText("Met")).click();
		Thread.sleep(10000);
		checkStatus("Finished");
		Thread.sleep(10000);
		orderItemPage.clickExternalRejection();
		baseTest.setImplicitWait(5);
		Thread.sleep(5000);
//		Assert.assertTrue(baseTest.getWebDriver().findElement(By.className("overall-status")).getText().trim().equals("Ext. Rej - Fulfillment - Delivery - Default Status"));
		Assert.assertEquals(baseTest.getWebDriver().findElement(By.className("overall-status")).getText().trim(), "Ext. Rej - Fulfillment - Delivery - Default Status");

		orderItemPage.clickUrgent();
		baseTest.setImplicitWait(5);
		Thread.sleep(5000);
//		Assert.assertTrue(baseTest.getWebDriver().findElement(By.className("overall-status")).getText().trim().equals("Fulfillment - Delivery - Default Status"));
		Assert.assertEquals(baseTest.getWebDriver().findElement(By.className("overall-status")).getText().trim(), "Fulfillment - Delivery - Default Status");
		orderItemPage.clickCancel();
		baseTest.setImplicitWait(5);
		Thread.sleep(10000);
//		Assert.assertTrue(baseTest.getWebDriver().findElement(By.className("overall-status")).getText().trim().equals("Canceled"));
		Assert.assertEquals(baseTest.getWebDriver().findElement(By.className("overall-status")).getText().trim(), "Canceled");
	}
	public void checkStatus(String status) {
		WebElement name_row = baseTest.getWebDriver().findElement(By.className("name-row"));
		WebElement parent = name_row.findElement(By.xpath(".."));
		//System.out.println(new Select(parent.findElement(By.name("status"))).getFirstSelectedOption().getText());
//		Assert.assertTrue(new Select(parent.findElement(By.name("status"))).getFirstSelectedOption().getText().trim().equals(status));
		Assert.assertEquals(new Select(parent.findElement(By.name("status"))).getFirstSelectedOption().getText().trim(), status);
		parent.findElement(By.linkText("x")).click();
	}
}
