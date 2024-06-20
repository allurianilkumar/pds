package net.premieredigital.portal.reports.dashboardview;

import net.premieredigital.portal.BaseTest;
import net.premieredigital.portal.ordersview.othertest.GlobalSearch;
import net.premieredigital.portal.reports.dashboardview.correctnumberofrecordsinlistview.DashboardTableCountListView;
import net.premieredigital.portal.reports.dashboardview.totalforallrowsandcolumns.DashboardTotalsView;
import net.premieredigital.portal.reports.dashboardview.totalforallrowsandcolumns.PackagedVsNonPackagedFilter;
import net.premieredigital.portal.reports.dashboardview.totalforallrowsandcolumns.TestFiltersOnDashboard;

import org.testng.annotations.Test;

public class DashboardWrapper extends BaseTest{
	@Test(description="7.1.1 Dashboard View for Finished Orders")
    public void dashboardTableCountListView()  throws Exception {
		DashboardTableCountListView dashboardTableCountListView = new DashboardTableCountListView(this);
		dashboardTableCountListView.dashboardTableCountListView();
    }
	@Test(groups = "ExcludeSafari",description="7.1.2 Synchronization of dashboard items: Client Services,Mastering and Fulfillment")
    public void workInProgressSynchronization()  throws Exception {
		WorkInProgressSynchronization workInProgressSynchronization = new WorkInProgressSynchronization(this);
		workInProgressSynchronization.workInProgressSynchronization();
    }
	@Test(description="7.1.3 Checking Dashboard Totals for all statuses")
    public void dashboardTotalsView()  throws Exception {
		DashboardTotalsView dashboardTotalsView = new DashboardTotalsView(this);
		dashboardTotalsView.dashboardTotalsView();
    }
	@Test(groups = "ExcludeSafari",description="7.1.5 Packaged vs Non-Packaged Filter")
    public void packagedVsNonPackagedFilter()  throws Exception {
		PackagedVsNonPackagedFilter packagedVsNonPackagedFilter = new PackagedVsNonPackagedFilter(this);
		packagedVsNonPackagedFilter.packagedVsNonPackagedFilter();
    }
	@Test(description="7.1.6 Test Filters on Dashboard")
    public void testFiltersOnDashboard()  throws Exception {
		TestFiltersOnDashboard testFiltersOnDashboard = new TestFiltersOnDashboard(this);
		testFiltersOnDashboard.testFiltersOnDashboard();
    }
}
