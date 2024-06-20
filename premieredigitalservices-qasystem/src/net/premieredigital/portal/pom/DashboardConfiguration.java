package net.premieredigital.portal.pom;

import java.lang.reflect.Field;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class DashboardConfiguration extends BaseTestConfig{
	private String service_name;
	private String provider_name;
	private String modified_status;
	private String packaged_name;
	private String non_packaged_name;
	private XSSFSheet sheet;
	public DashboardConfiguration() throws Exception{
		this.sheet = getSheet("DashboardConfiguration");
		XSSFRow header_row = sheet.getRow(0);
		XSSFRow value_row = sheet.getRow(1);
			
		for (int i = 0; header_row.getCell(i) != null; i++) {
		    Field field = getClass().getDeclaredField(header_row.getCell(i).getStringCellValue());
		    field.set(this, value_row.getCell(i).getStringCellValue());
		}
	}
	public String getServiceName() {
		return this.service_name;
	}
	public String getProviderName() {
		return this.provider_name;
	}
	public String getModifiedStatus() {
		return this.modified_status;
	}
	public String getPackagedName() {
		return this.packaged_name;
	}
	public String getNonPackagedName() {
		return this.non_packaged_name;
	}
}
