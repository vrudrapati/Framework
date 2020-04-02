package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_Fulfillment_OrderSearch implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_Fulfillment_OrderSearch {

        tf_ProgramTitle_textBox("//input[@id='progTitle' and @type='text']"), btn_ProgramTitleSearch_Btn("//button[@id='selectOptionsFormButton' and text()='Search']"), ele_ddSelectingTheSeries("//div[@class='seariesName' and contains(text(),'<Programtitle>')]/preceding-sibling::span/input[@type='checkbox']"), btn_AddToCart_Btn("//button[@id='addToCart']"), btn_GoToCart_Btn("//button[@id='goToCart']"), ele_ddDeliveryFormat_FrameRate("//ul[@unselectable='on' and contains(@id,'deliveryFormatDDL_listbox')]//li[contains(text(),'<DeliveryFormat>')]"), ele_ddBulkUpdateDropDown("//ul[@unselectable='on' and contains(@id,'bulkupdateList_listbox')]//li[contains(text(),'<BulkUpdate>')]"), tf_TargetShipDate_Input("//input[@id='targetShipDateId']"), btn_TemplateName_select_btn("/html/body/div[1]/div/div[2]/div/article/div/div[1]/div/div/div[6]/div/div[2]/div/div[2]/div[1]/span/span/span/span"), ele_ddTemplateName_Dropdown("//ul[@unselectable='on' and contains(@id,'templateId_listbox')]//li[contains(text(),'<TemplateName>')]"), lnk_SourceClick("/html/body/div[1]/div/div[2]/div/article/div/div[1]/div/div/div[3]/div[1]/div[2]/table/tbody/tr[1]/td[12]"), ele_ddSource("//ul[@unselectable='on' and contains(@id,'SourceDropId0_listbox')]//li[contains(text(),'<Source>')]"), chk_Fulfilment_Checkbox("//input[@type='checkbox' and @id='0']"), btn_BulkUpdate_ApplyChanges_Btn("//button[@id='applyChangesBtn']"), btn_Save_Btn("//button[@id='saveBtn' and text()='Save' and @data-bind='click: saveData']"), btn_RequestFullfillment_Btn("//button[@id='requestFulfillmentBtn']"), btn_Popup_Ok("//button[@id='bulkupdateSuccess' and text()='Ok']"), ele_FulfillmentOrderGridBox("//article[@class='main-content']//div[@id='orderSearchContent']"), btn_ButtonClick("xpath=(//button[text()='<idf_ButtonName>'])[<idf_Index>]");

    private String searchPath;
  
    /**
    *  Page PG_Fulfillment_OrderSearch.
    */
    private PG_Fulfillment_OrderSearch(final String psearchPath) {
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