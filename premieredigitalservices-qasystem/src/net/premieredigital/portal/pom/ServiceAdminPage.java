package net.premieredigital.portal.pom;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

public class ServiceAdminPage extends BasePage{
	private PageConfiguration pageConfig = null;
	private ServiceAdminConfiguration serviceAdminConf;
	public ServiceAdminPage(WebDriver driver, PageConfiguration pageConfig, ServiceAdminConfiguration serviceAdminConf) {
		this.serviceAdminConf = serviceAdminConf;
		this.driver = driver;
		this.pageConfig = pageConfig;
	}
	public void addService() {
		try {
			driver.get(getPageURL());
			Thread.sleep(1000);
			assertPageTitle("Services");
			for(int count=0;count<10;count++) {
			if(driver.findElements(By.linkText(serviceAdminConf.getServiceName())).size()>0)
				serviceAdminConf.setServiceName();
			else
				break;
			}
			assertPageTitle("Services");
			System.out.println("service name:");
			System.out.println(serviceAdminConf.getServiceName());
			Thread.sleep(5000);
	        fluentlyWait(new FluentWait<By>(By.name("service")));
			this.driver.findElement(By.name("service")).sendKeys(serviceAdminConf.getServiceName());
			this.driver.findElement(By.name("artwork_description")).sendKeys(serviceAdminConf.getDescription());
			this.driver.findElement(By.name("submit")).click();
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteService() {
		setImplicitWait(5);
		driver.get(getPageURL());
		assertPageTitle("Services");
		fluentlyWait(new FluentWait<By>(By.linkText(serviceAdminConf.getServiceName())));
		Boolean exists = driver.findElements(By.linkText(serviceAdminConf.getServiceName())).size()>0;
		if (exists) {
			WebElement elem = driver.findElement(By.linkText(serviceAdminConf.getServiceName()));
			WebElement parent = elem.findElement(By.xpath(".."));
			List<WebElement> anchors = parent.findElements(By.tagName("a"));
			anchors.get(1).click();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void editService() {
		setImplicitWait(5);
		driver.get(getPageURL());
		assertPageTitle("Services");
		fluentlyWait(new FluentWait<By>(By.linkText(serviceAdminConf.getServiceName())));
		driver.findElement(By.linkText(serviceAdminConf.getServiceName())).click();
		assertPageTitle(serviceAdminConf.getServiceName());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FluentWait<By> fluentWait = new FluentWait<By>(By.name("notes"));
		fluentlyWait(fluentWait);
//		new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.name("notes")));
		driver.findElement(By.name("notes")).sendKeys(this.serviceAdminConf.getAddress());
		driver.findElement(By.name("price_notes")).sendKeys(this.serviceAdminConf.getPriceNotes());
		driver.findElement(By.name("artwork_description")).sendKeys(this.serviceAdminConf.getRequirements());
//		if( this.pageConfig.getBrowserName() != "safari") {
//			new Select(driver.findElement(By.name("packaging_method"))).selectByVisibleText(this.packaging_method);
//			new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.name("packaging.file_renaming")));
//			new Select(driver.findElement(By.name("packaging.file_renaming"))).selectByVisibleText(this.file_renaming);
//			driver.findElement(By.name("packaging.transcode_hot_folder")).sendKeys(this.transcode_folder);
//			new Select(driver.findElement(By.name("delivery_method"))).selectByVisibleText(this.delivery_method);
//		}
		fluentlyWait(new FluentWait<By>(By.name("automatic_delivery")));
		WebElement automatic_delivery = driver.findElement(By.name("automatic_delivery"));
		scrollInToElement(automatic_delivery);
		automatic_delivery.click();
		try {
			Thread.sleep(1000);
			fluentlyWait(new FluentWait<By>(By.name("allows_delivery_resume")));
			WebElement allows_delivery_resume = driver.findElement(By.name("allows_delivery_resume"));
			scrollInToElement(allows_delivery_resume);
			allows_delivery_resume.click();
			Thread.sleep(1000);
			driver.findElement(By.name("save-changes")).click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addDeliveryMethods() {
		setImplicitWait(5);
		driver.get(getPageURL());
		assertPageTitle("Services");
		//System.out.println("Looking for service name" + serviceAdminConf.getServiceName());
		fluentlyWait(new FluentWait<By>(By.linkText(serviceAdminConf.getServiceName())));
		driver.findElement(By.linkText(serviceAdminConf.getServiceName())).click();
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(2000, TimeUnit.MILLISECONDS);
        fluentWait.until(new Predicate<WebDriver>() {
            public boolean apply(WebDriver driver) {
                try {
           	
                    return driver.findElement(By.name("packaging_method")) != null;
                } catch (NoSuchElementException ex) {
                  return false;
                }
            }
        });
		new Select(driver.findElement(By.name("packaging_method"))).selectByVisibleText(serviceAdminConf.getPackagingMethod());
		FluentWait<By> fWait = new FluentWait<By>(By.name("packaging.file_renaming"));
		fluentlyWait(fWait);
//		new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.name("packaging.file_renaming")));
		new Select(driver.findElement(By.name("packaging.file_renaming"))).selectByVisibleText(serviceAdminConf.getFileRenaming());
		driver.findElement(By.name("packaging.transcode_hot_folder")).sendKeys(serviceAdminConf.getTranscodeFolder());
		new Select(driver.findElement(By.name("delivery_method"))).selectByVisibleText(serviceAdminConf.getDeliveryMethod());
		driver.findElement(By.name("save-changes")).click();
		fWait = new FluentWait<By>(By.linkText("Add Provider"));
		fluentlyWait(fWait);
//		new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Add Provider")));
		driver.findElement(By.linkText("Add Provider")).click();
		fWait = new FluentWait<By>(By.className("provider_id"));
		fluentlyWait(fWait);
//		new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("provider_id")));
		new Select(driver.findElement(By.className("provider_id"))).selectByVisibleText(pageConfig.getProviderName());
		fWait = new FluentWait<By>(By.name("delivery.Folder"));
		fluentlyWait(fWait);
//		new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.name("delivery.Folder")));
		WebElement elm = driver.findElement(By.className("ui-dialog-buttonset"));
		List<WebElement> tags = elm.findElements(By.tagName("span"));
		tags.get(1).click();
		new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("provider_id")));
	}
	public void addSpecification() throws InterruptedException {
		driver.get(getPageURL());
		assertPageTitle("Services");
		driver.findElement(By.linkText(serviceAdminConf.getServiceName())).click();
		Thread.sleep(2000);
		fluentlyWait(new FluentWait<By>(By.name("upload")));
//		new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.name("upload")));
		driver.findElement(By.name("upload")).sendKeys(serviceAdminConf.getFilePath());
		new Select(driver.findElement(By.id("category_type"))).selectByVisibleText(serviceAdminConf.getCategory());
		driver.findElement(By.name("publication_date")).click();
		fluentlyWait(new FluentWait<By>(By.className("ui-datepicker-calendar")));
		WebElement dateWidget = driver.findElement(By.className("ui-datepicker-calendar"));
		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
		try {
			for (WebElement cell: columns){
				   if (cell.getText().equals(serviceAdminConf.getPublicationDate())){
				      cell.findElement(By.linkText(serviceAdminConf.getPublicationDate())).click();
				      try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				      break;
				   }
				}
				List<WebElement> saves = driver.findElements(By.name("save-changes"));
				saves.get(1).click();
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteSpecification() {
		this.driver.get(getPageURL());
		assertPageTitle("Services");
		FluentWait<By> fluentWait = new FluentWait<By>(By.linkText(serviceAdminConf.getServiceName()));
		fluentlyWait(fluentWait);
//		new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.linkText(service_name)));
		driver.findElement(By.linkText(serviceAdminConf.getServiceName())).click();
		fluentlyWait(new FluentWait<By>(By.className("service_specs")));
		WebElement elm = driver.findElement(By.className("service_specs"));
		WebElement body = elm.findElement(By.tagName("tbody"));
		fluentlyWait(new FluentWait<By>(By.tagName("td")));
		List<WebElement> tds = body.findElements(By.tagName("td"));
		if(tds.size()!=0){
			if (tds.get(0).equals(serviceAdminConf.getServiceName())) {
				tds.get(4).click();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public void addDeliveryMethodsFail() {
		this.driver.get(getPageURL());
		assertPageTitle("Services");
		driver.findElement(By.linkText(serviceAdminConf.getServiceName())).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getPageURL() {
		return pageConfig.getRootURL() + "/admin/add_service";
	}
}
