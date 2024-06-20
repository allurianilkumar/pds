package net.premieredigital.portal.pom;

import java.lang.reflect.Field;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class OrderItemConfiguration extends BaseTestConfig{
	private String status_for_trailer;
	private String status_for_feature;
	private String status_for_artwork;
	private String status_for_meta_data;
	private String note_feature;
	private String view;
	private String status;
	private String message_type;
	private String client_notes;
	private String message;
	private String order_notes_for_main_task;
	private String order_item_notes_for_main_task;
	private String feature_for_main_task;
	private String trailer_for_main_task;
	private String cc_for_main_task;
	private String artwork_for_main_task;
	private String metadata_for_main_task;
	private String audit_package_for_main_task;
	private String provider_approval_for_main_task;
	private String delivery_for_main_task;

	private XSSFSheet sheet;
	public OrderItemConfiguration() throws Exception{
		this.sheet = getSheet("OrderItemConfiguration");
		XSSFRow header_row = sheet.getRow(0);
		XSSFRow value_row = sheet.getRow(1);
			
		for (int i = 0; header_row.getCell(i) != null; i++) {
		    Field field = getClass().getDeclaredField(header_row.getCell(i).getStringCellValue());
		    field.set(this, value_row.getCell(i).getStringCellValue());
		}
	}
	public String getStatusForTrailer() {
		return this.status_for_trailer;
	}
	public String getStatusForFeature() {
		return this.status_for_feature;
	}
	public String getStatusForArtwork() {
		return this.status_for_artwork;
	}
	public String getStatusForMetadata() {
		return this.status_for_meta_data;
	}
	public String getNoteFeature(){
		return this.note_feature;
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
	public String getMessageType() {
		return message_type;
	}
	public void setMessageType(String message_type) {
		this.message_type = message_type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getOrderNotesForMainTask() {
		return order_notes_for_main_task;
	}
	public void setOrderNotesForMainTask(String order_notes_for_main_task) {
		this.order_notes_for_main_task = order_notes_for_main_task;
	}
	public String getOrderItemNotesForMainTask() {
		return order_item_notes_for_main_task;
	}
	public void setOrderItemNotesForMainTask(
			String order_item_notes_for_main_task) {
		this.order_item_notes_for_main_task = order_item_notes_for_main_task;
	}
	public String getFeatureForMainTask() {
		return feature_for_main_task;
	}
	public void setFeatureForMainTask(String feature_for_main_task) {
		this.feature_for_main_task = feature_for_main_task;
	}
	public String getTrailerForMainTask() {
		return trailer_for_main_task;
	}
	public void setTrailerForMainTask(String trailer_for_main_task) {
		this.trailer_for_main_task = trailer_for_main_task;
	}
	public String getCcForMainTask() {
		return cc_for_main_task;
	}
	public void setCcForMainTask(String cc_for_main_task) {
		this.cc_for_main_task = cc_for_main_task;
	}
	public String getArtworkForMainTask() {
		return artwork_for_main_task;
	}
	public void setArtworkForMainTask(String artwork_for_main_task) {
		this.artwork_for_main_task = artwork_for_main_task;
	}
	public String getMetadataForMainTask() {
		return metadata_for_main_task;
	}
	public void setMetadataForMainTask(String metadata_for_main_task) {
		this.metadata_for_main_task = metadata_for_main_task;
	}
	public String getAuditPackageForMainTask() {
		return audit_package_for_main_task;
	}
	public void setAuditPackageForMainTask(
			String audit_package_for_main_task) {
		this.audit_package_for_main_task = audit_package_for_main_task;
	}
	public String getProviderApprovalForMainTask() {
		return provider_approval_for_main_task;
	}
	public void setProviderApprovalForMainTask(
			String provider_approval_for_main_task) {
		this.provider_approval_for_main_task = provider_approval_for_main_task;
	}
	public String getDeliveryForMainTask() {
		return delivery_for_main_task;
	}
	public void setDeliveryForMainTask(String delivery_for_main_task) {
		this.delivery_for_main_task = delivery_for_main_task;
	}
}
