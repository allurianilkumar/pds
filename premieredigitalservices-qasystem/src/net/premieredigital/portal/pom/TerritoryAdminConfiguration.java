package net.premieredigital.portal.pom;

import java.lang.reflect.Field;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class TerritoryAdminConfiguration extends BaseTestConfig {
	private String two_letter_code;
	private String full_name;
	private String edit_full_name;
	private XSSFSheet sheet;
	public TerritoryAdminConfiguration() throws Exception {	
		 this.sheet = getSheet("TerritoryAdminConfiguration");
		XSSFRow header_row = sheet.getRow(0);
		XSSFRow value_row = sheet.getRow(1);
		
		for (int i = 0; header_row.getCell(i) != null; i++) {
		    Field field = getClass().getDeclaredField(header_row.getCell(i).getStringCellValue());
		    field.set(this, value_row.getCell(i).getStringCellValue());
		}	
	}
	public String getTwoLetterCode() {
		return two_letter_code;
	}
	
	public String getFullName() {
		return full_name;
	}
	
	public String getEditFullName() {
		return edit_full_name;
	}
}
