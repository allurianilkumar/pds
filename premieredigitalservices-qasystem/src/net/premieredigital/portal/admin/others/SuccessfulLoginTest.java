package net.premieredigital.portal.admin.others;

import java.util.concurrent.TimeUnit;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.ChangePasswordPage;
import net.premieredigital.portal.pom.ForgotPasswordPage;
import net.premieredigital.portal.pom.LoginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.base.Predicate;

public class SuccessfulLoginTest {
	private BaseTest baseTest;
	public SuccessfulLoginTest(BaseTest baseTest) {
		this.baseTest = baseTest;
	}
	public String getLogoutURL() {
		return baseTest.getPageConfiguration().getRootURL() + "/logout/";
	}
	public void preCondition(){
		new LoginPage(baseTest.getWebDriver(),baseTest.getPageConfiguration()).logout();
	}
	public void postCondition(){
		new LoginPage(baseTest.getWebDriver(),baseTest.getPageConfiguration()).login();
	}
	//1.1
	//@Test(description="1.1 Successful login to the system",priority=1)
	public void successfulLoginTest() {
	  this.preCondition();
	  baseTest.setImplicitWait(5);
	  new LoginPage(baseTest.getWebDriver(), baseTest.getPageConfiguration()).login();
	  FluentWait<By> fluentWait = new FluentWait<By>(By.className("user-dropdown"));
	  fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
	  fluentWait.withTimeout(2000, TimeUnit.MILLISECONDS);
	  fluentWait.until(new Predicate<By>() {
	      public boolean apply(By by) {
	          try {
	            return baseTest.getWebDriver().findElements(by).size() > 0;
	          } catch (NoSuchElementException ex) {
	            return false;
	          }
	      }
	  });
	  WebElement elm = baseTest.getWebDriver().findElement(By.className("user-dropdown"));
	  elm = elm.findElement(By.className("username"));
	  Assert.assertTrue(elm.getText().equals(baseTest.getPageConfiguration().getUsername()));
	}
	//1.2
	public void successfulAttemptToChangePasswordTest() {
		this.preCondition();
		this.postCondition();
		baseTest.setImplicitWait(5);
		new ChangePasswordPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(),"temppassword","newpassword","newpassword").changePassword();
		baseTest.getWebDriver().get(getLogoutURL());
		new ChangePasswordPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(),"newpassword","temppassword","temppassword").changePassword();
	}
    //1.3
	//@Test(description="1.3 Unsuccessful login to the system",priority=2)
	public void unsuccessfulLoginTest() {
		this.preCondition();
		new LoginPage(baseTest.getWebDriver(), baseTest.getPageConfiguration()).invalidLogin();
		WebElement login_form = baseTest.getWebDriver().findElement(By.id("login_form"));
		Assert.assertEquals(login_form.findElement(By.tagName("span")).getText().trim(),"Invalid username / password.");
		new LoginPage(baseTest.getWebDriver(), baseTest.getPageConfiguration()).login();
	}
	//1.4
	public void unsuccessfulAttemptToChangePasswordTest() {
		this.preCondition();
		new ChangePasswordPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(),"temppassword","newpassword","wrongpassword").changePassword();
		baseTest.getWebDriver().findElement(By.className("under-submit")).findElement(By.tagName("li")).equals("Passwords don't match.");
		new LoginPage(baseTest.getWebDriver(), baseTest.getPageConfiguration()).login();
	}
	//1.5
	//@Test(description="1.5 Forgot Password",priority=3)
	public void forgotPasswordTest() {
		this.preCondition();
		new ForgotPasswordPage(baseTest.getWebDriver(), baseTest.getPageConfiguration()).forgotPassword();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		baseTest.getWebDriver().getPageSource().contains("Please check your email to reset your password.");
		//new LoginPage(baseTest.getWebDriver(), baseTest.getPageConfiguration()).login();
	}
	//1.6
	//@Test(description="1.6 Invalid username during forgot password",priority=4)
	public void invalidEmailDuringForgotPasswordTest() {
		this.preCondition();
		new ForgotPasswordPage(baseTest.getWebDriver(),baseTest.getPageConfiguration()).invalidEmailForgotPassword();
		baseTest.fluentlyWait(new FluentWait<By>(By.className("under-submit")));
		WebElement under_submit = baseTest.getWebDriver().findElement(By.className("under-submit"));
		WebElement errorlist = under_submit.findElement(By.className("errorlist"));
		Assert.assertEquals(errorlist.findElement(By.tagName("li")).getText().trim(),"That e-mail address doesn't have an associated user account. Are you sure you've registered?");
		new LoginPage(baseTest.getWebDriver(), baseTest.getPageConfiguration()).logout();
		new LoginPage(baseTest.getWebDriver(), baseTest.getPageConfiguration()).login();
	}
}
