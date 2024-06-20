package net.premieredigital.portal.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

public class ChangePasswordPage extends BasePage {	
	
	private PageConfiguration pageConfig = null;

	private String old_password,new_password,re_password;
	
	public void setOldPassword(String old_password) {
		this.old_password = old_password;
	}
	
	public void setNewPassword(String new_password) {
		this.new_password = new_password;
	}
	
	public void setRePassword(String re_password) {
		this.re_password = re_password;
	}
	
	public String getOldPassword() {
		return old_password;
	}
	
	public String getNewPassword() {
		return new_password;
	}
	
	public String getRePassword() {
		return re_password;
	}
	
	public String getPageURL() {
		return pageConfig.getRootURL() + "/change_password/";
	}
	
	public ChangePasswordPage(WebDriver driver, PageConfiguration pageConfig, String old_password, String new_password, String re_password) {
		this.driver = driver;
		this.pageConfig = pageConfig;
		setOldPassword(old_password);
		setNewPassword(new_password);
		setRePassword(re_password);
		new LoginPage(this.driver, this.pageConfig).login();
	}
	
	public void changePassword() {
		this.driver.get(getPageURL());
		fluentlyWait(new FluentWait<By>(By.name("old_password")));
		this.driver.findElement(By.name("old_password")).sendKeys(old_password);
		this.driver.findElement(By.name("new_password1")).sendKeys(new_password);
		this.driver.findElement(By.name("new_password2")).sendKeys(re_password);
		this.driver.findElement(By.name("submit")).click();
		makeThreadSleep(5000);
		this.pageConfig.setPassword(new_password);
	}

}