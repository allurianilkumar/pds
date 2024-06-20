package net.premieredigital.portal.pom;

import java.lang.reflect.Field;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class LocaleAdminConfiguration extends BaseTestConfig{
	private String code;
	private String edit_code;
	private String description;
	private String edit_description;
	private XSSFSheet sheet;
	
	public LocaleAdminConfiguration() throws Exception {	
		 this.sheet = getSheet("LocaleAdminConfiguration");
		XSSFRow header_row = sheet.getRow(0);
		XSSFRow value_row = sheet.getRow(1);
		
		for (int i = 0; header_row.getCell(i) != null; i++) {
		    Field field = getClass().getDeclaredField(header_row.getCell(i).getStringCellValue());
		    field.set(this, value_row.getCell(i).getStringCellValue());
		}	
	}
	
	
	public String getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getEditDescription() {
		return edit_description;
	}
	
	public String getEditCode() {
		return edit_code;
	}
}
