package net.premieredigital.portal.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RatingAdminPage extends BasePage{
  private PageConfiguration pageConfig = null;
  private RatingAdminConfiguration ratingAdminConfig = null;
  public RatingAdminPage(WebDriver driver, PageConfiguration pageConfig, RatingAdminConfiguration ratingAdminConfig) {
    this.driver = driver;
    this.pageConfig = pageConfig;
    this.ratingAdminConfig = ratingAdminConfig;
  }
  public void addRating() {
    this.driver.get(getPageURL());
    FluentWait<By> fluentWait = new FluentWait<By>(By.name("territory"));
	fluentlyWait(fluentWait);
    new Select(driver.findElement(By.name("territory"))).selectByVisibleText(ratingAdminConfig.getTerritory());
    driver.findElement(By.name("name")).sendKeys(ratingAdminConfig.getSystemName());
    driver.findElement(By.name("new_code")).sendKeys(ratingAdminConfig.getRating1());
    driver.findElement(By.linkText("Add")).click();
    fluentlyWait(new FluentWait<By>(By.name("new_code")));
    driver.findElement(By.name("new_code")).sendKeys(ratingAdminConfig.getRating2());
    driver.findElement(By.linkText("Add")).click();
    fluentlyWait(new FluentWait<By>(By.name("new_code")));
    driver.findElement(By.name("new_code")).sendKeys(ratingAdminConfig.getRating3());
    driver.findElement(By.linkText("Add")).click();
    fluentlyWait(new FluentWait<By>(By.name("new_code")));
    driver.findElement(By.name("new_code")).sendKeys(ratingAdminConfig.getRating4());
    driver.findElement(By.linkText("Add")).click();
    fluentlyWait(new FluentWait<By>(By.className("new_only")));
    WebElement elm = driver.findElement(By.className("new_only"));
    WebElement parent = elm.findElement(By.xpath(".."));
    List<WebElement> itags = parent.findElements(By.tagName("input"));
    for (WebElement itag:itags) {
      if (itag.getText() == ratingAdminConfig.getSystemType()) {
        itag.click();
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        break;
      }
    }
    driver.findElement(By.name("submit")).click();
    try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  public void deleteRating() { 
  }
  public void addTerritory() {
    this.driver.get(getPageURL());
    fluentlyWait(new FluentWait<By>(By.linkText("Add one here.")));
    this.driver.findElement(By.linkText("Add one here.")).click();
    fluentlyWait(new FluentWait<By>(By.name("code")));
    this.driver.findElement(By.name("code")).sendKeys(ratingAdminConfig.getTwoLetterCode());
    this.driver.findElement(By.name("name")).sendKeys(ratingAdminConfig.getFullName());
    this.driver.findElement(By.name("submit")).click();
    try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    this.driver.get(getPageURL());
  }
  public void editRating() {
	  addRating();
	  this.driver.get(getPageURL());
	  fluentlyWait(new FluentWait<By>(By.linkText(ratingAdminConfig.getSystemName())));
	  this.driver.findElement(By.linkText(ratingAdminConfig.getSystemName())).click();
	  try {
		Thread.sleep(1000);
		  WebElement elm = driver.findElement(By.linkText(ratingAdminConfig.getSystemName()));
		  WebElement parent = elm.findElement(By.xpath(".."));
		  new Select(parent.findElement(By.id("id_territory"))).selectByVisibleText(ratingAdminConfig.getEdittedTerritory());
		  parent.findElement(By.name("name")).clear();
		  parent.findElement(By.name("name")).sendKeys(ratingAdminConfig.getEdittedSystemName());
		  parent.findElement(By.name("new_code")).sendKeys(ratingAdminConfig.getEditRating1());
		  parent.findElement(By.linkText("Add")).click();
		  fluentlyWait(new FluentWait<By>(By.name("new_code")));
		  parent.findElement(By.name("new_code")).sendKeys(ratingAdminConfig.getEditRating2());
		  parent.findElement(By.linkText("Add")).click();
		  fluentlyWait(new FluentWait<By>(By.name("new_code")));
		  parent.findElement(By.name("new_code")).sendKeys(ratingAdminConfig.getEditRating3());
		  parent.findElement(By.linkText("Add")).click();
		  Thread.sleep(1000);
		  List<WebElement> spans = parent.findElements(By.tagName("span"));
		  for (WebElement span:spans) {
			  if (span.getText().equals(ratingAdminConfig.getDeleteRating1() + " X")) {
				  span.findElement(By.linkText("X")).click();
				  Thread.sleep(1000);
			  }
		  }
		  parent.findElement(By.name("submit")).click();
		  Thread.sleep(1000);
		  this.driver.get(getPageURL());
		  FluentWait<By> fluentWait = new FluentWait<By>(By.linkText(ratingAdminConfig.getSystemName()));
		  fluentlyWait(fluentWait);
//		  new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.linkText(ratingAdminConfig.getSystemName())));
		  this.driver.findElement(By.linkText(ratingAdminConfig.getSystemName())).click();
		  Thread.sleep(1000);
	  } catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	  }
  }  
  public String getPageURL() {
    return pageConfig.getRootURL() + "/admin/rating_systems";
  }
}
