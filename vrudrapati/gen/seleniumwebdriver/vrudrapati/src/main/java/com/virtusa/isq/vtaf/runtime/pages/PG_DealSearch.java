package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_DealSearch implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_DealSearch {

        tf_ContractNo("//label[text()='Contract Number']/../input"), ele_ddDealDetailSearch("//label[text()='<idf_DealDetails>']/../div/div"), ele_ddPrimaryTerritory("//label[text()='Primary Territory']/..//input"), btn_DealSearch("//button[@id='dealSearchBtn']"), ele_ClickOnDealSearchResult("//a[text()='<idf_DealTableHeader>']/following::tr[<idf_TableRow>]/td[<idf_TableColumn>][@role='gridcell']"), ele_VerfiySearchedResults("//a[text()='<idf_TableHeader>']/following::tr[1]/td[contains(text(),'<idf_SearchedInfo>')]"), ele_SelectFromList("//ul[@id='<idf_listbox>']/li[text()='<idf_SelectData>']"), ele_SelectFromTerritoryList("//ul[@id='territoryField_listbox']/li[text()='<idf_SelectData>']"), ele_MultiFacetSearcheField("//label[text()='<idf_LabelName>']/..//div[@class='k-widget k-multiselect k-header']"), ele_PrmaryTeritory("//label[text()='Primary Territory']/..//span[@class='k-widget k-combobox k-header']"), ele_SelectAOption("//li[text()='<idf_SelectOption>']"), ele_SelectTerritory("//h3[text()=\"Select Territories\"]/../..//div[@id='territory']"), ele_SelectHeader("//h3[text()=\"<idf_HeaderName>\"]/../..//div[@class=\"k-multiselect-wrap k-floatwrap\"]"), ele_VerifyAddRight("//a[text()='Territory']/following::tr[last()]/td[text()='<idf_Country>']/preceding::a[text()='Language']/following::tr[last()]/td[text()='<idf_Language>']/preceding::a[text()='Media']/following::tr[last()]/td[text()='<idf_Media>']"), ele_VerifyHeader("//a[text()='<idf_TableHeader>']/following::tr[1]"), ele_lblImageTypeOption("//ul[@id='imageTypeId_listbox']/li[text()='<idf_SelectOption>']"), ele_lblVerifyDealsCount("//div[contains(text(),'<idf_Name>')]"), ele_NewRow("//div[text()='<idf_gridHeader>']/..//table//tr[last()]/td[<idf_IndexNumber>]"), tf_InputRowDetails("//td[@role=\"gridcell\"]//input[@aria-autocomplete=\"both\"]"), ele_ddCellDetailsSelect("//li[text()='<idf_CellDetail>']"), ele_TableLastRow("xpath=(//table//following::tr[last()]//td)[1]"), btn_AddNewButton("//div[text()='<idf_gridHeader>']/..//button[text()='<idf_ButtonName>']"), ele_VerifyResults("//th[text()='<idf_ColumnHeader>']/following::tr/td[text()='<idf_Cellresult>']"), ele_ResultRowCount("//th[text()='<idf_ColumnName>']//following::tr");

    private String searchPath;
  
    /**
    *  Page PG_DealSearch.
    */
    private PG_DealSearch(final String psearchPath) {
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