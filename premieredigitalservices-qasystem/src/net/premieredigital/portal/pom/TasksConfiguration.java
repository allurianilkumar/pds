package net.premieredigital.portal.pom;

import java.lang.reflect.Field;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

	public class TasksConfiguration extends BaseTestConfig{
		private String assigned_to;
		private String task_status;
		private String task_provider;
		private String view;
		private String status;
		private String add_type;
		private String client_notes;
		private String message;
		private XSSFSheet sheet;
		public TasksConfiguration() throws Exception {
			this.sheet = getSheet("TaskConfiguration");
			XSSFRow header_row = sheet.getRow(0);
			XSSFRow value_row = sheet.getRow(1);
			for (int i = 0; header_row.getCell(i) != null; i++) {
			    Field field = getClass().getDeclaredField(header_row.getCell(i).getStringCellValue());
			    field.set(this, value_row.getCell(i).getStringCellValue());
			}
		}
		public String getAssignedTo() {
			return this.assigned_to;
		}
		public String getTaskStatus() {
			return this.task_status;
		}
		public String getDefaultTaskProvider(){
			return this.task_provider;
		}
		public String getView() {
			return view;
		}
		public void setView(String view) {
			this.view = view;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getClientNotes() {
			return client_notes;
		}
		public void setClientNotes(String client_notes) {
			this.client_notes = client_notes;
		}
		public String getAddType() {
			return add_type;
		}
		public void setAddType(String add_type) {
			this.add_type = add_type;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
}
