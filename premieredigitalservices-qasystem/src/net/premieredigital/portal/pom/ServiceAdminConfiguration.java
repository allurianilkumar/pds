package net.premieredigital.portal.pom;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ServiceAdminConfiguration extends BaseTestConfig {
	private String service_name;
	private String description;
	private String address;
	private String price_notes;
	private String requirements;
	private String packaging_method;
	private String file_renaming;
	private String transcode_folder;
	private String delivery_method;
	private String provider_name;
	private String publication_date;
	private String category;
	private String file_path;
	private String uploaded_date;
	private Random random = new Random();
	private XSSFSheet sheet;
	
public ServiceAdminConfiguration() throws Exception {	
	 this.sheet = getSheet("ServiceAdminConfiguration");
	XSSFRow header_row = sheet.getRow(0);
	XSSFRow value_row = sheet.getRow(1);
	
	for (int i = 0; header_row.getCell(i) != null; i++) {
	    Field field = getClass().getDeclaredField(header_row.getCell(i).getStringCellValue());
	    field.set(this, value_row.getCell(i).getStringCellValue());
	}
	this.setServiceName();
}

    public String getServiceName() {
		return this.service_name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public String getPriceNotes() {
		return this.price_notes;
	}
	
	public String getRequirements() {
		return this.requirements;
	}
	
	public String getPackagingMethod() {
		return this.packaging_method;
	}
	
	public String getFileRenaming() {
		return this.file_renaming;
	}
	
	public String getTranscodeFolder() {
		return this.transcode_folder;
	}
	
	public String getDeliveryMethod() {
		return this.delivery_method;
	}
	
	public String getProviderName() {
		return this.provider_name;
	}
	
	public String getPublicationDate() {
		return this.publication_date;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public String getFilePath() {
		return(System.getProperty("user.dir") + this.file_path);
		//return this.file_path;
	}
	
	public String getUploadedDate() {
		return this.uploaded_date;
	}
	public void setServiceName() {
		this.service_name = service_name + Integer.toString(random.nextInt(100000) + 1);
//		return service_name;
	}
}