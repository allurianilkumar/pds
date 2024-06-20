package net.premieredigital.portal.pom;
	import java.io.FileInputStream;
	import java.lang.reflect.Field;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;	
        	public abstract class BaseTestConfig {
			public XSSFSheet getSheet(String sheetname) throws Exception
			{
				FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//resources//TestData.xlsx");							
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				XSSFSheet sheet= wb.getSheet(sheetname);
				return sheet;
								
			}
			public void setField(Object entity, String fieldName, String value)
			        throws NoSuchFieldException, IllegalAccessException {
			    Field field = entity.getClass().getDeclaredField(fieldName);
			    field.set(entity, value);
			}			
		}
		



