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
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserViewAndEditTitleInTheMasteringGrid {
	private OrderItemConfiguration orderItemConfig;
	private OrderItemPage orderItemPage;
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private MasteringPage masteringPage;
	private MasteringConfiguration masteringConfig;
	private BaseTest baseTest;
	public UserViewAndEditTitleInTheMasteringGrid(BaseTest baseTest) {
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
		//new LoginPage(baseTest.getWebDriver(), baseTest.getPageConfiguration()).login();
		this.orderItemPage = new OrderItemPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderItemConfig, this.orderConfig);
		this.masteringPage = new MasteringPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderItemConfig, this.orderConfig, this.masteringConfig);
	}
	//4.6
	//@Test(groups="ExcludeSafari" ,description="4.6 User Shall Be Able To View And Edit Title In The Mastering Grid")
	public void viewAndEditTitleInMasteringGrid() throws Exception{
		this.preCondition();
		boolean exists = false;
		orderPage.placeOrder();
		masteringPage.addExistingTitle(orderConfig.getTitle3());
		masteringPage.fillSearchCriteria(masteringConfig.getProvider());
		exists=findTitleInMasteringGrid(orderConfig.getTitle3());
		Assert.assertTrue(exists);
		masteringPage.clickLinkText("view title");
//		assertTitleView();
		masteringPage.modifyTitleView();
//		Capabilities cap = ((RemoteWebDriver) baseTest.getWebDriver()).getCapabilities();
//		if(cap.getBrowserName().toLowerCase().equals("safari")) {
//			baseTest.getWebDriver().get(pageConfig.getRootURL() + "/mastering");
//		}
//		else {
//			baseTest.getWebDriver().navigate().back();
//			Thread.sleep(5000);
//			baseTest.getWebDriver().navigate().back();
//			Thread.sleep(5000);
//		}
		masteringPage.fillSearchCriteria(masteringConfig.getModifiedProvider());
//		new Select(baseTest.getWebDriver().findElement(By.name("provider_id"))).selectByVisibleText(masteringConfig.getModifiedProvider());
//		baseTest.getWebDriver().findElement(By.className("grey-button")).click();
		Thread.sleep(5000);
		this.assertModifiedTitleView();
	}
	public boolean findTitleInMasteringGrid(String title) {
		baseTest.fluentlyWait(new FluentWait<By> (By.tagName("tbody")));
		WebElement tbody = baseTest.getWebDriver().findElement(By.tagName("tbody"));
		baseTest.fluentlyWait(new FluentWait<By> (By.tagName("tr")));
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
	public void assertTitleView() {
		//System.out.println("-----------------------");
		//System.out.println(baseTest.getWebDriver().findElement(By.name("provider")).getText().trim());
		Assert.assertTrue(baseTest.getWebDriver().findElement(By.name("provider")).getText().trim().equals(masteringConfig.getProvider()));
		Assert.assertTrue(baseTest.getWebDriver().findElement(By.name("language")).getText().trim().equals(masteringConfig.getLanguage()));
		Assert.assertTrue(baseTest.getWebDriver().findElement(By.name("name")).getText().trim().equals(orderConfig.getTitle3()));
	}
	public void assertModifiedTitleView() throws InterruptedException {
		baseTest.fluentlyWait(new FluentWait<By>(By.id("content-wrapper")));
		WebElement contentWrapper= baseTest.getWebDriver().findElement(By.id("content-wrapper"));
		baseTest.fluentlyWait(new FluentWait<By>(By.id("table-container")));
		WebElement tableContainer= contentWrapper.findElement(By.id("table-container"));
		masteringPage.scrollInToElement(tableContainer.findElement(By.tagName("table")));
		baseTest.fluentlyWait(new FluentWait<By>(By.tagName("table")));
		WebElement table = tableContainer.findElement(By.tagName("table"));
		masteringPage.scrollInToElement(table.findElement(By.tagName("tbody")));
		WebElement tbody= table.findElement(By.tagName("tbody"));
		masteringPage.scrollInToElement(table.findElement(By.tagName("tr")));
		baseTest.fluentlyWait(new FluentWait<By> (By.tagName("tr")));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		for(WebElement tr:trs) {
			if(tr.findElements(By.className("normal-looking-link")).size() > 0) {
				if(tr.findElement(By.className("normal-looking-link")).getText().contains(orderConfig.getTitle3())) {
//						Assert.assertTrue(tr.findElement(By.className("normal-looking-link")).getText().contains(masteringConfig.getModifiedLanguage().substring(masteringConfig.getModifiedLanguage().length(), -5)));
					Assert.assertTrue(tr.getText().contains(masteringConfig.getModifiedProvider()));
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
