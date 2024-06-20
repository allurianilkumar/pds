package net.premieredigital.portal.orderitemsview;

import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderItemConfiguration;
import net.premieredigital.portal.pom.OrderItemPage;
import net.premieredigital.portal.pom.OrderPage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SaveDefaultSearchInOrderItemView {
	private OrderItemConfiguration orderItemConfig;
	private OrderItemPage orderItemPage;
	private OrderPage orderPage;
	private OrderConfiguration orderConfig;
	private BaseTest baseTest;
	public SaveDefaultSearchInOrderItemView(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void postCondition() {
//		orderPage.deleteOrder();
	}
	public void preCondition() throws Exception {
		orderItemConfig = new OrderItemConfiguration();
		orderConfig = new OrderConfiguration();
		orderPage = new OrderPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderConfig);
		//new LoginPage(this.baseTest.getWebDriver(), this.pageConfig).login();
		this.orderItemPage = new OrderItemPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), new OrderItemConfiguration(), this.orderConfig);
	}
	//@Test(groups = "ExcludeSafari",description="5.4 Save Default Search in Order Item View") 
	public void saveDefaultSearch() throws Exception{
		this.preCondition();
		baseTest.setImplicitWait(5);
		orderPage.placeOrder();
		orderPage.addSameRequirementToAll();
//		orderPage.addRequirement();
		orderItemPage.fillOrderItemPage();
		baseTest.fluentlyWait(new FluentWait<By>(By.linkText("Save as Default")));
		baseTest.getWebDriver().findElement(By.linkText("Save as Default")).click();
		if(new WebDriverWait(baseTest.getWebDriver(),5).until(ExpectedConditions.alertIsPresent())!=null) {
			baseTest.getWebDriver().switchTo().alert().accept();
			Thread.sleep(5000);
		}
		baseTest.fluentlyWait(new FluentWait<By>(By.linkText("Clear Filter")));
		baseTest.getWebDriver().findElement(By.linkText("Clear Filter")).click();
		Thread.sleep(5000);
		baseTest.fluentlyWait(new FluentWait<By>(By.name("provider_id")));
		//System.out.println("provider name after clearing filter");
		//System.out.println(new Select(baseTest.getWebDriver().findElement(By.name("provider_id"))).getFirstSelectedOption().getText());
		Assert.assertTrue(new Select(baseTest.getWebDriver().findElement(By.name("provider_id"))).getFirstSelectedOption().getText().trim().equals(orderConfig.getDefaultProvider()));
		baseTest.fluentlyWait(new FluentWait<By>(By.linkText("Apply Default")));
		baseTest.getWebDriver().findElement(By.linkText("Apply Default")).click();
		Thread.sleep(5000);
		baseTest.fluentlyWait(new FluentWait<By>(By.name("provider_id")));
		//System.out.println("provider name after applying default");
		//System.out.println(new Select(baseTest.getWebDriver().findElement(By.name("provider_id"))).getFirstSelectedOption().getText());
		Assert.assertTrue(new Select(baseTest.getWebDriver().findElement(By.name("provider_id"))).getFirstSelectedOption().getText().trim().equals(orderConfig.getProviderName()));
	}
}
