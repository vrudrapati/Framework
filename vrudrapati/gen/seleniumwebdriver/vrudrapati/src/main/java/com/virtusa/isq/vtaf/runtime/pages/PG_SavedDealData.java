package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_SavedDealData implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_SavedDealData {

        tf_TextArea("//textarea[@id='commentsSecondId']"), btn_RightsBtn("//button[@id='addrightsBtnId' and text()='Rights']");

    private String searchPath;
  
    /**
    *  Page PG_SavedDealData.
    */
    private PG_SavedDealData(final String psearchPath) {
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