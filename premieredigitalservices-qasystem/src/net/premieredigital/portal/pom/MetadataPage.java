package net.premieredigital.portal.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class MetadataPage extends BasePage{
	private OrderConfiguration orderConfig;
	private OrderItemConfiguration orderItemConfig;
	private MasteringConfiguration masteringConfig;
	private PageConfiguration pageConfig;
	private ServiceAdminConfiguration serviceAdminConfig;
	private MetadataConfiguration metadataConfig;
	private OrderItemPage orderItemPage;
	public MetadataPage(WebDriver driver,PageConfiguration pageConfig,ServiceAdminConfiguration serviceConfig,MetadataConfiguration metadataConfig, OrderItemPage orderItemPage, OrderConfiguration orderConfig) {
		this.driver = driver;
		this.pageConfig = pageConfig;
		this.serviceAdminConfig = serviceConfig;
		this.metadataConfig = metadataConfig;
		this.orderItemPage = orderItemPage;
		this.orderConfig = orderConfig;
	}
	public void modifyCustomizedMetadataLayoutForAmazon() {
		this.driver.get(getPageURL());
		assertPageTitle("Services");
		fluentlyWait(new FluentWait<By>(By.linkText(serviceAdminConfig.getServiceName())));
		driver.findElement(By.linkText(serviceAdminConfig.getServiceName())).click();
		assertPageTitle(serviceAdminConfig.getServiceName());
		fluentlyWait(new FluentWait<By>(By.className("subset")));
		WebElement subset_grid = driver.findElement(By.className("subset"));
		List<WebElement> grid_rows = subset_grid.findElements(By.className("grid_row"));

		for(WebElement grid_row : grid_rows) {
			if(grid_row.findElements(By.className("grid_cell")).size()>0) {
				List<WebElement> grid_cells = grid_row.findElements(By.className("grid_cell"));
				if(grid_cells.get(0).getText().contains("Custom"))
					break;
				if(grid_cells.get(0).getText().trim().equals("Title")) {
					//System.out.println("In title");
					if(metadataConfig.getDefaultBehaviourForTitle()!=null) {
						WebElement select = grid_cells.get(1).findElement(By.tagName("select"));
						new Select(select).selectByVisibleText(metadataConfig.getDefaultBehaviourForTitle());
					}
					if(metadataConfig.getAlternateLabelForTitle()!=null)
					grid_cells.get(2).findElement(By.name("metadata_def_alt_label")).sendKeys(metadataConfig.getAlternateLabelForTitle());
					List<WebElement> inputs = grid_cells.get(3).findElements(By.tagName("input"));
					if(metadataConfig.getIsTitleLocalizable()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					inputs = grid_cells.get(4).findElements(By.tagName("input"));
					if(metadataConfig.getIsTitleRequired()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					if(metadataConfig.getMaxLengthForTitle()!=null)
					grid_cells.get(5).findElement(By.name("metadata_def_max_length")).sendKeys(metadataConfig.getMaxLengthForTitle());
					try {
						if(metadataConfig.getHelpTextForTitle()!=null) {
							grid_cells.get(6).findElement(By.className("note_marker")).click();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).clear();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).sendKeys(metadataConfig.getHelpTextForTitle());
							Thread.sleep(1000);
							grid_cells.get(6).findElement(By.className("popup_message_ok")).click();
						}
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(grid_cells.get(0).getText().trim().equals("Vendor ID")) {
					if(metadataConfig.getDefaultBehaviourForVendorId()!=null) {
						WebElement select = grid_cells.get(1).findElement(By.tagName("select"));
						new Select(select).selectByVisibleText(metadataConfig.getDefaultBehaviourForVendorId());
					}
					if(metadataConfig.getAlternateLabelForVendorId()!=null)
					grid_cells.get(2).findElement(By.name("metadata_def_alt_label")).sendKeys(metadataConfig.getAlternateLabelForVendorId());
					List<WebElement> inputs = grid_cells.get(3).findElements(By.tagName("input"));
					if(metadataConfig.getIsVendorIdLocalizable()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					inputs = grid_cells.get(4).findElements(By.tagName("input"));
					if(metadataConfig.getIsVendorIdRequired()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					if(metadataConfig.getMaxLengthForVendorId()!=null)
					grid_cells.get(5).findElement(By.name("metadata_def_max_length")).sendKeys(metadataConfig.getMaxLengthForVendorId());
					try {
						if(metadataConfig.getHelpTextForVendorId()!=null) {
							grid_cells.get(6).findElement(By.className("note_marker")).click();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).clear();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).sendKeys(metadataConfig.getHelpTextForVendorId());
							Thread.sleep(1000);
							grid_cells.get(6).findElement(By.className("popup_message_ok")).click();
						}
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(grid_cells.get(0).getText().trim().equals("SD Vendor ID")) {
					if(metadataConfig.getDefaultBehaviourForSDVendorId()!=null){
						WebElement select = grid_cells.get(1).findElement(By.tagName("select"));
						new Select(select).selectByVisibleText(metadataConfig.getDefaultBehaviourForSDVendorId());
					}
					if(metadataConfig.getAlternateLabelForSDVendorId()!=null)
					grid_cells.get(2).findElement(By.name("metadata_def_alt_label")).sendKeys(metadataConfig.getAlternateLabelForSDVendorId());
					List<WebElement> inputs = grid_cells.get(3).findElements(By.tagName("input"));
					if(metadataConfig.getIsSDVendorIdLocalizable()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					inputs = grid_cells.get(4).findElements(By.tagName("input"));
					if(metadataConfig.getIsSDVendorIdRequired()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					if(metadataConfig.getMaxLengthForSDVendorId()!=null)
					grid_cells.get(5).findElement(By.name("metadata_def_max_length")).sendKeys(metadataConfig.getMaxLengthForSDVendorId());
					try {
						if(metadataConfig.getHelpTextForSDVendorId()!=null) {
							grid_cells.get(6).findElement(By.className("note_marker")).click();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).clear();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).sendKeys(metadataConfig.getHelpTextForSDVendorId());
							Thread.sleep(1000);
							grid_cells.get(6).findElement(By.className("popup_message_ok")).click();
						}
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(grid_cells.get(0).getText().trim().equals("Synopsis")) {
					if(metadataConfig.getDefaultBehaviourForSynopsis()!=null) {
						WebElement select = grid_cells.get(1).findElement(By.tagName("select"));
						new Select(select).selectByVisibleText(metadataConfig.getDefaultBehaviourForSynopsis());
					}
					if(metadataConfig.getAlternateLabelForSynopsis()!=null)
					grid_cells.get(2).findElement(By.name("metadata_def_alt_label")).sendKeys(metadataConfig.getAlternateLabelForSynopsis());
					List<WebElement> inputs = grid_cells.get(3).findElements(By.tagName("input"));
					if(metadataConfig.getIsSynopsisLocalizable()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					inputs = grid_cells.get(4).findElements(By.tagName("input"));
					if(metadataConfig.getIsSynopsisRequired()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					if(metadataConfig.getMaxLengthForSynopsis()!=null)
					grid_cells.get(5).findElement(By.name("metadata_def_max_length")).sendKeys(metadataConfig.getMaxLengthForSynopsis());
					try {
						if(metadataConfig.getHelpTextForSynopsis()!=null) {
							grid_cells.get(6).findElement(By.className("note_marker")).click();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).clear();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).sendKeys(metadataConfig.getHelpTextForSynopsis());
							Thread.sleep(1000);
							grid_cells.get(6).findElement(By.className("popup_message_ok")).click();
						}
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(grid_cells.get(0).getText().trim().equals("Short Synopsis")) {
					if(metadataConfig.getDefaultBehaviourForShortSynopsis()!=null) {
						WebElement select = grid_cells.get(1).findElement(By.tagName("select"));
						new Select(select).selectByVisibleText(metadataConfig.getDefaultBehaviourForShortSynopsis());
					}
					if(metadataConfig.getAlternateLabelForShortSynopsis()!=null)
					grid_cells.get(2).findElement(By.name("metadata_def_alt_label")).sendKeys(metadataConfig.getAlternateLabelForShortSynopsis());
					List<WebElement> inputs = grid_cells.get(3).findElements(By.tagName("input"));
					if(metadataConfig.getIsShortSynopsisLocalizable()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					inputs = grid_cells.get(4).findElements(By.tagName("input"));
					if(metadataConfig.getIsShortSynopsisRequired()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					if(metadataConfig.getMaxLengthForShortSynopsis()!=null)
					grid_cells.get(5).findElement(By.name("metadata_def_max_length")).sendKeys(metadataConfig.getMaxLengthForShortSynopsis());
					try {
						if(metadataConfig.getHelpTextForShortSynopsis()!=null) {
							grid_cells.get(6).findElement(By.className("note_marker")).click();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).clear();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).sendKeys(metadataConfig.getHelpTextForShortSynopsis());
							Thread.sleep(1000);
							grid_cells.get(6).findElement(By.className("popup_message_ok")).click();
						}
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(grid_cells.get(0).getText().trim().equals("Production Company")) {
					if(metadataConfig.getDefaultBehaviourForProductionCompany()!=null) {
						WebElement select = grid_cells.get(1).findElement(By.tagName("select"));
						new Select(select).selectByVisibleText(metadataConfig.getDefaultBehaviourForProductionCompany());
					}
					if(metadataConfig.getAlternateLabelForProductionCompany()!=null)
					grid_cells.get(2).findElement(By.name("metadata_def_alt_label")).sendKeys(metadataConfig.getAlternateLabelForProductionCompany());
					List<WebElement> inputs = grid_cells.get(3).findElements(By.tagName("input"));
					if(metadataConfig.getIsProductionCompanyLocalizable()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					inputs = grid_cells.get(4).findElements(By.tagName("input"));
					if(metadataConfig.getIsProductionCompanyRequired()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					if(metadataConfig.getMaxLengthForProductionCompany()!=null)
					grid_cells.get(5).findElement(By.name("metadata_def_max_length")).sendKeys(metadataConfig.getMaxLengthForProductionCompany());
					try {
						if(metadataConfig.getHelpTextForProductionCompany()!=null) {
							grid_cells.get(6).findElement(By.className("note_marker")).click();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).clear();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).sendKeys(metadataConfig.getHelpTextForProductionCompany());
							Thread.sleep(1000);
							grid_cells.get(6).findElement(By.className("popup_message_ok")).click();
						}
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(grid_cells.get(0).getText().trim().equals("Copyright")) {
					if(metadataConfig.getDefaultBehaviourForCopyright()!=null) {
						WebElement select = grid_cells.get(1).findElement(By.tagName("select"));
						new Select(select).selectByVisibleText(metadataConfig.getDefaultBehaviourForCopyright());
					}
					if(metadataConfig.getAlternateLabelForCopyright()!=null)
					grid_cells.get(2).findElement(By.name("metadata_def_alt_label")).sendKeys(metadataConfig.getAlternateLabelForCopyright());
					List<WebElement> inputs = grid_cells.get(3).findElements(By.tagName("input"));
					if(metadataConfig.getIsCopyrightLocalizable()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					inputs = grid_cells.get(4).findElements(By.tagName("input"));
					if(metadataConfig.getIsCopyrightRequired()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					if(metadataConfig.getMaxLengthForCopyright()!=null)
					grid_cells.get(5).findElement(By.name("metadata_def_max_length")).sendKeys(metadataConfig.getMaxLengthForCopyright());
					try {
						if(metadataConfig.getHelpTextForCopyright()!=null) {
							grid_cells.get(6).findElement(By.className("note_marker")).click();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).clear();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).sendKeys(metadataConfig.getHelpTextForCopyright());
							Thread.sleep(1000);
							grid_cells.get(6).findElement(By.className("popup_message_ok")).click();
						}
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(grid_cells.get(0).getText().trim().equals("Episode Production Number")) {
					if(metadataConfig.getDefaultBehaviourForEpisodeProductionNumber()!=null) {
						WebElement select = grid_cells.get(1).findElement(By.tagName("select"));
						new Select(select).selectByVisibleText(metadataConfig.getDefaultBehaviourForEpisodeProductionNumber());
					}
					if(metadataConfig.getAlternateLabelForEpisodeProductionNumber()!=null)
					grid_cells.get(2).findElement(By.name("metadata_def_alt_label")).sendKeys(metadataConfig.getAlternateLabelForEpisodeProductionNumber());
					List<WebElement> inputs = grid_cells.get(3).findElements(By.tagName("input"));
					if(metadataConfig.getIsEpisodeProductionNumberLocalizable()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					inputs = grid_cells.get(4).findElements(By.tagName("input"));
					if(metadataConfig.getIsEpisodeProductionNumberRequired()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					if(metadataConfig.getMaxLengthForEpisodeProductionNumber()!=null)
					grid_cells.get(5).findElement(By.name("metadata_def_max_length")).sendKeys(metadataConfig.getMaxLengthForEpisodeProductionNumber());
					try {
						if(metadataConfig.getHelpTextForEpisodeProductionNumber()!=null) {
							grid_cells.get(6).findElement(By.className("note_marker")).click();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).clear();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).sendKeys(metadataConfig.getHelpTextForEpisodeProductionNumber());
							Thread.sleep(1000);
							grid_cells.get(6).findElement(By.className("popup_message_ok")).click();
						}
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(grid_cells.get(0).getText().trim().equals("Container Position")) {
					if(metadataConfig.getDefaultBehaviourForContainerPosition()!=null) {
						WebElement select = grid_cells.get(1).findElement(By.tagName("select"));
						new Select(select).selectByVisibleText(metadataConfig.getDefaultBehaviourForContainerPosition());
					}
					if(metadataConfig.getAlternateLabelForContainerPosition()!=null)
					grid_cells.get(2).findElement(By.name("metadata_def_alt_label")).sendKeys(metadataConfig.getAlternateLabelForContainerPosition());
					List<WebElement> inputs = grid_cells.get(3).findElements(By.tagName("input"));
					if(metadataConfig.getIsContainerPositionLocalizable()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					inputs = grid_cells.get(4).findElements(By.tagName("input"));
					if(metadataConfig.getIsContainerPositionRequired()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					if(metadataConfig.getMaxLengthForContainerPosition()!=null)
					grid_cells.get(5).findElement(By.name("metadata_def_max_length")).sendKeys(metadataConfig.getMaxLengthForContainerPosition());
					try {
						if(metadataConfig.getHelpTextForContainerPosition()!=null) {
							grid_cells.get(6).findElement(By.className("note_marker")).click();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).clear();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).sendKeys(metadataConfig.getHelpTextForContainerPosition());
							Thread.sleep(1000);
							grid_cells.get(6).findElement(By.className("popup_message_ok")).click();
						}
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(grid_cells.get(0).getText().trim().equals("Episode ID")) {
					if(metadataConfig.getDefaultBehaviourForEpisodeID()!=null) {
						WebElement select = grid_cells.get(1).findElement(By.tagName("select"));
						new Select(select).selectByVisibleText(metadataConfig.getDefaultBehaviourForEpisodeID());
					}
					if(metadataConfig.getAlternateLabelForEpisodeID()!=null)
					grid_cells.get(2).findElement(By.name("metadata_def_alt_label")).sendKeys(metadataConfig.getAlternateLabelForEpisodeID());
					List<WebElement> inputs = grid_cells.get(3).findElements(By.tagName("input"));
					if(metadataConfig.getIsEpisodeIDLocalizable()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					inputs = grid_cells.get(4).findElements(By.tagName("input"));
					if(metadataConfig.getIsEpisodeIDRequired()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					if(metadataConfig.getMaxLengthForEpisodeID()!=null)
					grid_cells.get(5).findElement(By.name("metadata_def_max_length")).sendKeys(metadataConfig.getMaxLengthForEpisodeID());
					try {
						if(metadataConfig.getHelpTextForEpisodeID()!=null) {
							grid_cells.get(6).findElement(By.className("note_marker")).click();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).clear();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).sendKeys(metadataConfig.getHelpTextForEpisodeID());
							Thread.sleep(1000);
							grid_cells.get(6).findElement(By.className("popup_message_ok")).click();
						}
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(grid_cells.get(0).getText().trim().equals("Run Time")) {
					if(metadataConfig.getDefaultBehaviourForRunTime()!=null) {
						WebElement select = grid_cells.get(1).findElement(By.tagName("select"));
						new Select(select).selectByVisibleText(metadataConfig.getDefaultBehaviourForRunTime());
					}
					if(metadataConfig.getAlternateLabelForRunTime()!=null)
					grid_cells.get(2).findElement(By.name("metadata_def_alt_label")).sendKeys(metadataConfig.getAlternateLabelForRunTime());
					List<WebElement> inputs = grid_cells.get(3).findElements(By.tagName("input"));
					if(metadataConfig.getIsRunTimeLocalizable()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					inputs = grid_cells.get(4).findElements(By.tagName("input"));
					if(metadataConfig.getIsRunTimeRequired()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					if(metadataConfig.getMaxLengthForRunTime()!=null)
					grid_cells.get(5).findElement(By.name("metadata_def_max_length")).sendKeys(metadataConfig.getMaxLengthForRunTime());
					try {
						if(metadataConfig.getHelpTextForRunTime()!=null) {
							grid_cells.get(6).findElement(By.className("note_marker")).click();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).clear();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).sendKeys(metadataConfig.getHelpTextForRunTime());
							Thread.sleep(1000);
							grid_cells.get(6).findElement(By.className("popup_message_ok")).click();
						}
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(grid_cells.get(0).getText().trim().equals("Trailer Run Time")) {
					if(metadataConfig.getDefaultBehaviourForTrailerRunTime()!=null) {					
						WebElement select = grid_cells.get(1).findElement(By.tagName("select"));
						new Select(select).selectByVisibleText(metadataConfig.getDefaultBehaviourForTrailerRunTime());
					}
					if(metadataConfig.getAlternateLabelForTrailerRunTime()!=null)
					grid_cells.get(2).findElement(By.name("metadata_def_alt_label")).sendKeys(metadataConfig.getAlternateLabelForTrailerRunTime());
					List<WebElement> inputs = grid_cells.get(3).findElements(By.tagName("input"));
					if(metadataConfig.getIsTrailerRunTimeLocalizable()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					inputs = grid_cells.get(4).findElements(By.tagName("input"));
					if(metadataConfig.getIsTrailerRunTimeRequired()!=inputs.get(0).isSelected()) {
						inputs.get(0).click();
					}
					if(metadataConfig.getMaxLengthForTrailerRunTime()!=null)
					grid_cells.get(5).findElement(By.name("metadata_def_max_length")).sendKeys(metadataConfig.getMaxLengthForTrailerRunTime());
					try {
						if(metadataConfig.getHelpTextForTrailerRunTime()!=null) {
							grid_cells.get(6).findElement(By.className("note_marker")).click();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).clear();
							grid_cells.get(6).findElement(By.name("metadata_def_help_text")).sendKeys(metadataConfig.getHelpTextForTrailerRunTime());
							Thread.sleep(1000);
							grid_cells.get(6).findElement(By.className("popup_message_ok")).click();
						}
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		//System.out.println("saving changes");
		List<WebElement> saves = driver.findElements(By.name("save-changes"));
		//System.out.println(saves.size());
		saves.get(0).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getPageURL() {
		return (pageConfig.getRootURL() + "/admin/add_service");
	}
	public void openTitleMetadata() {
		orderItemPage.fillOrderItemPage();
		fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		for(WebElement tr:trs) {
			if(tr.findElement(By.className("col1")).getText().contains(orderConfig.getTitle3())) {
				WebElement td = tr.findElement(By.className("col1"));
				td.findElement(By.tagName("a")).click();
				assertPageTitle("Summary");
				try {
					Thread.sleep(5000);
					WebElement ul = driver.findElement(By.id("navigation_list"));
					Thread.sleep(2000);
					fluentlyWait(new FluentWait<By>(By.linkText("Metadata")));
					ul.findElement(By.linkText("Metadata")).click();
					assertPageTitle("Metadata");
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("opened metadata");
				break;
			}
		}
	}
	public boolean addCrew(){
		try {
			Thread.sleep(1000);
			fluentlyWait(new FluentWait<By>(By.linkText("+")));
			List<WebElement> link= driver.findElements(By.linkText("+"));
			//System.out.println("the link size is "+link.size());
			link.get(0).click();
			List<WebElement> row = driver.findElements(By.className("body-row"));
			//System.out.println("the row size is "+row.size());
			WebElement person = row.get(0).findElement(By.id("id_crew-0-person"));
			person.clear();
			person.sendKeys(metadataConfig.getOrderItemMetadataCrewPersonName());
			//System.out.println("person is "+person.getAttribute("value"));
			WebElement role = driver.findElement(By.name("crew-0-role"));
			new Select(role).selectByVisibleText(metadataConfig.getOrderItemMetadataCrewPersonRoleName());
			fluentlyWait(new FluentWait<By>(By.className("save-cancel-buttons")));
			WebElement submit = driver.findElement(By.className("save-cancel-buttons"));
			submit.findElement(By.tagName("input")).click();
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement roleUpdate = driver.findElement(By.name("crew-0-role"));
		List<WebElement> optionName = roleUpdate.findElements(By.tagName("option"));
		//System.out.println("Size of select"+optionName.size());
		String roleName = "";
		for(int i=0;i<optionName.size();i++){
			//System.out.print(" -->"+optionName.get(i).getText().trim());
			//System.out.println(" -->"+optionName.get(i).isSelected());
			if(optionName.get(i).isSelected()){
				roleName = optionName.get(i).getText().trim();
				break;
			}
		}
		List<WebElement> rowUpdate = driver.findElements(By.className("body-row"));
		//System.out.println("rowUpdate :"+rowUpdate.size());
		WebElement personUodate = rowUpdate.get(0).findElement(By.id("id_crew-0-person"));
		String personName = personUodate.getAttribute("value").trim();
		//System.out.println("Add: person name is "+personName);
		//System.out.println("Add: roleName:"+roleName);
		if(personName.equals(metadataConfig.getOrderItemMetadataCrewPersonName()) && roleName.equals(metadataConfig.getOrderItemMetadataCrewPersonRoleName())){
			return true;
		}else{
			return false;
		}
	}
	public boolean editCrew(){
			try {
				Thread.sleep(1000);
				fluentlyWait(new FluentWait<By>(By.id("id_crew-0-role")));
				WebElement role = driver.findElement(By.name("crew-0-role"));
				new Select(role).selectByVisibleText(metadataConfig.getOrderItemMetadataCrewEditPersonRoleName());
				fluentlyWait(new FluentWait<By>(By.className("save-cancel-buttons")));
				WebElement submit = driver.findElement(By.className("save-cancel-buttons"));
				submit.findElement(By.tagName("input")).click();
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebElement roleUpdate = driver.findElement(By.name("crew-0-role"));
			List<WebElement> optionName = roleUpdate.findElements(By.tagName("option"));
			//System.out.println("Size of select"+optionName.size());
			String roleName = "";
			for(int i=0;i<optionName.size();i++){
				//System.out.println(" -->"+optionName.get(i).getText().trim());
				//System.out.print(" -->"+optionName.get(i).isSelected());
				if(optionName.get(i).isSelected()){
					roleName = optionName.get(i).getText().trim();
					break;
				}
			}
			List<WebElement> rowUpdate = driver.findElements(By.className("body-row"));
			//System.out.println("rowUpdate :"+rowUpdate.size());
			WebElement personUodate = rowUpdate.get(0).findElement(By.id("id_crew-0-person"));
			String personName = personUodate.getAttribute("value").trim();
			//System.out.println("Edit: person name is "+personName);
			//System.out.println("Edit:roleName:"+roleName);
			if(personName.equals(metadataConfig.getOrderItemMetadataCrewPersonName()) && roleName.equals(metadataConfig.getOrderItemMetadataCrewEditPersonRoleName())){
				return true;
			}else{
				return false;
			}
	}
	public boolean addCast(){
			try {
				Thread.sleep(1000);
				fluentlyWait(new FluentWait<By>(By.linkText("+")));
				List<WebElement> link= driver.findElements(By.linkText("+"));
				//System.out.println("the link size is "+link.size());
				link.get(1).click();
				List<WebElement> row = driver.findElements(By.className("body-row"));
				//System.out.println("the row size is "+row.size());
				WebElement castPerson = row.get(1).findElement(By.id("id_cast-0-person"));
				//System.out.println("the list size is ");
				castPerson.clear();
				castPerson.sendKeys(metadataConfig.getOrderItemMetadataCast_person_name());
				//System.out.println("initial cast name is"+castPerson.getAttribute("value").trim());
				WebElement castCharacter = driver.findElement(By.id("id_cast-0-character"));
				castCharacter.clear();
				castCharacter.sendKeys(metadataConfig.getOrderItemMetadataCastPersonCharacter());
				//System.out.println("initial cast character is"+castCharacter.getAttribute("value").trim());
				fluentlyWait(new FluentWait<By>(By.className("save-cancel-buttons")));
				WebElement submit = driver.findElement(By.className("save-cancel-buttons"));
				submit.findElement(By.tagName("input")).click();
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			WebElement castPersonUpdate = driver.findElement(By.id("id_cast-0-person"));
			String castPersonUpdateName = castPersonUpdate.getAttribute("value").trim();
			//System.out.println("Add: castPersonUpdateName: "+castPersonUpdateName);
			WebElement castCharacterUpdate = driver.findElement(By.id("id_cast-0-character"));
			String castCharacterUpdateName = castCharacterUpdate.getAttribute("value").trim();
			//System.out.println("Add:castCharacterUpdateName: "+castCharacterUpdateName);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(castPersonUpdateName.equals(metadataConfig.getOrderItemMetadataCast_person_name()) && castCharacterUpdateName.equals(metadataConfig.getOrderItemMetadataCastPersonCharacter())){
				return true;
			}else{
				return false;
			}
	}
	public boolean editCast(){
			try {
				Thread.sleep(1000);
				List<WebElement> row = driver.findElements(By.className("body-row"));
				//System.out.println("the row size is "+row.size());
				WebElement castPerson = row.get(1).findElement(By.id("id_cast-0-person"));
				//System.out.println("the list size is ");
				castPerson.clear();
				castPerson.sendKeys(metadataConfig.getOrderItemMetadataCastEditPersonName());
				//System.out.println("initial cast name is"+castPerson.getAttribute("value").trim());
				WebElement castCharacter = driver.findElement(By.id("id_cast-0-character"));
				castCharacter.clear();
				castCharacter.sendKeys(metadataConfig.getOrderItemMetadataCastEditPersonCharacter());
				//System.out.println("initial cast character is"+castCharacter.getAttribute("value").trim());
				fluentlyWait(new FluentWait<By>(By.className("save-cancel-buttons")));
				WebElement submit = driver.findElement(By.className("save-cancel-buttons"));
				submit.findElement(By.tagName("input")).click();
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebElement castPersonUpdate = driver.findElement(By.id("id_cast-0-person"));
			String castPersonUpdateName = castPersonUpdate.getAttribute("value").trim();
			//System.out.println("Edit: castPersonUpdateName: "+castPersonUpdateName);
			WebElement castCharacterUpdate = driver.findElement(By.id("id_cast-0-character"));
			String castCharacterUpdateName = castCharacterUpdate.getAttribute("value").trim();
			//System.out.println("Edit: castCharacterUpdateName: "+castCharacterUpdateName);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(castPersonUpdateName.equals(metadataConfig.getOrderItemMetadataCastEditPersonName()) && castCharacterUpdateName.equals(metadataConfig.getOrderItemMetadataCastEditPersonCharacter())){
				return true;
			}else{
				return false;
			}
	}
	public boolean closeCrewAndCast(){
		try {
			Thread.sleep(5000);
			List<WebElement> closeList = driver.findElements(By.linkText("X"));
			//System.out.println("closeList is "+closeList.size());
			for(int j=0;j<closeList.size();j++){
				Thread.sleep(1000);
				closeList.get(j).click();
			}
			fluentlyWait(new FluentWait<By>(By.className("save-cancel-buttons")));
			WebElement submit = driver.findElement(By.className("save-cancel-buttons"));
			submit.findElement(By.tagName("input")).click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> closeAll = driver.findElements(By.linkText("X"));
		//System.out.println("closeAll is "+closeAll.size());
		if(closeAll.size() == 0){
			return true;
		}else{
			return false;
		}
	}
	public void assertTitle() throws InterruptedException {
		//System.out.println("in assertTitle");
		if(metadataConfig.getDefaultBehaviourForTitle() != "Hidden") {
			Thread.sleep(2000);
			fluentlyWait(new FluentWait<By>(By.name("service_specific_name")));
			WebElement name = driver.findElement(By.name("service_specific_name"));
			WebElement input_area = name.findElement(By.xpath(".."));
			WebElement text_field = input_area.findElement(By.xpath(".."));
			String label_name = text_field.findElement(By.tagName("label")).getText().trim();
			if(metadataConfig.getAlternateLabelForTitle() != null && !(metadataConfig.getAlternateLabelForTitle().equals(label_name))) {
				Assert.assertEquals(metadataConfig.getAlternateLabelForTitle(),"Title");
			}
//			if(metadataConfig.getDefaultBehaviourForTitle() == "Use Raw Title")
//				text_field.findElement(By.className("default"));
//			else
//				text_field.findElement(By.className("cascading"));
			if(metadataConfig.getHelpTextForTitle() != null) 
				Assert.assertTrue(text_field.findElement(By.className("explanatory")).getText().trim().contains(metadataConfig.getHelpTextForTitle()));
			if(metadataConfig.getMaxLengthForTitle() != null)
				Assert.assertTrue(text_field.findElement(By.className("max_char_count")).getText().trim().contains(metadataConfig.getMaxLengthForTitle()));
			overrideWithServiceSpecificValue(text_field,"OverridableTitle","service_specific_name");
			Assert.assertFalse(driver.findElement(By.className("top-gradient-bar")).getText().contains("OverridableTitle"));
			//System.out.println("done...");
		}
	}

	public void assertVendorID() throws InterruptedException {
		if(metadataConfig.getDefaultBehaviourForVendorId() != "Hidden") {
			Thread.sleep(2000);
			WebElement name = driver.findElement(By.name("service_specific_film_id"));
			WebElement input_area = name.findElement(By.xpath(".."));
			WebElement text_field = input_area.findElement(By.xpath(".."));
			String label_name = text_field.findElement(By.tagName("label")).getText().trim();
			//System.out.println(metadataConfig.getAlternateLabelForVendorId());
			if(metadataConfig.getAlternateLabelForVendorId() != null && !(metadataConfig.getAlternateLabelForVendorId().equals(label_name))) {
				Assert.assertEquals(metadataConfig.getAlternateLabelForVendorId(),"Vendor ID");
			}
//			if(metadataConfig.getDefaultBehaviourForVendorId() != "Use Raw Title") {
//				scrollInToElement(text_field.findElement(By.name("service_specific_film_id")));
//				text_field.findElement(By.name("service_specific_film_id")).click();
//				Assert.assertTrue(text_field.findElement(By.name("service_specific_film_id")).isSelected());
//			}
			if(metadataConfig.getHelpTextForVendorId() != null)
				Assert.assertTrue(text_field.findElement(By.className("explanatory")).getText().trim().contains(metadataConfig.getHelpTextForVendorId()));
			if(metadataConfig.getMaxLengthForVendorId() != null)
				Assert.assertTrue(text_field.findElement(By.className("max_char_count")).getText().trim().contains(metadataConfig.getMaxLengthForVendorId()));
			overrideWithServiceSpecificValue(text_field,"OverridableVendorID","service_specific_film_id");
			fluentlyWait(new FluentWait<By>(By.linkText("Summary")));
			driver.findElement(By.linkText("Summary")).click();
			assertPageTitle("Summary");
			List<WebElement> labels = driver.findElements(By.tagName("label"));
			for(WebElement label:labels) {
				if(label.getText().trim().equals("Film ID")) {
					WebElement text = label.findElement(By.xpath(".."));
					Assert.assertEquals(text.findElement(By.tagName("div")).getText().trim(), "OverridableVendorID");
				}
			}
			try {
				Thread.sleep(5000);
				driver.findElement(By.linkText("Metadata")).click();
				assertPageTitle("Metadata");
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void assertSDVendorID() throws InterruptedException {
		if(metadataConfig.getDefaultBehaviourForSDVendorId() != "Hidden") {
			Thread.sleep(2000);
			fluentlyWait(new FluentWait<By>(By.name("sd_vendor_id")));
			WebElement name = driver.findElement(By.name("sd_vendor_id"));
			WebElement input_area = name.findElement(By.xpath(".."));
			WebElement text_field = input_area.findElement(By.xpath(".."));
			String label = text_field.findElement(By.tagName("label")).getText().trim();
			//System.out.println(metadataConfig.getAlternateLabelForSDVendorId());
			if(metadataConfig.getAlternateLabelForSDVendorId() != null && !(metadataConfig.getAlternateLabelForSDVendorId().equals(label))) {
				Assert.assertEquals(metadataConfig.getAlternateLabelForSDVendorId(),"SD Vendor ID");
			}
//			if(metadataConfig.getDefaultBehaviourForSDVendorId() != "Use Raw Title") {
//				scrollInToElement(text_field.findElement(By.name("service_specific_sd_vendor_id")));
//				text_field.findElement(By.name("service_specific_sd_vendor_id")).click();
//				Assert.assertTrue(text_field.findElement(By.name("service_specific_sd_vendor_id")).isSelected());
//			}
			if(metadataConfig.getHelpTextForSDVendorId() != null)
				Assert.assertTrue(text_field.findElement(By.className("explanatory")).getText().trim().contains(metadataConfig.getHelpTextForSDVendorId()));
			if(metadataConfig.getMaxLengthForSDVendorId() != null)
				Assert.assertTrue(text_field.findElement(By.className("max_char_count")).getText().trim().contains(metadataConfig.getMaxLengthForSDVendorId()));
			overrideWithServiceSpecificValue(text_field,"OverridableSDVendorID","service_specific_sd_vendor_id");
		}
	}

	public void assertProductionCompany() throws InterruptedException {
		if(metadataConfig.getDefaultBehaviourForProductionCompany() != "Hidden") {
			Thread.sleep(2000);
			fluentlyWait(new FluentWait<By>(By.name("service_specific_production_company")));
			WebElement name = driver.findElement(By.name("service_specific_production_company"));
			WebElement input_area = name.findElement(By.xpath(".."));
			WebElement text_field = input_area.findElement(By.xpath(".."));
			String label = text_field.findElement(By.tagName("label")).getText().trim();
			//System.out.println(metadataConfig.getAlternateLabelForProductionCompany());
			if(metadataConfig.getAlternateLabelForProductionCompany() != null && !(metadataConfig.getAlternateLabelForProductionCompany().equals(label))) {
				Assert.assertEquals(metadataConfig.getAlternateLabelForProductionCompany(),"Production Company");
			}
//			if(metadataConfig.getDefaultBehaviourForProductionCompany() != "Use Raw Title") {
//				scrollInToElement(text_field.findElement(By.name("service_specific_sd_vendor_id")));
//				text_field.findElement(By.name("service_specific_sd_vendor_id")).click();
//				Assert.assertTrue(text_field.findElement(By.name("service_specific_sd_vendor_id")).isSelected());
//			}
			if(metadataConfig.getHelpTextForProductionCompany() != null)
				Assert.assertTrue(text_field.findElement(By.className("explanatory")).getText().trim().contains(metadataConfig.getHelpTextForProductionCompany()));
			if(metadataConfig.getMaxLengthForProductionCompany() != null)
				Assert.assertTrue(text_field.findElement(By.className("max_char_count")).getText().trim().contains(metadataConfig.getMaxLengthForProductionCompany()));
			overrideWithServiceSpecificValue(text_field,"OverridableProductionCompany","service_specific_production_company");
		}
	}
	public void assertCopyright() throws InterruptedException {
		if(metadataConfig.getDefaultBehaviourForCopyright() != "Hidden") {
			Thread.sleep(2000);
			fluentlyWait(new FluentWait<By>(By.name("service_specific_copyright")));
			WebElement name = driver.findElement(By.name("service_specific_copyright"));
			WebElement input_area = name.findElement(By.xpath(".."));
			WebElement text_field = input_area.findElement(By.xpath(".."));
			String label = text_field.findElement(By.tagName("label")).getText().trim();
			//System.out.println(metadataConfig.getAlternateLabelForCopyright());
			if(metadataConfig.getAlternateLabelForCopyright() != null && !(metadataConfig.getAlternateLabelForCopyright().equals(label))) {
				Assert.assertEquals(metadataConfig.getAlternateLabelForCopyright(),"Copyright");
			}
//			if(metadataConfig.getDefaultBehaviourForCopyright() != "Use Raw Title") {
//				scrollInToElement(text_field.findElement(By.name("service_specific_sd_vendor_id")));
//				text_field.findElement(By.name("service_specific_sd_vendor_id")).click();
//				Assert.assertTrue(text_field.findElement(By.name("service_specific_sd_vendor_id")).isSelected());
//			}
			if(metadataConfig.getHelpTextForCopyright() != null)
				Assert.assertTrue(text_field.findElement(By.className("explanatory")).getText().trim().contains(metadataConfig.getHelpTextForCopyright()));
			if(metadataConfig.getMaxLengthForCopyright() != null)
				Assert.assertTrue(text_field.findElement(By.className("max_char_count")).getText().trim().contains(metadataConfig.getMaxLengthForCopyright()));
			overrideWithServiceSpecificValue(text_field,"OverridableCopyright","service_specific_copyright");
		}
	}
	public void assertRunTime() throws InterruptedException {
		if(metadataConfig.getDefaultBehaviourForRunTime() != "Hidden") {
			Thread.sleep(2000);
			fluentlyWait(new FluentWait<By>(By.name("service_specific_run_time")));
			WebElement name = driver.findElement(By.name("service_specific_run_time"));
			WebElement input_area = name.findElement(By.xpath(".."));
			WebElement text_field = input_area.findElement(By.xpath(".."));
			String label = text_field.findElement(By.tagName("label")).getText().trim();
			//System.out.println(metadataConfig.getAlternateLabelForRunTime());
			if(metadataConfig.getAlternateLabelForRunTime() != null && !(metadataConfig.getAlternateLabelForRunTime().equals(label))) {
				Assert.assertEquals(metadataConfig.getAlternateLabelForRunTime(),"Run Time");
			}
//			if(metadataConfig.getDefaultBehaviourForRunTime() != "Use Raw Title") {
//				scrollInToElement(text_field.findElement(By.name("service_specific_sd_vendor_id")));
//				text_field.findElement(By.name("service_specific_sd_vendor_id")).click();
//				Assert.assertTrue(text_field.findElement(By.name("service_specific_sd_vendor_id")).isSelected());
//			}
			if(metadataConfig.getHelpTextForRunTime() != null)
				Assert.assertTrue(text_field.findElement(By.className("explanatory")).getText().trim().contains(metadataConfig.getHelpTextForRunTime()));
			if(metadataConfig.getMaxLengthForRunTime() != null)
				Assert.assertTrue(text_field.findElement(By.className("max_char_count")).getText().trim().contains(metadataConfig.getMaxLengthForRunTime()));
			cascadeChangesToTheRawTitle(text_field,100,"run_time");
		}
	}
	public void assertTrailerRunTime() throws InterruptedException {
		if(metadataConfig.getDefaultBehaviourForTrailerRunTime() != "Hidden") {
			Thread.sleep(2000);
			fluentlyWait(new FluentWait<By>(By.name("service_specific_trailer_run_time")));
			WebElement name = driver.findElement(By.name("service_specific_trailer_run_time"));
			WebElement input_area = name.findElement(By.xpath(".."));
			WebElement text_field = input_area.findElement(By.xpath(".."));
			String label = text_field.findElement(By.tagName("label")).getText().trim();
			//System.out.println(metadataConfig.getAlternateLabelForTrailerRunTime());
			if(metadataConfig.getAlternateLabelForTrailerRunTime() != null && !(metadataConfig.getAlternateLabelForTrailerRunTime().equals(label))) {
				Assert.assertEquals(metadataConfig.getAlternateLabelForTrailerRunTime(),"Trailer Run Time");
			}
//			if(metadataConfig.getDefaultBehaviourForTrailerRunTime() != "Use Raw Title") {
//				scrollInToElement(text_field.findElement(By.name("service_specific_sd_vendor_id")));
//				text_field.findElement(By.name("service_specific_sd_vendor_id")).click();
//				Assert.assertTrue(text_field.findElement(By.name("service_specific_sd_vendor_id")).isSelected());
//			}
			if(metadataConfig.getHelpTextForTrailerRunTime() != null)
				Assert.assertTrue(text_field.findElement(By.className("explanatory")).getText().trim().contains(metadataConfig.getHelpTextForTrailerRunTime()));
			if(metadataConfig.getMaxLengthForTrailerRunTime() != null)
				Assert.assertTrue(text_field.findElement(By.className("max_char_count")).getText().trim().contains(metadataConfig.getMaxLengthForTrailerRunTime()));
			cascadeChangesToTheRawTitle(text_field,50,"trailer_run_time");
		}
	}
	public void assertSynopsis() throws InterruptedException {
		if(metadataConfig.getDefaultBehaviourForSynopsis() != "Hidden") {
			Thread.sleep(2000);
			fluentlyWait(new FluentWait<By>(By.name("service_specific_synopsis")));
			WebElement name = driver.findElement(By.name("service_specific_synopsis"));
			WebElement input_area = name.findElement(By.xpath(".."));
			WebElement text_field = input_area.findElement(By.xpath(".."));
			String label = text_field.findElement(By.tagName("label")).getText().trim();
			//System.out.println(metadataConfig.getAlternateLabelForSynopsis());
			if(metadataConfig.getAlternateLabelForSynopsis() != null && !(metadataConfig.getAlternateLabelForSynopsis().equals(label))) {
				Assert.assertEquals(metadataConfig.getAlternateLabelForSynopsis(),"Synopsis");
			}
//			if(metadataConfig.getDefaultBehaviourForSynopsis() != "Use Raw Title") {
//				scrollInToElement(text_field.findElement(By.name("service_specific_sd_vendor_id")));
//				text_field.findElement(By.name("service_specific_sd_vendor_id")).click();
//				Assert.assertTrue(text_field.findElement(By.name("service_specific_sd_vendor_id")).isSelected());
//			}
			if(metadataConfig.getHelpTextForSynopsis() != null)
				Assert.assertTrue(text_field.findElement(By.className("explanatory")).getText().trim().contains(metadataConfig.getHelpTextForSynopsis()));
			if(metadataConfig.getMaxLengthForSynopsis() != null)
				Assert.assertTrue(text_field.findElement(By.className("max_char_count")).getText().trim().contains(metadataConfig.getMaxLengthForSynopsis()));
			overrideWithServiceSpecificValue(text_field,"OverridableSynopsis","service_specific_synopsis");
		}
	}
	public void assertShortSynopsis() throws InterruptedException {
		if(metadataConfig.getDefaultBehaviourForShortSynopsis() != "Hidden") {
			Thread.sleep(2000);
			fluentlyWait(new FluentWait<By>(By.name("service_specific_short_synopsis")));
			WebElement name = driver.findElement(By.name("service_specific_short_synopsis"));
			WebElement input_area = name.findElement(By.xpath(".."));
			WebElement text_field = input_area.findElement(By.xpath(".."));
			String label = text_field.findElement(By.tagName("label")).getText().trim();
			//System.out.println(metadataConfig.getAlternateLabelForShortSynopsis());
			if(metadataConfig.getAlternateLabelForShortSynopsis() != null && !(metadataConfig.getAlternateLabelForShortSynopsis().equals(label))) {
				Assert.assertEquals(metadataConfig.getAlternateLabelForShortSynopsis(),"Short Synopsis");
			}
//			if(metadataConfig.getDefaultBehaviourForShortSynopsis() != "Use Raw Title") {
//				scrollInToElement(text_field.findElement(By.name("service_specific_sd_vendor_id")));
//				text_field.findElement(By.name("service_specific_sd_vendor_id")).click();
//				Assert.assertTrue(text_field.findElement(By.name("service_specific_sd_vendor_id")).isSelected());
//			}
			if(metadataConfig.getHelpTextForShortSynopsis() != null)
				Assert.assertTrue(text_field.findElement(By.className("explanatory")).getText().trim().contains(metadataConfig.getHelpTextForShortSynopsis()));
			if(metadataConfig.getMaxLengthForShortSynopsis() != null)
				Assert.assertTrue(text_field.findElement(By.className("max_char_count")).getText().trim().contains(metadataConfig.getMaxLengthForShortSynopsis()));
			overrideWithServiceSpecificValue(text_field,"OverridableShortSynopsis","service_specific_short_synopsis");
		}
	}
	public void overrideWithServiceSpecificValue(WebElement element, String key,String class_name) {
//		scrollInToElement(element.findElement(By.className("note_marker")));
		element.findElement(By.className("note_marker")).click();
		try {
			Thread.sleep(5000);
			fluentlyWait(new FluentWait<By>(By.className("behavior_choices")));
			WebElement ul = driver.findElement(By.className("behavior_choices"));
			List<WebElement> lis = ul.findElements(By.tagName("li"));
			lis.get(2).click();
			Thread.sleep(1000);
			fluentlyWait(new FluentWait<By>(By.name(class_name)));
			scrollInToElement(driver.findElement(By.name(class_name)));
			driver.findElement(By.name(class_name)).clear();
			driver.findElement(By.name(class_name)).sendKeys(key);
			WebElement submit = driver.findElement(By.className("save-cancel-buttons"));
			submit.findElement(By.tagName("input")).click();
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void cascadeChangesToTheRawTitle(WebElement element, int key,String class_name) {
//		scrollInToElement(element.findElement(By.className("note_marker")));
//		element.findElement(By.className("note_marker")).click();
//		Thread.sleep(5000);
//		fluentlyWait(new FluentWait<By>(By.className("behavior_choices")));
//		WebElement ul = driver.findElement(By.className("behavior_choices"));
//		List<WebElement> lis = ul.findElements(By.tagName("li"));
//		lis.get(2).click();
//		Thread.sleep(1000);
		fluentlyWait(new FluentWait<By>(By.name(class_name)));
		scrollInToElement(driver.findElement(By.name(class_name)));
		WebElement time_element = driver.findElement(By.name(class_name));
		driver.findElement(By.name(class_name)).clear();
//		driver.findElement(By.name(class_name)).sendKeys(key+"");
		JavascriptExecutor executer = (JavascriptExecutor) driver;
		executer.executeScript("arguments[0].setAttribute('value', arguments[1])", time_element,key);
		WebElement submit = driver.findElement(By.className("save-cancel-buttons"));
		submit.findElement(By.tagName("input")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fluentlyWait(new FluentWait<By>(By.name(class_name)));
		scrollInToElement(driver.findElement(By.name(class_name)));
//		time_element = driver.findElement(By.name(class_name));
//		int time = Integer.parseInt(executer.executeScript("arguments[0].getAttribute('value')", time_element).toString());
		int time = Integer.parseInt(driver.findElement(By.name(class_name)).getAttribute("value").toString());
		//System.out.println("time:"+time);
		Assert.assertEquals(key, time);
	}
}
