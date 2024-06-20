package net.premieredigital.portal.orderitemsview;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderItemConfiguration;
import net.premieredigital.portal.pom.OrderItemPage;
import net.premieredigital.portal.pom.OrderPage;

public class AddNotesInOrderItems {
	private OrderItemConfiguration orderItemConfig;
	private OrderItemPage orderItemPage;
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private BaseTest baseTest;
	public AddNotesInOrderItems(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void postCondition() {
//        	orderPage.deleteOrder();
	}

	public void preCondition() throws Exception {
		this.orderItemConfig = new OrderItemConfiguration();
		this.orderConfig = new OrderConfiguration();
		this.orderPage = new OrderPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderConfig);
		this.orderItemPage = new OrderItemPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), new OrderItemConfiguration(), this.orderConfig);
	}
	// 5.13 Add Notes in Order Items
	public void addNotesInOrderItems() throws Exception {
        this.preCondition();
        orderPage.placeOrder();
        String sameAddTitle = orderConfig.getTitle3();
        String orderNumber = orderConfig.getOrderNumber();
        orderPage.addSameRequirementToAll();
        orderConfig.setOrderNumber();
        orderPage.placeOrder();
        orderConfig.setOrderNumber(orderNumber);
        orderPage.AddTitleRedeliver(sameAddTitle);
        orderPage.addSameRequirementToAll();
        orderItemPage.fillOrderItemPage();
        Thread.sleep(3000);
        boolean openModal = orderItemPage.clickFirstTableRowOrderItemNote(0);
        if(openModal){
            this.addTaskNoteForOrderItems("Hello, a Test Note for all orders","checked","unchecked",0,1);
            this.addTaskNoteForOrderItems("Hello, a Test Note for Order 1","unchecked","unchecked",0,2);
            this.addTaskNoteForOrderItems("Hello, this is for client for all orders","checked","checked",0,3);
            this.addTaskNoteForOrderItems("Hello, this is for client for Order 1","unchecked","checked",0,4);
            closeModal();
            Assert.assertTrue(orderItemPage.clickFirstTableRowOrderItemNote(1));
            orderItemPage.checkNoteMessage("Hello, a Test Note for all orders",0,"Hello, this for client for all orders",1);
            closeModal();
            new LoginPage(this.baseTest.getWebDriver(), baseTest.getPageConfiguration()).logout();
            new LoginPage(this.baseTest.getWebDriver(), baseTest.getPageConfiguration()).clientLogin();
            orderItemPage.fillOrderItemPage();
            Thread.sleep(3000);
            Assert.assertTrue(orderItemPage.clickFirstTableRowOrderItemNote(0));
            Thread.sleep(1000);
            orderItemPage.checkNoteMessage("Hello, this is for client for all orders",0,"Hello, this is for client for Order 1",1);
            closeModal();
            Thread.sleep(3000);
            Assert.assertTrue(orderItemPage.clickFirstTableRowOrderItemNote(1));
            Thread.sleep(1000);
            Assert.assertTrue(orderItemPage.checkNoteMessage("Hello, this is for client for all orders",0));
            closeModal();
            new LoginPage(this.baseTest.getWebDriver(), baseTest.getPageConfiguration()).logout();
            new LoginPage(this.baseTest.getWebDriver(), baseTest.getPageConfiguration()).login();
        }else{
            Assert.assertTrue(openModal);
        }
    }
    public void addTaskNoteForOrderItems(String noteInfo,String allOrdersCheckBox,String showClientCheckBox,int rowNoteIndex,int totalNotes) throws InterruptedException{
        orderItemPage.addTasksNotes(noteInfo,orderItemConfig.getMessageType(),allOrdersCheckBox,showClientCheckBox);
        Thread.sleep(3000);
        boolean openModal = orderItemPage.clickFirstTableRowOrderItemNote(rowNoteIndex);
        Assert.assertTrue(openModal);
        orderItemPage.selectViewInModal();
        Assert.assertTrue(orderItemPage.testingAddTaskNotes(totalNotes));
    }
    public void closeModal(){
    	baseTest.fluentlyWait(new FluentWait<By>(By.className("ui-icon-closethick")));
        baseTest.getWebDriver().findElement(By.className("ui-icon-closethick")).click();
    }
}
