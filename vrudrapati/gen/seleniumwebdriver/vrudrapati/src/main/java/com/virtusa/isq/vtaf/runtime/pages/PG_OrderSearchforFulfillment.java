package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_OrderSearchforFulfillment implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_OrderSearchforFulfillment {

        ele_lblOrderSearchforFulfillment("//label[text()='<idf_SearchCategory>']/..//input[@id='<idf_SearchCategoryID>']"), tf_OrderID("//INPUT[@id='orderid']"), btn_Search("//BUTTON[@id='contractNumberSearch']"), ele_lblSearchResults("//SPAN[@id='seriesIconId_0']"), btn_Expandall("//SPAN[@id='seasonIconId_0']"), tf_Contractnumber("//*[@id=\"contractNumber\"]"), btn_Expandallcontract("//*[@id=\"expandCollapseAll\"]"), ele_ddChannel("//ul[@unselectable='on' and contains(@id,'channelId_listbox')]//li[contains(text(),'<channel>')]"), btn_Searchchannel("//*[@id=\"selectOptionsFormButton\"]"), ele_ddSelectHeader("//label[text()='<idf_HeaderName>']/..//div[@class='k-multiselect-wrap k-floatwrap']"), tf_ProgramTitle("//label[text()='Program Title']/..//input"), ele_ddPrimaryTerritory("//label[text()='Primary Territory']/..//input"), btn_Search2("//button[@id=\"selectOptionsFormButton\"]"), ele_tabClickOnFirstResultHeader("//ul[@id=\"panelbarFullfillmentSearch\"]/li[1]"), ele_tabExpandSeason("//ul[@id=\"panelbarFullfillmentSearch\"]/li[1]//span[contains(text(),'<idf_SeasonNo>')]"), ele_ProgramNameVerify("//span[text()=\" Season: 1\"]/../../following::div[1]//a[text()='Program Name']/../../../../../following::div[1]//tr[@role=\"row\"][1]//td[7]"), ele_SelectCheckBox("//span[text()=\" Season: 1\"]/../../following::div[1]//a[text()='Program Name']/../../../../../following::div[1]//tr[<idf_RowNumber>]//input"), ele_ClickOnSeason("xpath=(//span[contains(text(),'<idf_SeasonNo>')])[1]"), chk_SearchResultHeaderCheckBox("//div[text()='<idf_searchResultName>']/..//input"), ele_ddSelection("//li[text()='<idf_SelectionOption>']"), ele_ddDropDownIcon("//span[@aria-controls='<idf_SelectionColumnID>']"), chk_FulfillmentCartPageSelectFirstSearchResult("//a[text()='<idf_ColumnHeadingName>']/../../../../../../..//tr/td/input[@id='0']"), ele_ddGridSelectDropDown("//a[text()='<idf_ColumnName>']/../../../../../../../..//span[@class=\"k-widget k-combobox k-header\"]//input[@tabindex='<idf_RowNumber>']"), ele_ddFulfillmentCartBulkUpdate("xpath=(//div[text()='Bulk Update']//following::div//input)[1]"), ele_ddFulfillmentCartBulkUpdateValueField("(//span[@class=\"k-widget k-combobox k-header\"]//input)[8]"), tbl_GridClick("xpath=(//td[@role='gridcell'])[12]"), ele_ExpandSeasonIndex("xpath=(//span[contains(text(),'<idf_SeasonNo>')])[1]"), ele_SelectBox("//a[text()='Program Name']/following::input[@type='checkbox'][<idf_RowNumber>]");

    private String searchPath;
  
    /**
    *  Page PG_OrderSearchforFulfillment.
    */
    private PG_OrderSearchforFulfillment(final String psearchPath) {
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