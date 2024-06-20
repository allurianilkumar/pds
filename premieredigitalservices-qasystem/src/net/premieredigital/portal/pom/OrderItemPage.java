package net.premieredigital.portal.pom;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class OrderItemPage extends BasePage {	
	private PageConfiguration pageConfig;
	private OrderItemConfiguration orderItemConfig;
	private OrderConfiguration orderConfig;
	private boolean nextTr;
	public OrderItemPage(WebDriver driver, PageConfiguration pageConfig, OrderItemConfiguration orderItemConfig, OrderConfiguration orderConfig) {
		this.driver = driver;
		this.pageConfig = pageConfig;
		this.orderItemConfig = orderItemConfig;
		this.orderConfig = orderConfig;
		this.nextTr = false;
	}
	public void fillOrderItemPage() {
		driver.get(pageConfig.getRootURL() + "/home");
		assertPageTitle("Titles");
		fluentlyWait(new FluentWait<By>(By.name("search")));
//		driver.findElement(By.name("search")).clear();
		driver.findElement(By.name("search")).sendKeys(orderConfig.getTitle3());
		new Select(driver.findElement(By.name("service_id"))).selectByVisibleText(orderConfig.getService());
		new Select(driver.findElement(By.name("provider_id"))).selectByVisibleText(orderConfig.getProviderName());
		driver.findElement(By.name("search")).click();
		driver.findElement(By.name("search")).sendKeys(Keys.RETURN);
		waitUntilTableisLoaded();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addNoteForEachTask(int count) throws InterruptedException{
		Thread.sleep(2000);
		fluentlyWait(new FluentWait<By>(By.id("note-form")));
		WebElement form = driver.findElement(By.id("note-form"));
		 fluentlyWait(new FluentWait<By>(By.name("note_type")));
		 WebElement taskList = form.findElement(By.name("note_type"));
		 List<WebElement> option = taskList.findElements(By.tagName("option"));
		 if(count <= option.size()) {
			 String value = option.get(count-1).getText().trim();
			 if(count >=4 && count <=8){
				if(count ==4)
					value = "Feature";
				if(count == 5)
					value = "Trailer";
				if(count == 6)
					value = "CC (Feature - en-US)";
				if(count == 7)
					value = "Artwork";
				if(count == 8)
					value = "Metadata";
			 }
			 String message = "This note is for "+value+" from the main task note";
			 fluentlyWait(new FluentWait<By>(By.name("note_type")));
			 new Select(form.findElement(By.name("note_type"))).selectByVisibleText(value);
			 fluentlyWait(new FluentWait<By>(By.name("content")));
			 form.findElement(By.name("content")).sendKeys(message);
			 Thread.sleep(1000);
			 fluentlyWait(new FluentWait<By>(By.linkText("Add Note")));
			 form.findElement(By.linkText("Add Note")).click();
			 Thread.sleep(2000);
			 count+=1;
			 this.addNoteForEachTask(count);
		 }
	}
	public void selectView(){
		fluentlyWait(new FluentWait<By>(By.id("top_filters")));
        WebElement topFilters = driver.findElement(By.id("top_filters"));
        new Select(topFilters.findElement(By.name("view_selector"))).selectByVisibleText("All");
	}
    public void addTasksNotes(String note,String noteType,String applyforAllOrdersActive,String showClientAcive){
        WebElement form = driver.findElement(By.id("note-form"));
        scrollInToElement(form.findElement(By.name("note_type")));
        fluentlyWait(new FluentWait<By>(By.name("note_type")));
        new Select(form.findElement(By.name("note_type"))).selectByVisibleText(noteType);
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
    public boolean testingAddTaskNotes(int count){
    	fluentlyWait(new FluentWait<By>(By.id("table-notes")));
        WebElement table = driver.findElement(By.id("table-notes"));
        WebElement tbody = table.findElement(By.tagName("tbody"));
        List<WebElement> tr = tbody.findElements(By.tagName("tr"));
        if(tr.size() == count){
            return true;
        }else{
            return false;
        }
    }
    public void selectViewInModal(){
		fluentlyWait(new FluentWait<By>(By.id("top_filters")));
		WebElement topFilters = driver.findElement(By.id("top_filters"));
		new Select(topFilters.findElement(By.name("view_selector"))).selectByVisibleText(orderItemConfig.getView());
    }
	public void changeStatusForFeature(String status) {
		fillOrderItemPage();
		FluentWait<By> fluentWait = new FluentWait<By>(By.linkText("Feat"));
		fluentlyWait(fluentWait);
//		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Feat")));
		driver.findElement(By.linkText("Feat")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setImplicitWait(5);
		WebElement feature = driver.findElement(By.className("name-row"));
		if(feature.getText().contains("Feature")) {
			//System.out.println("in feature...");
			WebElement parent = feature.findElement(By.xpath(".."));
			new Select(parent.findElement(By.name("status"))).selectByVisibleText(status);
			clickSave(parent);
			parent.findElement(By.linkText("x")).click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public boolean clickFirstTableRowOrderItemNote(int rowIndex) throws InterruptedException{
		fluentlyWait(new FluentWait<By>(By.id("table_header")));
		WebElement tableHeader = driver.findElement(By.id("table_header"));
		scrollInToElement(tableHeader.findElement(By.tagName("tbody")));
		fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = tableHeader.findElement(By.tagName("tbody"));
		scrollInToElement(tbody.findElement(By.tagName("tr")));
		fluentlyWait(new FluentWait<By>(By.tagName("tr")));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		WebElement tr = trs.get(rowIndex);
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
	public boolean checkNoteMessage(String message1,int firstRowIndex,String message2,int secondRowIndex){
        fluentlyWait(new FluentWait<By>(By.id("top_filters")));
        WebElement topFilters = driver.findElement(By.id("top_filters"));
        new Select(topFilters.findElement(By.name("view_selector"))).selectByVisibleText(orderItemConfig.getView());
        WebElement table = driver.findElement(By.id("table-notes"));
        WebElement tbody = table.findElement(By.tagName("tbody"));
        List<WebElement> tr = tbody.findElements(By.tagName("tr"));
        List<WebElement> firstTd = tr.get(firstRowIndex).findElements(By.tagName("td"));
        List<WebElement> SecondTd = tr.get(secondRowIndex).findElements(By.tagName("td"));
        if(firstTd.get(3).getText().equals(message1) && SecondTd.get(3).getText().equals(message2)){
            return true;
        }else{
            return false;
        }
	}
	public boolean checkNoteMessage(String message,int firstRowIndex) throws InterruptedException{
		fluentlyWait(new FluentWait<By>(By.id("table-notes")));
        WebElement table = driver.findElement(By.id("table-notes"));
        fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
        WebElement tbody = table.findElement(By.tagName("tbody"));
        fluentlyWait(new FluentWait<By>(By.tagName("tr")));
        List<WebElement> tr = tbody.findElements(By.tagName("tr"));
        List<WebElement> firstTd = tr.get(firstRowIndex).findElements(By.tagName("td"));
        if(firstTd.get(3).getText().equals(message)){
            return true;
        }else{
            return false;
        }
	}
	public void changeStatusForTrailer() {
		driver.navigate().refresh();
		fillOrderItemPage();
		driver.navigate().refresh();
		driver.findElement(By.linkText("Trlr")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setImplicitWait(5);
		WebElement trailer = driver.findElement(By.className("name-row"));
		if(trailer.getText().contains("Trailer")) {
			WebElement parent = trailer.findElement(By.xpath(".."));
			new Select(parent.findElement(By.name("status"))).selectByVisibleText(orderItemConfig.getStatusForTrailer());
			clickSave(parent);
			parent.findElement(By.linkText("x")).click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//System.out.println("b");
	}
	public void changeStatusForArtwork() {
		driver.navigate().refresh();
		fillOrderItemPage();
		driver.navigate().refresh();
		driver.findElement(By.linkText("Art")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setImplicitWait(5);
		WebElement artwork = driver.findElement(By.className("name-row"));
		if(artwork.getText().contains("Artwork")) {
			WebElement parent = artwork.findElement(By.xpath(".."));
			//System.out.println("inside artwork.");
			//System.out.println(orderItemConfig.getStatusForArtwork());
			new Select(parent.findElement(By.name("status"))).selectByVisibleText(orderItemConfig.getStatusForArtwork());
			clickSave(parent);
			parent.findElement(By.linkText("x")).click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//System.out.println("c");
	}
	public void clickOnNoteForAllOrderItemGems(String link){
		fluentlyWait(new FluentWait<By>(By.linkText(link)));
		driver.findElement(By.linkText(link)).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		fluentlyWait(new FluentWait<By>(By.className("individual-status-target")));
		WebElement target = driver.findElement(By.className("individual-status-target"));
		fluentlyWait(new FluentWait<By>(By.className("name-row")));
		WebElement metadata = target.findElement(By.className("name-row"));
		WebElement parent = metadata.findElement(By.xpath(".."));
		fluentlyWait(new FluentWait<By>(By.linkText("notes")));
		parent.findElement(By.linkText("notes")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void cancelOrderItemPopUpWindow(){
		fluentlyWait(new FluentWait<By>(By.className("individual-status-target")));
		WebElement target = driver.findElement(By.className("individual-status-target"));
		fluentlyWait(new FluentWait<By>(By.className("name-row")));
		WebElement metadata = target.findElement(By.className("name-row"));
		WebElement parent = metadata.findElement(By.xpath(".."));
		parent.findElement(By.linkText("x")).click();
	}
	public void changeStatusForMetadata() {
		driver.navigate().refresh();
		fillOrderItemPage();
		driver.navigate().refresh();
		driver.findElement(By.linkText("Met")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setImplicitWait(5);
		//System.out.println("clicked Met");
		WebElement target = driver.findElement(By.className("individual-status-target"));
		WebElement metadata = target.findElement(By.className("name-row"));
		//System.out.println("found name-row");
		//System.out.println(metadata.getText());
		if(metadata.getText().contains("Metadata")) {
			//System.out.println("in metadata");
			WebElement parent = metadata.findElement(By.xpath(".."));
			new Select(parent.findElement(By.name("status"))).selectByVisibleText(orderItemConfig.getStatusForMetadata());
			clickSave(parent);
			parent.findElement(By.linkText("x")).click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//System.out.println("d");
	}
	public void clickSave(WebElement parent) {
		//System.out.println("in save");
		WebElement submit_section = parent.findElement(By.className("submit-section"));
		WebElement submit = submit_section.findElement(By.tagName("input"));
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript(String.format("window.scrollTo(0, 0);", submit.getLocation().getY()));
		submit.click();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("saved...");
	}
	public void searchTaskView() {
		driver.get(pageConfig.getRootURL() + "/tasks");
		assertPageTitle("Tasks");
		driver.findElement(By.name("show_hidden_tasks")).click();
		driver.findElement(By.name("search")).sendKeys(orderConfig.getTitle3());
		new Select(driver.findElement(By.name("service_id"))).selectByVisibleText(orderConfig.getService());
		new Select(driver.findElement(By.name("task_status"))).selectByVisibleText("All Statuses");
		waitUntilTableisLoaded();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateStatusInTasksView() {
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		for(WebElement tr:trs) {
			//System.out.println("{}{}{}");
			List<WebElement> tds = tr.findElements(By.tagName("td"));
			for(WebElement td:tds) {
				//System.out.println(td.getText());
				//System.out.println("..........");
				if(td.getText().trim().startsWith("Feature")) {
					//System.out.println("///////////");
					setStatus(tr);
					//System.out.println("???????");
					nextTr = true;
				}
				else if(td.getText().trim().startsWith("Trailer")) {
					//System.out.println("///////////");
					setStatus(tr);
					//System.out.println("???????");
					nextTr = true;
				}
				else if(td.getText().trim().startsWith("Artwork")) {
					//System.out.println("///////////");
					setStatus(tr);
					//System.out.println("???????");
					nextTr = true;
				}
				else if(td.getText().trim().startsWith("Metadata")) {
					//System.out.println("///////////");
					setStatus(tr);
					//System.out.println("???????");
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
	}
	public void setStatus(WebElement tr) {
		WebElement status = tr.findElement(By.className("status"));
		tr.findElement(By.className("status")).click();
		WebElement parent = status.findElement(By.xpath(".."));
		List<WebElement> uls = parent.findElements(By.tagName("ul"));
		List<WebElement> lis = uls.get(1).findElements(By.tagName("li"));
		for(WebElement li:lis) {
			if(li.getText().trim().equals("Default Status")) {
				li.click();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}
	public void clickFinish() {
//		driver.navigate().refresh();
		FluentWait<By> fluentWait = new FluentWait<By>(By.className("finish-button"));
		fluentlyWait(fluentWait);
//		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("finish-button")));
		driver.findElement(By.className("finish-button")).click();
		clickAlert();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		driver.navigate().refresh();
	}
	public void clickExternalRejection() {
//		driver.navigate().refresh();
//		WebElement actionButtons = driver.findElement(By.className("action-buttons"));
		FluentWait<By> fluentWait = new FluentWait<By>(By.className("rejection-button"));
		fluentlyWait(fluentWait);
//		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("rejection-button")));
		driver.findElement(By.className("rejection-button")).click();
		clickAlert();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void clickUrgent() {
//		driver.navigate().refresh();
//		WebElement actionButtons = driver.findElement(By.className("action-buttons"));
		FluentWait<By> fluentWait = new FluentWait<By>(By.className("urgent-button"));
		fluentlyWait(fluentWait);
//		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("urgent-button")));
		driver.findElement(By.className("urgent-button")).click();
		clickAlert();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void clickCancel() {
		FluentWait<By> fluentWait = new FluentWait<By>(By.className("archive-button"));
		fluentlyWait(fluentWait);
//		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("archive-button")));
		driver.findElement(By.className("archive-button")).click();
		clickAlert();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateActionButtons() {
		clickFinish();
		clickFinish();
		clickUrgent();
	}
	public void checkOrderItemViewGemStatus() throws InterruptedException {
		driver.get(pageConfig.getRootURL() + "/home");
		fluentlyWait(new FluentWait<By>(By.name("new_status")));
		new Select(driver.findElement(By.name("new_status"))).selectByVisibleText("All Outstanding");
		driver.findElement(By.name("search")).sendKeys(orderConfig.getTitle3());
		driver.findElement(By.name("search")).click();
		//driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
		driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
		waitUntilTableisLoaded();
		Thread.sleep(10000);
		fluentlyWait(new FluentWait<By>(By.linkText("Feat")));
		driver.findElement(By.linkText("Feat")).click();
	}
	public int getTotlaOrderItemResutl(){
		WebElement  titlesTotal = driver.findElement(By.className("titles"));
		WebElement layoutOptions= titlesTotal.findElement(By.className("layout_options"));
		WebElement itemCount = layoutOptions.findElement(By.className("item_count"));
		int totalOrderItems = Integer.parseInt(itemCount.getText());
		return totalOrderItems;
	}

	public void addNotesForFeature(){
		fillOrderItemPage();
		driver.navigate().refresh();
		FluentWait<By> fluentWait = new FluentWait<By>(By.linkText("Feat"));
		fluentlyWait(fluentWait);
//		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Feat")));
		driver.findElement(By.linkText("Feat")).click();
		setImplicitWait(5);
		WebElement feature = driver.findElement(By.className("name-row"));
		if(feature.getText().contains("Feature")) {
			//System.out.println("in feature...");				
			driver.findElement(By.linkText("notes")).click();
			driver.findElement(By.name("content")).sendKeys(orderItemConfig.getNoteFeature());
			driver.findElement(By.linkText("Add note")).click();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Assert.assertTrue(driver.findElement(By.className("note-content")).getText().equals(orderItemConfig.getNoteFeature()));
		}
	}

	public void addNotes(){
		fillOrderItemPage();
		driver.navigate().refresh();
		FluentWait<By> fluentWait = new FluentWait<By>(By.linkText("Feat"));
		fluentlyWait(fluentWait);
//		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Feat")));
		driver.findElement(By.linkText("Feat")).click();
		setImplicitWait(5);
		WebElement feature = driver.findElement(By.className("name-row"));
		if(feature.getText().contains("Feature")) {
			//System.out.println("in feature...");				
			driver.findElement(By.linkText("notes")).click();
			driver.findElement(By.name("content")).sendKeys(orderItemConfig.getNoteFeature());
			driver.findElement(By.linkText("Add note")).click();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Assert.assertTrue(driver.findElement(By.className("note-content")).getText().equals(orderItemConfig.getNoteFeature()));
		}
	}
}
