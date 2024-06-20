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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ClearAllEntriesForTitleInMastering {
	private OrderItemConfiguration orderItemConfig;
	private OrderItemPage orderItemPage;
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private MasteringPage masteringPage;
	private MasteringConfiguration masteringConfig;
	private BaseTest baseTest;
	public ClearAllEntriesForTitleInMastering(BaseTest baseTest) {
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
		//new LoginPage(this.baseTest.getWebDriver(), this.pageConfig).login();
		this.orderItemPage = new OrderItemPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), this.orderItemConfig, this.orderConfig);
		this.masteringPage = new MasteringPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), this.orderItemConfig, this.orderConfig, this.masteringConfig);
	}
	//4.4
	//@Test(groups = "ExcludeSafari",description="4.4 Clear All Fields For A Title In Mastering Grid")
	public void clearTitleInMasteringGrid() throws Exception{
		this.preCondition();
		boolean exists = false;
		orderPage.placeOrder();
		masteringPage.addExistingTitle(orderConfig.getTitle3());
		masteringPage.fillSearchCriteria(masteringConfig.getProvider());
		exists=findTitleInMasteringGrid(orderConfig.getTitle3());
		Assert.assertTrue(exists);
		masteringPage.clearFields();
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
					Assert.assertTrue(new Select(tr.findElement(By.name("update_video_type"))).getFirstSelectedOption().getText().equals("Select category"));
					Assert.assertTrue(new Select(tr.findElement(By.name("update_processing_stage"))).getFirstSelectedOption().getText().equals("--"));
					Assert.assertTrue(new Select(tr.findElement(By.name("update_received_from"))).getFirstSelectedOption().getText().equals("--"));
					Assert.assertTrue(new Select(tr.findElement(By.name("update_assigned_to"))).getFirstSelectedOption().getText().equals("Select Assignee"));
					Assert.assertTrue(new Select(tr.findElement(By.name("update_pasp"))).getFirstSelectedOption().getText().equals("Select CASP"));
					Assert.assertTrue(new Select(tr.findElement(By.name("update_vasp"))).getFirstSelectedOption().getText().equals("Select VASP"));
					Assert.assertTrue(new Select(tr.findElement(By.name("update_resolution"))).getFirstSelectedOption().getText().equals("Select Res"));
					Assert.assertTrue(new Select(tr.findElement(By.name("update_format"))).getFirstSelectedOption().getText().equals("Select Video Format"));
					Assert.assertTrue(new Select(tr.findElement(By.name("update_office"))).getFirstSelectedOption().getText().equals("Select Office"));
					Assert.assertTrue(tr.findElement(By.name("update_notes")).getText().equals(""));
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
