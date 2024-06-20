package net.premieredigital.portal.pom;
import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.pom.BillingAdminConfiguration;
import net.premieredigital.portal.pom.PageConfiguration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import com.google.common.base.Predicate;
public class BillingAdminPage extends BasePage{			
		private PageConfiguration pageConfig = null;
		private BillingAdminConfiguration billingAdminConf =null;
		public BillingAdminPage(WebDriver driver, PageConfiguration pageConfig, BillingAdminConfiguration billingAdminConf){			
			this.driver=driver;
			this.pageConfig=pageConfig;
			this.billingAdminConf = billingAdminConf;			
		}
		public void addBillingParty(){
			setImplicitWait(5);
			driver.get(pageConfig.getRootURL()+"/admin/billing_parties");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			assertPageTitle("Edit Billing Parties");
			for(int count=0;count<10;count++) {
				if(driver.findElements(By.linkText(billingAdminConf.getName())).size()>0)
					billingAdminConf.setName();
				else
					break;
			}
			fluentlyWait(new FluentWait<By>(By.name("name")));
			driver.findElement(By.name("name")).sendKeys(billingAdminConf.getName());
			driver.findElement(By.name("contact_emails")).sendKeys(billingAdminConf.getContactEmails());
			driver.findElement(By.name("billing_address")).sendKeys(billingAdminConf.getBillingAddress());
			new Select(driver.findElement(By.name("is_service"))).selectByVisibleText(billingAdminConf.getTypeOfBillingParty());
			new Select(driver.findElement(By.name("billing_cycle"))).selectByVisibleText(billingAdminConf.getBillingFrequency());
			new Select(driver.findElement(By.name("payment_net"))).selectByVisibleText(billingAdminConf.getPaymentDueAfter());
			new Select(driver.findElement(By.name("invoice_by_order_po"))).selectByVisibleText(billingAdminConf.getInvoiceAfterOrderPOFinished());
			new Select(driver.findElement(By.name("invoice_features_separate_from_episodes"))).selectByVisibleText(billingAdminConf.getInvoiceFeaturesSeparateFromEpisodes());
			new Select(driver.findElement(By.name("invoice_features_individually"))).selectByVisibleText(billingAdminConf.getInvoiceFeaturesIndividually());
			new Select(driver.findElement(By.name("multiple_po_billing_forbidden"))).selectByVisibleText(billingAdminConf.getMultiplePOBillingForbidden());
			driver.findElement(By.name("submit")).submit();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void editBillingParty(){
			FluentWait<WebDriver>fluentWait = new FluentWait<WebDriver>(driver);
	        fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
	        fluentWait.withTimeout(2000, TimeUnit.MILLISECONDS);
	        fluentWait.until(new Predicate<WebDriver>() {
	            public boolean apply(WebDriver driver) {
	                try {                	
	                    return driver.findElement(By.linkText(billingAdminConf.getName())) != null;
	                } catch (NoSuchElementException ex) {
	                    return false;
	                }
	            }
	        });
			driver.findElement(By.linkText(billingAdminConf.getName())).click();
			fluentlyWait(new FluentWait<By>(By.name("billing_cycle")));
			new Select(driver.findElement(By.name("billing_cycle"))).selectByVisibleText(billingAdminConf.getBillingFrequencyUpdate());
			new Select(driver.findElement(By.name("payment_net"))).selectByVisibleText(billingAdminConf.getPaymentDueAfterUpdate());
			new Select(driver.findElement(By.name("invoice_features_separate_from_episodes"))).selectByVisibleText(billingAdminConf.getInvoiceFeaturesSeparatefromEpisodesUpdate());
			driver.findElement(By.name("submit")).click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}

