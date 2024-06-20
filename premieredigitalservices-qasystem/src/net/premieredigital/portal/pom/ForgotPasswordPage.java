package net.premieredigital.portal.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

public class ForgotPasswordPage extends BasePage {	
	private PageConfiguration pageConfig = null;

	public String getPageURL() {
		return pageConfig.getRootURL() + "/password/reset";
	}
	
	public ForgotPasswordPage(WebDriver driver, PageConfiguration pageConfig) {
		this.driver = driver;
		this.pageConfig = pageConfig;
	}
	
	public void forgotPassword() {
	    this.driver.get(getPageURL());
	    fluentlyWait(new FluentWait<By>(By.id("id_email")));
	    this.driver.findElement(By.id("id_email")).sendKeys(pageConfig.getUsername());
		this.driver.findElement(By.name("submit")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void invalidEmailForgotPassword() {
		this.driver.get(getPageURL());
		fluentlyWait(new FluentWait<By>(By.id("id_email")));
	    this.driver.findElement(By.id("id_email")).sendKeys("nouser@nouser.com");
		this.driver.findElement(By.name("submit")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
