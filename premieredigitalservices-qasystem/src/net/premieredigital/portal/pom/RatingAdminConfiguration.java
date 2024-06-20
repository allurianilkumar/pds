package net.premieredigital.portal.pom;

import java.lang.reflect.Field;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class RatingAdminConfiguration extends BaseTestConfig{
  private String territory;
  private String system_name;
  private String rating1;
  private String rating2;
  private String rating3;
  private String rating4;
  private String edit_rating1;
  private String edit_rating2;
  private String edit_rating3;
  private String system_type;
  private String two_letter_code;
  private String full_name;
  private String edit_territory;
  private String edit_system_name;
  private String delete_rating1;
  private XSSFSheet sheet;
  
  public RatingAdminConfiguration() throws Exception {	
		 this.sheet = getSheet("RatingAdminConfiguration");
		XSSFRow header_row = sheet.getRow(0);
		XSSFRow value_row = sheet.getRow(1);
		
		for (int i = 0; header_row.getCell(i) != null; i++) {
		    Field field = getClass().getDeclaredField(header_row.getCell(i).getStringCellValue());
		    field.set(this, value_row.getCell(i).getStringCellValue());
		}	
	}
  
  public String getTerritory() {
    return this.territory;
  }
  
  public String getSystemName() {
    return this.system_name;
  }
  
  public String getRating1() {
    return this.rating1;
  }
  
  public String getRating2() {
    return this.rating2;
  }
  
  public String getRating3() {
    return this.rating3;
  }
  
  public String getRating4() {
    return this.rating4;
  }
  
  public String getEditRating1() {
    return this.edit_rating1;
  }
  
  public String getEditRating2() {
    return this.edit_rating2;
  }
  
  public String getEditRating3() {
    return this.edit_rating3;
  }
  
  public String getSystemType() {
    return this.system_type;
  }
  
  public String getTwoLetterCode() {
    return this.two_letter_code;
  }
  
  public String getFullName() {
    return this.full_name;
  }
  
  public String getEdittedTerritory() {
	  return this.edit_territory;
  }
  
  public String getEdittedSystemName() {
	  return this.edit_system_name;
  }
  
  public String getDeleteRating1() {
	  return this.delete_rating1;
  }
}
