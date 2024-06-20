package net.premieredigital.portal.admin.services;

import java.util.List;
import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.ServiceAdminConfiguration;
import net.premieredigital.portal.pom.ServiceAdminPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserDeleteSpecification extends BaseTest{
	private ServiceAdminConfiguration serviceAdminConf;
	private ServiceAdminPage serviceAdmin;
	private BaseTest baseTest;
	public UserDeleteSpecification(BaseTest baseTest) {
		this.baseTest = baseTest;
	}	
	public void postCondition() {
		serviceAdmin.deleteService();
	}

	public void preCondition() throws Exception {
		this.serviceAdminConf = new ServiceAdminConfiguration();
		//new LoginPage(this.webDriver.get(), this.pageConfig).login();
		this.serviceAdmin = new ServiceAdminPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), serviceAdminConf);
			}
	//2.3.8
	//@Test(groups = "ExcludeSafari",description="2.3.8 User shall be able to delete a specification")
	public void userDeleteSpecification() throws Exception {
		this.preCondition();
		setImplicitWait(5);
		serviceAdmin.addService();
		serviceAdmin.addSpecification();
		serviceAdmin.deleteSpecification();
		Boolean exists = false;
		String split_name[] = serviceAdminConf.getFilePath().split("/");
		String file_name = split_name[split_name.length-1];
		WebElement elm = webDriver.get().findElement(By.className("service_specs"));
		WebElement body = elm.findElement(By.tagName("tbody"));
		List<WebElement> trs = body.findElements(By.tagName("tr"));
		for (WebElement tr:trs) {
			List<WebElement> tds = tr.findElements(By.tagName("td"));
			if (tds.get(0).getText() == file_name || tds.get(1).getText() == serviceAdminConf.getCategory()) {
				exists =true;
				break;
			}
		}
		Assert.assertFalse(exists);
	}
}
