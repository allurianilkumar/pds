package net.premieredigital.portal.admin.providers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.ProviderAdminConfiguration;
import net.premieredigital.portal.pom.ProviderAdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.base.Predicate;

public class UserModifyProviderInfo {
	private ProviderAdminConfiguration providerAdminConfig;
	private ProviderAdminPage providerAdmin;
	private BaseTest baseTest;
	public UserModifyProviderInfo(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public void postCondition() {
		//providerAdmin.deleteProvider();
	}

	public void preCondition() {
		this.providerAdminConfig = new ProviderAdminConfiguration();
		//new LoginPage(baseTest.getWebDriver(), this.pageConfig).login();
		this.providerAdmin = new ProviderAdminPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.providerAdminConfig);
	}
	//2.2.3
	//@Test(groups = "ExcludeSafari",description="2.2.3 User shall be able to edit provider information")
	public void userShouldBeAbleToModifyProviderInfo() {
		this.preCondition();
		providerAdmin.createProvider();
		providerAdmin.modifyProvider();
		FluentWait<By> fluentWait = new FluentWait<By>(By.name("notes"));
        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(2000, TimeUnit.MILLISECONDS);
        fluentWait.until(new Predicate<By>() {
            public boolean apply(By by) {
                try {
                	System.out.println(baseTest.getWebDriver().findElements(by).size());                	
                    return baseTest.getWebDriver().findElements(by).size() > 0;
                } catch (NoSuchElementException ex) {
                    return false;
                }
            }
        });
//		new WebDriverWait(baseTest.getWebDriver(),30).until(ExpectedConditions.presenceOfElementLocated(By.name("notes")));
		ExpectedConditions.textToBePresentInElementLocated(By.name("notes"),providerAdminConfig.getNotes());
		ExpectedConditions.textToBePresentInElementLocated(By.name("price_notes"),providerAdminConfig.getPriceNotes());

		WebElement element=baseTest.getWebDriver().findElement(By.name("groups"));
		Select oSelection = new Select(element);
		List<WebElement> selectedOptions = oSelection.getAllSelectedOptions();
		for (WebElement webElement : selectedOptions)
		{
			Assert.assertTrue(webElement.getText().equals(providerAdminConfig.getGroupOption1()) || webElement.getText().equals(providerAdminConfig.getGroupOption2()));
		}
//		providerAdmin.deleteProvider();
	}
}
