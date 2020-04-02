package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_NewDeal implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_NewDeal {

        lst_NewDealSearchValue("//label[text()='<idf_ClientSearchCriteria>']/following::li[text()='<idf_ClientSearchValue>'][1]"), tf_NewDealSearch("//label[text()='<idf_SearchCriteria>']/..//input"), lst_NewSearchCriteria("//label[text()=\"<idf_SearchCriteriaName>\"]/..//input[@type=\"text\"]"), btn_SaveDeal("//button[@id='saveDealBtnId']"), ele_ddSearchCriteria("//label[text()='<idf_SearchCriteriaNme>']/..//input[@role='listbox']"), ele_ddDealsLanguage("//label[@for='dealsLanguageLabelId']/..//input[@role='listbox']"), tf_SearchPeriod("//label[text()='<idf_SearchCriteriaLable>']/following::input[@id='<idf_SearchCriteriaID>']"), ele_lblVerifyMessage("//div[text()='<idf_VerifyMessege>']"), ele_ddPrimaryTerritory("//label[text()='<idf_SearchCriteriaLable>']/following::input[@aria-owns='<idf_SearchFieldAriaOwns>']"), ele_ddChannelSelection("//ul[@id='channelMultiSelectId_listbox']/li[text()='<idf_ChannelType>']"), lst_DealType("//label[text()=\"Deal Type\"]/../span//input"), ele_DealType("//ul[@id=\"dealTypeId_listbox\" ]/li[text()='<idf_DealType>']"), ele_DealName("//label[text()=\"Deal Name\"]/../input"), ele_SelectItem1("//label[text()=\"<idf_ClickItem>\"]/../span//input"), ele_SelectItem2("//label[text()=\"<idf_ClickItem>\"]/../div//input"), ele_Channel("//label[text()=\"Channel\"]/../div//div/input"), lst_ListBoxValue("//ul[@id=\"<idf_ListBoxId>\"]/li[text()=\"<idf_ListBoxValue>\"]"), tf_LicensePeriod("//input[@id=\"licensePeriodSecondId\"]"), tf_SelectionPeriod("//input[@id=\"selectionPeriodId\"]"), ele_Season("xpath=(//span[@id='seasonIconId_0'])[1]"), ele_lblProgramId("xpath=(//a[text()='Program Id'])[1]/following::td[@role='gridcell'][3]"), ele_ProgramID("xpath=(//a[text()='Program Id'])[1]/following::td[text()='<idf_ProgramId>']/../td/input[@type='checkbox']"), btn_Search("//button[@id=\"programFilterSearchBtn\"]"), lst_SeriesName("//ul[@id=\"series_listbox\"]/li[text()=\"<idf_SeriesName>\"]"), ele_SeriesName("//div[@class=\"seariesName\"]"), ele_ddLicessePeriodMonths("xpath=(//label[text()='License Period (Months)']/..//input)[1]"), ele_ChannelByRole("//label[text()='Channel']/..//input[@role='<idf_NameOfTheBox>']"), tf_DealsLanguage("xpath=(//label[text()=\"<idf_LabelName>\"])[<idf_IndexNO>]/..//input"), btn_DisabledButton("//button[@disabled=\"\" and text()='<idf_ButtonName>']"), ele_TermYear("//input[@id=\"<idf_IDOfTermYear>\"]"), ele_Attorney("//label[text()=\"Attorney\"]/../../../..//input[@aria-owns=\"attorneyId_listbox\"]"), ele_LabelThroughTextField("//label[text()=\"<idf_LabelName>\"]/../../../..//input[@aria-owns=\"<idf_ariaOwns>\"]"), ele_ResponsiblePartyforMaterialExpenses("//label[text()=\"Responsible Party for Material Expenses\"]/../../../../..//input[@aria-owns=\"responsiblePartyForMaterialExpensesId_listbox\"]"), ele_LibraryContentYears("//label[text()=\"Library Content (Years)\"]/../../../../..//input[@id=\"licensePeriodSecondId\"]"), ele_PostShipmentDate("//label[text()=\"Post-Shipment License Start (months)\"]/../../../../..//input[@id=\"postShipmentLicenseStartId\"]"), btn_StartDateMonthLabel("xpath=(//div[@class='k-header']/a)[2]"), btn_EndDateMonthLabel("(//div[@class='k-header']//a[2])[2]"), ele_lblDate("xpath=(//td[contains(@class,\"k-today k-state-focused\")]//a)[2]"), ele_YearMonthDate("//table[@class='k-content']//a[text()='<idf_Date>'][contains(@title,'<idf_Month>')]"), ele_YearMonthDateEnd("xpath=(//table[@class='k-content'])[2]//a[text()='<idf_Date>'][contains(@title,'<idf_Month>')]"), ele_lblEndCalenderDate("//input[@class=\"k-input\"]//following::a[@aria-live=\"assertive\"][2]"), ele_lblTodaysDate("//td[contains(@class,\"k-today k-state-focused\")]/a"), ele_DDFieldSentToJaguarOption("//label[text()=\"<idf_LabelName>\"]/../../../following::div[1]//input[@aria-owns=\"<idf_AriaOwns>\"]"), ele_InputFieldSentToJaguarOption("//label[text()=\"<idf_LabelName>\"]/../../../following::div[1]//input[@id=\"<idf_IdOfTheInput>\"]"), ele_lblContractNumber("//span[@id=\"contractNumber\"]"), lst_NewDealSearchValueDropDown("xpath=(//ul/li[text()='<idf_ClientSearchValue>'])[1]"), lst_NewDealCompanyName("//ul[@id='companyNameId_listbox']/li[text()='<idf_ClientSearchValue>']");

    private String searchPath;
  
    /**
    *  Page PG_NewDeal.
    */
    private PG_NewDeal(final String psearchPath) {
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