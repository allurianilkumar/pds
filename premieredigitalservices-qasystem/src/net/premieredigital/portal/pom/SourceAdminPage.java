package net.premieredigital.portal.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

public class SourceAdminPage extends BasePage {
	private PageConfiguration pageConfig = null;
	private SourceAdminConfiguration sourceAdminConfig = null;
	
	public SourceAdminPage(WebDriver driver, PageConfiguration pageConfig, SourceAdminConfiguration sourceAdminConfig) {
		this.driver = driver;
		this.pageConfig = pageConfig;
		this.sourceAdminConfig = sourceAdminConfig;
	}
	public void addSource() {
		this.driver.get(getPageURL());
		driver.findElement(By.name("name")).sendKeys(sourceAdminConfig.getName());
		driver.findElement(By.name("submit")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addDuplicateSource() {
		this.driver.get(getPageURL());
		driver.findElement(By.name("name")).sendKeys(sourceAdminConfig.getName());
		driver.findElement(By.name("submit")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteSource() {
		this.driver.get(getPageURL());
		Boolean exists = driver.findElements(By.linkText(sourceAdminConfig.getName())).size()>0;
		if (exists) {
			WebElement elem = driver.findElement(By.linkText(sourceAdminConfig.getName()));
			WebElement parent = elem.findElement(By.xpath(".."));
			List<WebElement> anchors = parent.findElements(By.tagName("a"));
			anchors.get(1).click();
			clickAlert();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void deleteSource(String name) {
		this.driver.get(getPageURL());
		Boolean exists = driver.findElements(By.linkText(name)).size()>0;
		if (exists) {
			WebElement elem = driver.findElement(By.linkText(name));
			WebElement parent = elem.findElement(By.xpath(".."));
			List<WebElement> anchors = parent.findElements(By.tagName("a"));
			anchors.get(1).click();
			clickAlert();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void editSource() {
		this.driver.get(getPageURL());
		fluentlyWait(new FluentWait<By>(By.linkText(sourceAdminConfig.getName())));
		driver.findElement(By.linkText(sourceAdminConfig.getName())).click();
		try {
			Thread.sleep(1000);
			WebElement elm = driver.findElement(By.linkText(sourceAdminConfig.getName()));
			WebElement parent = elm.findElement(By.xpath(".."));
			parent.findElement(By.name("name")).clear();
			parent.findElement(By.name("name")).sendKeys(sourceAdminConfig.getEditName());
			parent.findElement(By.name("submit")).click();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getPageURL() {
		return pageConfig.getRootURL() + "/asset_sources";
	}
}
