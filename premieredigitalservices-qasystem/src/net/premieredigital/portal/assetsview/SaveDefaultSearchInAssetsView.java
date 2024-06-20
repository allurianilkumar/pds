
	package net.premieredigital.portal.assetsview;

	import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.AssetsConfiguration;
import net.premieredigital.portal.pom.AssetsPage;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.MasteringConfiguration;
import net.premieredigital.portal.pom.MasteringPage;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderItemConfiguration;
import net.premieredigital.portal.pom.OrderPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

	public class SaveDefaultSearchInAssetsView extends BaseTest {
		private AssetsConfiguration assetsConfig;
		private AssetsPage assetsPage;
		private OrderPage orderPage;
		private OrderConfiguration orderConfig;
		private OrderItemConfiguration orderItemConfig;
		private MasteringConfiguration masteringConfig;
		private MasteringPage masteringPage;

		public void postCondition() {
//			orderPage.deleteOrder();
			webDriver.get().findElement(By.linkText("Clear Filter")).click();
			fluentlyWait(new FluentWait<By> (By.linkText("Save as Default")));
			webDriver.get().findElement(By.linkText("Save as Default")).click();
			assetsPage.clickAlert();
//			webDriver.get().findElement(By.linkText("Apply Default")).click();
//			Assert.assertTrue(new Select(webDriver.get().findElement(By.name("provider_id"))).getFirstSelectedOption().getText().trim().equals(assetsConfig.getProvider()));	
		}
		
		public void preCondition() throws Exception {
			assetsConfig = new AssetsConfiguration();
			orderConfig = new OrderConfiguration();
			masteringConfig = new MasteringConfiguration();
			//System.out.println("in saveDefault..........");
			orderPage = new OrderPage(this.webDriver.get(), this.pageConfig, this.orderConfig);
			//System.out.println("back from order page instantiation");
			//new LoginPage(this.webDriver.get(), this.pageConfig).login();
			//System.out.println("back from login");
			this.assetsPage = new AssetsPage(this.webDriver.get(),this.pageConfig, orderItemConfig, orderConfig, assetsConfig, masteringConfig);
			//System.out.println("back from taskspage instantiation");
			this.masteringPage = new MasteringPage(this.webDriver.get(),this.pageConfig,this.orderItemConfig,this.orderConfig,this.masteringConfig);
		}
		//@Test(groups = "ExcludeSafari",description="3.3 Save Default Search in Assets View") 
		public void saveDefaultAssetsSearch() throws Exception {
			setImplicitWait(5);
			orderPage.placeOrder();
			masteringPage.addExistingTitle(orderConfig.getTitle3());
			assetsPage.fillSearchCriteria();
			Thread.sleep(5000);
			fluentlyWait(new FluentWait<By> (By.linkText("Save as Default")));
			WebElement save_default = webDriver.get().findElement(By.linkText("Save as Default"));
			assetsPage.scrollInToElement(save_default);
			save_default.click();
			assetsPage.clickAlert();
			Thread.sleep(5000);
			fluentlyWait(new FluentWait<By> (By.linkText("Clear Filter")));
			WebElement clear_filter = webDriver.get().findElement(By.linkText("Clear Filter"));
			assetsPage.scrollInToElement(clear_filter);
			clear_filter.click();
			//System.out.println("clicked Clear Filter...");
			Thread.sleep(10000);
			fluentlyWait(new FluentWait<By> (By.name("provider_id")));
			Assert.assertEquals(new Select(webDriver.get().findElement(By.name("provider_id"))).getFirstSelectedOption().getText().trim(), assetsConfig.getProvider());
			fluentlyWait(new FluentWait<By> (By.linkText("Apply Default")));
			WebElement apply_default = webDriver.get().findElement(By.linkText("Apply Default"));
			assetsPage.scrollInToElement(apply_default);
			apply_default.click();
			//System.out.println("clicked apply default...");
			Thread.sleep(10000);
			fluentlyWait(new FluentWait<By> (By.name("provider_id")));
			Assert.assertEquals(new Select(webDriver.get().findElement(By.name("provider_id"))).getFirstSelectedOption().getText().trim(),masteringConfig.getProvider());
			fluentlyWait(new FluentWait<By> (By.linkText("Clear Filter")));
			clear_filter = webDriver.get().findElement(By.linkText("Clear Filter"));
			assetsPage.scrollInToElement(clear_filter);
			clear_filter.click();
			Thread.sleep(5000);
			fluentlyWait(new FluentWait<By> (By.linkText("Save as Default")));
			save_default = webDriver.get().findElement(By.linkText("Save as Default"));
			assetsPage.scrollInToElement(save_default);
			save_default.click();
			assetsPage.clickAlert();
			Thread.sleep(5000);
			fluentlyWait(new FluentWait<By> (By.linkText("Apply Default")));
			apply_default =  webDriver.get().findElement(By.linkText("Apply Default"));
			assetsPage.scrollInToElement(apply_default);
			apply_default.click();
			Thread.sleep(5000);
			Assert.assertTrue(new Select(webDriver.get().findElement(By.name("provider_id"))).getFirstSelectedOption().getText().trim().equals(assetsConfig.getProvider()));
		}
}
