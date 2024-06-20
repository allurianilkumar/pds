package net.premieredigital.portal.masteringview;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.MasteringConfiguration;
import net.premieredigital.portal.pom.MasteringPage;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderItemConfiguration;
import net.premieredigital.portal.pom.OrderItemPage;
import net.premieredigital.portal.pom.OrderPage;

public class SaveModificationsInMasteringGrid {
	private OrderItemConfiguration orderItemConfig;
	private OrderItemPage orderItemPage;
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private MasteringPage masteringPage;
	private MasteringConfiguration masteringConfig;
	private BaseTest baseTest;
	public SaveModificationsInMasteringGrid(BaseTest baseTest) {
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
	//4.5
	//@Test(groups="ExcludeSafari", description="4.5 Save Modifications In Mastering Grid")
	public void saveModificationsInMasteringGrid() throws Exception{
		this.preCondition();
		boolean exists = false;
		orderPage.placeOrder();
		masteringPage.addExistingTitle(orderConfig.getTitle3());
		masteringPage.fillSearchCriteria(masteringConfig.getProvider());
		exists=findTitleInMasteringGrid(orderConfig.getTitle3());
		Assert.assertTrue(exists);
		masteringPage.modifyFieldsAndSave();
		masteringPage.fillModifiedSearchCriteria();
		checkFieldsForTitle(orderConfig.getTitle3());
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
	public void checkFieldsForTitle(String title) {
		WebElement tbody = baseTest.getWebDriver().findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		for(WebElement tr:trs) {
			if(tr.findElements(By.className("normal-looking-link")).size() > 0) {
				if(tr.findElement(By.className("normal-looking-link")).getText().contains(title)) {
					Assert.assertTrue(new Select(tr.findElement(By.name("update_video_type"))).getFirstSelectedOption().getText().equals(masteringConfig.getModifiedVideoType()));
					Assert.assertTrue(new Select(tr.findElement(By.name("update_processing_stage"))).getFirstSelectedOption().getText().equals(masteringConfig.getModifiedStage()));
					Assert.assertTrue(new Select(tr.findElement(By.name("update_resolution"))).getFirstSelectedOption().getText().equals(masteringConfig.getModifiedResolution()));
					Assert.assertTrue(new Select(tr.findElement(By.name("update_format"))).getFirstSelectedOption().getText().equals(masteringConfig.getModifiedVideoFormat()));
					break;
				}
				else {
					continue;
				}
			}
			else {
				continue;
			}
		}
	}
}
