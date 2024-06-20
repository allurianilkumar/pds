package net.premieredigital.portal.pom;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.google.common.base.Predicate;

public class MasteringPage extends BasePage{
	private WebElement parent;
	private OrderConfiguration orderConfig;
	private OrderItemConfiguration orderItemConfig;
	private MasteringConfiguration masteringConfig;
	private PageConfiguration pageConfig;
	public MasteringPage(WebDriver driver, PageConfiguration pageConfig, OrderItemConfiguration orderItemConfig, OrderConfiguration orderConfig, MasteringConfiguration masteringConfig) {
		this.driver = driver;
		this.pageConfig = pageConfig;
		this.orderItemConfig = orderItemConfig;
		this.orderConfig = orderConfig;
		this.masteringConfig = masteringConfig;
		this.parent = null;
	}
	public String getPageURL() {
		return pageConfig.getRootURL() + "/mastering";
	}
	public void addExistingTitle(String title) {
//		this.driver.get(getPageURL() + "/mastering/?find_view=0");
		try {
		driver.get(getPageURL());
		Thread.sleep(3000);
		assertPageTitle("Mastering");
		fluentlyWait(new FluentWait<By>(By.linkText("Add")));
		driver.findElement(By.linkText("Add")).click();
		assertPageTitle("Add Video");
		//System.out.println("found add video after clicking Add");
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addTitle(title);
	}
	public void addTitle(String title) {
		fluentlyWait(new FluentWait<By> (By.name("search")));
		WebElement search = driver.findElement(By.name("search"));
		 try {
             ((JavascriptExecutor) driver).executeScript(
                     "arguments[0].click();", search);
         } catch (Exception ex) {
        	 ex.printStackTrace();
         }
//		driver.findElement(By.name("search")).clear();
//		fluentlyWait(new FluentWait<By> (By.name("search")));
//		driver.findElement(By.name("search")).sendKeys(title);
		search.clear();
		search.sendKeys(title);
//		WebElement select = driver.findElement(By.name("provider_id"));
//		scrollInToElement(select);
//		
//		new Select(driver.findElement(By.name("provider_id"))).selectByVisibleText("Any Provider");
//		new Select(select).selectByVisibleText("Any Provider"); 
		driver.findElement(By.className("grey-button")).click();
		assertPageTitle(title);
		//System.out.println("found title " + title);
		//waitTillNotNull("titles");
		try {
			Thread.sleep(4000);
			if(driver.findElements(By.className("title-entry")).size() == 0)
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fluentlyWait(new FluentWait<By>(By.className("title-entry")));
		WebElement parent = driver.findElement(By.className("title-entry"));
//		fluentlyWait(new FluentWait<By> (By.className("name")));
//		WebElement name = driver.findElement(By.className("name"));
//		WebElement parent = name.findElement(By.xpath(".."));
		//System.out.println("parentTag:" + parent.getTagName());
        //System.out.println("video type:" + masteringConfig.getVideoType());
		new Select(parent.findElement(By.name("video_type"))).selectByVisibleText(masteringConfig.getVideoType());
		new Select(parent.findElement(By.name("video_locale"))).selectByVisibleText(masteringConfig.getAssetLanguage());
		new Select(parent.findElement(By.name("processing_stage"))).selectByVisibleText(masteringConfig.getStage());
		new Select(parent.findElement(By.name("format"))).selectByVisibleText(masteringConfig.getVideoFormat());
		new Select(parent.findElement(By.name("resolution"))).selectByVisibleText(masteringConfig.getResolution());
		new Select(parent.findElement(By.name("vasp"))).selectByVisibleText(masteringConfig.getVASP());
		new Select(parent.findElement(By.name("pasp"))).selectByVisibleText(masteringConfig.getPASP());
		fluentlyWait(new FluentWait<By> (By.name("add")));
		try {
			Thread.sleep(500);
			parent.findElement(By.name("add")).click();
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fluentlyWait(new FluentWait<By> (By.className("grey-button")));
		driver.findElement(By.className("grey-button")).click();
		//System.out.println("added the item to the grid...");
//		fluentlyWait(new FluentWait<By> (By.name("title_id")));
//		List<WebElement> names = driver.findElements(By.className("name"));
//		//System.out.println(names.get(0).getText());
//		for(WebElement name:names) {
//			String linkText = name.getText();
//			if(linkText.contains(title)) {
//				WebElement parent = name.findElement(By.xpath(".."));
//				System.out.println("parentTag:" + parent.getTagName());
//		        System.out.println("video type:" + masteringConfig.getVideoType());
//				new Select(parent.findElement(By.name("video_type"))).selectByVisibleText(masteringConfig.getVideoType());
//				new Select(parent.findElement(By.name("video_locale"))).selectByVisibleText(masteringConfig.getAssetLanguage());
//				new Select(parent.findElement(By.name("processing_stage"))).selectByVisibleText(masteringConfig.getStage());
//				new Select(parent.findElement(By.name("format"))).selectByVisibleText(masteringConfig.getVideoFormat());
//				new Select(parent.findElement(By.name("resolution"))).selectByVisibleText(masteringConfig.getResolution());
//				new Select(parent.findElement(By.name("vasp"))).selectByVisibleText(masteringConfig.getVASP());
//				new Select(parent.findElement(By.name("pasp"))).selectByVisibleText(masteringConfig.getPASP());
//				parent.findElement(By.name("add")).click();
//				Thread.sleep(10000);
//				System.out.println("added the item to the grid...");
//				break;
//			}
//			else {
//				continue;
//			}
//		}
	}
	public void addNewTitle() {
		fluentlyWait(new FluentWait<By>(By.linkText("Add new title")));
		driver.findElement(By.linkText("Add new title")).click();
		fluentlyWait(new FluentWait<By> (By.name("provider")));
		masteringConfig.setTitle(orderConfig.getOrderNumber());
		driver.findElement(By.name("provider")).sendKeys(masteringConfig.getProvider());
		try {
			Thread.sleep(5000);
			driver.findElement(By.name("provider")).sendKeys(Keys.TAB);
			Thread.sleep(2000);
			driver.findElement(By.name("title")).sendKeys(masteringConfig.getTitle());
			driver.findElement(By.name("language")).sendKeys(masteringConfig.getLanguage());
			Thread.sleep(5000);
			driver.findElement(By.name("language")).sendKeys(Keys.TAB);
			Thread.sleep(2000);
			WebElement elm = driver.findElement(By.className("submit-section"));
			//System.out.println("found fancy blue button");
			WebElement element = elm.findElement(By.className("fancy-blue-button"));
			scrollInToElement(element);
//			JavascriptExecutor js=(JavascriptExecutor) driver;
//			js.executeScript(String.format("window.scrollTo(0, 0);", element.getLocation().getY()));
			element.click();
//			driver.navigate().refresh();
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void fillSearchCriteria(String provider) {
		driver.get(getPageURL());
		try {
			Thread.sleep(5000);
			assertPageTitle("Mastering");
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		fluentlyWait(new FluentWait<By>(By.name("search")));
		driver.findElement(By.name("search")).sendKeys(orderConfig.getTitle3());
		new Select(driver.findElement(By.name("provider_id"))).selectByVisibleText(provider);
		new Select(driver.findElement(By.name("language"))).selectByVisibleText(masteringConfig.getTitleLanguage());
		new Select(driver.findElement(By.name("asset_language"))).selectByVisibleText(masteringConfig.getAssetLanguageForSearch());
		new Select(driver.findElement(By.name("video_type"))).selectByVisibleText(masteringConfig.getVideoType());
		new Select(driver.findElement(By.name("pasp"))).selectByVisibleText(masteringConfig.getPASP());
		new Select(driver.findElement(By.name("vasp"))).selectByVisibleText(masteringConfig.getVASP());
		new Select(driver.findElement(By.name("resolution"))).selectByVisibleText(masteringConfig.getResolution());
		new Select(driver.findElement(By.name("format"))).selectByVisibleText(masteringConfig.getVideoFormat());
		driver.findElement(By.className("grey-button")).click();
		assertPageTitle(orderConfig.getTitle3());
		//System.out.println("found title:" + orderConfig.getTitle3());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteTitle() {
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		for(WebElement tr:trs) {
			if(tr.findElements(By.className("normal-looking-link")).size() > 0) {
				if(tr.findElement(By.className("normal-looking-link")).getText().contains(orderConfig.getTitle3())) {
					tr.findElement(By.linkText("x")).click();
					clickAlert();
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
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
	public void clearFields() {
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		for(WebElement tr:trs) {
			if(tr.findElements(By.className("normal-looking-link")).size() > 0) {
				if(tr.findElement(By.className("normal-looking-link")).getText().contains(orderConfig.getTitle3())) {
					tr.findElement(By.name("clear")).click();
					clickAlert();
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
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
	public void modifyFieldsAndSave() {
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		for(WebElement tr:trs) {
			if(tr.findElements(By.className("normal-looking-link")).size() > 0) {
				if(tr.findElement(By.className("normal-looking-link")).getText().contains(orderConfig.getTitle3())) {
					new Select(tr.findElement(By.name("update_video_type"))).selectByVisibleText(masteringConfig.getModifiedVideoType());
					new Select(tr.findElement(By.name("update_processing_stage"))).selectByVisibleText(masteringConfig.getModifiedStage());
					new Select(tr.findElement(By.name("update_resolution"))).selectByVisibleText(masteringConfig.getModifiedResolution());
					new Select(tr.findElement(By.name("update_format"))).selectByVisibleText(masteringConfig.getModifiedVideoFormat());
					tr.findElement(By.name("save")).click();
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
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
	public void fillModifiedSearchCriteria() {
		driver.get(getPageURL());
		try {
			Thread.sleep(5000);
			assertPageTitle("Mastering");
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		fluentlyWait(new FluentWait<By> (By.name("search")));
		driver.findElement(By.name("search")).clear();
		driver.findElement(By.name("search")).sendKeys(orderConfig.getTitle3());
		new Select(driver.findElement(By.name("provider_id"))).selectByVisibleText(masteringConfig.getProvider());
		new Select(driver.findElement(By.name("language"))).selectByVisibleText(masteringConfig.getTitleLanguage());
		new Select(driver.findElement(By.name("asset_language"))).selectByVisibleText(masteringConfig.getAssetLanguageForSearch());
		new Select(driver.findElement(By.name("video_type"))).selectByVisibleText(masteringConfig.getModifiedVideoType());
		new Select(driver.findElement(By.name("pasp"))).selectByVisibleText(masteringConfig.getPASP());
		new Select(driver.findElement(By.name("vasp"))).selectByVisibleText(masteringConfig.getVASP());
		new Select(driver.findElement(By.name("resolution"))).selectByVisibleText(masteringConfig.getModifiedResolution());
		new Select(driver.findElement(By.name("format"))).selectByVisibleText(masteringConfig.getModifiedVideoFormat());
		driver.findElement(By.className("grey-button")).click();
		assertPageTitle(orderConfig.getTitle3());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void clickLinkText(String link) {
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		for(WebElement tr:trs) {
			if(tr.findElements(By.className("normal-looking-link")).size() > 0) {
				if(tr.findElement(By.className("normal-looking-link")).getText().contains(orderConfig.getTitle3())) {
					tr.findElement(By.linkText(link)).click();
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
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
	public void modifyTitleView() {
		fluentlyWait(new FluentWait<By>(By.name("provider")));
		driver.findElement(By.name("provider")).clear();
		driver.findElement(By.name("provider")).sendKeys(masteringConfig.getModifiedProvider());
		driver.findElement(By.name("provider")).sendKeys(Keys.ENTER);
		try {
			Thread.sleep(1000);
			driver.findElement(By.name("language")).clear();
			driver.findElement(By.name("language")).sendKeys(masteringConfig.getModifiedLanguage());
			driver.findElement(By.name("language")).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			WebElement button = driver.findElement(By.className("save-cancel-buttons"));
			button.findElement(By.className("fancy-blue-button")).click();
//			clickAlert();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void modifyStatusAndRenderTaskView() {
		fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		fluentlyWait(new FluentWait<By>(By.tagName("tr")));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		for(WebElement tr:trs) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			fluentlyWait(new FluentWait<By>(By.className("normal-looking-link")));
			if(tr.findElements(By.className("normal-looking-link")).size() > 0) {
				if(tr.findElement(By.className("normal-looking-link")).getText().contains(orderConfig.getTitle3())) {
					try {
					scrollInToElement(tr.findElement(By.name("status")));
					new Select(tr.findElement(By.name("status"))).selectByVisibleText(masteringConfig.getStatus());
					scrollInToElement(tr.findElement(By.linkText("view task")));
					fluentlyWait(new FluentWait<By>(By.linkText("view task")));
					tr.findElement(By.linkText("view task")).click();
					Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
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
	public void modifyStatusInTaskView() {
		List<WebElement> trs = null;
		List<WebElement> tds = null;
		fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		parent = tbody;
		FluentWait<By> fluentWait = new FluentWait<By>(By.tagName("tr"));
        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(4000, TimeUnit.MILLISECONDS);
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
		WebElement status = tds.get(7);
		status.click();
		try {
			Thread.sleep(1000);
			List<WebElement> uls = status.findElements(By.tagName("ul"));
			scrollInToElement(uls.get(1));
			List<WebElement> lis = uls.get(1).findElements(By.tagName("li"));
			for(WebElement li:lis) {
				if(li.getText().equals(masteringConfig.getModifiedStatus())) {
					li.click();
					Thread.sleep(2000);
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void modifyStatus(String status) {
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		for(WebElement tr:trs) {
			if(tr.findElements(By.className("normal-looking-link")).size() > 0) {
				if(tr.findElement(By.className("normal-looking-link")).getText().contains(orderConfig.getTitle3())) {
					scrollInToElement(tr.findElement(By.name("status")));
					new Select(tr.findElement(By.name("status"))).selectByVisibleText(status);
					try {
						Thread.sleep(2000);
						tr.findElement(By.name("save")).click();
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
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
	public void getMastertingVideoUrl(){
		fluentlyWait(new FluentWait<By>(By.className("header")));
		List<WebElement> header = driver.findElements(By.className("header"));
		List<WebElement> list= header.get(3).findElements(By.className("show-section"));
		String checkClickAction = list.get(0).getText();
		if(checkClickAction.equals("+"))
		list.get(0).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean addQcNote() throws InterruptedException{
		fluentlyWait(new FluentWait<By>(By.name("new_qc")));
		WebElement newForm= driver.findElement(By.name("new_qc"));
		List<WebElement> beforeRowCount = driver.findElements(By.className("grid_row"));
		if(newForm.findElements(By.name("start_time")).size() == 0)
		Thread.sleep(5000);
		fluentlyWait(new FluentWait<By>(By.name("start_time")));
		newForm.findElement(By.name("start_time")).sendKeys(masteringConfig.getStartTime());
		fluentlyWait(new FluentWait<By>(By.name("end_time")));
		newForm.findElement(By.name("end_time")).sendKeys(masteringConfig.getEndTime());
		fluentlyWait(new FluentWait<By>(By.id("id_qc_issue")));
		newForm.findElement(By.id("id_qc_issue")).sendKeys(masteringConfig.getIssue());
		fluentlyWait(new FluentWait<By>(By.id("id_severity")));
		new Select(newForm.findElement(By.id("id_severity"))).selectByVisibleText(masteringConfig.getSeverity());
		newForm.findElement(By.name("screenshot")).sendKeys(masteringConfig.getScreenshot());
		newForm.findElement(By.name("submit")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fluentlyWait(new FluentWait<By>(By.className("grid_row")));
		List<WebElement> afterRowCount = driver.findElements(By.className("grid_row"));
		if(beforeRowCount.size()<afterRowCount.size())
			return true;
		else{
			return false;
		}
	}
	public boolean EditQcNote(){
		fluentlyWait(new FluentWait<By>(By.name("form1")));
		WebElement editForm= driver.findElement(By.name("form1"));
		List<WebElement> gridList = editForm.findElements(By.className("grid_cell"));
		List<WebElement> beforeRowCount = driver.findElements(By.className("grid_row"));
		fluentlyWait(new FluentWait<By>(By.name("fix-status")));
		new Select(editForm.findElement(By.name("fix-status"))).selectByVisibleText(masteringConfig.getFixStatus());
		fluentlyWait(new FluentWait<By>(By.name("time-to-fix")));
		new Select(editForm.findElement(By.name("time-to-fix"))).selectByVisibleText(masteringConfig.getTimeToFix());
		fluentlyWait(new FluentWait<By>(By.name("fix-notes")));
		editForm.findElement(By.name("fix-notes")).sendKeys(masteringConfig.getFixNotes());
		WebElement action= gridList.get(gridList.size()-1);
		action.findElement(By.name("submit")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fluentlyWait(new FluentWait<By>(By.className("grid_row")));
		List<WebElement> afterRowCount = driver.findElements(By.className("grid_row"));
		if(beforeRowCount.size() == afterRowCount.size()){
			return true;
		}else{
			return false;
		}
	}
	public boolean deleteQcNote(){
		fluentlyWait(new FluentWait<By>(By.className("grid_row")));
		List<WebElement> beforeRowCount = driver.findElements(By.className("grid_row"));
		fluentlyWait(new FluentWait<By>(By.name("form1")));
		WebElement deleteForm= driver.findElement(By.name("form1"));
		fluentlyWait(new FluentWait<By>(By.className("grid_cell")));
		List<WebElement> gridList = deleteForm.findElements(By.className("grid_cell"));
		WebElement action= gridList.get(gridList.size()-1);
		action.findElement(By.linkText("x")).click();
		try {
			Thread.sleep(1000);
			driver.switchTo().alert().accept();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fluentlyWait(new FluentWait<By>(By.className("grid_row")));
		List<WebElement> afterRowCount = driver.findElements(By.className("grid_row"));
		if(beforeRowCount.size()>(afterRowCount.size()-1)){
			return true;
		}else{
			return false;
		}
	}
	public void navigateVideoArchivePage(){
		WebElement contents = driver.findElement(By.className("contents"));
		List<WebElement> rowList =  contents.findElements(By.className("row"));
		List<WebElement> link = rowList.get(2).findElements(By.tagName("a"));
		WebElement click = link.get(2).findElement(By.tagName("img"));
		click.click();
		try {
			Thread.sleep(1000);
			WebElement back = driver.findElement(By.linkText("« back"));
			back.click();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
