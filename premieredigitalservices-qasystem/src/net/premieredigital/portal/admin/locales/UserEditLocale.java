package net.premieredigital.portal.admin.locales;

import java.util.List;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LocaleAdminConfiguration;
import net.premieredigital.portal.pom.LocaleAdminPage;
import net.premieredigital.portal.pom.LoginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserEditLocale extends BaseTest{
	private LocaleAdminConfiguration localeAdminConfig;
	private LocaleAdminPage localeAdmin;

	public void postCondition() {
//		territoryAdmin.deleteTerritory();
	}

	public void preCondition() {
		try {
			this.localeAdminConfig = new LocaleAdminConfiguration();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new LoginPage(webDriver.get(), this.pageConfig).login();
		try {
			this.localeAdmin = new LocaleAdminPage(webDriver.get(),this.pageConfig, new LocaleAdminConfiguration());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
	//2.5.2
	//@Test(description="2.5.2 User shall be able to edit a locale")
	public void userEditLocale() {
		localeAdmin.addLocale();
		localeAdmin.editLocale();
		Boolean exists = false;
		WebElement elm = webDriver.get().findElement(By.tagName("tbody"));
		List<WebElement> tds = elm.findElements(By.tagName("td"));
		for (WebElement td:tds) {
			if(td.getText().equals(localeAdminConfig.getEditDescription() + " - " + localeAdminConfig.getEditCode())) {
				exists = true;
				break;
			}
		}
		Assert.assertTrue(exists);
		//localeAdmin.deleteLocale();
	}
}
