package net.premieredigital.portal.tasksview;

import java.util.List;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderItemConfiguration;
import net.premieredigital.portal.pom.OrderItemPage;
import net.premieredigital.portal.pom.OrderPage;
import net.premieredigital.portal.pom.TasksConfiguration;
import net.premieredigital.portal.pom.TasksPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdateStatusInTasksView {
	private TasksConfiguration tasksConfig;
	private TasksPage tasksPage;
	private OrderPage orderPage;
	private OrderConfiguration orderConfig;
	private BaseTest baseTest;
	public UpdateStatusInTasksView(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void postCondition() {
//		orderPage.deleteOrder();
	}
	
	public void preCondition() throws Exception {
		tasksConfig = new TasksConfiguration();
		orderConfig = new OrderConfiguration();
		orderPage = new OrderPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), orderConfig);
		//new LoginPage(baseTest.getWebDriver(), baseTest.getPageConfiguration()).login();
		this.tasksPage = new TasksPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.tasksConfig, orderConfig);
	}
	//@Test(groups = "ExcludeSafari",description="8.1 Updating Statuses in Tasks View")
	public void updateStatus() throws Exception {
		this.preCondition();
		orderPage.placeOrder();
		orderPage.addSameRequirementToAll();
//		orderPage.addRequirement();
		tasksPage.fillTasksPage();
		tasksPage.updateStatusInTasksView();
		Thread.sleep(2000);
		tasksPage.fillTasksPage();
		WebElement tbody = baseTest.getWebDriver().findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		for(WebElement tr:trs) {
			//System.out.println("{}{}{}");
			List<WebElement> tds = tr.findElements(By.tagName("td"));
			for(WebElement td:tds) {
				if(td.getText().trim().startsWith("Feature")) {
					tr = td.findElement(By.xpath(".."));
//					Assert.assertTrue(tr.findElement(By.className("status")).getText().trim().equals(tasksConfig.getTaskStatus()));
					Assert.assertEquals(tr.findElement(By.className("status")).getText().trim(), tasksConfig.getTaskStatus());
					Assert.assertEquals(new Select(tr.findElement(By.name("assignee"))).getFirstSelectedOption().getText().trim(), tasksConfig.getAssignedTo());
					break;
				}
				else {
					continue;
				}
			}
		}
	}
}

