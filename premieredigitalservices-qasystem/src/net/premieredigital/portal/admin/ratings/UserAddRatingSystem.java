package net.premieredigital.portal.admin.ratings;

import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.RatingAdminConfiguration;
import net.premieredigital.portal.pom.RatingAdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserAddRatingSystem {
  private RatingAdminConfiguration ratingAdminConfig;
  private RatingAdminPage ratingAdmin;
  private BaseTest baseTest;
  public UserAddRatingSystem(BaseTest baseTest) {
		this.baseTest = baseTest;
  }
  public void postCondition() {
    ratingAdmin.deleteRating();
  }

  public void preCondition() throws Exception {
    this.ratingAdminConfig = new RatingAdminConfiguration();
    //new LoginPage(webDriver.get(), this.pageConfig).login();
    this.ratingAdmin = new RatingAdminPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), new RatingAdminConfiguration());
      }
  //1.6.1
  //@Test(description="2.6.1 User shall be able to add a rating system")
  public void userAddRatingSystem() throws Exception  {
	this.preCondition();
	baseTest.setImplicitWait(5);
    ratingAdmin.addRating();
    ExpectedConditions.textToBePresentInElementLocated(By.tagName("a"),ratingAdminConfig.getSystemName());
  }
}
