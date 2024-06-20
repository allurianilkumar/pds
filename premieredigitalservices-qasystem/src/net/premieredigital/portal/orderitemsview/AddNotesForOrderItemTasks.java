package net.premieredigital.portal.orderitemsview;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderItemConfiguration;
import net.premieredigital.portal.pom.OrderItemPage;
import net.premieredigital.portal.pom.OrderPage;

public class AddNotesForOrderItemTasks {
	private OrderItemConfiguration orderItemConfig;
	private OrderItemPage orderItemPage;
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private BaseTest baseTest;
	public AddNotesForOrderItemTasks(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void postCondition() {
//        orderPage.deleteOrder();
	}
	public void preCondition() throws Exception {
		this.orderItemConfig = new OrderItemConfiguration();
		this.orderConfig = new OrderConfiguration();
		this.orderPage = new OrderPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderConfig);
		this.orderItemPage = new OrderItemPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), new OrderItemConfiguration(), this.orderConfig);
	}
	// 5.14 Add Notes for Order Item tasks
	public void addNotesForOrderItemTasks() throws Exception{
		this.preCondition();
		orderPage.placeOrder();
		orderPage.addSameRequirementToAll();
		orderItemPage.fillOrderItemPage();
		Thread.sleep(3000);
		boolean openModal = orderItemPage.clickFirstTableRowOrderItemNote(0);
		if(openModal){
			orderItemPage.addNoteForEachTask(2);
			orderItemPage.selectView();
			baseTest.getWebDriver().findElement(By.className("ui-icon-closethick")).click();
			baseTest.getWebDriver().navigate().refresh();
			baseTest.fluentlyWait(new FluentWait<By>(By.linkText(orderConfig.getTitle3()+" (en-US)")));
			baseTest.getWebDriver().findElement(By.linkText(orderConfig.getTitle3()+" (en-US)")).click();
			Thread.sleep(1000);
			baseTest.fluentlyWait(new FluentWait<By>(By.linkText("View all notes")));
			baseTest.getWebDriver().findElement(By.linkText("View all notes")).click();
			Assert.assertTrue(orderItemPage.testingAddTaskNotes(10));
			baseTest.fluentlyWait(new FluentWait<By>(By.className("ui-icon-closethick")));
	        baseTest.getWebDriver().findElement(By.className("ui-icon-closethick")).click();
        }else{
            Assert.assertTrue(openModal);
        }
    }
}
