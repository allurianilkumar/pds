package net.premieredigital.portal.pom;

import java.lang.reflect.Field;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class SourceAdminConfiguration extends BaseTestConfig {
	private String name;
	private String edit_name;
	private Random random = new Random();
	private XSSFSheet sheet;
	
	public SourceAdminConfiguration() throws Exception {	
		 this.sheet = getSheet("SourceAdminConfiguration");
		XSSFRow header_row = sheet.getRow(0);
		XSSFRow value_row = sheet.getRow(1);
		
		for (int i = 0; header_row.getCell(i) != null; i++) {
			Field field = getClass().getDeclaredField(header_row.getCell(i).getStringCellValue());
		    field.set(this, value_row.getCell(i).getStringCellValue());
		}
		this.setName();
		this.setEditName();
	}
	
	public String getName() {
		return name;
	}
	
	public String getEditName() {
		return edit_name;
	}
	
	public void setName() {
		this.name = name + Integer.toString(random.nextInt(1000000) + 1);
	}
	
	public void setEditName() {
		this.edit_name = edit_name + Integer.toString(random.nextInt(1000000) + 1);
	}
}
