package net.premieredigital.portal.pom;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import com.google.common.base.Predicate;

public class TasksPage extends BasePage{	
		
		private PageConfiguration pageConfig;
		private TasksConfiguration tasksConfig;
		private OrderConfiguration orderConfig;
		public TasksPage(WebDriver driver, PageConfiguration pageConfig, TasksConfiguration tasksConfig, OrderConfiguration orderConfig) {
			this.driver = driver;
			this.pageConfig = pageConfig;
			this.tasksConfig = tasksConfig;
			this.orderConfig = orderConfig;
		}
		public void fillTasksPage() throws InterruptedException {
			driver.get(pageConfig.getRootURL() + "/home");
			fluentlyWait(new FluentWait<By>(By.linkText("Tasks")));
			driver.findElement(By.linkText("Tasks")).click();
			Thread.sleep(5000);
			fluentlyWait(new FluentWait<By>(By.name("search")));
			new Select(driver.findElement(By.name("service_id"))).selectByVisibleText(orderConfig.getService());
			waitUntilTableisLoaded();
			new Select(driver.findElement(By.name("provider_id"))).selectByVisibleText(orderConfig.getProviderName());
			waitUntilTableisLoaded();
			new Select(driver.findElement(By.name("task_status"))).selectByVisibleText("All Statuses");
			waitUntilTableisLoaded();
			driver.findElement(By.name("show_hidden_tasks")).click();
			waitUntilTableisLoaded();
			driver.findElement(By.name("search")).clear();
			driver.findElement(By.name("search")).sendKeys(orderConfig.getTitle3());
			driver.findElement(By.name("search")).click();
			driver.findElement(By.name("search")).sendKeys(Keys.RETURN);
			waitUntilTableisLoaded();
		}
		public boolean clickFirstTableRowTaskNote() throws InterruptedException{
			driver.get(pageConfig.getRootURL() + "/tasks/");
			Thread.sleep(2000);
			scrollInToElement(driver.findElement(By.tagName("tbody")));
			fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
			WebElement tbody = driver.findElement(By.tagName("tbody"));
			scrollInToElement(tbody.findElement(By.tagName("tr")));
			fluentlyWait(new FluentWait<By>(By.tagName("tr")));
			List<WebElement> trs = tbody.findElements(By.tagName("tr"));
			WebElement tr = trs.get(0);
			fluentlyWait(new FluentWait<By>(By.tagName("td")));
			List<WebElement> tds = tr.findElements(By.tagName("td"));
			if(tds.size()>0){
				Thread.sleep(3000);
				scrollInToElement(tds.get(0).findElement(By.tagName("img")));
				fluentlyWait(new FluentWait<By>(By.tagName("img")));
				WebElement image = tds.get(0).findElement(By.tagName("img"));
				image.click();
				return true;
			}else{
				return false;
			}
		}
	    public void addTasksNotes(String note,String type,String applyforAllOrdersActive,String showClientAcive){
	    	scrollInToElement(driver.findElement(By.id("note-form")));
	    	fluentlyWait(new FluentWait<By>(By.id("note-form")));
	    	WebElement form = driver.findElement(By.id("note-form"));
	        scrollInToElement(form.findElement(By.name("note_type")));
	        fluentlyWait(new FluentWait<By>(By.name("note_type")));
	        new Select(form.findElement(By.name("note_type"))).selectByVisibleText(type);
	        scrollInToElement(form.findElement(By.name("content")));
	        form.findElement(By.name("content")).sendKeys(note);
	        scrollInToElement(form.findElement(By.name("apply_note_to_all")));
	        WebElement applyNoteToAll = form.findElement(By.name("apply_note_to_all"));
	        if(applyforAllOrdersActive.equals("checked") && !applyNoteToAll.isSelected()){
	        	form.findElement(By.name("apply_note_to_all")).click();
	        }
	        if(applyforAllOrdersActive.equals("unchecked") && applyNoteToAll.isSelected()){
	        	form.findElement(By.name("apply_note_to_all")).click();
	        }
	        scrollInToElement(form.findElement(By.name("show_client")));
	        WebElement showClient = form.findElement(By.name("show_client"));
	        if(showClientAcive.equals("checked") && !showClient.isSelected()){
	        	form.findElement(By.name("show_client")).click();
	        }
	        if(showClientAcive.equals("unchecked") && showClient.isSelected()){
	        	form.findElement(By.name("show_client")).click();
	        }
	        scrollInToElement(form.findElement(By.linkText("Add Note")));
	        form.findElement(By.linkText("Add Note")).click();
	        fluentlyWait(new FluentWait<By>(By.className("ui-icon-closethick")));
	        driver.findElement(By.className("ui-icon-closethick")).click();
	    }
        public boolean testingAddTaskNotes(String note,int rowNoteIndex) throws InterruptedException{
        	Thread.sleep(4000);
            fluentlyWait(new FluentWait<By>(By.id("top_filters")));
            WebElement topFilters = driver.findElement(By.id("top_filters"));
            fluentlyWait(new FluentWait<By>(By.name("view_selector")));
            new Select(topFilters.findElement(By.name("view_selector"))).selectByVisibleText(tasksConfig.getView());
            fluentlyWait(new FluentWait<By>(By.name("status_selector")));
            new Select(topFilters.findElement(By.name("status_selector"))).selectByVisibleText(tasksConfig.getStatus());
            fluentlyWait(new FluentWait<By>(By.name("client_selector")));
            new Select(topFilters.findElement(By.name("client_selector"))).selectByVisibleText(tasksConfig.getClientNotes());
            fluentlyWait(new FluentWait<By>(By.id("table-notes")));
            WebElement table = driver.findElement(By.id("table-notes"));
            if( table.findElements(By.tagName("tbody")).size() == 0 )
            Thread.sleep(5000);
            fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
            WebElement tbody = table.findElement(By.tagName("tbody"));
            if( tbody.findElements(By.tagName("tr")).size() == 0 )
            Thread.sleep(5000);
            fluentlyWait(new FluentWait<By>(By.tagName("tr")));
            List<WebElement> tr = tbody.findElements(By.tagName("tr"));
            fluentlyWait(new FluentWait<By>(By.tagName("td")));
            List<WebElement> td = tr.get(rowNoteIndex).findElements(By.tagName("td"));
            if(td.get(3).getText().equals(note)){
                return true;
            }else{
                return false;
            }
        }
		public boolean testAddTaskNotes(String someText){
			fluentlyWait(new FluentWait<By>(By.name("content")));
			driver.findElement(By.name("content")).sendKeys(someText);
			fluentlyWait(new FluentWait<By>(By.className("submit-note")));
			WebElement submit = driver.findElement(By.className("submit-note"));
			submit.click();
			List<WebElement> list = driver.findElements(By.className("note-content"));
			String result = list.get(0).getText().trim().toString();
			fluentlyWait(new FluentWait<By>(By.className("remove")));
			driver.findElement(By.className("remove")).click();
			boolean popUpclose = driver.findElement(By.className("notes-popup")).isDisplayed();
			//System.out.println("pop up visible"+popUpclose);
			//System.out.println("result value"+result.equals(someText));
			if(result.equals(someText) &&  (popUpclose == false) ){
				return true;
			}else{
				return false;
			}
		}
		public void updateStatusInTasksView() throws InterruptedException {
			WebElement tbody = driver.findElement(By.tagName("tbody"));
			List<WebElement> trs = tbody.findElements(By.tagName("tr"));
			for(WebElement tr:trs) {
				List<WebElement> tds = tr.findElements(By.tagName("td"));
				for(WebElement td:tds) {
					if(td.getText().trim().startsWith("Feature")) {
						setStatus(tr);
						setAssignee(tr);
						break;
					}
					
					else {
						continue;
					}
				}
			}
		}
		public void getTaskskUrl() throws InterruptedException{
			driver.get(pageConfig.getRootURL() + "/tasks/");
			List<WebElement> link = driver.findElements(By.className("normal-looking-link"));
			link.get(1).click();
			Thread.sleep(2000);
			List<WebElement> nextPageLink = driver.findElements(By.className("normal-looking-link"));
			nextPageLink.get(0).click();
			Thread.sleep(2000);
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
		}
		public void setStatus(WebElement tr) throws InterruptedException {
			WebElement status = tr.findElement(By.className("status"));
			tr.findElement(By.className("status")).click();
			Thread.sleep(5000);
			WebElement parent = status.findElement(By.xpath(".."));
			List<WebElement> uls = parent.findElements(By.tagName("ul"));
			List<WebElement> lis = uls.get(1).findElements(By.tagName("li"));
			for(WebElement li:lis) {
				if(li.getText().trim().equals("Default Status")) {
					li.click();
					Thread.sleep(5000);
					System.out.println("status set");
					break;
				}
			}
		}
		public void setAssignee(WebElement tr) throws InterruptedException {
			WebElement assigned_to = tr.findElement(By.className("assigned-to"));			
			new Select(assigned_to.findElement(By.name("assignee"))).selectByVisibleText(tasksConfig.getAssignedTo());
			Thread.sleep(5000);
		}
}

