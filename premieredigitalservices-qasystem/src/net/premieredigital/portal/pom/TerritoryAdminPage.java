package net.premieredigital.portal.pom;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TerritoryAdminPage extends BasePage {
	private PageConfiguration pageConfig = null;
	private String two_letter_code,full_name,edit_full_name;
	public TerritoryAdminPage(WebDriver driver, PageConfiguration pageConfig, TerritoryAdminConfiguration territoryAdminConf) {
		this.driver = driver;
		this.pageConfig = pageConfig;
		this.two_letter_code = territoryAdminConf.getTwoLetterCode();
		this.full_name = territoryAdminConf.getFullName();
		this.edit_full_name = territoryAdminConf.getEditFullName();
	}
	public void addTerritory() {
		setImplicitWait(5);
		this.driver.get(getPageURL());
		assertPageTitle("Territories");
		this.driver.findElement(By.name("code")).sendKeys(this.two_letter_code);
		this.driver.findElement(By.name("name")).sendKeys(this.full_name);
		this.driver.findElement(By.name("submit")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void editTerritory() {
		setImplicitWait(5);
		this.driver.get(getPageURL());
		assertPageTitle("Territories");
		this.driver.findElement(By.linkText(this.full_name + " (" + this.two_letter_code + ")")).click();
		try {
			Thread.sleep(1000);
			WebElement elm = this.driver.findElement(By.linkText(this.full_name + " (" + this.two_letter_code + ")"));
			WebElement parent = elm.findElement(By.xpath(".."));
			parent.findElement(By.name("name")).clear();
			parent.findElement(By.name("name")).sendKeys(this.edit_full_name);
			parent.findElement(By.name("submit")).click();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getPageURL() {
		return pageConfig.getRootURL() + "/territories/";
	}
}
