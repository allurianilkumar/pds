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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindTitleInAssetsView extends BaseTest {
	private OrderItemConfiguration orderItemConfig;
	private OrderItemPage orderItemPage;
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private AssetsPage assetsPage;
	private AssetsConfiguration assetsConfig;
	private MasteringConfiguration masteringConf;
	private MasteringPage masteringPage;
	public void postCondition() {
//		orderPage.deleteOrder();
	}
	
	public void preCondition() throws Exception {
		assetsConfig = new AssetsConfiguration();
		orderItemConfig = new OrderItemConfiguration();
		orderConfig = new OrderConfiguration();
		masteringConf = new MasteringConfiguration();
		orderPage = new OrderPage(this.webDriver.get(), this.pageConfig, orderConfig);
		//new LoginPage(this.webDriver.get(), this.pageConfig).login();
		this.orderItemPage = new OrderItemPage(this.webDriver.get(),this.pageConfig, this.orderItemConfig, orderConfig);
		this.assetsConfig = new AssetsConfiguration();
		this.assetsPage = new AssetsPage(this.webDriver.get(),this.pageConfig, this.orderItemConfig, orderConfig, this.assetsConfig,this.masteringConf);
		this.masteringPage = new MasteringPage(this.webDriver.get(),this.pageConfig,this.orderItemConfig,this.orderConfig,this.masteringConf);
	}
	//
	//@Test(description="3.1 Find Title In Assets View")
	public void findTitleInAssets() throws Exception{
		boolean exists = false;
		orderPage.placeOrder();
		masteringPage.addExistingTitle(orderConfig.getTitle3());
		assetsPage.fillSearchCriteria();
		Thread.sleep(5000);
		//System.out.println(webDriver.get().getCurrentUrl());
		exists=findTitleInAssets(orderConfig.getTitle3());
		Assert.assertTrue(exists);
	}
	public boolean findTitleInAssets(String title) {
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

}
