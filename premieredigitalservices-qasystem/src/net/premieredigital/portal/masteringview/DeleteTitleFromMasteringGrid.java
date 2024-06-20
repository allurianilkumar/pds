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

public class DeleteTitleFromMasteringGrid {
	private OrderItemConfiguration orderItemConfig;
	private OrderItemPage orderItemPage;
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private MasteringPage masteringPage;
	private MasteringConfiguration masteringConfig;
	private BaseTest baseTest;
	public DeleteTitleFromMasteringGrid(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void postCondition() {
//		orderPage.deleteOrder();
	}
	
	public void preCondition() throws Exception {
		masteringConfig = new MasteringConfiguration();
		orderItemConfig = new OrderItemConfiguration();
		orderConfig = new OrderConfiguration();
		orderPage = new OrderPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), this.orderConfig);
		//new LoginPage(this.webDriver.get(), this.pageConfig).login();
		this.orderItemPage = new OrderItemPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), this.orderItemConfig, this.orderConfig);
		this.masteringPage = new MasteringPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), this.orderItemConfig, this.orderConfig, this.masteringConfig);
	}
	//4.3
	//@Test(groups = "ExcludeSafari",description="4.3 Delete A Title From Mastering Grid View")
	public void deleteTitleFromMasteringGrid() throws Exception{
		this.preCondition();
		boolean exists = false;
		orderPage.placeOrder();
		masteringPage.addExistingTitle(orderConfig.getTitle3());
		masteringPage.fillSearchCriteria(masteringConfig.getProvider());
		exists=findTitleInMasteringGrid(orderConfig.getTitle3());
		Assert.assertTrue(exists);
		masteringPage.deleteTitle();
		exists=findTitleInMasteringGrid(orderConfig.getTitle3());
		Assert.assertFalse(exists);
	}
	public boolean findTitleInMasteringGrid(String title) {
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
