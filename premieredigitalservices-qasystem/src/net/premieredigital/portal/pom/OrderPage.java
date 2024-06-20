package net.premieredigital.portal.pom;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Predicate;

public class OrderPage extends BasePage {	
	private PageConfiguration pageConfig;
	private OrderConfiguration orderConfig;
	private int totalStatus;
	private String currentStatus;
	private boolean placingOrder;
	private WebElement parent;
	List<WebElement> query;
	public OrderPage(WebDriver driver, PageConfiguration pageConfig, OrderConfiguration orderConfig) {
		this.driver = driver;
		this.pageConfig = pageConfig;
		this.orderConfig = orderConfig;
		this.totalStatus = 0;
		this.currentStatus = null;
		this.placingOrder = true;
		this.parent = null;
		this.query = null;
	}
	public void placeOrder() throws Exception {
//		driver.navigate().refresh();
		Thread.sleep(2000);
		fluentlyWait(new FluentWait<By>(By.linkText("Place Order")));
		driver.findElement(By.linkText("Place Order")).click();
		assertPageTitle("Place Order");
		fluentlyWait(new FluentWait<By>(By.name("provider")));
		new Select(driver.findElement(By.name("provider"))).selectByVisibleText(orderConfig.getProviderName());
		Thread.sleep(1000);
		FluentWait<By> fluentWait = new FluentWait<By>(By.linkText("add a new order"));
		fluentlyWait(fluentWait);
//		new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.linkText("add a new order")));
		driver.findElement(By.linkText("add a new order")).click();
		fluentWait = new FluentWait<By>(By.name("order_number"));
		fluentlyWait(fluentWait);
		driver.findElement(By.name("order_number")).sendKeys(orderConfig.getOrderNumber());
		driver.findElement(By.name("submit")).click();
		assertPageTitle(orderConfig.getOrderNumber());
		fluentWait = new FluentWait<By>(By.name("service"));
		fluentlyWait(fluentWait);
		new Select(driver.findElement(By.name("service"))).selectByVisibleText(orderConfig.getService());
		driver.findElement(By.name("due_date")).click();
		Thread.sleep(1000);
		WebElement div = driver.findElement(By.id("ui-datepicker-div"));
		List<WebElement> aTags = div.findElements(By.tagName("a"));
		aTags.get(1).click();
		Thread.sleep(1000);
		WebElement dateWidget = driver.findElement(By.className("ui-datepicker-calendar"));
		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
		for (WebElement cell: columns){
		   if (cell.getText().equals(orderConfig.getDueDate())){
		      cell.findElement(By.linkText(orderConfig.getDueDate())).click();
		      Thread.sleep(1000);
		      break;
		   }
		}
//		WebElement elm = driver.findElement(By.name("edit-order-form"));
//		List<WebElement> inputs = elm.findElements(By.tagName("input"));
//		inputs.get(inputs.size()-1).sendKeys(Keys.RETURN);
		fluentlyWait(new FluentWait<By>(By.className("save-button")));
		WebElement element = driver.findElement(By.className("save-button"));
		Thread.sleep(1000);
		scrollInToElement(element);
		element.click();
		fluentlyWait(new FluentWait<By>(By.name("from_order_id")));
		//fluentlyWait(new FluentWait<By> (By.id("search_input")));
		//addTitle(orderConfig.getTitle1());
		addTitle(orderConfig.getTitle3());
		//addTitle(orderConfig.getTitle2());
		Thread.sleep(1000);
		//driver.navigate().refresh();
		//fluentlyWait(new FluentWait<By> (By.linkText("Place Order")));
	}
	public void setCustomeMonth(String navigationType,int totalMonths) throws InterruptedException{
		WebElement div = driver.findElement(By.id("ui-datepicker-div"));
		List<WebElement> aTags = div.findElements(By.tagName("a"));
		if(navigationType.equals("back")){
			for(int i=0;i<totalMonths;i++){
				div = driver.findElement(By.id("ui-datepicker-div"));
				aTags = div.findElements(By.tagName("a"));
				aTags.get(0).click();// previous = 0 and next = 1 from current month
				Thread.sleep(1000);
			}
		}
		if(navigationType.equals("next")){
			for(int j=0;j<totalMonths;j++){
				div = driver.findElement(By.id("ui-datepicker-div"));
				aTags = div.findElements(By.tagName("a"));
				aTags.get(1).click();// previous = 0 and next = 1 from current month
				Thread.sleep(1000);
			}
		}
		Thread.sleep(1000);
		WebElement dateWidget = driver.findElement(By.className("ui-datepicker-calendar"));
		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
		for (WebElement cell: columns){
		   if (cell.getText().equals(orderConfig.getDueDate())){
		      cell.findElement(By.linkText(orderConfig.getDueDate())).click();
		      Thread.sleep(1000);
		      break;
		   }
		}
	}
	public void addSameRequirementToAll() {
		try {
			fluentlyWait(new FluentWait<By>(By.linkText("Add requirements")));
			driver.findElement(By.linkText("Add requirements")).click();
			assertPageTitle("Requirements");
			WebElement elm = driver.findElement(By.className("select-all-section"));
			if(!elm.findElement(By.className("select-all")).isSelected()) {
				elm.findElement(By.className("select-all")).click();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			WebElement requirements = driver.findElement(By.className("generic-add-requirements"));
			new Select(requirements.findElement(By.className("select-requirement"))).selectByVisibleText("*Apply Service Defaults*");
			Thread.sleep(2000);
			fluentlyWait(new FluentWait<By>(By.name("add-requirement")));
			requirements.findElement(By.name("add-requirement")).click();
			Thread.sleep(5000);
			fluentlyWait(new FluentWait<By>(By.linkText("Approve Tasks")));
			driver.findElement(By.linkText("Approve Tasks")).click();
			clickAlert();
	        clickAlert();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addRequirement() {
		setImplicitWait(5);
		fluentlyWait(new FluentWait<By>(By.linkText("Add requirements")));
		driver.findElement(By.linkText("Add requirements")).click();
		assertPageTitle("Requirements");
		FluentWait<By> fluentWait = new FluentWait<By>(By.linkText("+ Requirement"));
        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(2000, TimeUnit.MILLISECONDS);
        fluentWait.until(new Predicate<By>() {
            public boolean apply(By by) {
                try {               	
                    return driver.findElements(by).size() > 0;
                } catch (NoSuchElementException ex) {
                    return false;
                }
            }
        });
		List<WebElement> requirements = driver.findElements(By.linkText("+ Requirement"));
		////System.out.println(requirements.size());
		requirements.get(0).click();
		parent = requirements.get(0).findElement(By.xpath(".."));
		fluentWait = new FluentWait<By>(By.name("task_ajax"));
        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(2000, TimeUnit.MILLISECONDS);
        fluentWait.until(new Predicate<By>() {
            public boolean apply(By by) {
                try {               	
                  return parent.findElements(by).size() > 0;
                } catch (NoSuchElementException ex) {
                  return false;
                }
            }
        });
        fluentlyWait(new FluentWait<By>(By.name("task_ajax")));
		new Select(parent.findElement(By.name("task_ajax"))).selectByVisibleText(orderConfig.getRequirement1());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		parent.findElement(By.name("add-requirement")).click();
//		setResolution();
//		setAspectRatio();
		
		fluentWait = new FluentWait<By>(By.linkText("+ Requirement"));
        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(2000, TimeUnit.MILLISECONDS);
        fluentWait.until(new Predicate<By>() {
            public boolean apply(By by) {
                try {               	
                    return driver.findElements(by).size() > 0;
                } catch (NoSuchElementException ex) {
                  return false;
                }
            }
        });
        try{
        	List<WebElement> requirements2 = driver.findElements(By.linkText("+ Requirement"));
    		requirements2.get(0).click();
    		parent = requirements2.get(0).findElement(By.xpath(".."));
    		fluentWait = new FluentWait<By>(By.name("task_ajax"));
            fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
            fluentWait.withTimeout(2000, TimeUnit.MILLISECONDS);
            fluentWait.until(new Predicate<By>() {
                public boolean apply(By by) {
                    try {              	
                        return parent.findElements(by).size() > 0;
                    } catch (NoSuchElementException ex) {
                        return false;
                    }
                }
            });
    		new Select(parent.findElement(By.name("task_ajax"))).selectByVisibleText(orderConfig.getRequirement2());
    		Thread.sleep(2000);
    		parent.findElement(By.name("add-requirement")).click();	
        } catch(Exception e) {
        	e.printStackTrace();
        }
        fluentWait = new FluentWait<By>(By.linkText("+ Requirement"));
        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(60000, TimeUnit.MILLISECONDS);
        fluentWait.until(new Predicate<By>() {
            public boolean apply(By by) {
                try {
                	//System.out.println("Fluentwait");
                	//System.out.println(driver.findElements(by).size());                	
                    return driver.findElements(by).size() > 0;
                } catch (NoSuchElementException ex) {
                  return false;
                }
            }
        });
        try {
			List<WebElement> requirements3 = driver.findElements(By.linkText("+ Requirement"));
			requirements3.get(0).click();
			parent = requirements3.get(0).findElement(By.xpath(".."));
			fluentWait = new FluentWait<By>(By.name("task_ajax"));
	        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
	        fluentWait.withTimeout(2000, TimeUnit.MILLISECONDS);
	        fluentWait.until(new Predicate<By>() {
	            public boolean apply(By by) {
	                try {               	
	                    return parent.findElements(by).size() > 0;
	                } catch (NoSuchElementException ex) {
	                  return false;
	                }
	            }
	        });
			new Select(parent.findElement(By.name("task_ajax"))).selectByVisibleText(orderConfig.getRequirement2());
			Thread.sleep(2000);
			parent.findElement(By.name("add-requirement")).click();
        }
		catch (Exception ex) {
			ex.printStackTrace();
		}
//		setResolution();
//		setAspectRatio();
        driver.findElement(By.id("approve_tasks")).click();
        clickAlert();
        clickAlert();
	}
//	public void setAspectRatio() {
//		driver.navigate().refresh();
//		new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("aspect-ratio")));
//		List<WebElement> selects = driver.findElements(By.tagName("select"));
//		System.out.println(selects.size());
//		for(WebElement select:selects) {
//			//WebElement parent = select.findElement(By.xpath(".."));
//			List<WebElement> selectOptions = select.findElements(By.tagName("option"));
//			if(selectOptions.size()>0) {
//				for(WebElement selectOption:selectOptions) {
//					System.out.println("in span.....");
//					new Select(selectOption).selectByVisibleText(orderConfig.getResolution());
//				}
//			}
//		}
//		driver.navigate().refresh();
//		new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("aspect-ratio")));
//		
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		List<WebElement> selects = driver.findElements(By.tagName("select"));
//		System.out.println("Slects" + selects.size());
//		for(WebElement select:selects) {
//			List<WebElement> options = select.findElements(By.tagName("option"));
//			for(WebElement option:options) {
//				System.out.println("Option" + option.getText());
//				if (option.getText() == "1.78 AR") {
//					System.out.println("1.78 AR");
//				}
//			}
//			
//		}
//		List<WebElement> aspects = driver.findElements(By.className("aspect-ratio"));
//		System.out.println("aspect-ratios:");
//		System.out.println(aspects.size());
//		for(WebElement aspect:aspects) {
////			System.out.println(aspect.getText());
//			List<WebElement> options = aspects.get(2).findElements(By.tagName("option"));
//			System.out.println("options" + options.size());
//			System.out.println(options.contains(orderConfig.getAspectRatio()));
//			if(options.contains(orderConfig.getAspectRatio())) {
//				int index = options.indexOf(orderConfig.getAspectRatio());
//				options.get(index).click();
//			}
//			new Select(aspect.findElement(By.tagName("select"))).selectByVisibleText(orderConfig.getAspectRatio());
//			System.out.println("in select");
//			System.out.println(aspects.getText());
//			if(span.findElements(By.className("aspect-ratio")).size()>0) {
//				System.out.println("selecting aspect-ratio");
//				new Select(span.findElement(By.className("aspect-ratio"))).selectByVisibleText(orderConfig.getAspectRatio());
//			}
//		}
//		for(WebElement select:selects) {
//			WebElement parent = select.findElement(By.xpath(".."));
//			List<WebElement> selectOptions = parent.findElements(By.className("aspect-ratio"));
//			if(selectOptions.size()>0) {
//				for(WebElement selectOption:selectOptions) {
//					System.out.println(selectOption.getTagName());
//					System.out.println("in span.....");
//					new Select(selectOption).selectByVisibleText(orderConfig.getAspectRatio());
//				}
//			}
//		}
//	}
//	public void setAspectRatio() {
//		List<WebElement> divs = driver.findElements(By.className("tasks-wrapper"));
//		for (WebElement div:divs) {
//			List<WebElement> ars = div.findElements(By.className("aspect-ratio"));
//			for (WebElement ar:ars) {
//				new Select(ar).selectByVisibleText(orderConfig.getAspectRatio());
//			}
//		}
//	}
	public void setResolution() {
		//driver.navigate().refresh();
		FluentWait<By> fluentWait = new FluentWait<By>(By.className("hd-or-sd"));
		fluentlyWait(fluentWait);
//		new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.className("hd-or-sd")));
		List<WebElement> selects = driver.findElements(By.tagName("select"));
		//System.out.println(selects.size());
		for(WebElement select:selects) {
			WebElement parent = select.findElement(By.xpath(".."));
			List<WebElement> selectOptions = parent.findElements(By.className("hd-or-sd"));
			if(selectOptions.size()>0) {
				for(WebElement selectOption:selectOptions) {
					//System.out.println("in span.....");
					new Select(selectOption).selectByVisibleText(orderConfig.getResolution());
				}
			}
		}
	}
	public void addTitle(String title) throws InterruptedException {
		setImplicitWait(5);
		fluentlyWait(new FluentWait<By>(By.id("search_input")));
		waitForElementToBeVisible(new FluentWait<By>(By.id("search_input")));
		WebElement elm_input = driver.findElement(By.id("search_input"));
		scrollInToElement(elm_input);
		elm_input.clear();
//		elm_input.click();
		elm_input.sendKeys(title);
		elm_input.sendKeys(Keys.ENTER);
		fluentlyWait(new FluentWait<By>(By.className("search-results")));
		waitTillNotNull("search-results");
		boolean redeliver_available = false;
		if(redeliver_available) {
			driver.findElements(By.className("redeliver")).get(0).click();
			Thread.sleep(2000);
			driver.switchTo().alert().sendKeys("redeliver");
			driver.switchTo().alert().accept();
		}
		else {
			fluentlyWait(new FluentWait<By>(By.linkText("Add new title")));
			driver.findElement(By.linkText("Add new title")).click();
			for(int count=0;count<10;count++) {
				// Provider
				fluentlyWait(new FluentWait<By>(By.name("provider")));
				WebElement elm = driver.findElement(By.name("provider"));
				elm.click();
				elm.sendKeys(orderConfig.getProviderName());
				// Title
				fluentlyWait(new FluentWait<By>(By.name("title")));
				WebElement elm1 = driver.findElement(By.name("title"));
				elm1.clear();
				elm1.sendKeys(title);
				// Language
				fluentlyWait(new FluentWait<By>(By.name("language")));
				WebElement elm2 = driver.findElement(By.name("language"));
				elm2.clear();
				elm2.sendKeys(orderConfig.getLanguage());
				try {
					Thread.sleep(4000);
					elm2.sendKeys(Keys.TAB);
					Thread.sleep(4000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// Submit
				fluentlyWait(new FluentWait<By>(By.className("submit-section")));
				WebElement button = driver.findElement(By.className("submit-section"));
				fluentlyWait(new FluentWait<By>(By.className("fancy-blue-button")));
				button.findElement(By.className("fancy-blue-button")).click();
                                makeThreadSleep(25000);
				try {
					//System.out.println("error message: "+driver.findElement(By.className("hide-errors-at-start")).getText());
					fluentlyWait(new FluentWait<By>(By.className("hide-errors-at-start")));
					if(driver.findElement(By.className("hide-errors-at-start")).getText().trim().equals("") || isNextPageRendered()) {
						break;
					}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		Thread.sleep(2000);
	}
public void AddTitleRedeliver(String addTitle) throws InterruptedException{
	WebElement elementInput = driver.findElement(By.id("search_input"));
	scrollInToElement(elementInput);
	elementInput.clear();
// elm_input.click();
	elementInput.sendKeys(addTitle);
	elementInput.sendKeys(Keys.ENTER);
	driver.findElements(By.className("redeliver")).get(0).click();
	Thread.sleep(2000);
	driver.switchTo().alert().sendKeys("redeliver");
	driver.switchTo().alert().accept();
}
public boolean isNextPageRendered() {
		try {
			//System.out.println("in isNextPageRendered...");
			fluentlyWait(new FluentWait<By>(By.id("search_input")));
			return(waitForElementToBeVisible(new FluentWait<By>(By.id("search_input"))));
		} catch (Exception e) {
			takeScreenshot("add_title");
			e.printStackTrace();
			return false;
		}
	}
	public void searchOrder() {
		driver.get(getOrderURL());
		assertPageTitle("Orders");
		driver.findElement(By.name("status")).sendKeys(orderConfig.getStatus());
		driver.findElement(By.name("due_from")).click();
		try {
			Thread.sleep(2000);
			selectDate();
			driver.findElement(By.name("due_through")).click();
			Thread.sleep(2000);
			selectNextMonth();
			selectDate();
			driver.findElement(By.name("created_from")).click();
			Thread.sleep(2000);
			selectDate();
			driver.findElement(By.name("created_through")).click();
			Thread.sleep(2000);
		} catch (InterruptedException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		selectNextMonth();
		selectDate();
//		driver.findElement(By.name("has_been_invoiced")).sendKeys(orderConfig.getInvoice());
//		driver.findElement(By.name("service_id")).sendKeys(orderConfig.getService());
//		driver.findElement(By.name("provider_id")).sendKeys(orderConfig.getProviderName());
		driver.findElement(By.name("search")).sendKeys(orderConfig.getOrderNumber());
		driver.findElement(By.name("search")).sendKeys(Keys.RETURN);
		waitUntilTableisLoaded();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void selectDate() {
		WebElement dateWidget = driver.findElement(By.className("ui-datepicker-calendar"));
		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
		for (WebElement cell: columns){
		   if (cell.getText().equals(orderConfig.getDueDate())){
		      cell.findElement(By.linkText(orderConfig.getDueDate())).click();
		      try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      break;
		   }
		}
	}
	public void selectNextMonth() {
		WebElement div = driver.findElement(By.id("ui-datepicker-div"));
		List<WebElement> aTags = div.findElements(By.tagName("a"));
		aTags.get(1).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getOrderURL() {
		return pageConfig.getRootURL() + "/home/order_view/";
	}
	public void viewOrder() {
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		for(WebElement tr:trs) {
			if(tr.getText().contains(orderConfig.getOrderNumber())) {
				List<WebElement> tds = tr.findElements(By.tagName("td"));
				WebElement order = tds.get(1);
				order.findElement(By.linkText(orderConfig.getOrderNumber())).click();
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public void syncOrderItemViewOnFeature() {
        selectStatus(-1, "Ingest - Ready to Begin");
    }
    public void selectStatus(int i, String statusName) {
        //System.out.println("in selectStatus");
        boolean found = false;
        if(!placingOrder)
        {
            driver.get(pageConfig.getRootURL() + "/orders/add/");
            fluentlyWait(new FluentWait<By>(By.linkText(this.orderConfig.getOrderNumber())));
//            WebElement tbody = driver.findElement(By.tagName("tbody"));
//            List<WebElement> hrefs = tbody.findElements(By.tagName("a"));
//            hrefs.get(0).click();
            driver.findElement(By.linkText(this.orderConfig.getOrderNumber())).click();
            try {
				Thread.sleep(5000);
	            fluentlyWait(new FluentWait<By>(By.linkText("Add requirements")));
	            driver.findElement(By.linkText("Add requirements")).click();
	            Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else {
            placingOrder = false;
        }
        List<WebElement> tasks_containers = driver.findElements(By.className("tasks_container"));
        for(WebElement tasks_container:tasks_containers) {
            //System.out.println("task_container");
            WebElement ol = tasks_container.findElement(By.className("tasks"));
            //System.out.println("ol");
            List<WebElement> lis = ol.findElements(By.tagName("li"));
            //System.out.println(lis.size());
            for(WebElement li:lis) {
                //System.out.println("li");
                List<WebElement> spans = li.findElements(By.tagName("span"));
                for(WebElement span:spans) {
                    //System.out.println("span");
                    if(span.findElements(By.linkText("Feature")).size()>0) {
                        //System.out.println("found Feature ...");
                        WebElement feature = span.findElement(By.linkText("Feature"));
                        WebElement task_definitoin = feature.findElement(By.xpath(".."));
                        li = task_definitoin.findElement(By.xpath(".."));
                        WebElement task_status = li.findElement(By.className("task-status"));
                        //System.out.println("task_status");
                        WebElement element = task_status.findElement(By.className("text-status"));
                        JavascriptExecutor js=(JavascriptExecutor) driver;
                        js.executeScript(String.format("window.scrollTo(0, 0);", element.getLocation().getY()));
                        element.click();
                        try {
							Thread.sleep(5000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                        Actions actions = new Actions(driver);
                        //System.out.println("clicked status...");
                        WebElement ul = task_status.findElement(By.tagName("ul"));
                        //System.out.println("ul");
                        lis = ul.findElements(By.tagName("li"));
                        totalStatus = lis.size();
                        if (statusName == null){
                            currentStatus = lis.get(i).getText();
                            //System.out.println(lis.size());
                            //System.out.println(actions.moveToElement(lis.get(i)).toString());
                            actions.moveToElement(lis.get(i)).click().perform();
                        } else {
                            for(WebElement l:lis) {
                                if(l.getText().trim().equals(statusName)){
                                    currentStatus = statusName;
                                    actions.moveToElement(l).click().perform();
                                    break;
                                }
                            }
                        }
                        try {
                            Thread.sleep(5000);
                            driver.switchTo().alert().accept();
                        } catch(Exception e){
                            found = true;
                            break;
                        }
                        found = true;
                        break;
                    }
                }
                if(found) {
                    break;
                }
            }
            if(found) {
                break;
            }
        }
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public void syncTasksView() throws InterruptedException {
		driver.get(pageConfig.getRootURL() + "/tasks");
		assertPageTitle("Tasks");
		driver.findElement(By.name("search")).sendKeys(orderConfig.getTitle3());
		driver.findElement(By.name("search")).click();
		driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
		new Select(driver.findElement(By.name("service_id"))).selectByVisibleText(orderConfig.getService());
		new Select(driver.findElement(By.name("task_definition"))).selectByVisibleText("Feature");
		new Select(driver.findElement(By.name("task_status"))).selectByVisibleText("All Statuses");
		waitUntilTableisLoaded();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(10000);
	}
	public void syncAllTasksInRequirementPage() {
		for(int i=1;i<3;i++) {
			//System.out.println("i:" + i);
			selectStatus(i,null);
			checkTaskView();
		}
	}
	public void checkTaskView() {
		fillTaskView();
		List<WebElement> trs = null;
		List<WebElement> tds = null;
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		parent = tbody;
		FluentWait<By> fluentWait = new FluentWait<By>(By.tagName("tr"));
        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(60000, TimeUnit.MILLISECONDS);
        fluentWait.until(new Predicate<By>() {
            public boolean apply(By by) {
                try {
                	//System.out.println("Fluentwait");
                	//System.out.println(driver.findElements(by).size());                	
                    return parent.findElements(by).size() > 0;
                } catch (NoSuchElementException ex) {
                	//System.out.println("Fluentwait no such element");
                    return false;
                }
            }
        });
		trs = parent.findElements(By.tagName("tr"));
		tds = trs.get(0).findElements(By.tagName("td"));
		WebElement status = tds.get(7);
		//System.out.println("Status comparisons:");
		//System.out.println(status.findElement(By.className("status")).getText());
		//System.out.println(currentStatus);
		Assert.assertTrue(status.findElement(By.className("status")).getText().equals(currentStatus));
	}
	public void modifyStatusInTasksView() {
		fillTaskView();
		setImplicitWait(5);
		List<WebElement> trs = null;
		List<WebElement> tds = null;
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		trs = tbody.findElements(By.tagName("tr"));
		tds = trs.get(0).findElements(By.tagName("td"));		
		WebElement status = tds.get(7);
		status.click();
		List<WebElement> uls = status.findElements(By.tagName("ul"));
		List<WebElement> lis = uls.get(1).findElements(By.tagName("li"));
		for(WebElement li:lis) {
			if(li.getText().equals("Ingest - In Progress")) {
//				Capabilities cp = ((RemoteWebDriver) driver).getCapabilities();
//	            if (cp.getBrowserName().equals("chrome")) {
//	                try {
//	                    ((JavascriptExecutor) driver).executeScript(
//	                            "arguments[0].scrollIntoView(true);", li);
//	                } catch (Exception e) {
//
//	                }
//	            }
				scrollInToElement(li);
				li.click();
			}
		}
	}
	public String checkRequirementPageStatus() {
		setImplicitWait(5);
		driver.get(pageConfig.getRootURL() + "/orders/add/");
		assertPageTitle("Place Order");
		driver.findElement(By.linkText(this.orderConfig.getOrderNumber())).click();
		fluentlyWait(new FluentWait<By>(By.linkText("Add requirements")));
		driver.findElement(By.linkText("Add requirements")).click();
		fluentlyWait(new FluentWait<By>(By.className("tasks_container")));
		String status = null;
		List<WebElement> tasks_containers = driver.findElements(By.className("tasks_container"));
		for(WebElement tasks_container:tasks_containers) {
			//System.out.println("task_container");
			WebElement ol = tasks_container.findElement(By.className("tasks"));
			//System.out.println("ol");
			List<WebElement> lis = ol.findElements(By.tagName("li"));
			//System.out.println(lis.size());
			for(WebElement li:lis) {
				//System.out.println("li");
				List<WebElement> spans = li.findElements(By.tagName("span"));
				for(WebElement span:spans) {
					//System.out.println("span");
					if(span.findElements(By.linkText("Feature")).size()>0) {
						//System.out.println("found Feature ...");
						WebElement feature = span.findElement(By.linkText("Feature"));
						WebElement task_definitoin = feature.findElement(By.xpath(".."));
						li = task_definitoin.findElement(By.xpath(".."));
						WebElement task_status = li.findElement(By.className("task-status"));
						WebElement text_status = task_status.findElement(By.className("text-status"));
						//System.out.println("text_status");
						status = text_status.getText();
						//System.out.println("status:" + status);
						break;
					}
				}
				break;
			}
			break;
		}
		return status;
	}
	public void fillTaskView() {
		driver.get(pageConfig.getRootURL() + "/tasks");
		assertPageTitle("Tasks");
		driver.findElement(By.name("search")).click();
		driver.findElement(By.name("search")).sendKeys(orderConfig.getTitle3());
		driver.findElement(By.name("show_hidden_tasks")).click();
		new Select(driver.findElement(By.name("service_id"))).selectByVisibleText(orderConfig.getService());
		waitUntilTableisLoaded();
		new Select(driver.findElement(By.name("task_definition"))).selectByVisibleText("Feature");
		waitUntilTableisLoaded();
		new Select(driver.findElement(By.name("task_status"))).selectByVisibleText("All Statuses");
		waitUntilTableisLoaded();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
