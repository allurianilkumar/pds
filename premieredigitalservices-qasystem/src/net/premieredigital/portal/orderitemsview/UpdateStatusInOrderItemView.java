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
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdateStatusInOrderItemView {
	private OrderItemConfiguration orderItemConfig;
	private OrderItemPage orderItemPage;
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private BaseTest baseTest;
	public UpdateStatusInOrderItemView(BaseTest baseTest) {
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
	//Test 5.1
	//@Test(groups = "ExcludeSafari",description="5.1 Update status in order items view") 
	public void updateStatus() throws Exception {
		this.preCondition();
		orderPage.placeOrder();
		orderPage.addSameRequirementToAll();
		orderItemPage.changeStatusForFeature(orderItemConfig.getStatusForFeature());
		System.out.println("1");
		//baseTest.getWebDriver().navigate().refresh();
		orderItemPage.fillOrderItemPage();
		//baseTest.getWebDriver().navigate().refresh();
		baseTest.fluentlyWait(new FluentWait<By>(By.linkText("Feat")));
		baseTest.getWebDriver().findElement(By.linkText("Feat")).click();
		Thread.sleep(2000);
		baseTest.fluentlyWait(new FluentWait<By>(By.className("name-row")));
		WebElement feature = baseTest.getWebDriver().findElement(By.className("name-row"));
		//System.out.println("feature:" + feature.getText());
		if(feature.getText().contains("Feature")) {
			WebElement parent = feature.findElement(By.xpath(".."));
			//System.out.println(new Select(parent.findElement(By.name("status"))).getFirstSelectedOption().getText());
			//System.out.println(orderItemConfig.getStatusForFeature());
			Assert.assertTrue(new Select(parent.findElement(By.name("status"))).getFirstSelectedOption().getText().trim().equals(orderItemConfig.getStatusForFeature()));
			//System.out.println("...........");
			parent.findElement(By.linkText("x")).click();
			Thread.sleep(2000);
		}
		orderItemPage.changeStatusForTrailer();
		//System.out.println("2");
		//baseTest.getWebDriver().navigate().refresh();
		orderItemPage.fillOrderItemPage();
		//baseTest.getWebDriver().navigate().refresh();
		baseTest.fluentlyWait(new FluentWait<By>(By.linkText("Trlr")));
		baseTest.getWebDriver().findElement(By.linkText("Trlr")).click();
		Thread.sleep(2000);
		baseTest.fluentlyWait(new FluentWait<By>(By.className("name-row")));
		WebElement trailer = baseTest.getWebDriver().findElement(By.className("name-row"));
		//System.out.println("trailer:" + trailer.getText());
		if(trailer.getText().contains("Trailer")) {
			WebElement parent = trailer.findElement(By.xpath(".."));
			Assert.assertTrue(new Select(parent.findElement(By.name("status"))).getFirstSelectedOption().getText().trim().equals(orderItemConfig.getStatusForTrailer()));
			//System.out.println("...........");
			parent.findElement(By.linkText("x")).click();
			Thread.sleep(2000);
		}
		orderItemPage.changeStatusForArtwork();
		//System.out.println("3");
		//baseTest.getWebDriver().navigate().refresh();
		orderItemPage.fillOrderItemPage();
		//baseTest.getWebDriver().navigate().refresh();
		baseTest.fluentlyWait(new FluentWait<By>(By.linkText("Art")));
		baseTest.getWebDriver().findElement(By.linkText("Art")).click();
		Thread.sleep(2000);
		baseTest.fluentlyWait(new FluentWait<By>(By.className("name-row")));
		WebElement artwork = baseTest.getWebDriver().findElement(By.className("name-row"));
		//System.out.println("artwork:" + artwork.getText());
		if(artwork.getText().contains("Artwork")) {
			WebElement parent = artwork.findElement(By.xpath(".."));
			Assert.assertTrue(new Select(parent.findElement(By.name("status"))).getFirstSelectedOption().getText().trim().equals(orderItemConfig.getStatusForArtwork()));
			//System.out.println("...........");
			parent.findElement(By.linkText("x")).click();
			Thread.sleep(2000);
		}
		orderItemPage.changeStatusForMetadata();
		//System.out.println("4");
		//baseTest.getWebDriver().navigate().refresh();
		orderItemPage.fillOrderItemPage();
		//baseTest.getWebDriver().navigate().refresh();
		baseTest.fluentlyWait(new FluentWait<By>(By.linkText("Met")));
		baseTest.getWebDriver().findElement(By.linkText("Met")).click();
		Thread.sleep(2000);
		baseTest.fluentlyWait(new FluentWait<By>(By.className("name-row")));
		WebElement metadata = baseTest.getWebDriver().findElement(By.className("name-row"));
		if(metadata.getText().contains("Metadata")) {
			WebElement parent = metadata.findElement(By.xpath(".."));
			Assert.assertTrue(new Select(parent.findElement(By.name("status"))).getFirstSelectedOption().getText().trim().equals(orderItemConfig.getStatusForMetadata()));
			parent.findElement(By.linkText("x")).click();
			Thread.sleep(2000);
		}
		//System.out.println("completed");
	}
}
