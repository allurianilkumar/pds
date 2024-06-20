package net.premieredigital.portal.pom;

import java.lang.reflect.Field;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;



public class OrderConfiguration extends BaseTestConfig{
	
	private Random random = new Random();
	private String provider_name;
	private String order_number;
	private String service;
	private String due_date;
	private String title1;
	private String title2;
	private String title3;
	private String language;
	private String requirement1;
	private String requirement2;
	private String requirement3;
	private String aspect_ratio;
	private String resolution;
	private String default_provider;
	private String status;
	private String invoice;
	private String search_name;
	private XSSFSheet sheet;

	public OrderConfiguration() throws Exception {
		this.sheet = getSheet("OrderConfiguration");
		XSSFRow header_row = sheet.getRow(0);
		XSSFRow value_row = sheet.getRow(1);
			
		for (int i = 0; header_row.getCell(i) != null; i++) {
		    Field field = getClass().getDeclaredField(header_row.getCell(i).getStringCellValue());
		    field.set(this, value_row.getCell(i).getStringCellValue());
		}
		this.setOrderNumber();
	}
	
	public String getProviderName() {
		return this.provider_name;
	}
	
	public String getOrderNumber() {
		return this.order_number;
	}
	
	public String getService() {
		return this.service;
	}
	
	public String getDueDate() {
		return this.due_date;
	}
	
	public String getTitle1() {
		return this.title1 + this.order_number;
	}
	
	public String getTitle2() {
		return this.title2 + this.order_number;
	}
	
	public String getTitle3() {
		return this.title3 + this.order_number;
	}
	public void setTitle3(String title3){
		this.title3 = title3;
	}
	public String getLanguage() {
		return this.language;
	}
	
	public String getRequirement1() {
		return this.requirement1;
	}
	
	public String getRequirement2() {
		return this.requirement2;
	}
	
	public String getRequirement3() {
		return this.requirement3;
	}
	
	public String getAspectRatio() {
		return this.aspect_ratio;
	}
	
	public String getResolution() {
		return this.resolution;
	}
	public String getDefaultProvider(){
		return this.default_provider;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public String getInvoice() {
		return this.invoice;
	}
	
//	public void setTitle1() {
//		this.title1 = title1 + Integer.toString(random.nextInt(100000)+1);
//	}
//	
//	public void setTitle2() {
//		this.title2 = title2 + Integer.toString(random.nextInt(100000)+1);
//	}
//	
//	public void setTitle3() {
//		this.title3 = title3 + Integer.toString(random.nextInt(100000)+1);
//	}
	
	public void setOrderNumber() {
		order_number = Integer.toString(random.nextInt(100000000)+1);
	}
	public void setOrderNumber(String orderNumber) {
		order_number = orderNumber;
	}
	public void setService(String service_name) {
		this.service = service_name;
	}

	public String getSearchName() {
		return search_name;
	}

	public void setSearchName(String search_name) {
		this.search_name = search_name;
	}
}
