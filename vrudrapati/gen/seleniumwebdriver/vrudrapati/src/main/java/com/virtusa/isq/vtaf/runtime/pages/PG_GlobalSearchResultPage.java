package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_GlobalSearchResultPage implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_GlobalSearchResultPage {

        btn_FilterSelecting("//label[@for='<SearchCat>']"), btn_VerifyFilter("//*[@id='searchTabStrip-1']/div[1]/div/ul[1]/li/p/span[2]"), tf_Searchfield("//*[@id=\"srch-term\"]"), btn_Searchbutton("//*[@id=\"searchBtn\"]");

    private String searchPath;
  
    /**
    *  Page PG_GlobalSearchResultPage.
    */
    private PG_GlobalSearchResultPage(final String psearchPath) {
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