package net.premieredigital.portal.pom;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ProviderAdminPage extends BasePage{	
	private PageConfiguration pageConfig = null;
	private UserAdminConfiguration userAdminConf =null;
	private String delivery_email1,delivery_email2,problem_email1,problem_email2;
	private String group_option1,group_option2,file_URL,date1,date2,service,territory;
	private String mapping_field, export_value,notes,price_notes,provider_name;
	
	public ProviderAdminPage(WebDriver driver, PageConfiguration pageConfig, ProviderAdminConfiguration dataConfiguration) {
		this.driver = driver;
		this.pageConfig = pageConfig;
		this.userAdminConf=userAdminConf;
		this.delivery_email1 = dataConfiguration.getDeliveryEmail1();
		this.delivery_email2 = dataConfiguration.getDeliveryEmail2();
		this.problem_email1 = dataConfiguration.getProblemEmail1();
		this.problem_email2 = dataConfiguration.getProblemEmail2();
		this.group_option1 = dataConfiguration.getGroupOption1();
		this.group_option2 = dataConfiguration.getGroupOption2();
		this.file_URL = dataConfiguration.getFileURL();
		this.date1 = dataConfiguration.getDate1();
		this.date2 = dataConfiguration.getDate2();
		this.service = dataConfiguration.getService();
		this.territory = dataConfiguration.getTerritory();
		this.mapping_field = dataConfiguration.getMappingField();
		this.export_value = dataConfiguration.getExportValue();
		this.notes = dataConfiguration.getNotes();
		this.price_notes = dataConfiguration.getPriceNotes();
		this.provider_name =dataConfiguration.getProvider();
	}
	public void createProvider(){
		this.driver.get(getPageURL());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertPageTitle("Providers");
		fluentlyWait(new FluentWait<By>(By.name("provider")));
		//this.driver.navigate().refresh();
		this.driver.findElement(By.name("provider")).sendKeys(pageConfig.getProviderName());
		this.driver.findElement(By.name("itunes_aggregating_for")).click();
		this.driver.findElement(By.name("google_aggregating_for")).click();
		this.driver.findElement(By.name("quiver_aggregating_for")).click();
		this.driver.findElement(By.name("submit")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String provider_name = pageConfig.getProviderName().toLowerCase().replaceAll(" ", "");
		fluentlyWait(new FluentWait<By>(By.linkText(provider_name)));
//		ExpectedConditions.textToBePresentInElementLocated(By.tagName("a"),pageConfig.getProviderName().toLowerCase().replaceAll(" ", ""));
//		ExpectedConditions.textToBePresentInElementLocated(By.className("content"),"(aggregating for: iTunes, quiver, google)");
	}
	public void deleteProvider() {
		driver.get(getPageURL());
		assertPageTitle("Providers");
		String provider_name = pageConfig.getProviderName().toLowerCase().replaceAll(" ", "");

		fluentlyWait(new FluentWait<By>(By.linkText(provider_name)));
		Boolean exists = driver.findElements(By.linkText(provider_name)).size()>0;
		if (exists) {
			WebElement elem = driver.findElement(By.linkText(pageConfig.getProviderName().toLowerCase().replaceAll(" ", "")));
			WebElement parent = elem.findElement(By.xpath(".."));
			//System.out.println(parent.getTagName());
			List<WebElement> anchors = parent.findElements(By.tagName("a"));
			//System.out.println(anchors.get(0).getText());
			//System.out.println(anchors.size());
			List<WebElement> spans = parent.findElements(By.tagName("span"));
			//System.out.println(spans.size());
			WebElement delete_provider = parent.findElement(By.tagName("a"));
			//System.out.println("Delete provider link:");
			//System.out.println(delete_provider.getText());
			anchors.get(1).click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void editMapping() {
		driver.get(getPageURL());
		assertPageTitle("Providers");
		fluentlyWait(new FluentWait<By>(By.id("edit_mapping")));
//		searchProviderAndRender();
		driver.findElement(By.id("edit_mapping")).click();
		fluentlyWait(new FluentWait<By>(By.id("id_form-0-service")));
		new Select(driver.findElement(By.id("id_form-0-service"))).selectByVisibleText(service);
		new Select(driver.findElement(By.id("id_form-0-territory"))).selectByVisibleText(territory);
		new Select(driver.findElement(By.id("id_form-0-mapping_key"))).selectByVisibleText(mapping_field);
		driver.findElement(By.id("id_form-0-mapping_value")).sendKeys(export_value);
		driver.findElement(By.id("submit_mapping")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void unsuccessfulEditMapping() {
		driver.get(getPageURL());
		assertPageTitle("Providers");
		fluentlyWait(new FluentWait<By>(By.id("edit_mapping")));
	//	searchProviderAndRender();
		driver.findElement(By.id("edit_mapping")).click();
		fluentlyWait(new FluentWait<By>(By.id("id_form-0-service")));
		new Select(driver.findElement(By.id("id_form-0-service"))).selectByVisibleText(service);
		new Select(driver.findElement(By.id("id_form-0-territory"))).selectByVisibleText(territory);
		new Select(driver.findElement(By.id("id_form-0-mapping_key"))).selectByVisibleText(mapping_field);
		driver.findElement(By.id("id_form-0-mapping_value")).sendKeys("");
		driver.findElement(By.id("submit_mapping")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void modifyProvider() {
		this.driver.get(getPageURL());
		assertPageTitle("Providers");
		searchProviderAndRender(pageConfig.getProviderName());
		fluentlyWait(new FluentWait<By>(By.name("delivery_email")));
		this.driver.findElement(By.name("delivery_email")).sendKeys(delivery_email1);
		this.driver.findElement(By.name("add-notification-email")).click();
		fluentlyWait(new FluentWait<By>(By.name("delivery_email")));
		this.driver.findElement(By.name("delivery_email")).sendKeys(delivery_email2);
		this.driver.findElement(By.name("add-notification-email")).click();
		fluentlyWait(new FluentWait<By>(By.tagName("span")));
		this.driver.findElement(By.tagName("span")).equals(delivery_email1 + "," + delivery_email2);
		this.driver.findElement(By.name("problem_email")).sendKeys(problem_email1);
		this.driver.findElement(By.name("add-notification-email")).click();		
		fluentlyWait(new FluentWait<By>(By.name("problem_email")));
		this.driver.findElement(By.name("problem_email")).sendKeys(problem_email2);
		setImplicitWait(5);
		this.driver.findElement(By.name("add-notification-email")).click();
		fluentlyWait(new FluentWait<By>(By.tagName("span")));
		this.driver.findElement(By.tagName("span")).equals(problem_email1 + "," + problem_email2);
		ExpectedConditions.textToBePresentInElementLocated(By.tagName("span"),delivery_email1);
		this.driver.findElement(By.className("delete")).click();
		clickAlert();
		try {
			Thread.sleep(1000);
			this.driver.findElement(By.name("notes")).sendKeys(notes);
			this.driver.findElement(By.name("price_notes")).sendKeys(price_notes);
			WebElement element=driver.findElement(By.name("groups"));
			Select oSelection = new Select(element);
			oSelection.selectByVisibleText(group_option1);
			oSelection.selectByVisibleText(group_option2);
			this.driver.findElement(By.name("save-changes")).click();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addContracts() {
		this.driver.get(this.getPageURL());
		assertPageTitle("Providers");
		searchProviderAndRender(this.provider_name);
		fluentlyWait(new FluentWait<By>(By.name("upload")));
		driver.findElement(By.name("upload")).sendKeys(file_URL);
		driver.findElement(By.name("start_date")).click();
		fluentlyWait(new FluentWait<By>(By.className("ui-datepicker-calendar")));
		WebElement dateWidget = driver.findElement(By.className("ui-datepicker-calendar"));
		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));

		for (WebElement cell: columns){
		   if (cell.getText().equals(date1)){
		      cell.findElement(By.linkText(date1)).click();
		      try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      break;
		   }
		}
		driver.findElement(By.name("end_date")).click();
		dateWidget = driver.findElement(By.className("ui-datepicker-calendar"));
		columns=dateWidget.findElements(By.tagName("td"));
		try {
			for (WebElement cell: columns){
				   if (cell.getText().equals(date2)){
				      cell.findElement(By.linkText(date2)).click();
				      try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				      break;
				   }
				}
				List<WebElement> elm = driver.findElements(By.name("save-changes"));		
				elm.get(1).click();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void unsuccessfulAddContract() {
		this.driver.get(getPageURL());
		assertPageTitle("Providers");
		searchProviderAndRender(this.provider_name);
		fluentlyWait(new FluentWait<By>(By.tagName("table")));
		WebElement dateWidget = driver.findElement(By.tagName("table"));
		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));

		for (WebElement cell: columns){
		   if (cell.getText().equals(date1)){
		      cell.findElement(By.linkText(date1)).click();
		      try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      break;
		   }
		}
		dateWidget = driver.findElement(By.tagName("table"));
		columns=dateWidget.findElements(By.tagName("td"));

		for (WebElement cell: columns){
		   if (cell.getText().equals(date2)){
		      cell.findElement(By.linkText(date2)).click();
		      try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      break;
		   }
		}
		driver.findElement(By.name("save-changes")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void searchProviderAndRender(String provider_name) {
		//System.out.println("provider name:");
		//System.out.println(provider_name);
		//System.out.println(provider_name.toLowerCase().replaceAll(" ", ""));
		ExpectedConditions.textToBePresentInElementLocated(By.tagName("a"),provider_name.toLowerCase().replaceAll(" ", ""));
		driver.findElement(By.linkText(provider_name.toLowerCase().replaceAll(" ", ""))).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Assert.assertEquals("Edit Provider | " + pageConfig.getProviderName(), driver.getTitle());
	}
	public String getPageURL() {
		return pageConfig.getRootURL() + "/admin/add_provider";
	}
}
