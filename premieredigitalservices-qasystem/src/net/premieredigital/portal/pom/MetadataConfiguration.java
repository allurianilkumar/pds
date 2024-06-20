package net.premieredigital.portal.pom;

import java.lang.reflect.Field;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class MetadataConfiguration extends BaseTestConfig{
	private XSSFSheet sheet;
	
	private String default_behaviour_for_title;
	private String alternate_label_for_title;
	private boolean is_title_localizable;
	private boolean is_title_required;
	private String max_length_for_title;
	private String help_text_for_title;
	
	private String default_behaviour_for_vendor_id;
	private String alternate_label_for_vendor_id;
	private boolean is_vendor_id_localizable;
	private boolean is_vendor_id_required;
	private String max_length_for_vendor_id;
	private String help_text_for_vendor_id;
	
	private String default_behaviour_for_sd_vendor_id;
	private String alternate_label_for_sd_vendor_id;
	private boolean is_sd_vendor_id_localizable;
	private boolean is_sd_vendor_id_required;
	private String max_length_for_sd_vendor_id;
	private String help_text_for_sd_vendor_id;
	
	private String default_behaviour_for_synopsis;
	private String alternate_label_for_synopsis;
	private boolean is_synopsis_localizable;
	private boolean is_synopsis_required;
	private String max_length_for_synopsis;
	private String help_text_for_synopsis;
	
	private String default_behaviour_for_short_synopsis;
	private String alternate_label_for_short_synopsis;
	private boolean is_short_synopsis_localizable;
	private boolean is_short_synopsis_required;
	private String max_length_for_short_synopsis;
	private String help_text_for_short_synopsis;
	
	private String default_behaviour_for_production_company;
	private String alternate_label_for_production_company;
	private boolean is_production_company_localizable;
	private boolean is_production_company_required;
	private String max_length_for_production_company;
	private String help_text_for_production_company;
	
	private String default_behaviour_for_copyright;
	private String alternate_label_for_copyright;
	private boolean is_copyright_localizable;
	private boolean is_copyright_required;
	private String max_length_for_copyright;
	private String help_text_for_copyright;
	
	private String default_behaviour_for_episode_production_number;
	private String alternate_label_for_episode_production_number;
	private boolean is_episode_production_number_localizable;
	private boolean is_episode_production_number_required;
	private String max_length_for_episode_production_number;
	private String help_text_for_episode_production_number;
	
	private String default_behaviour_for_container_position;
	private String alternate_label_for_container_position;
	private boolean is_container_position_localizable;
	private boolean is_container_position_required;
	private String max_length_for_container_position;
	private String help_text_for_container_position;
	
	private String default_behaviour_for_episode_id;
	private String alternate_label_for_episode_id;
	private boolean is_episode_id_localizable;
	private boolean is_episode_id_required;
	private String max_length_for_episode_id;
	private String help_text_for_episode_id;
	
	private String default_behaviour_for_run_time;
	private String alternate_label_for_run_time;
	private boolean is_run_time_localizable;
	private boolean is_run_time_required;
	private String max_length_for_run_time;
	private String help_text_for_run_time;
	
	private String default_behaviour_for_trailer_run_time;
	private String alternate_label_for_trailer_run_time;
	private boolean is_trailer_run_time_localizable;
	private boolean is_trailer_run_time_required;
	private String max_length_for_trailer_run_time;
	private String help_text_for_trailer_run_time;
	
	private String	order_item_metadata_crew_person_name;
	private String	order_item_metadata_crew_person_role_name;
	private String	order_item_metadata_cast_person_name;
	private String	order_item_metadata_cast_person_character;

	private String	order_item_metadata_crew_edit_person_role_name;
	private String	order_item_metadata_cast_edit_person_name;
	private String	order_item_metadata_cast_edit_person_character;
	
	public MetadataConfiguration(String sheet_name) throws Exception {
		this.sheet = getSheet(sheet_name);
		XSSFRow header_row = sheet.getRow(0);
		XSSFRow value_row = sheet.getRow(1);
			
		for (int i = 0; header_row.getCell(i) != null; i++) {
		    Field field = getClass().getDeclaredField(header_row.getCell(i).getStringCellValue());
		    try {
		    	if (header_row.getCell(i).getStringCellValue().contains("default_behaviour_for"))
					field.set(this, "Use Raw Title");
				else
					field.set(this, value_row.getCell(i).getStringCellValue());
		    }
		    catch (Exception e) {
		    	try {
		    		field.set(this, value_row.getCell(i).getBooleanCellValue());
		    	}
		    	catch (Exception ex) {
	    			try {
	    				field.set(this, false);
	    			}
	    			catch(Exception ex2) {
	    				field.set(this, null);
	    			}
		    	}
		    }
		}
	}
	public String getOrderItemMetadataCrewPersonName(){
		return this.order_item_metadata_crew_person_name;
	}
	public String getOrderItemMetadataCrewPersonRoleName(){
		return this.order_item_metadata_crew_person_role_name;
	}
	public String getOrderItemMetadataCast_person_name(){
		return this.order_item_metadata_cast_person_name;
	}
	public String getOrderItemMetadataCastPersonCharacter(){
		return this.order_item_metadata_cast_person_character;
	}
	public String getOrderItemMetadataCrewEditPersonRoleName(){
		return this.order_item_metadata_crew_edit_person_role_name;
	}
	public String getOrderItemMetadataCastEditPersonName(){
		return this.order_item_metadata_cast_edit_person_name;
	}
	public String getOrderItemMetadataCastEditPersonCharacter(){
		return this.order_item_metadata_cast_edit_person_character;
	}
	public String getDefaultBehaviourForTitle() {
		return this.default_behaviour_for_title;
	}
	public String getAlternateLabelForTitle() {
		return this.alternate_label_for_title;
	}
	public boolean getIsTitleLocalizable() {
		return this.is_title_localizable;
	}
	public boolean getIsTitleRequired() {
		return this.is_title_required;
	}
	public String getMaxLengthForTitle() {
		return this.max_length_for_title;
	}
	public String getHelpTextForTitle() {
		return this.help_text_for_title;
	}
	
	public String getDefaultBehaviourForVendorId() {
		return this.default_behaviour_for_vendor_id;
	}
	public String getAlternateLabelForVendorId() {
		return this.alternate_label_for_vendor_id;
	}
	public boolean getIsVendorIdLocalizable() {
		return this.is_vendor_id_localizable;
	}
	public boolean getIsVendorIdRequired() {
		return this.is_vendor_id_required;
	}
	public String getMaxLengthForVendorId() {
		return this.max_length_for_vendor_id ;
	}
	public String getHelpTextForVendorId() {
		return this.help_text_for_vendor_id;
	}
	
	public String getDefaultBehaviourForSDVendorId() {
		return this.default_behaviour_for_sd_vendor_id;
	}
	public String getAlternateLabelForSDVendorId() {
		return this.alternate_label_for_sd_vendor_id;
	}
	public boolean getIsSDVendorIdLocalizable() {
		return this.is_sd_vendor_id_localizable;
	}
	public boolean getIsSDVendorIdRequired() {
		return this.is_sd_vendor_id_required;
	}
	public String getMaxLengthForSDVendorId() {
		return this.max_length_for_sd_vendor_id;
	}
	public String getHelpTextForSDVendorId() {
		return this.help_text_for_sd_vendor_id;
	}
	
	public String getDefaultBehaviourForSynopsis() {
		return this.default_behaviour_for_synopsis;
	}
	public String getAlternateLabelForSynopsis() {
		return this.alternate_label_for_synopsis;
	}
	public boolean getIsSynopsisLocalizable() {
		return this.is_synopsis_localizable;
	}
	public boolean getIsSynopsisRequired() {
		return this.is_synopsis_required;
	}
	public String getMaxLengthForSynopsis() {
		return this.max_length_for_synopsis;
	}
	public String getHelpTextForSynopsis() {
		return this.help_text_for_synopsis;
	}
	
	public String getDefaultBehaviourForShortSynopsis() {
		return this.default_behaviour_for_short_synopsis;
	}
	public String getAlternateLabelForShortSynopsis() {
		return this.alternate_label_for_short_synopsis;
	}
	public boolean getIsShortSynopsisLocalizable() {
		return this.is_short_synopsis_localizable;
	}
	public boolean getIsShortSynopsisRequired() {
		return this.is_short_synopsis_required;
	}
	public String getMaxLengthForShortSynopsis() {
		return this.max_length_for_short_synopsis;
	}
	public String getHelpTextForShortSynopsis() {
		return this.help_text_for_short_synopsis;
	}
	
	public String getDefaultBehaviourForProductionCompany() {
		return this.default_behaviour_for_production_company;
	}
	public String getAlternateLabelForProductionCompany() {
		return this.alternate_label_for_production_company;
	}
	public boolean getIsProductionCompanyLocalizable() {
		return this.is_production_company_localizable;
	}
	public boolean getIsProductionCompanyRequired() {
		return this.is_production_company_required;
	}
	public String getMaxLengthForProductionCompany() {
		return this.max_length_for_production_company;
	}
	public String getHelpTextForProductionCompany() {
		return this.help_text_for_production_company;
	}
	
	public String getDefaultBehaviourForCopyright() {
		return this.default_behaviour_for_copyright;
	}
	public String getAlternateLabelForCopyright() {
		return this.alternate_label_for_copyright;
	}
	public boolean getIsCopyrightLocalizable() {
		return this.is_copyright_localizable;
	}
	public boolean getIsCopyrightRequired() {
		return this.is_copyright_required;
	}
	public String getMaxLengthForCopyright() {
		return this.max_length_for_copyright;
	}
	public String getHelpTextForCopyright() {
		return this.help_text_for_copyright;
	}
	
	public String getDefaultBehaviourForEpisodeProductionNumber() {
		return this.default_behaviour_for_episode_production_number;
	}
	public String getAlternateLabelForEpisodeProductionNumber() {
		return this.alternate_label_for_episode_production_number;
	}
	public boolean getIsEpisodeProductionNumberLocalizable() {
		return this.is_episode_production_number_localizable;
	}
	public boolean getIsEpisodeProductionNumberRequired() {
		return this.is_episode_production_number_required;
	}
	public String getMaxLengthForEpisodeProductionNumber() {
		return this.max_length_for_episode_production_number;
	}
	public String getHelpTextForEpisodeProductionNumber() {
		return this.help_text_for_episode_production_number;
	}
	
	public String getDefaultBehaviourForContainerPosition() {
		return this.default_behaviour_for_container_position;
	}
	public String getAlternateLabelForContainerPosition() {
		return this.alternate_label_for_container_position;
	}
	public boolean getIsContainerPositionLocalizable() {
		return this.is_container_position_localizable;
	}
	public boolean getIsContainerPositionRequired() {
		return this.is_container_position_required;
	}
	public String getMaxLengthForContainerPosition() {
		return this.max_length_for_container_position;
	}
	public String getHelpTextForContainerPosition() {
		return this.help_text_for_container_position;
	}
	
	public String getDefaultBehaviourForRunTime() {
		return this.default_behaviour_for_run_time;
	}
	public String getAlternateLabelForRunTime() {
		return this.alternate_label_for_run_time;
	}
	public boolean getIsRunTimeLocalizable() {
		return this.is_run_time_localizable;
	}
	public boolean getIsRunTimeRequired() {
		return this.is_run_time_required;
	}
	public String getMaxLengthForRunTime() {
		return this.max_length_for_run_time;
	}
	public String getHelpTextForRunTime() {
		return this.help_text_for_run_time;
	}
	
	public String getDefaultBehaviourForEpisodeID() {
		return this.default_behaviour_for_episode_id;
	}
	public String getAlternateLabelForEpisodeID() {
		return this.alternate_label_for_episode_id;
	}
	public boolean getIsEpisodeIDLocalizable() {
		return this.is_episode_id_localizable;
	}
	public boolean getIsEpisodeIDRequired() {
		return this.is_episode_id_required;
	}
	public String getMaxLengthForEpisodeID() {
		return this.max_length_for_episode_id;
	}
	public String getHelpTextForEpisodeID() {
		return this.help_text_for_episode_id;
	}
	
	public String getDefaultBehaviourForTrailerRunTime() {
		return this.default_behaviour_for_trailer_run_time;
	}
	public String getAlternateLabelForTrailerRunTime() {
		return this.alternate_label_for_trailer_run_time;
	}
	public boolean getIsTrailerRunTimeLocalizable() {
		return this.is_trailer_run_time_localizable;
	}
	public boolean getIsTrailerRunTimeRequired() {
		return this.is_trailer_run_time_required;
	}
	public String getMaxLengthForTrailerRunTime() {
		return this.max_length_for_trailer_run_time;
	}
	public String getHelpTextForTrailerRunTime() {
		return this.help_text_for_trailer_run_time;
	}
}
