package net.premieredigital.portal.pom;

import java.util.List;

import net.premieredigital.portal.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class AssetsPage extends BasePage{
	private OrderItemConfiguration orderItemConfig;
	private OrderConfiguration orderConfig;
	private AssetsConfiguration assetsConfig;
	private MasteringConfiguration masteringConfig;
	private PageConfiguration pageConfig;
	
	public AssetsPage(WebDriver driver,PageConfiguration pageConfig, OrderItemConfiguration orderItemConfig, OrderConfiguration orderConfig, AssetsConfiguration assetsConfig, MasteringConfiguration masteringConfig) {
		this.driver = driver;
		this.pageConfig = pageConfig;
		this.orderItemConfig = orderItemConfig;
		this.orderConfig = orderConfig;
		this.assetsConfig = assetsConfig;
		this.masteringConfig = masteringConfig;
		//System.out.println(masteringConfig.getProvider());
}
	public String getPageURL() {
		return pageConfig.getRootURL() + "/assets";
	}
	public void fillSearchCriteria() {
		driver.get(getPageURL());
		makeThreadSleep(10000);
		fluentlyWait(new FluentWait<By>(By.name("search")));
		WebElement search = driver.findElement(By.name("search"));
		scrollInToElement(search);
		makeThreadSleep(4000);
		driver.findElement(By.name("search")).clear();
		driver.findElement(By.name("search")).sendKeys(orderConfig.getTitle3());		
		WebElement element = driver.findElement(By.name("provider_id"));
		scrollInToElement(element);
		new Select(element).selectByVisibleText(masteringConfig.getProvider());
		driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
		waitUntilTableisLoaded();
		assertPageTitle(orderConfig.getTitle3());
}
	public void clickTitle() {
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		for(WebElement tr:trs) {
			List<WebElement> links = tr.findElements(By.className("normal-looking-link"));
			if(links.size() > 0) {
				if(links.get(0).getText().contains(orderConfig.getTitle3())) {
					links.get(0).click();
					makeThreadSleep(1000);
					break;
				}
				else {
					continue;
				}
			}
			else {
				continue;
			}
		}
	}
	
	public void clickSummary() {
		driver.findElement(By.linkText("Summary")).click();
		makeThreadSleep(4000);
	}
}
