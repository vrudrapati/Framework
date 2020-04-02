package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_DealOffer implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_DealOffer {

        tf_ProgramId("//label[text()=\"Program Id\"]/../input"), ele_ddSearchDetails2("//label[text()='<idf_SearchDetails>']/../span//select"), btn_Search("//button[@id='dealOfferSearchBtn']"), ele_DatePicker("//input[@id='<idf_DatePickerValue>']/..//span[@class=\"k-icon k-i-calendar\"]"), ele_Date("//td[contains(@class,'k-state-selected k-state-focused')]/a"), ele_CalandarMonthAndYear("xpath=(//div[@class='k-header']/a)[2]"), ele_YearMonthDate("//table[@class='k-content']//a[text()='<idf_Date>'][contains(@title,'<idf_Month>')]"), ele_Month("//a[@class='k-link' and contains(text(),'<idf_Month>')]"), ele_Year("//a[@class='k-link' and contains(text(),'<idf_Year>')]"), ele_EndDate("//div[@id='endDateId_dateview']//a[2]"), ele_SelectDate("//span[@aria-controls='endDateId_dateview']/../../../../../../../../../../../../../..//td[@aria-selected='true']/a"), ele_Territory("//label[text()='Territory']/../div"), ele_lblTerritoryName("//span[text()='<idf_Territory>']"), ele_ExapndItem("//span[text()='<idf_ExpandItem>']/../span[@class=\"tree-hit tree-collapsed\"]"), ele_AddRightsTerritory("//h3[text()='Select Territories']/../div "), ele_RightsTf("//label[text()='<idf_RightsLabel>']/..//input"), ele_Option("//li[text()='<idf_Option>']"), chk_TerritoryCountry("//span[text()='<idf_Country>']/..//span[@class='tree-checkbox tree-checkbox0']"), ele_IntOption("//li[text()=\"Int'l Off-Net\"]"), btn_DealOfferSearch("//button[@id='dealOfferSearchBtn']"), ele_YearMonthDateEnd("xpath=(//table[@class='k-content'])[2]//a[text()='<idf_Date>'][contains(@title,'<idf_Month>')]"), ele_WorldWide("//div[text()='<idf_WorldWide>']"), ele_dataGird("//div[@class=\"k-grid-content\"]"), ele_lblCommonDiv("//label[text()=\"<idf_CommonLabelText>\"]/../div"), ele_lblCommonSpan("//label[text()=\"<idf_CommonLabelText>\"]/../span"), tf_CommonDateField("//label[text()=\"<idf_CommonDateLabelText>\"]/../span//span//input"), rdo_AlternateVersion("//label[text()='Include Alternate Versions']/..//input[@value='<idf_LabelYesNo>']/..//ins"), ele_SavedSearchOption("xpath=(//li[contains(text(),'<idf_SavedSearch>')])[<idf_Index>] "), chk_Series("//div[text()=\"<idf_SeriesName>\"]/..//input"), ele_lblSeries("//div[@class=\"seariesName\"]"), ele_selectSeriesName("xpath=(//div[@class=\"seariesName\"])[1]"), ele_firstHeaderSeason("xpath=(//div[@class=\"seariesName\"])[1]/../../following::div[1]//span[contains(text(),'<idf_SeasonNo>')]"), ele_RowCount("xpath=(//div[@class=\"seariesName\"])[1]/../../following::div[<idf_IndexNO>]//tr"), ele_EndDatePicker("//span[@aria-controls='endDateId_dateview']");

    private String searchPath;
  
    /**
    *  Page PG_DealOffer.
    */
    private PG_DealOffer(final String psearchPath) {
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