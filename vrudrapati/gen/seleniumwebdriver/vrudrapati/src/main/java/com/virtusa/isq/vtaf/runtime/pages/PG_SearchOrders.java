package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_SearchOrders implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_SearchOrders {

        tf_OrderID("//label[text()=\"Order ID\"]/../input"), btn_Search1("//button[@id=\"contractOrOrderSearchButton\"]"), tf_ContractNumber("//label[text()=\"Contract Number\"]/../input"), ele_ddSelectField("//label[text()=\"<idf_FieldHeader>\"]/..//input"), tf_FilterValue("//label[text()=\"Filter Value\"]/following::input[@aria-owns='channelId_listbox']"), btn_Search2("//button[@id=\"otherFieldsSearchButton\"]"), btn_Search("//BUTTON[@id='contractOrOrderSearchButton']"), ele_lblSearchResults("//TD[@role='gridcell'][text()='118']"), ele_Order("//INPUT[@id='0']"), ele_ddFieldnameDropdown("//ul[@unselectable='on' and contains(@id,'order_fieldName_listbox')]//li[contains(text(),'<FieldName>')]"), tf_FieldValue("//INPUT[@id='changedComments']"), btn_ApplyChanges("//BUTTON[@id='applyChangesBtn']"), ele_lblSuccessMessage("//*[@id=\"orderSuccessText\"]"), tf_TermYear("06/01/2012 - 05/31/2013"), ele_FilterBy("//*[@id=\"otherFieldsSearchForm\"]/div/div[1]/div/div[1]/span/span/input"), ele_lblFilterValue("//INPUT[@id='dealId']"), btn_OtherFieldsSearchBtn("//BUTTON[@id='otherFieldsSearchButton']"), tf_DateType("//*[@id=\"otherFieldsSearchForm\"]/div/div[2]/div/div[1]/span/span/input"), ele_VerifyTableValues("xpath=(//a[text()='<idf_TableColumnName>']/../../../../../../..//td[text()='<idf_SearchedValue>'])[<idf_RowNumber>]"), ele_verifyTableRowCount("//a[text()='<idf_tableSearchedColumnHeader>']/../../../../../../..//tbody/tr"), btn_SearchOrderOther("//button[@id='otherFieldsSearchButton']"), dd_SelectFromlistIndex("xpath=(//label[text()='<idf_FieldValue>']/following::li[text()='<idf_BulkUpdateFieldValue>'])[2]"), ele_ddSelectFilterValue("//label[text()='Filter Value']/following::div[@id='showModalId']/following::div[6]//ul/li[text()='<idf_filter_value>']"), ele_VerfiyDataGridData("//a[text()=\"<idf_TableHeader>\"]/../../../../../../..//tbody[@role=\"rowgroup\"]/tr/td[<idf__IndexNo>]"), btn_ButtonID("//button[@id=\"<idf_ButtonID>\"]");

    private String searchPath;
  
    /**
    *  Page PG_SearchOrders.
    */
    private PG_SearchOrders(final String psearchPath) {
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