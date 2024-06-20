package net.premieredigital.portal.pom;

import java.lang.reflect.Field;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ProviderAdminConfiguration extends BaseTestConfig {
	private String problem_email1;
	private String problem_email2;
	private String delivery_email1;
	private String delivery_email2;
	private String notes;
	private String price_notes;
	private String group_option1;
	private String group_option2;
	private String file_URL;
	private String date1;
	private String date2;
	private String service;
	private String territory;
	private String mapping_field;
	private String export_value;
	private String provider_name;
	private XSSFSheet sheet;
	
	
	public ProviderAdminConfiguration() {	
		 try {
			this.sheet = getSheet("ProviderAdminConfiguration");

		XSSFRow header_row = sheet.getRow(0);
		XSSFRow value_row = sheet.getRow(1);
		
		for (int i = 0; header_row.getCell(i) != null; i++) {
		    Field field = getClass().getDeclaredField(header_row.getCell(i).getStringCellValue());
		    field.set(this, value_row.getCell(i).getStringCellValue());
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public String getProblemEmail1() {
		return problem_email1;
	}
	
	public String getProblemEmail2() {
		return problem_email2;
	}
	
	public String getDeliveryEmail1() {
		return delivery_email1;
	}
	
	public String getDeliveryEmail2() {
		return delivery_email2;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public String getPriceNotes() {
		return price_notes;
	}
	
	public String getGroupOption1() {
		return group_option1;
	}
	
	public String getGroupOption2() {
		return group_option2;
	}
	
	public String getFileURL() {
		return(System.getProperty("user.dir") + this.file_URL);
		//return file_URL;
	}
	
	public String getDate1() {
		return date1;
	}
	
	public String getDate2() {
		return date2;
	}
	
	public String getService() {
		return service;
	}
	
	public String getTerritory() {
		return territory;
	}
	
	public String getMappingField() {
		return mapping_field;
	}
	
	public String getExportValue() {
		return export_value;
	}
	public String getProvider() {
		return provider_name;
	}
}
