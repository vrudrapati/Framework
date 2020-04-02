package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_OrderDetailsPage implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_OrderDetailsPage {

        tf_OrderIdSearch("//input[@id='orderId']"), btn_SearchOrderIdBtn("//button[@id='contractOrOrderSearchButton']"), ele_ResultOrder("//td[text()='<ResultOrder>']");

    private String searchPath;
  
    /**
    *  Page PG_OrderDetailsPage.
    */
    private PG_OrderDetailsPage(final String psearchPath) {
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