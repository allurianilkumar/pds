package net.premieredigital.portal.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

public class DashboardPage extends BasePage{
	private OrderConfiguration orderConfig;
	private OrderItemConfiguration orderItemConfig;
	private MasteringConfiguration masteringConfig;
	private PageConfiguration pageConfig;
	private ServiceAdminConfiguration serviceAdminConfig;
	private MetadataConfiguration metadataConfig;
	private OrderItemPage orderItemPage;
	private DashboardConfiguration dashboardConfig;
	public DashboardPage(WebDriver driver,PageConfiguration pageConfig,ServiceAdminConfiguration serviceConfig,DashboardConfiguration dashboardConfig, OrderItemPage orderItemPage, OrderConfiguration orderConfig) {
		this.driver = driver;
		this.pageConfig = pageConfig;
		this.serviceAdminConfig = serviceConfig;
		this.dashboardConfig = dashboardConfig;
		this.orderItemPage = orderItemPage;
		this.orderConfig = orderConfig;
	}
	public String getPageUrl() {
		return pageConfig.getRootURL()+"/reports/dashboard"+"?autorefresh=false";
	}
	public void fillDashboard() throws InterruptedException {
		this.driver.get(getPageUrl());
		Thread.sleep(1000);
		assertPageTitle("DashBoard");
		new Select(driver.findElement(By.name("service_id"))).selectByVisibleText(dashboardConfig.getServiceName());
		new Select(driver.findElement(By.name("provider_id"))).selectByVisibleText(dashboardConfig.getProviderName());
		Thread.sleep(2000);
		fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
		WebElement elm = driver.findElement(By.className("main-section-loader"));
		waitForCssToChange(elm);
		Thread.sleep(2000);
	}
	public void fillNonPackagedData() throws InterruptedException{
        fillDashboard();
        new Select(driver.findElement(By.name("packaging_type"))).selectByVisibleText(dashboardConfig.getNonPackagedName());
        Thread.sleep(500);
        fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
        WebElement elm = driver.findElement(By.className("main-section-loader"));
        waitForCssToChange(elm);
        Thread.sleep(500);
    }
    public void fillPackagedData() throws InterruptedException{
        fillDashboard();
        new Select(driver.findElement(By.name("packaging_type"))).selectByVisibleText(dashboardConfig.getPackagedName());
        Thread.sleep(500);
        fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
        WebElement elm = driver.findElement(By.className("main-section-loader"));
        waitForCssToChange(elm);
        Thread.sleep(500);
    }
    public boolean testTableValue(int value){
        boolean result =  true;
        WebElement tbody = driver.findElement(By.tagName("tbody"));
        List<WebElement> trs = tbody.findElements(By.tagName("tr"));
        for(int i=0;i<trs.size();i++){
        List<WebElement> td = getTableRow().get(i).findElements(By.tagName("td"));
            for(int j=1;j<getTableColumn().size();j++){
                if(value == Integer.parseInt(td.get(j).getText().replace(",", "").replace("$", "").trim())){
                    continue;
                }
                else {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
	public String clickDesiredLinkAndReadTotal(String link) throws InterruptedException {
        Thread.sleep(2000);
        fluentlyWait(new FluentWait<By>(By.linkText("Work In Progress")));
        driver.findElement(By.linkText("Work In Progress")).click();
        Thread.sleep(10000);
        fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
        WebElement elm1 = driver.findElement(By.className("main-section-loader"));
        waitForCssToChange(elm1);
        Thread.sleep(25000);
        WebElement sidebar = driver.findElement(By.className("left-sidebar-inner-content"));
        fluentlyWait(new FluentWait<By>(By.linkText(link)));
        sidebar.findElement(By.linkText(link)).click();
        Thread.sleep(2000);
        fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
        WebElement elm2 = driver.findElement(By.className("main-section-loader"));
        waitForCssToChange(elm2);
        String total= readTotal(link);
        return total;

	}
	public void clickDesiredLink(String link) throws InterruptedException {
		Thread.sleep(5000);
		fluentlyWait(new FluentWait<By>(By.linkText("Work In Progress")));
		driver.findElement(By.linkText("Work In Progress")).click();
		Thread.sleep(1000);
		fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
		WebElement elm1 = driver.findElement(By.className("main-section-loader"));
		waitForCssToChange(elm1);
		WebElement sidebar = driver.findElement(By.className("left-sidebar-inner-content"));		
		fluentlyWait(new FluentWait<By>(By.linkText(link)));
		sidebar.findElement(By.linkText(link)).click();
		Thread.sleep(1000);
		fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
		WebElement elm2 = driver.findElement(By.className("main-section-loader"));
		waitForCssToChange(elm2);
	}
	public void clickSubOption(String link) throws InterruptedException{
		WebElement sidebar = driver.findElement(By.className("left-sidebar-inner-content"));
		fluentlyWait(new FluentWait<By>(By.linkText("Client Services")));
		sidebar = driver.findElement(By.className("left-sidebar-inner-content"));
		fluentlyWait(new FluentWait<By>(By.linkText(link)));
		sidebar.findElement(By.linkText(link)).click();
		Thread.sleep(1000);
		fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
		WebElement elm3 = driver.findElement(By.className("main-section-loader"));
		waitForCssToChange(elm3);
		Thread.sleep(1000);
	}
	public void clickWorkInProgress() throws InterruptedException {
		fluentlyWait(new FluentWait<By>(By.linkText("Work In Progress")));
		driver.findElement(By.linkText("Work In Progress")).click();
		Thread.sleep(2000);
		fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
		WebElement elm1 = driver.findElement(By.className("main-section-loader"));
		waitForCssToChange(elm1);
	}
	public void clickInnerLink(String link) throws InterruptedException {
		Thread.sleep(1000);
		fluentlyWait(new FluentWait<By>(By.linkText("Work In Progress")));
		driver.findElement(By.linkText("Work In Progress")).click();
		Thread.sleep(1000);
		fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
		WebElement elm1 = driver.findElement(By.className("main-section-loader"));
		waitForCssToChange(elm1);
		WebElement sidebar = driver.findElement(By.className("left-sidebar-inner-content"));
		fluentlyWait(new FluentWait<By>(By.linkText("Client Services")));
		sidebar.findElement(By.linkText("Client Services")).click();
		Thread.sleep(5000);
		fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
		WebElement elm2 = driver.findElement(By.className("main-section-loader"));
		waitForCssToChange(elm2);
		sidebar = driver.findElement(By.className("left-sidebar-inner-content"));
		fluentlyWait(new FluentWait<By>(By.linkText(link)));
		sidebar.findElement(By.linkText(link)).click();
		Thread.sleep(1000);
		fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
		WebElement elm3 = driver.findElement(By.className("main-section-loader"));
		waitForCssToChange(elm3);
		Thread.sleep(1000);
	}
	public String readTotal(String link) {
		fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		//System.out.println("trs: "+trs.size());
		WebElement tr = trs.get(trs.size()-1);
		List<WebElement> tds = tr.findElements(By.tagName("td"));
		String value = tds.get(2).getText().trim();
		return value;
	}
	public String getTotalRowValue(int row, int index) {
		fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		WebElement tr = trs.get(row);
		List<WebElement> tds = tr.findElements(By.tagName("td"));
		return tds.get(index).getText().trim().replace(",","");
//		for(WebElement tr:trs) {
//			List<WebElement> tds = tr.findElements(By.tagName("td"));
//			if(tds.get(0).getText().trim().equals(title)) {
//				return tds.get(2).getText().trim().replace(",","");
//			}
//		}
//		return null;
	}
	public String getSumOfColValues(int row, int col, int index) {
		int sum = 0;
		fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		WebElement tr = trs.get(row);
		List<WebElement> tds = tr.findElements(By.tagName("td"));
		for(int count=index;count<=col-1;count++) {
			//System.out.println(Integer.parseInt(tds.get(count).getText().trim().replace(",","")));
			sum += Integer.parseInt(tds.get(count).getText().trim().replace(",",""));
		}
//		for(WebElement tr:trs) {
//			List<WebElement> tds = tr.findElements(By.tagName("td"));
//			if(tds.get(0).getText().trim().equals(title)) {
//				for(int count=3;count<=tds.size()-1;count++) {
//					System.out.println(Integer.parseInt(tds.get(count).getText().trim().replace(",","")));
//					sum += Integer.parseInt(tds.get(count).getText().trim().replace(",",""));
//				}
//			}
//		}
		return Integer.toString(sum);
	}
	public String getSumOfColValuesExceptNeedsReview(int row, int col) {
		int sum = 0;
		fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		WebElement tr = trs.get(row);
		List<WebElement> tds = tr.findElements(By.tagName("td"));
		for(int count=3;count<=col-1;count++) {
			if(count==4)
				continue;
			//System.out.println(Integer.parseInt(tds.get(count).getText().trim().replace(",","")));
			sum += Integer.parseInt(tds.get(count).getText().trim().replace(",",""));
		}
		return Integer.toString(sum);
	}
	public String getTotalFor(int col) {
//		WebElement thead = driver.findElement(By.tagName("thead"));
//		WebElement tr = thead.findElement(By.tagName("tr"));
//		List<WebElement> ths = tr.findElements(By.tagName("th"));
//		for(WebElement th:ths) {
//			if(th.getText().trim().equals(head)) {
//				index = ths.indexOf(th);
//			}
//		}
		fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		List<WebElement> tds = trs.get(trs.size()-1).findElements(By.tagName("td"));
		return tds.get(col).getText().replace(",", "").replace("$", "").trim();
	}
	public String getSumTotalof(int col, int row) {
//		int index = 0;
//		WebElement thead = driver.findElement(By.tagName("thead"));
//		WebElement tr = thead.findElement(By.tagName("tr"));
//		List<WebElement> ths = tr.findElements(By.tagName("th"));
//		for(WebElement th:ths) {
//			if(th.getText().trim().equals(head)) {
//				index = ths.indexOf(th);
//			}
//		}
		int sum = 0;
		fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		for(int count=0;count<row-1;count++) {
			List<WebElement> tds = trs.get(count).findElements(By.tagName("td"));
			//System.out.println(tds.get(col).getText().replace(",", "").replace("$", "").trim());
			sum += Integer.parseInt(tds.get(col).getText().replace(",", "").replace("$", "").trim());
		}
		return Integer.toString(sum);
	}
	public int calculateNumberOfRows() {
		fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		return trs.size();
	}
	public void findProviderOrServiceTableLinkClick(String tableLink) throws InterruptedException {
		fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		for(int i=0;i<trs.size();i++)	{
			List<WebElement> intds = trs.get(i).findElements(By.tagName("td"));
			if(intds.get(0).getText().trim().equals(tableLink)) {
				scrollInToElement(intds.get(0));
				intds.get(0).click();
				Thread.sleep(2000);
				fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
				WebElement elm1 = driver.findElement(By.className("main-section-loader"));
				waitForCssToChange(elm1);
				break;
			}
		}
		Thread.sleep(500);
	}

	public void clickDesirdRowAndColumn(int row,int column) throws InterruptedException {
		fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		WebElement tr = trs.get(row);
		List<WebElement> tds = tr.findElements(By.tagName("td"));
		WebElement td = tds.get(column);
		scrollInToElement(td);
		td.click();
		//System.out.println("clicked on desired table cell");
		Thread.sleep(2000);
		fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
		WebElement elm1 = driver.findElement(By.className("main-section-loader"));
		waitForCssToChange(elm1);
	}
	public int claculateNumberOfColumns() {
		fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		List<WebElement> tds = trs.get(0).findElements(By.tagName("td"));
		return tds.size();
	}
	public void renderDashboardView() throws InterruptedException {
		this.driver.get(getPageUrl());
		Thread.sleep(5000);
	}
	public void clickSwitch() throws InterruptedException {
		fluentlyWait(new FluentWait<By>(By.className("onoffswitch-switch")));
		this.driver.findElement(By.className("onoffswitch-switch")).click();
		Thread.sleep(2000);
		fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
		WebElement elm1 = driver.findElement(By.className("main-section-loader"));
		waitForCssToChange(elm1);
	}
	public void rerenderView(String link) throws InterruptedException {
		driver.findElement(By.linkText(link)).click();
		Thread.sleep(2000);
		fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
		WebElement elm1 = driver.findElement(By.className("main-section-loader"));
		waitForCssToChange(elm1);
	}
	public int getRowNumber(String row_name,int total_rows) {
		fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		for(int count=0;count<total_rows;count++) {
			List<WebElement> tds = trs.get(count).findElements(By.tagName("td"));
			if(tds.get(0).getText().equals(row_name))
				return count;
		}
		return 0;
	}
	public int getColumnNumber(String column_name, int total_columns) {
		fluentlyWait(new FluentWait<By>(By.tagName("thead")));
		WebElement thead = driver.findElement(By.tagName("thead"));
		WebElement tr = thead.findElement(By.tagName("tr"));
		List<WebElement> ths = tr.findElements(By.tagName("th"));
		for(int count=0;count<total_columns;count++) {
			if(ths.get(count).getText().equals(column_name))
				return count;
		}
		return 0;
	}
	public int readValueFromDesiredRowAndColumn(int row, int column) {
		fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		WebElement tr = trs.get(row);
		List<WebElement> tds = tr.findElements(By.tagName("td"));
		WebElement td = tds.get(column);
		return Integer.parseInt(td.getText().trim().replace(",", ""));
	}
	public int getNumberOfRecords(int row, int column) throws InterruptedException {
		fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		WebElement tr = trs.get(row);
		List<WebElement> tds = tr.findElements(By.tagName("td"));
		WebElement td = tds.get(column);
		scrollInToElement(td);
		td.click();
		Thread.sleep(2000);
		fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
		WebElement elm1 = driver.findElement(By.className("main-section-loader"));
		waitForCssToChange(elm1);
		int recordCount = 0;
        if (driver.findElements(By.linkText("next »")).size() == 0) {
            recordCount+=getRecordTotal();
        }else{
            while(driver.findElements(By.linkText("next »")).size() > 0) {
                recordCount+=getRecordTotal();
                if(driver.findElements(By.linkText("next »")).size() > 0){
                    driver.findElement(By.linkText("next »")).click();
                    Thread.sleep(2000);
                }
            }
            recordCount+=getRecordTotal();
        }
		return recordCount;
	}
	public int getRecordTotal(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fluentlyWait(new FluentWait<By>(By.className("main-section-loader")));
		WebElement elm2 = driver.findElement(By.className("main-section-loader"));
		waitForCssToChange(elm2);
		fluentlyWait(new FluentWait<By>(By.id("invoice_items_section2")));
		WebElement tbody1 = driver.findElement(By.id("invoice_items_section2"));
		List<WebElement>  trs1 = tbody1.findElements(By.tagName("tr"));
		return trs1.size();
	}
	public String[][] getTableArrayList(List<WebElement> trs,List<WebElement> tds){
		String[][] table = new String[trs.size()][tds.size()];
		for(int i=0;i<trs.size();i++){
		 List<WebElement> td = trs.get(i).findElements(By.tagName("td"));
		  	for(int j=0;j<tds.size();j++){
		  		table[i][j] = td.get(j).getText().replace(",", "").replace("$", "").trim();
		  	}
		}
		return table;
	}
	public List<WebElement> getTableRow(){
		fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		return trs;
	}
	public List<WebElement> getTableColumn(){
		fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		List<WebElement> tds = trs.get(0).findElements(By.tagName("td"));
		return tds;
	}
	public int getCountDashboardTableTotal(int index){
		fluentlyWait(new FluentWait<By>(By.tagName("tbody")));
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		List<WebElement> tds = trs.get(trs.size()-1).findElements(By.tagName("td"));
		return Integer.parseInt(tds.get(2).getText().replace(",", "").replace("$", "").trim());
	}
	public void setCustomeMonth(String navigationType,int totalMonths) throws InterruptedException{
		WebElement div = driver.findElement(By.id("ui-datepicker-div"));
		List<WebElement> aTags = div.findElements(By.tagName("a"));
		if(navigationType.equals("back")){
			for(int i=0;i<totalMonths;i++){
				div = driver.findElement(By.id("ui-datepicker-div"));
				aTags = div.findElements(By.tagName("a"));
				aTags.get(0).click();// previous = 0 and next = 1 from current month
				Thread.sleep(1000);
			}
		}
		if(navigationType.equals("next")){
			for(int j=0;j<totalMonths;j++){
				div = driver.findElement(By.id("ui-datepicker-div"));
				aTags = div.findElements(By.tagName("a"));
				aTags.get(1).click();// previous = 0 and next = 1 from current month
				Thread.sleep(1000);
			}
		}
		Thread.sleep(1000);
		WebElement dateWidget = driver.findElement(By.className("ui-datepicker-calendar"));
		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
		for (WebElement cell: columns){
		   if (cell.getText().equals(orderConfig.getDueDate())){
		      cell.findElement(By.linkText(orderConfig.getDueDate())).click();
		      Thread.sleep(1000);
		      break;
		   }
		}
	}
}
