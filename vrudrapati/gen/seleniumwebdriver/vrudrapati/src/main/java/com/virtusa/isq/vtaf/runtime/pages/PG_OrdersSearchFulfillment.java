package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_OrdersSearchFulfillment implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_OrdersSearchFulfillment {

        tf_OrderID("//INPUT[@id='orderid']"), btn_Search("//BUTTON[@id='contractNumberSearch']"), ele_lblSearchResults("//SPAN[@id='seriesIconId_0']"), btn_Expandall("//SPAN[@id='seasonIconId_0']"), tf_Contractnumber("//*[@id=\"contractNumber\"]"), btn_Expandallcontract("//*[@id=\"expandCollapseAll\"]"), ele_ddChannel("//ul[@unselectable='on' and contains(@id,'channelId_listbox')]//li[contains(text(),'<channel>')]"), btn_Searchchannel("//*[@id=\"selectOptionsFormButton\"]");

    private String searchPath;
  
    /**
    *  Page PG_OrdersSearchFulfillment.
    */
    private PG_OrdersSearchFulfillment(final String psearchPath) {
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