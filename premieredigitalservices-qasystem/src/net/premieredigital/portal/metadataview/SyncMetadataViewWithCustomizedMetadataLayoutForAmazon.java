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

import org.testng.annotations.Test;

public class SyncMetadataViewWithCustomizedMetadataLayoutForAmazon {
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
	public SyncMetadataViewWithCustomizedMetadataLayoutForAmazon(BaseTest baseTest) {
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
		orderPage = new OrderPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), this.orderConfig);
		//new LoginPage(baseTest.getWebDriver(), baseTest.getPageConfiguration()).login();
		this.servicePage = new ServiceAdminPage(baseTest.getWebDriver(), baseTest.getPageConfiguration(), this.serviceConfig);
		this.orderItemPage = new OrderItemPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderItemConfig, this.orderConfig);
		this.masteringPage = new MasteringPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(), this.orderItemConfig, this.orderConfig, this.masteringConfig);
		this.metadataPage = new MetadataPage(baseTest.getWebDriver(),baseTest.getPageConfiguration(),this.serviceConfig,this.metadataConfig, this.orderItemPage, this.orderConfig);
	}
	//9.1
	//@Test(description="9.1 Synchronize Metadata View With Customized Metadata Layout For Amazon Service")
	public void syncMetadataViewWithCustomizedMetadataLayoutAmazon() throws Exception {
		this.preCondition();
		servicePage.addService();
		orderConfig.setService(serviceConfig.getServiceName());
		metadataPage.modifyCustomizedMetadataLayoutForAmazon();
		orderPage.placeOrder();
		metadataPage.openTitleMetadata();
		metadataPage.assertTitle();
		metadataPage.assertVendorID();
		metadataPage.assertSDVendorID();
		metadataPage.assertProductionCompany();
		metadataPage.assertCopyright();
		metadataPage.assertRunTime();
		metadataPage.assertTrailerRunTime();
		metadataPage.assertSynopsis();
		metadataPage.assertShortSynopsis();
	}
}
