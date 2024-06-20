package net.premieredigital.portal.admin.territories;

import java.util.List;
import java.util.concurrent.TimeUnit;



import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.ProviderAdminPage;
import net.premieredigital.portal.pom.ProviderAdminConfiguration;
import net.premieredigital.portal.pom.TerritoryAdminConfiguration;
import net.premieredigital.portal.pom.TerritoryAdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserAddTerritory extends BaseTest {
	private TerritoryAdminConfiguration territoryAdminConfig;
	private TerritoryAdminPage territoryAdmin;

	public void postCondition() {
//		territoryAdmin.deleteTerritory();
	}

	public void preCondition() throws Exception {
		this.territoryAdminConfig = new TerritoryAdminConfiguration();
		new LoginPage(this.webDriver.get(), this.pageConfig).login();
		this.territoryAdmin = new TerritoryAdminPage(this.webDriver.get(),this.pageConfig, new TerritoryAdminConfiguration());
		}
	//2.8.1
	//@Test(description="2.8.1 User shall be able to add a territory")
	public void userAddTerritory() {
		setImplicitWait(5);
		territoryAdmin.addTerritory();
		Boolean exists = false;
		WebElement elm = webDriver.get().findElement(By.tagName("tbody"));
		List<WebElement> tds = elm.findElements(By.tagName("td"));
		for (WebElement td:tds) {
			if(td.getText().equals(territoryAdminConfig.getFullName() + " (" + territoryAdminConfig.getTwoLetterCode() + ")")) {
				exists = true;
				break;
			}
		}
		Assert.assertTrue(exists);
		//territoryAdmin.deleteTerritory();
	}
	//2.8.3
	//@Test(description="2.8.3 User shall not be able to add territory due to duplicate entry")
	public void userUnsuccessfulAddTerritory() {
		setImplicitWait(5);
		territoryAdmin.addTerritory();
		territoryAdmin.addTerritory();
		webDriver.get().findElement(By.className("duplicate")).equals("Territory already available");
		//territoryAdmin.deleteTerritory();
	}
}
