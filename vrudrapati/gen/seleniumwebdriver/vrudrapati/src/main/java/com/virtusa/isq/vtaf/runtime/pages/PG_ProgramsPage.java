package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_ProgramsPage implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_ProgramsPage {

        btn_ClickOnSeries("//span[@dealsearchlevelicon='true' and @id='seriesIconId_0']"), lnk_ExpandSeries("//span[@id='seasonIconId_0' and @dealsearchlevel2icon='true']"), lnk_ClickOnProgram("//td[text()='<idf_ProgramId>']"), ele_tabRightsTab("//span[text()='<idf_Rights>']"), ele_WorldWideIconVerify("//span[@id='worldwideIconId']"), ele_RightsAcuriedVerify("//span[@id='rightsAcrId_0']"), ele_RightsNotAcquiredVerify("//span[@id='rightsNotAcrId_0']"), tf_Series_Text_Box("//ul[@unselectable='on' and contains(@id,'series_listbox')]//li[contains(text(),'<SeriesName>')]"), btn_Search_Btn("//button[@id='selectOptionsFormButton']"), tf_ProgramId("/html/body/div[1]/div/div[2]/div/article/div[1]/div/form[1]/div[2]/div[1]/div[2]/div/input"), btn_ProgramIdSearch("//button[@id='idOrTitleSearchButton']"), ele_tabOrderstab("//*[@id=\"programsOrderTabId\"]/span[2]"), tf_orderid_2299("//*[@id=\"program_detail_order\"]/div[2]/table/tbody/tr[1]/td[1]"), ele_tabImageTab("//*[@id=\"imagesTabId\"]/span[2]"), ele_Addownload("//*[@id=\"e7ca04bf-210a-4c3e-b9c8-54ac20bd37b0\"]"), btn_UploadImageBtn("//*[@id=\"uploadImagePopupId\"]"), ele_dropbtn("//*[@id=\"lettreAndThumbFileId\"]/div/div/div"), ele_tabSupportingdocumentstab("//SPAN[@class='k-link'][text()='Supporting Documents']"), ele_lblSucessMessage("//span[@id=\"screenersSuccessText\" and normalize-space(text()='<idf_VerifyMessege>')]"), btn_disblePropertyCheck("//button[@id=\"GoToScreenerListId\" and @disabled]"), ele_ProgramID("//*[@id=\"pplID\"]"), ele_SeasonNo("//*[@id=\"seasonLiId_1868_923\"]/span/span[1]/span[2]"), btn_SeasonName("//*[@id=\"seriesLiId_1868\"]/span/span[1]/div[1]"), btn_Expand("//*[@id=\"expandCollapseAll\"]");

    private String searchPath;
  
    /**
    *  Page PG_ProgramsPage.
    */
    private PG_ProgramsPage(final String psearchPath) {
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