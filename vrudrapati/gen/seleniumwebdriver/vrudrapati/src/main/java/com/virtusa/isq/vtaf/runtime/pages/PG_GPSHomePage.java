package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_GPSHomePage implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_GPSHomePage {

        ele_lblPageHeader("//h1[contains(text(),'<idf_PageHeader>')]"), lnk_Menu("//h2[text()='<idf_Menu>']"), lnk_SideMenuItems("//h1[text()='Menu']//following::span[contains(text(),'<idf_SideMenuItems>')][1]"), lnk_SubMenuItem1("xpath=(//ul[@id='navbar']//span[contains(text(),'<idf_SideMenuItem>')])[1]/../ul/li/span[contains(text(),'<idf_SubMenuItem1>')]"), lnk_SubMenuItem2("xpath=(//ul[@id='navbar']//span[contains(text(),'<idf_SideMenuItem>')])[1]/../ul/li/a[contains(text(),'<idf_SubMenuItem2>')]"), rdo_SelectFilters("//label[text()='<idf_FilterName>']/../div/ins[@class=\"iCheck-helper\"]"), lnk_SubMenuItemCount("//h1[text()='Menu']//following::span[contains(text(),'<idf_SideMenuItems>')][1]/../ul[@class='k-group k-panel']/li"), tf_GlobalSearchField("//input[@name=\"srch-term\"]"), btn_Search("//button[@id=\"searchBtn\"]"), ele_lblDashboardChartHeader("//h1[text()='<idf_HeaderName>']"), lnk_SignOut("//a[text()='Sign Out'] | //span[text()='sign out']"), lnk_Menu11("/html/body/header/div/div[2]/div[3]"), lnk_NewDealLink("//span[text()='New Deal']"), lnk_Dealsearch("//a[@href='/Deal/Deals']"), lnk_DealOffer("//a[@href='/DealOffer/DealsOffer']"), lnk_Deal("//li[@id='dealMenuId']/span"), lnk_Fulfillment("//span[contains(text(),'Fulfillment')]"), lnk_FulfillOrder("//a[text()='Fulfill Order']"), lnk_AssetCreation("//a[text()='Asset Creation']"), lnk_OrderDetails("//span[text()='Order Details']"), lnk_Reports("//li[@id='reportsMenuId']/span"), lnk_Dashboard("//a[text()='Dashboard']"), lnk_Programming("//li[@id='programmingMenuId']/span"), lnk_SearchPrograms("/html/body/div[1]/div/div[2]/nav/div[2]/ul/li[1]/ul/li[1]/a"), tf_GlobalSearchTextBox("//input[@id='srch-term']"), btn_GlobalSearchClick("//button[@id='searchBtn']"), btn_OrdersSearch("//*[@id=\"searchOrderClickedFromMenuId\"]/span"), tf_Dashboardtext("//H1[@class='topm float-left'][text()='Dashboard']"), lnk_EditPrograms("//*[@id=\"programEditClickedFomMenuId\"]/a"), lnk_Exhibit("//A[@href='/DealOffer/PitchList'][text()='Exhibit']"), ele_ddDealtype1("/html/body/div[3]/div/ul/li[contains(text(),'<DealType1>')]"), ele_ddDealtypedropdown("//*[@id=\"dealSearchFilters\"]/div[4]/div[2]/div[1]/div[1]/div/div"), lnk_SearchOrderDetails("//*[@id=\"searchOrderDetailsClickedFromMenuId\"]/span"), lnk_GPSHyperLink("//a[@href='/Dashboard/Index' and text()='GPS']"), lnk_ClientProfile("//li[@role='menuitem']/span[contains(text(),'Client Profile')]"), lnk_Administration("//li[@id='administrationMenuId']//span[contains(text(),'Administration')]"), ele_MenuItem("//div[@id='show_menu']//h2[text()='Menu']");

    private String searchPath;
  
    /**
    *  Page PG_GPSHomePage.
    */
    private PG_GPSHomePage(final String psearchPath) {
        this.searchPath = psearchPath;
    }
    
    /**
     *  Get search path.
     * @param searchPath search path.
     */
    public final String getSearchPath() {
        return searchPath;
    }
}