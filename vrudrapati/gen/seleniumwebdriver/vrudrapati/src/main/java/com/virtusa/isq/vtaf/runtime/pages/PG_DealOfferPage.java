package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_DealOfferPage implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_DealOfferPage {

        tf_ProgramId("//*[@id=\"programId\"]"), ele_ddExclusivity("//ul[@unselectable='on' and contains(@id,'delaExclusiveId_listbox')]//li[contains(text(),'<Exclusivity>')]"), ele_ddFirstRun("//ul[@unselectable='on' and contains(@id,'firstRunId_listbox')]//li[contains(text(),'<FirstRun>')]"), ele_ddLanguage("//ul[@unselectable='on' and contains(@id,'languageId_listbox')]//li[contains(text(),'<Language>')]"), ele_ddLinear("//ul[@unselectable='on' and contains(@id,'linearId_listbox')]//li[starts-with(text(),'<Linear>')]"), ele_ddDomesticFormat("//ul[@unselectable='on' and contains(@id,'formatTypeId_listbox')]//li[contains(text(),'<DomesticFormat>')]"), btn_ClickTerritory("//div[@id='primaryTerritoryDivId']"), chk_TerritoryWorldWideTree("//div[@id='_easyui_tree_1']//span"), chk_TerritorySelect1("//ul[@class='tree']//span[text()='<Territory1>']"), chk_TerritorySelect2("//ul[@class='tree']//span[text()='<Territory2>']"), chk_TerritorySelect3("//ul[@class='tree']//span[text()='<Territory3>']"), chk_TerritorySelect4("//ul[@class='tree']//span[text()='<Territory4>']"), chk_TerritorySelect5("//ul[@class='tree']//span[text()='<Territory5>']"), ele_ddSyndication("//ul[@unselectable='on' and contains(@id,'syndicationId_listbox')]//li[contains(text(),'<^Syndication>')]"), ele_ddNewMedia("//ul[@unselectable='on' and contains(@id,'newMediaId_listbox')]//li[contains(text(),'<NewMedia>')]"), ele_ddNewmedia1("//ul[@unselectable='on' and contains(@id,'newMediaId_listbox')]//li[contains(text(),'<NewMedia1>')]"), ele_ddNewMedia2("//ul[@unselectable='on' and contains(@id,'newMediaId_listbox')]//li[contains(text(),'<NewMedia2>')]"), ele_ddNewMedia3("//ul[@unselectable='on' and contains(@id,'newMediaId_listbox')]//li[contains(text(),'<NewMedia3>')]"), ele_ddNewMedia4("//ul[@unselectable='on' and contains(@id,'newMediaId_listbox')]//li[contains(text(),'<NewMedia4>')]"), ele_ddNewMedia5("//ul[@unselectable='on' and contains(@id,'newMediaId_listbox')]//li[contains(text(),'<NewMedia5>')]"), ele_ddReTransmission("//ul[@unselectable='on' and contains(@id,'reTransmissionId_listbox')]//li[contains(text(),'<Retransmission>')]"), tf_StartDate("//input[@id='startDateId']"), tf_EndDate("//input[@id='endDateId']"), btn_SearchBtn("//button[@id='dealOfferSearchBtn']"), ele_ddSeriesName("//ul[@unselectable='on' and contains(@id,'seriesId_listbox')]//li[contains(text(),'<SeriesName>')]"), ele_ddCategory("//ul[@unselectable='on' and contains(@id,'categoryid_listbox')]//li[contains(text(),'<Category>')]"), rdo_IncludeAlternativeVersionsNo("//input[@id='alternateversionOptionNo']"), ele_ResultGrid("//div[@id='searchResultsAccordianId']"), ele_ResultGrid2("//div[@class='seariesName']"), ele_lblNoRecordsFoundError("//div[@id='errorMessageTextId']"), btn_ExportAllPrograms("//BUTTON[@id='exportToExcelClickedId']"), tf_SeriesIconID("//*[@id=\"seriesIconId_0\"]"), tf_SeasonIconID("//*[@id=\"seasonIconId_0\"]"), tf_AvailabilityFrom("//TD[@role='gridcell'][text()='Available From ']"), ele_Availability("//TD[@role='gridcell'][text()='Available']"), tf_AvailabilityTo("//TD[@role='gridcell'][text()='Available To ']"), ele_ddProgramSelect("//INPUT[@id='0']"), btn_Save("//*[@id=\"saveSearchClickedId\"]"), btn_SaveSearch_Button("//*[@id=\"saveSearchDealofferBtn\"]"), ele_ddSavedSearches_Dropdown("//ul[@unselectable='on' and contains(@id,'savedSearchId_listbox')]//li[contains(text(),'<SavedSearchName>')]"), ele_ddvivekvalue("//*[@id=\"savedSearchId_listbox\"]/li[1152]"), tf_Searchcriterianame("//*[@id=\"saveSearchName\"]");

    private String searchPath;
  
    /**
    *  Page PG_DealOfferPage.
    */
    private PG_DealOfferPage(final String psearchPath) {
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