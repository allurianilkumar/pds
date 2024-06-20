package net.premieredigital.portal.admin.locales;

import java.util.List;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LocaleAdminConfiguration;
import net.premieredigital.portal.pom.LocaleAdminPage;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.TerritoryAdminConfiguration;
import net.premieredigital.portal.pom.TerritoryAdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserAddLocale extends BaseTest {
	private LocaleAdminConfiguration localeAdminConfig;
	private LocaleAdminPage localeAdmin;
	public void postCondition() {
//		territoryAdmin.deleteTerritory();
	}
	public void preCondition() throws Exception {
		try {
			this.localeAdminConfig = new LocaleAdminConfiguration();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new LoginPage(webDriver.get(), this.pageConfig).login();
		this.localeAdmin = new LocaleAdminPage(webDriver.get(),this.pageConfig, new LocaleAdminConfiguration());
			}
	//2.5.1
	//@Test(description="2.5.1 User shall be able to add a locale")
	public void userAddLocale() {    
		localeAdmin.addLocale();
		Boolean exists = false;
		WebElement elm = webDriver.get().findElement(By.tagName("tbody"));
		List<WebElement> tds = elm.findElements(By.tagName("td"));
		for (WebElement td:tds) {
			if(td.getText().equals(localeAdminConfig.getDescription() + " - " + localeAdminConfig.getCode())) {
				exists = true;
				break;
			}
		}
		Assert.assertTrue(exists);
		//localeAdmin.deleteLocale();
	}
	//2.5.3(description="2.5.3 User shall not be able to add locale due to duplicate entry")
	//@Test
	public void userUnsuccessfulAddLocale() {
		localeAdmin.addLocale();
		localeAdmin.addLocale();
		webDriver.get().findElement(By.className("duplicate")).equals("Locale already available");
		//localeAdmin.deleteLocale();
	}
}
