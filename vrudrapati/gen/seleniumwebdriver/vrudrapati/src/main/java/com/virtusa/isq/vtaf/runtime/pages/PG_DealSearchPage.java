package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_DealSearchPage implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_DealSearchPage {

        tf_ContractNumber("//input[@id='dealNum']"), btn_Search("//button[@id='dealSearchBtn']"), lnk_SelectProgram("//td[text()='<ProgramId>']"), ele_ddDealType("//*[@id=\"dealSearchFilters\"]/div[4]/div[2]/div[1]/div[1]/div/div"), lnk_DealTypevalue("/html/body/div[3]/div/ul/li[1]"), tf_DealnameSelection("//td[text()='<dealname>']"), btn_DealDNA("//BUTTON[@id='dealDNABtnId']"), ele_lblDealDNAHeader("//H1[@class='float-left topm'][text()='Deal DNA']"), DealIncludes("//DIV[@class='box_head'][text()='Deal Includes']"), DealExcludes("//DIV[@class='box_head'][text()='Deal Excludes']"), btn_TieredPricing("//BUTTON[@id='tieredPricingBtnId']"), ele_tabTieredPricingtab("//SPAN[@class='k-link'][text()='Tiered Pricing']"), ele_ddDealTypeSelect2("/html/body/div[3]/div/ul/li[contains(text(),'<DealType2>')]"), ele_ddDealTypeSelect3("/html/body/div[3]/div/ul/li[contains(text(),'<DealType3>')]"), ele_ddDealTypeSelect4("/html/body/div[3]/div/ul/li[contains(text(),'<DealType4>')]"), ele_ddDealTypeSelect5("/html/body/div[3]/div/ul/li[contains(text(),'<DealType5>')]"), ele_DealSearchcheck("//*[@id=\"deal_grid\"]/div[2]/table/tbody/tr/td[6]"), ele_lblNoRecords("//*[@id=\"zeroMessageId\"]/div"), ele_lblSelectOptionerror("//*[@id=\"warningMessageId\"]/div/span"), ele_ddDealTypeSelect1("/html/body/div[3]/div/ul/li[contains(text(),'<DealType1>')]"), rdo_Allradiobutton("//*[@id=\"dealSearchGrid\"]/div/div[1]/div[2]/div[4]/div/ins"), ele_Dealgrid1("//*[@id=\"deal_grid\"]/div[2]/table/tbody/tr[1]/td[1]"), btn_OrderHistoryBtn("//BUTTON[@id='orderHistoryBtnId']"), ele_Ordergrid1("(//TD[@role='gridcell'][text()='2000-Current'][text()='2000-Current'])[1]"), ele_Programgrid1("//*[@id=\"order\"]/div[2]/table/tbody/tr[1]/td[3]"), ele_CommonDataGridrow("//table[@class=\"k-selectable\"]//tbody/tr");

    private String searchPath;
  
    /**
    *  Page PG_DealSearchPage.
    */
    private PG_DealSearchPage(final String psearchPath) {
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