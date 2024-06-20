package net.premieredigital.portal.admin.ratings;

import java.util.List;
import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.RatingAdminConfiguration;
import net.premieredigital.portal.pom.RatingAdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserAddUnavailableTerritory extends BaseTest {
  private RatingAdminConfiguration ratingAdminConfig;
  private RatingAdminPage ratingAdmin;

  public void postCondition() {
//    ratingAdmin.deleteRating();
  }
  public void preCondition() throws Exception {
    this.ratingAdminConfig = new RatingAdminConfiguration();
    //new LoginPage(webDriver.get(), this.pageConfig).login();
    this.ratingAdmin = new RatingAdminPage(webDriver.get(),this.pageConfig, new RatingAdminConfiguration());
      }
  //1.6.2
  //@Test(description="2.6.2 User shall be able to add a new territory if the desired territory is not available")
  public void addUnavailableTerritory() {
	  setImplicitWait(5);
    ratingAdmin.addTerritory();
    fluentlyWait(new FluentWait<By>(By.name("territory")));
    webDriver.get().findElement(By.name("territory")).click();
    WebElement elm = webDriver.get().findElement(By.name("territory"));
    WebElement parent = elm.findElement(By.xpath(".."));
    List<WebElement> otags = parent.findElements(By.tagName("option"));
    Boolean exists = false;
    for (WebElement otag:otags) {
      if(otag.getText().equals(ratingAdminConfig.getTwoLetterCode() + " (" + ratingAdminConfig.getFullName() + ")")) {
        exists = true;
        break;
      }
    }
    Assert.assertTrue(exists);
  }
}
