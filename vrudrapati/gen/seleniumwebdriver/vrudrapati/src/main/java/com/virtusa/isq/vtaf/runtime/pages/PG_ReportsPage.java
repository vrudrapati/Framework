package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_ReportsPage implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_ReportsPage {

        ele_tabStandardReportTab("//span[text()='Standard Reports']"), ele_StandardReportGridView("//div[@id='powerReportsTabStripId-1']"), lnk_ReportSelection("//td[text()='<idf_StandaredReport>']"), tf_ReleaseStartDate("//*[@id='reportViewer1']/div/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/span/span/input"), tf_ReleaseEndDate("//*[@id='reportViewer1']/div/div[2]/div[1]/div[2]/div[1]/div[2]/div[3]/span/span/input"), tf_FromCreatedDate("//*[@id='reportViewer1']/div/div[2]/div[1]/div[2]/div[1]/div[3]/div[3]/span/span/input"), tf_ToCreatedDate("//*[@id='reportViewer1']/div/div[2]/div[1]/div[2]/div[1]/div[4]/div[3]/span/span/input"), btn_ShowReportButton("//button[text()='Show Report']"), ele_lblReportValidation("//div[@class='trv-page-container']//div[@class='trv-page-wrapper active']"), btn_ExportBtn("//a[@title='Export']"), btn_ExcelBtn("//a/span[text()='Excel 97-2003']"), NoRecordsFoundError("//div[text()='No Records Found']"), ele_tabNewPRReport("//*[@id=\"reportstab_476\"]/div[2]/table/tbody/tr[1]/td[3]"), tf_ChannelName("//*[@id=\"reportViewer1\"]/div/div[2]/div[1]/div[2]/div[1]/div[5]/div[3]/div/div/div[1]"), tf_OrderStatus("//*[@id=\"reportViewer1\"]/div/div[2]/div[1]/div[2]/div[1]/div[6]/div[3]/div/div/div[1]"), ele_ddSalesPerson("//INPUT[@class='k-input dropDownlist']"), tf_SeriesName("//*[@id=\"reportViewer1\"]/div/div[2]/div[1]/div[2]/div[1]/div[8]/div[3]/div/div/div[1]"), ele_lblSeasonNumber("//*[@id=\"reportViewer1\"]/div/div[2]/div[1]/div[2]/div[1]/div[9]/div[3]/div/div/div[1]"), ele_lblContactNumber("//*[@id=\"reportViewer1\"]/div/div[2]/div[1]/div[2]/div[1]/div[10]/div[3]/input"), tf_FromClientNeedByDate("(//INPUT[@type='text'])[8]"), tf_ToClientNeedByDate("(//INPUT[@type='text'])[9]"), tf_TargetShipStartDate("//*[@id=\"reportViewer1\"]/div/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/span/span/input"), tf_TargetShipEndDate("(//INPUT[@type='text'])[3]"), tf_OrderStartDate("//*[@id=\"reportViewer1\"]/div/div[2]/div[1]/div[2]/div[1]/div[3]/div[3]/span/span/input"), tf_OrderEndDate("//*[@id=\"reportViewer1\"]/div/div[2]/div[1]/div[2]/div[1]/div[4]/div[3]/span/span/input"), ele_ddChannelName1("/html/body/div[7]/div/ul/li[contains(text(),'<ChannelName1>')]"), ele_ddChannelName2("/html/body/div[7]/div/ul/li[contains(text(),'<ChannelName2>')]"), ele_ddChannelName3("/html/body/div[7]/div/ul/li[contains(text(),'<ChannelName3>')]"), ele_ddOrderStatus1("/html/body/div[8]/div/ul/li[contains(text(),'<OrderStatus1>')]"), ele_ddOrderStatus2("/html/body/div[8]/div/ul/li[contains(text(),'<OrderStatus2>')]"), ele_ddOrderStatus3("/html/body/div[8]/div/ul/li[contains(text(),'<OrderStatus3>')]"), ele_ddOrderStatus4("/html/body/div[8]/div/ul/li[contains(text(),'<OrderStatus4>')]"), ele_ddSalesPersondropdown("//*[@id=\"reportViewer1\"]/div/div[2]/div[1]/div[2]/div[1]/div[7]/div[3]/div/span/span/span"), tf_SeriesName1("/html/body/div[10]/div/ul/li[starts-with(text(),'<SeriesName1>')]"), tf_SeriesName2("/html/body/div[10]/div/ul/li[starts-with(text(),'<SeriesName2>')]"), tf_SeriesName3("/html/body/div[10]/div/ul/li[starts-with(text(),'<SeriesName3>')]"), tf_SeasonNumber1("/html/body/div[11]/div/ul/li[contains(text(),'<SeasonNumber1>')]"), tf_SeasonNumber2("/html/body/div[11]/div/ul/li[contains(text(),'<SeasonNumber2>')]"), tf_SeasonNumber3("/html/body/div[11]/div/ul/li[contains(text(),'<SeasonNumber3>')]"), ele_lblOpenOrderReportResultsCheck("(//DIV[text()='Channel'])[1]"), ele_lblChannelname("//*[@id=\"reportViewer1\"]/div/div[2]/div[1]/div[2]/div[1]/div[5]/div[3]/div/div/div[1]");

    private String searchPath;
  
    /**
    *  Page PG_ReportsPage.
    */
    private PG_ReportsPage(final String psearchPath) {
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