package net.premieredigital.portal.admin.territories;

import java.util.List;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.TerritoryAdminConfiguration;
import net.premieredigital.portal.pom.TerritoryAdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserEditTerritory extends BaseTest {
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
	//2.8.2
	//@Test(description="2.8.2 User shall be able to edit a territory")
	public void userEditTerritory() {
		territoryAdmin.addTerritory();
		territoryAdmin.editTerritory();
		Boolean exists = false;
		WebElement elm = webDriver.get().findElement(By.tagName("tbody"));
		List<WebElement> tds = elm.findElements(By.tagName("td"));
		for (WebElement td:tds) {
			if(td.getText().equals(territoryAdminConfig.getEditFullName() + " (" + territoryAdminConfig.getTwoLetterCode() + ")")) {
				exists = true;
				break;
			}
		}
		Assert.assertTrue(exists);
		//territoryAdmin.deleteTerritory();
	}
}
