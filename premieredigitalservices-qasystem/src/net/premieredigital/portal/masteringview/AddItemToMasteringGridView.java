package net.premieredigital.portal.masteringview;

import java.util.List;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.MasteringConfiguration;
import net.premieredigital.portal.pom.MasteringPage;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderItemConfiguration;
import net.premieredigital.portal.pom.OrderItemPage;
import net.premieredigital.portal.pom.OrderPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddItemToMasteringGridView {
	private OrderItemConfiguration orderItemConfig;
	private OrderItemPage orderItemPage;
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private MasteringPage masteringPage;
	private MasteringConfiguration masteringConfig;
	private BaseTest baseTest;
	public AddItemToMasteringGridView(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void postCondition() {
//		orderPage.deleteOrder();
	}
	
	public void preCondition() throws Exception {
		masteringConfig = new MasteringConfiguration();
		orderItemConfig = new OrderItemConfiguration();
		orderConfig = new OrderConfiguration();
		orderPage = new OrderPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderConfig);
		//new LoginPage(this.webDriver.get(), this.pageConfig).login();
		this.orderItemPage = new OrderItemPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderItemConfig, this.orderConfig);
		this.masteringPage = new MasteringPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderItemConfig, this.orderConfig, this.masteringConfig);
	}
	//4.1
	//@Test(groups="ExcludeSafari", description="4.1 User Shall Be Able To Add Mastering Grid View")
	public void addItemToMasteringGridView() throws Exception{
		this.preCondition();
		Boolean exists = false;
		orderPage.placeOrder();
		masteringPage.addExistingTitle(orderConfig.getTitle3());
		exists = checkItemInMasteringGrid(orderConfig.getTitle3());
		Assert.assertTrue(exists);
		//masteringPage.addNewTitle();
		//masteringPage.addTitle(masteringConfig.getTitle());
		//exists = checkItemInMasteringGrid(masteringConfig.getTitle());
		//Assert.assertTrue(exists);
	}
	public boolean checkItemInMasteringGrid(String title) {
		WebElement tbody = baseTest.getWebDriver().findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		for(WebElement tr:trs) {
			if(tr.findElements(By.className("normal-looking-link")).size() > 0) {
				if(tr.findElement(By.className("normal-looking-link")).getText().contains(title)) {
					return true;
				}
				else {
					continue;
				}
			}
			else {
				continue;
			}
		}
		return false;
	}
}
