package net.premieredigital.portal.tasksview;

import org.testng.Assert;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderPage;
import net.premieredigital.portal.pom.TasksConfiguration;
import net.premieredigital.portal.pom.TasksPage;

public class AddTaskNotes {
    private TasksConfiguration tasksConfig;
    private TasksPage tasksPage;
    private OrderPage orderPage;
    private OrderConfiguration orderConfig;
    private BaseTest baseTest;
	public AddTaskNotes(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
    public void postCondition() {
    }
    public void preCondition() throws Exception {
        this.tasksConfig = new TasksConfiguration();
        this.orderConfig = new OrderConfiguration();
        this.orderPage = new OrderPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), this.orderConfig);
        this.tasksPage = new TasksPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.tasksConfig, this.orderConfig);
    }
    // 8.3 Add Task Notes
    public void addTaskNotes()throws Exception {
    	this.preCondition();
        boolean tableNoteVisible = tasksPage.clickFirstTableRowTaskNote();
        if(tableNoteVisible){
        	Thread.sleep(2000);
        	tasksPage.addTasksNotes(tasksConfig.getMessage(),"Feature","checked","unchecked");
        	tableNoteVisible = tasksPage.clickFirstTableRowTaskNote();
        	if(tableNoteVisible){
        		boolean result = tasksPage.testingAddTaskNotes(tasksConfig.getMessage(),0);
        		Assert.assertTrue(result);
        	}else{
        		Assert.assertTrue(tableNoteVisible);
        	}
        	Thread.sleep(3000);
        }else{
        	Assert.assertTrue(tableNoteVisible);
        }
    }
}