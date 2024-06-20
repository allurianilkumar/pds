package net.premieredigital.portal.masteringview;

import java.util.List;
import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.MasteringConfiguration;
import net.premieredigital.portal.pom.MasteringPage;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderItemConfiguration;
import net.premieredigital.portal.pom.OrderItemPage;
import net.premieredigital.portal.pom.OrderPage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.base.Predicate;

public class SynchronizationBetweenMasteringViewAndTasksView {
	private OrderItemConfiguration orderItemConfig;
	private OrderItemPage orderItemPage;
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private MasteringPage masteringPage;
	private MasteringConfiguration masteringConfig;
	private WebElement parent;
	private BaseTest baseTest;
	public SynchronizationBetweenMasteringViewAndTasksView(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void postCondition() {
//		orderPage.deleteOrder();
	}
	
	public void preCondition() throws Exception{
		this.parent = null;
		masteringConfig = new MasteringConfiguration();
		orderItemConfig = new OrderItemConfiguration();
		orderConfig = new OrderConfiguration();
		orderPage = new OrderPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), orderConfig);
		// new LoginPage(baseTest.getWebDriver(), baseTest.getPageConfiguration()).login();
		this.orderItemPage = new OrderItemPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), this.orderItemConfig, orderConfig);
		this.masteringPage = new MasteringPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), this.orderItemConfig, orderConfig, this.masteringConfig);
	}
	//4.7
	//@Test(groups="ExcludeSafari", description="4.7 Synchronization Between Mastering View And Tasks View")
	public void synchTaskViewAndMasteringGridView() throws Exception{
		this.preCondition();
		Boolean exists = false;
		orderPage.placeOrder();
		masteringPage.addExistingTitle(orderConfig.getTitle3());
		exists = checkItemInMasteringGrid(orderConfig.getTitle3());
		Assert.assertTrue(exists);
		masteringPage.modifyStatusAndRenderTaskView();
		String winHandleBefore = baseTest.getWebDriver().getWindowHandle();
		for(String winHandle : baseTest.getWebDriver().getWindowHandles()){
			baseTest.getWebDriver().switchTo().window(winHandle);
		}
		assertTaskView(masteringConfig.getStatus());
		masteringPage.modifyStatusInTaskView();
		baseTest.getWebDriver().close();
		baseTest.getWebDriver().switchTo().window(winHandleBefore);
		masteringPage.fillSearchCriteria(masteringConfig.getProvider());
		assertStatus(masteringConfig.getModifiedStatus());
	}
	public boolean checkItemInMasteringGrid(String title) {
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
	public void assertTaskView(String status) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> trs = null;
		List<WebElement> tds = null;
		masteringPage.fluentlyWait(new FluentWait<By> (By.tagName("tbody")));
		WebElement tbody = baseTest.getWebDriver().findElement(By.tagName("tbody"));
		parent = tbody;
		FluentWait<By> fluentWait = new FluentWait<By>(By.tagName("tr"));
        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(2000, TimeUnit.MILLISECONDS);
        fluentWait.until(new Predicate<By>() {
            public boolean apply(By by) {
                try {               	
                  return parent.findElements(by).size() > 0;
                } catch (NoSuchElementException ex) {
                    return false;
                }
            }
        });
		trs = parent.findElements(By.tagName("tr"));
		tds = trs.get(0).findElements(By.tagName("td"));
		WebElement status_elm = tds.get(7);
		//System.out.println(status_elm.findElement(By.className("status")).getText());
		Assert.assertTrue(status_elm.findElement(By.className("status")).getText().equals(status));
	}
	public void assertStatus(String status) {
		WebElement tbody = baseTest.getWebDriver().findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		for(WebElement tr:trs) {
			if(tr.findElements(By.className("normal-looking-link")).size() > 0) {
				if(tr.findElement(By.className("normal-looking-link")).getText().contains(orderConfig.getTitle3())) {
					Assert.assertTrue(new Select(tr.findElement(By.name("status"))).getFirstSelectedOption().getText().trim().equals(status));
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
