package net.premieredigital.portal.pom;

import java.lang.reflect.Field;
import java.util.Random;

import net.premieredigital.portal.BaseTest;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class PageConfiguration extends BaseTestConfig{
	private String username;
	private String password;
	private String rootURL;
	private String providername;
	private String username1;
	private String browserName;
	private String client_user_name;
	private Random random = new Random();
	private XSSFSheet sheet;

	public PageConfiguration(String applicationUrl) throws Exception {
		 this.sheet = getSheet("PageConfiguration");
		XSSFRow header_row = sheet.getRow(0);
		XSSFRow value_row = sheet.getRow(1);
		
		for (int i = 0; header_row.getCell(i) != null; i++) {
		    Field field = getClass().getDeclaredField(header_row.getCell(i).getStringCellValue());
		    field.set(this, value_row.getCell(i).getStringCellValue());
		}	
		this.setProviderName();
		this.setRootURL(applicationUrl);
	}
	public void setRootURL(String applicationUrl){
		rootURL = applicationUrl;
	}
	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getRootURL() {
		return rootURL;
	}
	
	public String getProviderName() {
		return providername;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername1() {
		return username1;
	}
	public void setProviderName() {
		this.providername =  providername + Integer.toString(random.nextInt(100000) + 1);
	}
	public String getClientUserName() {
		return client_user_name;
	}
	public void setClientUserName(String clientUserName) {
		this.client_user_name = clientUserName;
	}
}
