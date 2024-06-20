package net.premieredigital.portal.ordersview.othertest;


import java.util.List;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderItemConfiguration;
import net.premieredigital.portal.pom.OrderItemPage;
import net.premieredigital.portal.pom.OrderPage;
import net.premieredigital.portal.pom.PageConfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GlobalSearch {
    private OrderItemConfiguration orderItemConfig;
    private OrderItemPage orderItemPage;
    private OrderConfiguration orderConfig;
    private OrderPage orderPage;
    private BaseTest baseTest;
    public GlobalSearch(BaseTest baseTest){
    	this.baseTest = baseTest;
    }
    public void postCondition() {
    }
    public void preCondition() throws Exception {
        orderItemConfig = new OrderItemConfiguration();
        this.orderConfig = new OrderConfiguration();
		orderPage = new OrderPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), orderConfig);
        //new LoginPage(this.baseTest.getWebDriver(), this.pageConfig).login();
        this.orderItemPage = new OrderItemPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), new OrderItemConfiguration(), orderConfig);
    }
    //Test 11.1
    //@Test(groups = "ExcludeSafari",description="11.1 Global Search")
    public void globalSearch() throws Exception{
		this.preCondition();
        WebElement searchIcon= baseTest.getWebDriver().findElement(By.className("search-section"));
        searchIcon.findElement(By.className("submit")).click();
        Thread.sleep(2000);
        WebElement searchInput = baseTest.getWebDriver().findElement(By.id("search_input"));
        searchInput.sendKeys(orderConfig.getSearchName());
        WebElement submit = baseTest.getWebDriver().findElement(By.className("fancy-blue-button"));
        submit.click();
        Thread.sleep(2000);
        WebElement searchDiv = baseTest.getWebDriver().findElement(By.className("search-filter"));
        List<WebElement> searchListItem = searchDiv.findElements(By.tagName("input"));
        List<WebElement> listName = searchDiv.findElements(By.tagName("label"));
        List<WebElement> headList = baseTest.getWebDriver().findElements(By.className("top_head"));
        for(int i=0;i<searchListItem.size();i++){
	    	if(i==0){
	            for(int j=1;j<listName.size();j++){
	            	if(listName.size()-1 == j){
	                    String filterName = listName.get(j).getText()+"s".trim();
	                    Assert.assertEquals(filterName,headList.get(j-1).getText().trim());
	            	}else{
	            		Thread.sleep(5000);
	                    Assert.assertEquals(listName.get(j).getText().trim(),headList.get(j-1).getText().trim());
	            	}
	            }
	    	}else{
		        searchListItem.get(i).click();
		        baseTest.getWebDriver().findElement(By.className("fancy-blue-button")).click();
		        Thread.sleep(2000);
		        baseTest.fluentlyWait(new FluentWait<By>(By.className("top_head")));
		        headList = baseTest.getWebDriver().findElements(By.className("top_head"));
            	if(headList.get(headList.size()-1).getText().equals("Files")){
                    String filterName = listName.get(i).getText()+"s".trim();
                    Assert.assertEquals(filterName,headList.get(headList.size()-1).getText().trim());
            	}else{
                    Assert.assertEquals(listName.get(i).getText().trim(),headList.get(headList.size()-1).getText().trim());
            	}
	    	}
	    }
    }
}