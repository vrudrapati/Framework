package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_ReportPage implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_ReportPage {

        ele_lblCommonReportsName("//td[text()='<idf_ReportName>']"), ele_lblReportCounts("//span[text()='<idf_TabName>']/../../..//table[@class=\"k-selectable\"]//td[@role=\"gridcell\"]/.."), tf_DateText("//div[text()='<idf_DateText>' ] /following::div[2]/span/span/input"), tf_CommonSalesSummaryReprtDetails("//div[text()='<idf_SearchDetails>'] /following::div[2]/div/div/div"), ele_lblSearchChannel("//div[@class=\"k-animation-container\"]//li"), ele_lblCalenderDropDown("//div[text() = '<idf_TextFieldName>']/../..//span[@class=\"k-icon k-i-calendar\"]"), ele_ddCalenderExpandedStatus("//div[text()=\"Created Date (To)\"]/..//following::span[@class=\"k-icon k-i-calendar\"]/../..//input[@aria-expanded=\"<idf_expandedStatus>\"]"), ele_PageFilters("//div[text()='<idf_PageFilterName>']/../..//input"), ele_TabHeadingName("//span[text()='<TabHeading>']"), tf_SavePopupReportName("//label[text()='Report Name']/../input"), ele_lblNewReportName("//div[@class=\"k-grid-content\"]//tr[last()]/td[6]"), ele_ddSearchDetailsClick("//div[text()='<idf_SearchFieldHeadingName>']/../..//input"), ele_ddSearchDetailsSelect("//div[text()='<idf_SeachFieldHeadingName>']/../../../../../../../../../../../../../../..//li[text()='<idf_SearchSelectValue>']"), ele_lblTableSearchRowCount("//div[text()='<idf_TableColumnName>']/../..//div[text()='<idf_TableColumnValue>']"), ele_lblTableSearchedRowValue("xpath=(//div[text()='<idf_TableColumnName>']/../..//div[contains(text(),'<idf_TableColumnValue>')])[<idf_RowNumber>]"), ele_ExportOption("//span[@class=\"k-icon k-i-arrow-s\"]/../..//span[text()='<idf_ExportOptionName>']"), ele_ddExportOptionIcon("//ul[@role='menubar']/li/a[@title='Export']"), ele_CalandarMonthAndYear("xpath=(//div[@data-role=\"calendar\"]/div/a[2])[last()]"), ele_lblYearMonthDate("xpath=(//table[@class='k-content']//a[text()='<idf_Date>'][contains(@title,'<idf_Month>')])[last()]"), ele_lblDragColumn("//div[text()='<idf_DragColumnName>']"), ele_DropPosition("//div[@id='grid']/div[@class=\"k-grouping-header\"]"), ele_ddFilterFileds("//div[text()=\"Show items with value that:\"]//following::span[@class=\"k-input\" and text()=\"<idf_StartandEndDateandAND>\"]"), ele_btnFilterIcon("//a[@class=\"k-grid-filter\"]/following::a[text()='<idf_FilterIcon>']/../a[@class=\"k-grid-filter\"]"), ele_multiSelectFilterValuesTextFiled("//div[text()=\"MultiSelect Filter Values:\"]//following::div[@class=\"k-multiselect-wrap k-floatwrap\"]"), ele_ddcountrySelector("//ul[@unselectable=\"on\" and @data-role=\"staticlist\"]//li[@role=\"option\" and text()=\"<idf_Country>\"]"), ele_StartDate("xpath=(//td[contains(@class,'k-today k-state-focused')]/a)[1]"), ele_EndDate("xpath=(//td[contains(@class,'k-today k-state-focused')]/a)[2]"), btn_StartDatePicker("//span[text()='Start Date']/../../following-sibling::span[1]//span[@class=\"k-icon k-i-calendar\"]"), btn_EndDatePicker("//span[text()='End Date']/../../following-sibling::span[1]//span[@class=\"k-icon k-i-calendar\"]"), btn_FilterIcon("//span[@class=\"k-icon k-filter\"]"), ele_FilterIcon("//a[@class=\"k-grid-filter\"]/following::a[text()='<idf_ReleaseDateOrAlternateVersion>']/../a[@class=\"k-grid-filter\"]"), ele_LblCalanderIcon("//span[text() = 'Start Date']//following::span[@class=\"k-icon k-i-calendar\"][1]"), ele_btnCalenderFilterIcon("//a[text()=\"Release Date\"]//preceding::span[@class=\"k-icon k-filter\"][1]"), ele_PickStartDate("xpath=(//a[text()='<idf_MonthAndYear>']/following::td/a[text()='<idf_StartDate>'])[1]"), ele_PickStartMonth("xpath=(//a[text()='<idf_StartYear>' and @role='button']/following::td/a[text()='<idf_StartMonth>'])[1]"), ele_PickYearRange("xpath=(//a[text()='<idf_YearRange>' and @role='button']/following::td/a[text()='<idf_YearRangeClick>'])[1]"), ele_iconEndDate("//input[@data-bind=\"value: filters[1].value\"]"), ele_btnEndDateCalender("//button[@type=\"submit\" and text()=\"Filter\"]"), ele_ddCalender("//div[text()='<idf_DatePickerHeadingName>']/../..//span[text()='select']"), ele_lblRowCount("//div[contains(@class,'textBox2 s7')]"), ele_searchResult("//*[@id=\"reportstab_476\"]/div[2]/table/tbody/tr[1]/td[4][contains(text(),'<idf_prm>')]"), ele_reportPage("//*[@id=\"episodeGrid_1868_923\"]/div[2]/table/tbody/tr/td[15][contains(text(),'<idf_USPermiereDate>')]");

    private String searchPath;
  
    /**
    *  Page PG_ReportPage.
    */
    private PG_ReportPage(final String psearchPath) {
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