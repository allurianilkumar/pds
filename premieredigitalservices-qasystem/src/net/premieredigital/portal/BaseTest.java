package net.premieredigital.portal;


import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.PageConfiguration;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Predicate;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;

public class BaseTest implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider {


	public SauceOnDemandAuthentication authentication;
    
    private String applicationUrl;

    protected WebDriver driver;

    protected static PageConfiguration pageConfig;

    private static Logger log = Logger.getLogger(BaseTest.class.getName());

    //private SessionId sessionId = null;
    private String methodName = null;

    protected static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    protected ThreadLocal<String> sessionId = new ThreadLocal<String>();
    private DesiredCapabilities capabilities;

    @BeforeClass
	@Parameters({"userName", "key", "os", "browser", "browserVersion", "suiteName", "runPlatform", "applicationUrl"})
    public void beforeSuite(String userName,
            String key,
            String os,
            String browser,
            String browserVersion,
            String suiteName,
            String runPlatform,
            String applicationUrl
           ) throws Exception {
         // one-time initialization code
       capabilities = DesiredCapabilities.chrome();
       // capabilities = DesiredCapabilities.phantomjs();
       // capabilities.setJavascriptEnabled(true);
       capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
       capabilities.setCapability("idle-timeout", 300);
       capabilities.setCapability("command-timeout", 600);
       capabilities.setCapability("sauce-advisor", true);
       capabilities.setCapability("elementScrollBehavior", 1);
       this.pageConfig = new PageConfiguration(applicationUrl);
       this.pageConfig.setBrowserName(browser);
       //webDriver.set(new ChromeDriver());
       // webDriver.set(new PhantomJSDriver(capabilities));
       capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,org.openqa.selenium.UnexpectedAlertBehaviour.ACCEPT);
       webDriver.set(new RemoteWebDriver(new URL("http://52.8.100.59:4444/wd/hub"), capabilities));
	   webDriver.get().manage().window().maximize();
	   ((RemoteWebDriver) webDriver.get()).setFileDetector(new LocalFileDetector());
	   webDriver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	   try {
		 new LoginPage(webDriver.get(), this.pageConfig).login();
	   }catch(NoSuchWindowException e) {
		   e.printStackTrace();
		   webDriver.get().quit();
	   }catch (Exception ex) {
		   ex.printStackTrace();
		   webDriver.get().quit();
	   }
    }
    @SuppressWarnings("unchecked")
	@BeforeMethod(alwaysRun=true)
    public void setUp(Method method) throws Exception {
    	DOMConfigurator.configure("log4j.xml");
    	//log.info("Hello World!");
        System.out.println("START TEST : "+getTestDescription(method));
        webDriver.get().get(pageConfig.getRootURL());
        try {
    		this.preCondition();
    	}
        catch(NoSuchWindowException e) {
    		e.printStackTrace();
    		webDriver.get().quit();
    	}
        catch (UnreachableBrowserException ex) {
    		ex.printStackTrace();
    		webDriver.get().quit();
        }
    }
    @AfterMethod
    public void afterTest(Method method) {
    	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm");
    	Date date = new Date();
    	createScreenshotFolder(System.getProperty("user.dir"));
    	createFailedScreenshot(System.getProperty("user.dir")+"/screenshot");
    	try {
    		File scrFile = ((TakesScreenshot)webDriver.get()).getScreenshotAs(OutputType.FILE);
    		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"/screenshot/"+getTestDescription(method).replaceAll(" ", "_")+"_"+dateFormat.format(date)+".png"));
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	} catch (NoSuchWindowException ex) {
    		ex.printStackTrace();
    	}
    	System.out.println("END TEST : "+getTestDescription(method));
    }
    public void preCondition() throws Exception {
    }
    public void postCondition(){
    }
    
    public void createScreenshotFolder(String path){
    	File dir = new File("screenshot");
    	dir.mkdir();
    }
    public void createFailedScreenshot(String path){
    	File dir = new File("failed");
    	dir.mkdir();
    }

    public void takeScreenshot(String name){
/*    	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm");
    	Date date = new Date();
    	try {
    		File scrFile = ((TakesScreenshot)webDriver.get()).getScreenshotAs(OutputType.FILE);
    		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"/screenshot/"+name+"_"+dateFormat.format(date)+".png"));
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	} catch (NoSuchWindowException ex) {
    		ex.printStackTrace();
    	}*/
    }
    public void adminUserLogin(){
    	new LoginPage(webDriver.get(), this.pageConfig).login();
    }
    public void adminUserLogout(){
    	new LoginPage(webDriver.get(), this.pageConfig).logout();
    }
	public void fluentlyWait(FluentWait<By> fluentWait) {
		fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(150000, TimeUnit.MILLISECONDS);
        fluentWait.until(new Predicate<By>() {
            public boolean apply(By by) {
                try {
                    return webDriver.get().findElement(by)!=null;
                } catch (NoSuchElementException ex) {
                    return false;
                }
            }
        });
	}
    public void setImplicitWait(int seconds) {
    }

    @Override
    public SauceOnDemandAuthentication getAuthentication() {
        return authentication;
    }
    protected String getTestDescription(Method m) {
        Annotation[] annotations = m.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if(annotation instanceof Test){
                Test test = (Test) annotation;
                if (!test.description().isEmpty()) {
                   return test.description();
                }
            }
        }
        return m.getName();
    }

	@Override
	public String getSessionId() {
		// TODO Auto-generated method stub
		return sessionId.get();
	}

	@AfterClass
	public void afterSuite() {
           try {
        	   new LoginPage(webDriver.get(), this.pageConfig).logout();
        	   webDriver.get().quit();
	    	}
	    	catch(Exception ex) {
	    		log.error(this.getClass().getName());
	    		log.error(ex.getMessage());
	    		ex.printStackTrace();
	    	}
	}
	public WebDriver getWebDriver() {
		// TODO Auto-generated method stub
		return webDriver.get();
	}
	public PageConfiguration getPageConfiguration() {
		// TODO Auto-generated method stub
		return this.pageConfig;
	}
}
