package net.premieredigital.portal.pom;
import java.lang.reflect.Field;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
	public class BillingAdminConfiguration extends BaseTestConfig {			
			private String name;
			private String type_of_billing_party;
			private String billing_frequency;
			private String payment_due_after;
			private String invoice_after_order_po_finished;
			private String invoice_features_separate_from_episodes;
			private String invoice_features_individually;			
			private String multiple_po_billing_forbidden;
			private String contact_emails;
			private String billing_address;
			private String billing_frequency_update;
			private String payment_due_after_update;
			private String invoice_features_separate_from_episodes_update;
			private XSSFSheet sheet;
			private Random random = new Random();
			
			public BillingAdminConfiguration() throws Exception {	
			 this.sheet = getSheet("BillingAdminConfiguration");
				XSSFRow header_row = sheet.getRow(0);
				XSSFRow value_row = sheet.getRow(1);
				
				for (int i = 0; header_row.getCell(i) != null; i++) {
				    Field field = getClass().getDeclaredField(header_row.getCell(i).getStringCellValue());
				    field.set(this, value_row.getCell(i).getStringCellValue());
				}
				this.setName();
			}
			
			public void setName() {
				this.name = name + Integer.toString(random.nextInt(1000000) + 1);
			}
			
			public String getName() {
				return name;
			}
			
			public String getTypeOfBillingParty() {
				return type_of_billing_party;
			}
			
			public String getBillingFrequency() {
				return billing_frequency;
			}
			
		    public String getPaymentDueAfter() {
				return payment_due_after;
     		}
			
			public String getInvoiceAfterOrderPOFinished() {
				return invoice_after_order_po_finished;
			}
			
     		public String getInvoiceFeaturesSeparateFromEpisodes() {
				return invoice_features_separate_from_episodes;
			}
     		
     		public String getInvoiceFeaturesIndividually() {
				return invoice_features_individually;
			}
   		   public String getMultiplePOBillingForbidden(){
				return multiple_po_billing_forbidden;
			}
		   		public String getContactEmails(){
					return contact_emails;
				}			
				public String getBillingAddress(){
					return billing_address;
				}		
				
				
				public String getBillingFrequencyUpdate(){
					return billing_frequency_update;
				}
				public String getPaymentDueAfterUpdate(){
					return payment_due_after_update;
				}
				public String getInvoiceFeaturesSeparatefromEpisodesUpdate(){
					return invoice_features_separate_from_episodes_update;
				}
	}