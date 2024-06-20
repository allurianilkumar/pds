package net.premieredigital.portal.tasksview;

import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderItemConfiguration;
import net.premieredigital.portal.pom.OrderItemPage;
import net.premieredigital.portal.pom.OrderPage;
import net.premieredigital.portal.pom.TasksConfiguration;
import net.premieredigital.portal.pom.TasksPage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sun.corba.se.spi.ior.MakeImmutable;

public class SaveDefaultSearchInTasksView {
	private TasksConfiguration tasksConfig;
	private TasksPage tasksPage;
	private OrderPage orderPage;
	private OrderConfiguration orderConfig;
	private BaseTest baseTest;
	public SaveDefaultSearchInTasksView(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void postCondition() {
//		orderPage.deleteOrder();
//		baseTest.getWebDriver().findElement(By.linkText("Clear Filter")).click();
//		fluentlyWait(new FluentWait<By> (By.linkText("Save as Default")));
//		baseTest.getWebDriver().findElement(By.linkText("Save as Default")).click();
//		if(new WebDriverWait(baseTest.getWebDriver(),5).until(ExpectedConditions.alertIsPresent())!=null) {
//			baseTest.getWebDriver().switchTo().alert().accept();
//		}
	}
	public void preCondition() throws Exception {
		tasksConfig = new TasksConfiguration();
		orderConfig = new OrderConfiguration();
		//System.out.println("in saveDefault..........");
		orderPage = new OrderPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), this.orderConfig);
		//System.out.println("back from order page instantiation");
		//new LoginPage(baseTest.getWebDriver(), baseTest.getPageConfiguration()).login();
		//System.out.println("back from login");
		this.tasksPage = new TasksPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), tasksConfig, orderConfig);
		//System.out.println("back from taskspage instantiation");
	}
	//@Test(groups = "ExcludeSafari",description="8.2 Save Default Search in Tasks View") 
	public void saveDefaultTaskSearch() throws Exception {
		this.preCondition();
		baseTest.setImplicitWait(5);
		orderPage.placeOrder();
		orderPage.addSameRequirementToAll();
//		orderPage.addRequirement();
		tasksPage.fillTasksPage();
		baseTest.fluentlyWait(new FluentWait<By> (By.linkText("Save as Default")));
		baseTest.getWebDriver().findElement(By.linkText("Save as Default")).click();
		Thread.sleep(1000);
		if(new WebDriverWait(baseTest.getWebDriver(),5).until(ExpectedConditions.alertIsPresent())!=null) {
			baseTest.getWebDriver().switchTo().alert().accept();
		}
		baseTest.getWebDriver().findElement(By.linkText("Clear Filter")).click();
		baseTest.fluentlyWait(new FluentWait<By> (By.name("provider_id")));
		Assert.assertTrue(new Select(baseTest.getWebDriver().findElement(By.name("provider_id"))).getFirstSelectedOption().getText().trim().equals(tasksConfig.getDefaultTaskProvider()));
		baseTest.getWebDriver().findElement(By.linkText("Apply Default")).click();
		baseTest.fluentlyWait(new FluentWait<By> (By.name("provider_id")));
		Assert.assertTrue(new Select(baseTest.getWebDriver().findElement(By.name("provider_id"))).getFirstSelectedOption().getText().trim().equals(orderConfig.getProviderName()));
		baseTest.getWebDriver().findElement(By.linkText("Clear Filter")).click();
		Thread.sleep(5000);
		baseTest.fluentlyWait(new FluentWait<By> (By.linkText("Save as Default")));
		baseTest.getWebDriver().findElement(By.linkText("Save as Default")).click();
		Thread.sleep(2000);
		if(new WebDriverWait(baseTest.getWebDriver(),5).until(ExpectedConditions.alertIsPresent())!=null) {
			baseTest.getWebDriver().switchTo().alert().accept();
			Thread.sleep(5000);
		}
		baseTest.getWebDriver().findElement(By.linkText("Apply Default")).click();
		baseTest.fluentlyWait(new FluentWait<By>(By.name("provider_id")));
		Assert.assertEquals(new Select(baseTest.getWebDriver().findElement(By.name("provider_id"))).getFirstSelectedOption().getText().trim(), tasksConfig.getDefaultTaskProvider());
		/*// 8.3 Add Task Notes
		/*boolean tableNoteVisible = tasksPage.clickFirstTableRowTaskNote();
		if(tableNoteVisible){
			Assert.assertTrue(tasksPage.testAddTaskNotes("text informarion"));
		}*/
	}
}
