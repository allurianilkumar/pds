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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserRemoveTaskAndAddTaskInMasteringGrid {
	private OrderItemConfiguration orderItemConfig;
	private OrderItemPage orderItemPage;
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private MasteringPage masteringPage;
	private MasteringConfiguration masteringConfig;
	private WebElement parent;
	private BaseTest baseTest;
	public UserRemoveTaskAndAddTaskInMasteringGrid(BaseTest baseTest) {
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
		orderPage = new OrderPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), this.orderConfig);
		//new LoginPage(baseTest.getWebDriver(), baseTest.getPageConfiguration()).login();
		this.orderItemPage = new OrderItemPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderItemConfig, this.orderConfig);
		this.masteringPage = new MasteringPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderItemConfig, this.orderConfig, this.masteringConfig);
	}
	//4.8
	//@Test(groups = "ExcludeSafari",description="4.8 User Shall Be Able To Remove Task And Add Task In Mastering Grid")
	public void addAndRemoveTaskInMasteringGridView() throws Exception{
		this.preCondition();
		Boolean exists = false;
		orderPage.placeOrder();
		masteringPage.addExistingTitle(orderConfig.getTitle3());
		exists = checkItemInMasteringGrid(orderConfig.getTitle3());
		Assert.assertTrue(exists);
		masteringPage.clickLinkText("remove task");
		Thread.sleep(500);
		masteringPage.clickAlert();
		Thread.sleep(15000);
		assertStatus(" ");
		masteringPage.clickLinkText("+ task");
		assertStatus("Default Status");
		masteringPage.clickLinkText("remove task");
		Thread.sleep(500);
		masteringPage.clickAlert();
		Thread.sleep(15000);
		assertStatus(" ");
		masteringPage.clickLinkText("+ final");
		assertStatus("Finished");
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
	public void assertStatus(String associated_task) {
		WebElement tbody = baseTest.getWebDriver().findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		for(WebElement tr:trs) {
			if(tr.findElements(By.className("normal-looking-link")).size() > 0) {
				if(tr.findElement(By.className("normal-looking-link")).getText().contains(orderConfig.getTitle3())) {
					JavascriptExecutor js = (JavascriptExecutor) baseTest.getWebDriver();
					if(tr.findElements(By.name("status")).size()>0) {
						WebElement status = tr.findElement(By.name("status"));
						js.executeScript(String.format("window.scrollTo(0, 0);", status.getLocation().getY()));
		               try {
		                   ((JavascriptExecutor) baseTest.getWebDriver()).executeScript(
		                           "arguments[0].scrollIntoView(true);", status);
		               } catch (Exception e) {
		            	   e.printStackTrace();
		               }
						String typeName = new Select(status).getFirstSelectedOption().getText().trim();
						Assert.assertEquals(typeName,associated_task.trim());
						break;
					}
					else {
						List<WebElement> tds = tr.findElements(By.tagName("td"));
						//System.out.println("associated task:");						
						WebElement status = tds.get(10);
						js.executeScript(String.format("window.scrollTo(0, 0);", status.getLocation().getY()));
						try {
			                   ((JavascriptExecutor) baseTest.getWebDriver()).executeScript(
			                           "arguments[0].scrollIntoView(true);", status);
			               } catch (Exception ex) {
			            	   ex.printStackTrace();
			               }
						//System.out.println(tds.get(10).getText());
						Assert.assertTrue(status.getText().contains(associated_task));
						break;
					}
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
