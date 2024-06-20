package net.premieredigital.portal.assetsview;

import java.util.List;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.AssetsConfiguration;
import net.premieredigital.portal.pom.AssetsPage;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.MasteringConfiguration;
import net.premieredigital.portal.pom.MasteringPage;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderItemConfiguration;
import net.premieredigital.portal.pom.OrderItemPage;
import net.premieredigital.portal.pom.OrderPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserShallBeAbleToViewTitleInTheAssets extends BaseTest {
	private OrderItemConfiguration orderItemConfig;
	private OrderItemPage orderItemPage;
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private AssetsPage assetsPage;
	private AssetsConfiguration assetsConfig;
	private MasteringConfiguration masteringConfig;
	private MasteringPage masteringPage;
	public void postCondition() {
//		orderPage.deleteOrder();
	}
	
	public void preCondition() throws Exception {
		assetsConfig = new AssetsConfiguration();
		orderItemConfig = new OrderItemConfiguration();
		orderConfig = new OrderConfiguration();
		masteringConfig = new MasteringConfiguration();
		orderPage = new OrderPage(this.webDriver.get(), this.pageConfig, orderConfig);
		//new LoginPage(this.webDriver.get(), this.pageConfig).login();
		this.orderItemPage = new OrderItemPage(this.webDriver.get(),this.pageConfig, this.orderItemConfig, orderConfig);
		this.assetsConfig = new AssetsConfiguration();
		this.assetsPage = new AssetsPage(this.webDriver.get(),this.pageConfig, this.orderItemConfig, orderConfig, this.assetsConfig, this.masteringConfig);
		this.masteringPage = new MasteringPage(this.webDriver.get(),this.pageConfig,this.orderItemConfig,this.orderConfig,this.masteringConfig);
	}
	//
	//@Test(description="3.2 User Shall Be Able To View Title In The Assets Grid")
	public void viewTitleInAssets() throws Exception{
		boolean exists = false;
		orderPage.placeOrder();
		masteringPage.addExistingTitle(orderConfig.getTitle3());
		assetsPage.fillSearchCriteria();
		Thread.sleep(5000);
		exists=viewTitleInAssets(orderConfig.getTitle3());
		Assert.assertTrue(exists);
		assetsPage.clickTitle();
		Thread.sleep(5000);
		verifyMetadata();
		assetsPage.clickSummary();
		verifySummary();		
		//exists=findTitleInAssets(orderConfig.getTitle3());

}
	public boolean viewTitleInAssets(String title) {
		assetsPage.fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = webDriver.get().findElement(By.tagName("tbody"));
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
	public void verifyMetadata(){
		Assert.assertTrue(webDriver.get().findElement(By.name("provider")).getAttribute("value").contains(masteringConfig.getProvider()));
		Assert.assertTrue(webDriver.get().findElement(By.name("language")).getAttribute("value").contains(masteringConfig.getLanguage()));
		Assert.assertTrue(webDriver.get().findElement(By.name("name")).getAttribute("value").trim().equals(orderConfig.getTitle3()));
	}
	public void verifySummary() {
		WebElement elm = webDriver.get().findElement(By.tagName("tbody"));
		List<WebElement> trs = elm.findElements(By.tagName("tr"));
		List<WebElement> tds = trs.get(1).findElements(By.tagName("td"));
		Assert.assertTrue(tds.get(0).getText().equals(masteringConfig.getVideoType()));
	}
}
