package net.premieredigital.portal.pom;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.SkipException;

import com.google.common.base.Predicate;

public class BasePage {
	protected WebDriver driver = null;

	public BasePage() {
	}
	public void setImplicitWait(int seconds) {
		
	}
	
	public void fluentlyWait(FluentWait<By> fluentWait) {
		try {
			fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
	        fluentWait.withTimeout(150000, TimeUnit.MILLISECONDS);
	        fluentWait.until(new Predicate<By>() {
	            public boolean apply(By by) {
	                try {
	                	//System.out.println("Fluentwait");
	                	//System.out.println(driver.findElements(by).size());                	
	                	return driver.findElement(by)!=null;
	                } catch (NoSuchElementException ex) {
	                 return false;
	                }
	            }
	        });
		} catch(TimeoutException e) {
			List<WebElement> error =  driver.findElements(By.tagName("img"));
            if(error.size()!=0){
                String errorFile =  error.get(0).getAttribute("src");
                if(errorFile.contains("500.jpg")){
                    //System.out.println("Internal Server Error");
                    e.printStackTrace();
                }else{
                    e.printStackTrace();
                    throw new SkipException("Skipped the test case due to timeout...");
                }
            }else{
                e.printStackTrace();
                throw new SkipException("Skipped the test case due to timeout...");
            }
		}
	}

	public Boolean waitForElementToBeVisible(FluentWait<By> fluentWait) {
		Boolean found = true;
		try {
			fluentWait.pollingEvery(1000, TimeUnit.MILLISECONDS);
	        fluentWait.withTimeout(200000, TimeUnit.MILLISECONDS);
	        fluentWait.until(new Predicate<By>() {
	            public boolean apply(By by) {
	                try {
	                	//System.out.println("Fluentwait for element to be visible");
	                	//System.out.println(driver.findElements(by).size());
	                	
	                	return driver.findElement(by).isDisplayed();
	                } catch (NoSuchElementException ex) {
	                  return false;
	                }
	            }
	        });
		} catch(TimeoutException e) {
			found = false;
			takeScreenshot("add_title");
			List<WebElement> error =  driver.findElements(By.tagName("img"));
            if(error.size()!=0){
                String errorFile =  error.get(0).getAttribute("src");
                if(errorFile.contains("500.jpg")){
                    ////System.out.println("Internal Server Error");
                    e.printStackTrace();
                }else{
                    e.printStackTrace();
                    //throw new SkipException("Skipped the test case due to timeout...");
                }
            }else{
                e.printStackTrace();
                //throw new SkipException("Skipped the test case due to timeout...");
            }
		}
		
		return found;
	}

	public void waitUntilTableisLoaded() {
		try {
			FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
			fluentWait.pollingEvery(1000, TimeUnit.MILLISECONDS);
	        fluentWait.withTimeout(120000, TimeUnit.MILLISECONDS);
	        fluentWait.until(new Predicate<WebDriver>() {
	            public boolean apply(WebDriver driver) {
	            		try {
	            			driver.findElement(By.className("table-loading-gif"));
	            			return false;
	            		}
	            		catch (NoSuchElementException ex) {
	            			return true;
	            		}
	            	//return(driver.findElement(By.className("table-loading-gif")) == null);
	            }
	        });
		} catch(TimeoutException e) {
			List<WebElement> error =  driver.findElements(By.tagName("img"));
            if(error.size()!=0){
                String errorFile =  error.get(0).getAttribute("src");
                if(errorFile.contains("500.jpg")){
                    //System.out.println("Internal Server Error");
                    e.printStackTrace();
                }else{
                    e.printStackTrace();
                    throw new SkipException("Skipped the test case due to timeout...");
                }
            }else{
                e.printStackTrace();
                throw new SkipException("Skipped the test case due to timeout...");
            }
		}
	}

	public void scrollInToElement(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);", element);
            Thread.sleep(2000);
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	public void makeThreadSleep(int time_unit) {
	//	try {
	//		Thread.sleep(100);
	//	} catch (InterruptedException e) {
	//		e.printStackTrace();
	//	}
	}
	public void clickAlert() {
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
			wait.pollingEvery(1000, TimeUnit.MILLISECONDS);
			wait.withTimeout(60000, TimeUnit.MILLISECONDS);
	        wait.until(
	                new Predicate<WebDriver>() {
	                    public boolean apply(WebDriver driver) {
	                    	try {
	                    		if(ExpectedConditions.alertIsPresent()!=null) {
	                    			//System.out.println("Accepting Alert.");
	                    			try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
	                        		driver.switchTo().alert().accept();
	                        		return true;
	                        	}
	                    	}
	                    	catch(NoAlertPresentException e) {
	                    		return false;
	                    	}
	                    	return false;
	                    }
	                }
	        );
		} catch(TimeoutException e) {
			List<WebElement> error =  driver.findElements(By.tagName("img"));
            if(error.size()!=0){
                String errorFile =  error.get(0).getAttribute("src");
                if(errorFile.contains("500.jpg")){
                    //System.out.println("Internal Server Error");
                    e.printStackTrace();
                }else{
                    e.printStackTrace();
                    throw new SkipException("Skipped the test case due to timeout...");
                }
            }else{
                e.printStackTrace();
                throw new SkipException("Skipped the test case due to timeout...");
            }
		}
	}
	public void assertPageTitle(final String title) {
		try {
			FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
			fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
	        fluentWait.withTimeout(100000, TimeUnit.MILLISECONDS);
	        fluentWait.until(new Predicate<WebDriver>() {
	            public boolean apply(WebDriver driver) {
	            		try {            			
	            			if(driver.getTitle().trim().contains(title)) {
	            				return true;
	            			}            			
	            		}
	            		catch (NoSuchElementException ex) {
	            			return false;
	            		}
	            	return false;
	            }
	        });
		} catch(TimeoutException e) {
			List<WebElement> error =  driver.findElements(By.tagName("img"));
            if(error.size()!=0){
                String errorFile =  error.get(0).getAttribute("src");
                if(errorFile.contains("500.jpg")){
                    //System.out.println("Internal Server Error");
                    e.printStackTrace();
                }else{
                    e.printStackTrace();
                    throw new SkipException("Skipped the test case due to timeout...");
                }
            }else{
                e.printStackTrace();
                throw new SkipException("Skipped the test case due to timeout...");
            }
		}
	}
	public void waitTillNotNull(final String search_result) {
		try {
			FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
			fluentWait.pollingEvery(1000, TimeUnit.MILLISECONDS);
	        fluentWait.withTimeout(220000, TimeUnit.MILLISECONDS);
	        fluentWait.until(new Predicate<WebDriver>() {
	            public boolean apply(WebDriver driver) {
	            		try {
	            			if(driver.findElement(By.className(search_result)).getText().trim().equals("")) {
	            				////System.out.println("null");
	            			}
	            			else {
	            				return true;
	            			}
	            		}
	            		catch (NoSuchElementException ex) {
	            			return false;
	            		}
	            	return false;
	            }
	        });
		} catch(TimeoutException e) {
			List<WebElement> error =  driver.findElements(By.tagName("img"));
            if(error.size()!=0){
                String errorFile =  error.get(0).getAttribute("src");
                if(errorFile.contains("500.jpg")){
                    //System.out.println("Internal Server Error");
                    e.printStackTrace();
                }else{
                    e.printStackTrace();
                    //throw new SkipException("Skipped the test case due to timeout...");
                }
            }else{
                e.printStackTrace();
                //throw new SkipException("Skipped the test case due to timeout...");
            }
		}
	}
	public void waitForCssToChange(final WebElement elm) {
		try {
			FluentWait<WebElement> fluentWait = new FluentWait<WebElement>(elm);
	        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
	        fluentWait.withTimeout(120000, TimeUnit.MILLISECONDS);
	        fluentWait.until(new Predicate<WebElement>() {
	            public boolean apply(WebElement by) {
	                try {               	
	                    return elm.getCssValue("display").equals("none");
	                } catch (NoSuchElementException ex) {
	                  return false;
	                }
	            }
	        });
		}catch(Exception e){
			List<WebElement> error =  driver.findElements(By.tagName("img"));
            if(error.size()!=0){
                String errorFile =  error.get(0).getAttribute("src");
                if(errorFile.contains("500.jpg")){
                    //System.out.println("Internal Server Error");
                    e.printStackTrace();
                }else{
                    e.printStackTrace();
                    throw new SkipException("Skipped the test case due to timeout...");
                }
            }else{
                e.printStackTrace();
                throw new SkipException("Skipped the test case due to timeout...");
            }
		}
	}

    public void takeScreenshot(String name){
    	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm");
    	Date date = new Date();
    	try {
    		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    		FileUtils.copyFile(scrFile, new File("/home/ubuntu/bamboo_home8/xml-data/build-dir/PQD-HG-JOB1/screenshot/"+name+"_"+dateFormat.format(date)+".png"));
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	} catch (NoSuchWindowException ex) {
    		ex.printStackTrace();
    	}
    }
}
