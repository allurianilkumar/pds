package net.premieredigital.portal.orderitemsview;

import java.util.List;
import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderItemConfiguration;
import net.premieredigital.portal.pom.OrderItemPage;
import net.premieredigital.portal.pom.OrderPage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.base.Predicate;

public class SyncBetweenOrderItemViewAndTaskView {
	private OrderItemConfiguration orderItemConfig;
	private OrderItemPage orderItemPage;
	private OrderPage orderPage;
	private OrderConfiguration orderConfig;
	private boolean nextTr;
	private BaseTest baseTest;
	public SyncBetweenOrderItemViewAndTaskView(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void postCondition() {
//		orderPage.deleteOrder();
	}

	public void preCondition() throws Exception {
		nextTr = false;
		orderItemConfig = new OrderItemConfiguration();
		this.orderConfig = new OrderConfiguration();
		orderPage = new OrderPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderConfig);
		// new LoginPage(this.baseTest.getWebDriver(), this.pageConfig).login();
		this.orderItemPage = new OrderItemPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), new OrderItemConfiguration(), this.orderConfig);
	}

	//@Test(groups = "ExcludeSafari",description="5.2 Sync Between Order Item View and Task View")
	public void syncBetweenOrderItemViewAndTaskView() throws Exception{
		this.preCondition();
		orderPage.placeOrder();
		orderPage.addSameRequirementToAll();
		orderItemPage.changeStatusForFeature(orderItemConfig.getStatusForFeature());
		orderItemPage.changeStatusForTrailer();
		orderItemPage.changeStatusForArtwork();
		orderItemPage.changeStatusForMetadata();
		orderItemPage.searchTaskView();
		baseTest.fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = baseTest.getWebDriver().findElement(By.tagName("tbody"));
		baseTest.fluentlyWait(new FluentWait<By>(By.tagName("tr")));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		for(WebElement tr:trs) {
			List<WebElement> tds = tr.findElements(By.tagName("td"));
			for(WebElement td:tds) {
				if(td.getText().trim().startsWith("Feature")) {
					Assert.assertTrue(tr.findElement(By.className("status")).getText().trim().equals(orderItemConfig.getStatusForFeature()));
					nextTr = true;
				}
				else if(td.getText().trim().startsWith("Trailer")) {
					Assert.assertTrue(tr.findElement(By.className("status")).getText().trim().equals(orderItemConfig.getStatusForTrailer()));
					nextTr = true;
				}
				else if(td.getText().trim().startsWith("Artwork")) {
					Assert.assertTrue(tr.findElement(By.className("status")).getText().trim().equals(orderItemConfig.getStatusForArtwork()));
					nextTr = true;
				}
				else if(td.getText().trim().startsWith("Metadata")) {
					Assert.assertTrue(tr.findElement(By.className("status")).getText().trim().equals(orderItemConfig.getStatusForMetadata()));
					nextTr = true;
				}
				else {
					continue;
				}
				if(nextTr) {
					break;
				}
			}
		}
		orderItemPage.searchTaskView();
		orderItemPage.updateStatusInTasksView();
		refreshAndFillOrderItemPage();
		FluentWait<By> fluentWait = new FluentWait<By>(By.linkText("Feat"));
		baseTest.fluentlyWait(fluentWait);
//		new WebDriverWait(baseTest.getWebDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Feat")));
		baseTest.getWebDriver().findElement(By.linkText("Feat")).click();
		baseTest.fluentlyWait(new FluentWait<By>(By.className("name-row")));
		WebElement name_row = baseTest.getWebDriver().findElement(By.className("name-row"));
		if(name_row.getText().contains("Feature")) {
			checkStatus(name_row);
		}
		refreshAndFillOrderItemPage();
		fluentWait = new FluentWait<By>(By.linkText("Trlr"));
		baseTest.fluentlyWait(fluentWait);
//		new WebDriverWait(baseTest.getWebDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Trlr")));
		baseTest.getWebDriver().findElement(By.linkText("Trlr")).click();
		baseTest.fluentlyWait(new FluentWait<By>(By.className("name-row")));
		name_row = baseTest.getWebDriver().findElement(By.className("name-row"));
		if(name_row.getText().contains("Trailer")) {
			checkStatus(name_row);
		}
		refreshAndFillOrderItemPage();
		fluentWait = new FluentWait<By>(By.linkText("Art"));
		baseTest.fluentlyWait(fluentWait);
//		new WebDriverWait(baseTest.getWebDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Art")));
		baseTest.getWebDriver().findElement(By.linkText("Art")).click();
		baseTest.fluentlyWait(new FluentWait<By>(By.className("name-row")));
		name_row = baseTest.getWebDriver().findElement(By.className("name-row"));
		if(name_row.getText().contains("Artwork")) {
			checkStatus(name_row);
		}
		refreshAndFillOrderItemPage();
		fluentWait = new FluentWait<By>(By.linkText("Met"));
		baseTest.fluentlyWait(fluentWait);
//		new WebDriverWait(baseTest.getWebDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Met")));
		baseTest.getWebDriver().findElement(By.linkText("Met")).click();
		baseTest.fluentlyWait(new FluentWait<By>(By.className("name-row")));
		name_row = baseTest.getWebDriver().findElement(By.className("name-row"));
		if(name_row.getText().contains("Metadata")) {
			checkStatus(name_row);
		}
	}
	public void checkStatus(WebElement name_row) {
		WebElement parent = name_row.findElement(By.xpath(".."));
		Assert.assertTrue(new Select(parent.findElement(By.name("status"))).getFirstSelectedOption().getText().trim().equals("Default Status"));
		parent.findElement(By.linkText("x")).click();
	}
	public void refreshAndFillOrderItemPage() {
		baseTest.getWebDriver().navigate().refresh();
		orderItemPage.fillOrderItemPage();
		baseTest.getWebDriver().navigate().refresh();
	}
}
