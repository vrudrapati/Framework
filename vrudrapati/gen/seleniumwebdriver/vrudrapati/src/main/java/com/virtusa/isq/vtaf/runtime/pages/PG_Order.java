package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_Order implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_Order {

        ele_VerifyTotalSelectedPrograms("//span[text()=\"Total Selected Programs:\"]/following::span[text()='<idf_TotalSelectedPrograms>']"), ele_lblVerifysearchResult("//span[text()=\"Search Results:\"]/../span[text()='<idf_ResultCount>'] "), ele_lblSearchResult("//div[text()=\"Search Result: \"]"), ele_lblTableElement("//table/tbody[@role=\"rowgroup\"]//tr"), lnk_ClickOnDealName("//span[@id='dealNameClickedId']"), ele_lblCaptureOrderID("//span[@id=\"orderidvalue\"]"), ele_lblCurrentTermYear("//label[text()=\"Current Term year\"]/..//span[@id=\"curTermYear\"]"), ele_GetProgramCount("xpath=(//span[text()=\"Total Selected Programs:\"]/../span)[2]");

    private String searchPath;
  
    /**
    *  Page PG_Order.
    */
    private PG_Order(final String psearchPath) {
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