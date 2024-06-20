package net.premieredigital.portal.metadataview;
import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.pom.LoginPage;
import net.premieredigital.portal.pom.MasteringConfiguration;
import net.premieredigital.portal.pom.MasteringPage;
import net.premieredigital.portal.pom.MetadataConfiguration;
import net.premieredigital.portal.pom.MetadataPage;
import net.premieredigital.portal.pom.OrderConfiguration;
import net.premieredigital.portal.pom.OrderItemConfiguration;
import net.premieredigital.portal.pom.OrderItemPage;
import net.premieredigital.portal.pom.OrderPage;
import net.premieredigital.portal.pom.ServiceAdminConfiguration;
import net.premieredigital.portal.pom.ServiceAdminPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
public class OrderItemMetadataCrewAndCast{
	private OrderItemConfiguration orderItemConfig;
	private OrderItemPage orderItemPage;
	private OrderConfiguration orderConfig;
	private OrderPage orderPage;
	private MasteringPage masteringPage;
	private MasteringConfiguration masteringConfig;
	private ServiceAdminPage servicePage;
	private ServiceAdminConfiguration serviceConfig;
	private MetadataPage metadataPage;
	private MetadataConfiguration metadataConfig;
	private BaseTest baseTest;
	public OrderItemMetadataCrewAndCast(BaseTest baseTest) {
		this.baseTest = baseTest;
	}

	public void postCondition() {
//		orderPage.deleteOrder();
	}
	
	public void preCondition() throws Exception {
		masteringConfig = new MasteringConfiguration();
		orderItemConfig = new OrderItemConfiguration();
		serviceConfig = new ServiceAdminConfiguration();
		orderConfig = new OrderConfiguration();
		metadataConfig = new MetadataConfiguration("MetadataConfigurationForAmazon");
		this.orderPage = new OrderPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(),this.orderConfig);
		//new LoginPage(baseTest.webDriver.get(), baseTest.getPageConfiguration()).login();
		this.servicePage = new ServiceAdminPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), this.serviceConfig);
		this.orderItemPage = new OrderItemPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderItemConfig, this.orderConfig);
		this.masteringPage = new MasteringPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderItemConfig, this.orderConfig, this.masteringConfig);
		this.metadataPage = new MetadataPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(),this.serviceConfig,this.metadataConfig, this.orderItemPage, this.orderConfig);
	}
	//9.3
	//@Test(groups="ExcludeSafari", description="9.3 Order Item Metadata - Crew and Cast")
	public void orderItemMetadataCrewAndCast() throws Exception{
		this.preCondition();
		orderPage.placeOrder();
		orderPage.addSameRequirementToAll();
		metadataPage.openTitleMetadata();
		Assert.assertTrue(metadataPage.addCrew());
		Assert.assertTrue(metadataPage.editCrew());
		Assert.assertTrue(metadataPage.addCast());
		Assert.assertTrue(metadataPage.editCast());
		Assert.assertTrue(metadataPage.closeCrewAndCast());
		
	}
}