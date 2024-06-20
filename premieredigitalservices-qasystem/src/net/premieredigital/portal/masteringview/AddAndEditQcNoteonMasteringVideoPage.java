package net.premieredigital.portal.masteringview;
import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.MasteringConfiguration;
import net.premieredigital.portal.pom.MasteringPage;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderItemConfiguration;
import net.premieredigital.portal.pom.OrderItemPage;
import net.premieredigital.portal.pom.OrderPage;
import net.premieredigital.portal.pom.TasksConfiguration;
import net.premieredigital.portal.pom.TasksPage;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
public class AddAndEditQcNoteonMasteringVideoPage {
	private TasksConfiguration tasksConfig;
	private OrderItemConfiguration orderItemConfig;
	private OrderItemPage orderItemPage;
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private MasteringPage masteringPage;
	private MasteringConfiguration masteringConfig;
	private WebElement parent;
	private TasksPage tasksPage;
	private BaseTest baseTest;
	public AddAndEditQcNoteonMasteringVideoPage(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void postCondition() {
//		orderPage.deleteOrder();
	}
	
	public void preCondition() throws Exception{
		tasksConfig = new TasksConfiguration();
		this.parent = null;
		masteringConfig = new MasteringConfiguration();
		orderItemConfig = new OrderItemConfiguration();
		orderConfig = new OrderConfiguration();
		orderPage = new OrderPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderConfig);
		//new LoginPage(this.webDriver.get(), this.pageConfig).login();
		this.orderItemPage = new OrderItemPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderItemConfig, this.orderConfig);
		this.tasksPage = new TasksPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), tasksConfig, orderConfig);
		this.masteringPage = new MasteringPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderItemConfig, this.orderConfig, this.masteringConfig);
	}
	//@Test(groups="ExcludeSafari", description="4.10 Add and Edit QC Note on Mastering Video Page")
	public void addAndEditQcNoteonMasteringVideoPage() throws Exception{
		this.preCondition();
		tasksPage.getTaskskUrl();
		// 4.9 User To Navigate to Video Archive Page and Mastering Video Page
		//masteringPage.navigateVideoArchivePage();
		// 4.10 Add and Edit QC Note on Mastering Video Page
		Thread.sleep(2000);
		masteringPage.getMastertingVideoUrl();
		Thread.sleep(2000);
		boolean addTest = masteringPage.addQcNote();
		boolean editTest = masteringPage.EditQcNote();
		boolean deleteTest = masteringPage.deleteQcNote();
		Assert.assertTrue(addTest);
		Assert.assertTrue(editTest);
		Assert.assertTrue(deleteTest);
	}
}
