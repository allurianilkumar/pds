package net.premieredigital.portal.pom;

import java.lang.reflect.Field;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class MasteringConfiguration extends BaseTestConfig{
	private String video_type;
	private String asset_language;
	private String title_language;
	private String stage;
	private String video_format;
	private String resolution;
	private String VASP;
	private String PASP;
	private String title;
	private String provider;
	private String language;
	private String asset_language_search;
	private String modified_video_type;
	private String modified_stage;
	private String modified_resolution;
	private String modified_video_format;
	private String modified_language;
	private String modified_provider;
	private String status;
	private String modified_status;
	
	private String start_time;
	private String end_time;
	private String issue;
	private String severity;
	private String screenshot;
	private String fix_status;
	private String time_to_fix;
	private String fix_notes;
	private XSSFSheet sheet;
	public MasteringConfiguration() throws Exception{
		this.sheet = getSheet("MasteringConfiguration");
		XSSFRow header_row = sheet.getRow(0);
		XSSFRow value_row = sheet.getRow(1);
			
		for (int i = 0; header_row.getCell(i) != null; i++) {
		    Field field = getClass().getDeclaredField(header_row.getCell(i).getStringCellValue());
		    field.set(this, value_row.getCell(i).getStringCellValue());
		}
	}
	public String getVideoType() {
		return this.video_type;
	}
	public String getAssetLanguage() {
		return this.asset_language;
	}
	public String getVideoFormat() {
		return this.video_format;
	}
	public String getStage() {
		return this.stage;
	}
	public String getResolution() {
		return this.resolution;
	}
	public String getVASP() {
		return this.VASP;
	}
	public String getPASP() {
		return this.PASP;
	}
	public String getTitle() {
		return this.title;
	}
	public String getProvider() {
		return this.provider;
	}
	public String getLanguage() {
		return this.language;
	}
	public String getTitleLanguage() {
		return this.title_language;
	}
	public String getAssetLanguageForSearch() {
		return this.asset_language_search;
	}
	public String getModifiedVideoFormat() {
		return this.modified_video_format;
	}
	public String getModifiedVideoType() {
		return this.modified_video_type;
	}
	public String getModifiedResolution() {
		return this.modified_resolution;
	}
	public String getModifiedStage() {
		return this.modified_stage;
	}
	public String getModifiedLanguage() {
		return this.modified_language;
	}
	public String getModifiedProvider() {
		return this.modified_provider;
	}
	public String getStatus() {
		return this.status;
	}
	public String getModifiedStatus() {
		return this.modified_status;
	}
	public void setTitle(String order_no) {
		this.title = this.title + order_no;
	}
	public String getStartTime(){
		return this.start_time;
	}
	public String getEndTime(){
		return this.end_time;
	}
	public String getIssue(){
		return this.issue;
	}
	public String getSeverity(){
		return this.severity;
	}
	public String getScreenshot(){
		String rootImage = new String(System.getProperty("user.dir")+this.screenshot);
		return rootImage;
	}
	public String getFixStatus(){
		return this.fix_status;
	}
	public String getTimeToFix(){
		return this.time_to_fix;
	}
	public String getFixNotes(){
		return this.fix_notes;
	}
}
