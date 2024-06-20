package net.premieredigital.portal.admin.ratings;

import java.util.List;
import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.RatingAdminConfiguration;
import net.premieredigital.portal.pom.RatingAdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.base.Predicate;

public class UserEditRatingSystem extends BaseTest {
	 private RatingAdminConfiguration ratingAdminConfig;
	  private RatingAdminPage ratingAdmin;

	  public void postCondition() {
	    ratingAdmin.deleteRating();
	  }

	  public void preCondition() throws Exception {
	    this.ratingAdminConfig = new RatingAdminConfiguration();
	    //new LoginPage(webDriver.get(), this.pageConfig).login();
	    this.ratingAdmin = new RatingAdminPage(webDriver.get(),this.pageConfig, new RatingAdminConfiguration());
	  	  }
	  //test 1.6.3
	  //@Test(2.6.3 User shall be able to edit a rating system")
	  public void userEditRatingSystem() {
		  ratingAdmin.editRating();
		  FluentWait<By> fluentWait = new FluentWait<By>(By.linkText(ratingAdminConfig.getEdittedSystemName()));
	        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
	        fluentWait.withTimeout(2000, TimeUnit.MILLISECONDS);
	        fluentWait.until(new Predicate<By>() {
	            public boolean apply(By by) {
	                try {
	                	System.out.println(driver.findElements(by).size());                	
	                    return webDriver.get().findElements(by).size() > 0;
	                } catch (NoSuchElementException ex) {
	                    return false;
	                }
	            }
	        });
//		  new WebDriverWait(webDriver.get(),30).until(ExpectedConditions.presenceOfElementLocated(By.linkText(ratingAdminConfig.getEdittedSystemName())));
		  WebElement elm = webDriver.get().findElement(By.linkText(ratingAdminConfig.getEdittedSystemName()));
		  elm.click();
		  WebElement parent = elm.findElement(By.xpath(".."));
//		  new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id("id_territory")));
		  String territory_name = new Select(parent.findElement(By.id("id_territory"))).getFirstSelectedOption().getText();
		  Assert.assertTrue(territory_name.equals(ratingAdminConfig.getEdittedTerritory()));
//		  Assert.assertTrue(parent.findElement(By.name("name")).getText().equals(ratingAdminConfig.getEdittedSystemName()));
		  List<WebElement> spans = parent.findElements(By.tagName("span"));
		  Assert.assertTrue(spans.get(0).getText().equals(ratingAdminConfig.getEditRating1()));
		  Assert.assertTrue(spans.get(1).getText().equals(ratingAdminConfig.getEditRating2()));
		  Assert.assertTrue(spans.get(2).getText().equals(ratingAdminConfig.getEditRating3()));
		  Assert.assertTrue(spans.get(3).getText().equals(ratingAdminConfig.getRating2()));
		  Assert.assertTrue(spans.get(4).getText().equals(ratingAdminConfig.getRating3()));
		  Assert.assertTrue(spans.get(5).getText().equals(ratingAdminConfig.getRating4()));
	  }
}
