package net.premieredigital.portal.pom;

import java.lang.reflect.Field;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class AssetsConfiguration extends BaseTestConfig {
	private String title;
	private String asset_stage;
	private String provider;
	private String service;
	private XSSFSheet sheet;
	public AssetsConfiguration() throws Exception{
		this.sheet = getSheet("AssetsConfiguration");
		XSSFRow header_row = sheet.getRow(0);
		XSSFRow value_row = sheet.getRow(1);
			
		for (int i = 0; header_row.getCell(i) != null; i++) {
		    Field field = getClass().getDeclaredField(header_row.getCell(i).getStringCellValue());
		    field.set(this, value_row.getCell(i).getStringCellValue());
		}
	}
	public String getTitle() {
		return this.title;
	}
	public String getAssetStage() {
		return this.asset_stage;
	}
	public String getProvider() {
		return this.provider;
	}	
	public String getService(){
		return this.service;
	}
}
