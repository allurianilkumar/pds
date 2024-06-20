package net.premieredigital.portal.pom;

import java.lang.reflect.Field;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class UserAdminConfiguration extends BaseTestConfig {	
			private String first_name;
			private String last_name;
			private String email;
			private String role;
			private String provider;
			private String services;
			private String password;
			private String random_number;
			private Random random = new Random();
			private XSSFSheet sheet;
			
			public UserAdminConfiguration() throws Exception {	
				 this.sheet = getSheet("UserAdminConfiguration");
				XSSFRow header_row = sheet.getRow(0);
				XSSFRow value_row = sheet.getRow(1);
				
				for (int i = 0; header_row.getCell(i) != null; i++) {
				    Field field = getClass().getDeclaredField(header_row.getCell(i).getStringCellValue());
				    field.set(this, value_row.getCell(i).getStringCellValue());
				}
				this.setRandomNumber();
				this.setEmail();
				this.setFirstName();
			}
					
			public String getFirstName() {
				return first_name;
			}
			
			public String getLastName() {
				return last_name;
			}
			
			public String getEmail() {
				return email;
			}
			
			public String getRole() {
				return role;
			}
			
			public String getProvider() {
				return provider;
			}
			
			public String getServices() {
				return services;
			}
			public String getPassword(){
				return password;
				
			}
			public void setEmail() {
				this.email =  random_number+email;
			}
			public void setFirstName() {
				this.first_name =  first_name+random_number;
			}
			public void setRandomNumber() {
				this.random_number = Integer.toString(random.nextInt(100000000)+1);
			}
			
	}



