package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_ClientProfileDetails implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_ClientProfileDetails {

        tf_ClientProfileTextField("//div[text()=\"<idf_SectionName>\"]/..//label[text()=\"<idf_TextFieldName>\"]/..//input[@type=\"text\"]"), ele_lblDropDown("//label[text()=\"<idf_TextFieldLabel>\"]/../..//span[@class=\"k-icon k-i-arrow-s\"]"), ele_lblCalenderDropDown("//label[text() ='<idf_ProgramStartDate>']/../..//span[@class=\"k-icon k-i-calendar\"]"), ele_ddCountryRegionDropDown("//label[text()=\"<idf_TextFieldName>\"]/../..//span[@class=\"k-icon k-i-arrow-s\"]/../../following::ul[@class=\"k-list k-reset\" and @id=\"pciCountryRegionId_listbox\"]/li[@role=\"option\"]"), ele_ddVideoResolutionDropDown("//label[text()=\"<idf_TextFieldName>\"]/../..//span[@class=\"k-icon k-i-arrow-s\"]/..//following::div[@id=\"pciVideoResolutionId-list\" ]//li"), lst_ClientSearchCriteria("//label[text()='<idf_ClientSearchCriteria>']/following::li[text()='<idf_ClientSearchValue>']"), tf_ClientSearchValue("//label[text()=\"<idf_ClientSearchCriteria>\"]/following::input[1]"), ele_FieldValue("//label[text()=\"Field Value\"]/../span[2]/span/input[@type=\"text\"]"), ele_ddSelect("//span[text()='<idf_ClickSelect>']"), tf_ClientTypeField("//input[@id='<idf_TextFieldId>']"), tf_FieldValue("//label[text()=\"Field Value\"]/..//input[2]"), ele_InputDateField("//span[@class='k-picker-wrap k-state-default']/input[@id='sscContractEndDateId']"), ele_searchresultHeader("//*[@id=\"comapnyLiId_0\"]/span/span[1]/div[1]"), ele_SearchResult("//*[@id=\"channelGrid_0\"]/div[2]/table/tbody/tr/td[1]"), ele_SSSignIn("//*[@id='login-signin']"), ele_ssusername("//*[@id=\"myModal\"]/app-login/div[1]/div/div/div[2]/form/div[1]/div[2]/input"), ele_sspassword("//*[@id=\"myModal\"]/app-login/div[1]/div/div/div[2]/form/div[2]/div[2]/input");

    private String searchPath;
  
    /**
    *  Page PG_ClientProfileDetails.
    */
    private PG_ClientProfileDetails(final String psearchPath) {
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