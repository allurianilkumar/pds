package net.premieredigital.portal.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LocaleAdminPage extends BasePage {	
	private PageConfiguration pageConfig = null;
	private LocaleAdminConfiguration localeAdminConf;
	public LocaleAdminPage(WebDriver driver, PageConfiguration pageConfig, LocaleAdminConfiguration localeAdminConf) {
		this.driver = driver;
		this.pageConfig = pageConfig;
		this.localeAdminConf = localeAdminConf;
	}
	
	public void addLocale() {
		this.driver.get(getPageURL());
		this.driver.findElement(By.name("code")).sendKeys(localeAdminConf.getCode());
		this.driver.findElement(By.name("description")).sendKeys(localeAdminConf.getEditDescription());
		this.driver.findElement(By.name("submit")).click();
	}
	
	public void editLocale() {
		this.driver.get(getPageURL());
		this.driver.findElement(By.linkText(localeAdminConf.getDescription() + " - " + localeAdminConf.getCode())).click();
		WebElement elm = this.driver.findElement(By.linkText(localeAdminConf.getDescription() + " - " + localeAdminConf.getCode()));
		WebElement parent = elm.findElement(By.xpath(".."));
		parent.findElement(By.name("code")).sendKeys(this.localeAdminConf.getEditCode());
		parent.findElement(By.name("description")).sendKeys(localeAdminConf.getEditDescription());
		parent.findElement(By.name("submit")).click();
	}
	
	public String getPageURL() {
		return pageConfig.getRootURL() + "/locales/";
	}
}                            
