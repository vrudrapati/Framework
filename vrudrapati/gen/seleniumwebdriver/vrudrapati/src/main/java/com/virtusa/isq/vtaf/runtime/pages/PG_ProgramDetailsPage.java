package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_ProgramDetailsPage implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_ProgramDetailsPage {

        ele_lblTabName("//span[normalize-space(text())='<idf_TabName>']"), ele_DetailsTab("//span[contains(text(),'Details ')]/../../../div/div/h2[text()=\"International Metadata\"]"), ele_AssetsTab("//span[contains(text(),'Assets')]/following::tr/th[1]/a[text()='Asset No']"), ele_ImagesTab("//span[contains(text(),'Images')]/../../../div/div/h2[text()=\"Images\"]"), ele_RightsTab("//span[contains(text(),'Rights')]/../../../div//label[text()=\"Rights\"]"), ele_OrdersTab("//span[contains(text(),'Orders')]/../../../div//div[text()=\"Search Result: \"]"), ele_AssignmentTab("//span[contains(text(),'Assignments')]/following::tr/th/a[text()='Assignment Name']"), btn_UploadImage("//button[text()='<idf_ButtonName>']"), ele_ddSelectImageType("//label[text()='Image Type:']/..//input/../span/span"), btn_UploadFromLocal("xpath=(//label[text()='<idf_HeaderName>']/..//input)[1]"), btn_ImageHeaderSave("//label[text()='<idf_UploadImageHeader>']/..//button[text()='Save']"), ele_ImageSave("//button[@id='saveSearchClickedId']"), ele_lblProgramTabResult("//div[@class=\"k-grid-content\"]//tbody[@role=\"rowgroup\"]//tr[@role=\"row\"]"), ele_lblVerifyResults("//span[text()='<idf_TabName>']/../../..//div[text()='Search Result: ']/span[text()='<idf_ResultsCount>']"), btn_RightsAndView("//button[text()='<idf_ButtonName>']"), ele_lblRightsAcquired("//div[text()=\"World Wide\"]/following::span[1]"), ele_lblRightsNotAcquired("//div[text()=\"World Wide\"]/following::span[2]"), ele_ValueSelect("//ul[@id=\"imageTypeId1_listbox\"]/li[text()='<idf_Value>']"), btn_DropDownImageType("//label[text()='Image Type']/..//input/../span/span"), lnk_TerritoryWorldWide("//div[text()='<idf_Territory>']"), lnk_SubTerritory("//span[text()='<idf_SubTerritory>']"), ele_RightsAcquired("//span[text()='<lnk_Territory>']/..//span[3]"), ele_RightsNotAcquired("//span[text()='<lnk_Territory>']/..//span[4]"), ele_MediaStartDate("//a[text()='Media']/following::tr/td[text()='<idf_MediaName>']/following::td[1]"), ele_MediaEndDate("//a[text()='Media']/following::tr/td[text()='<idf_MediaName>']/following::td[2]"), ele_lblSelectImage("//span[text()=\"Select/Drop Files\"]"), ele_lblCommonRightsCount("//span[contains(text(),'<idf_RightsStatus>')]"), ele_lblWorldWide("//div[text()='World Wide']"), ele_lblSubHeader("//span[text()='<idf_SelectHemisphere>']"), ele_lblRightsAquiredCount("//span[text()='<idf_SelectTerritory>']/../..//span[contains(text(),'<idf_Rightsaquired>')]");

    private String searchPath;
  
    /**
    *  Page PG_ProgramDetailsPage.
    */
    private PG_ProgramDetailsPage(final String psearchPath) {
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