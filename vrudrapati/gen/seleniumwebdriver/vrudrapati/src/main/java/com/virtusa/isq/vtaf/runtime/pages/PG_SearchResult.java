package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_SearchResult implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_SearchResult {

        ele_lblProgramId("//span[text()=\"Program ID:\"]/following::span/span[text()='<idf_ProgramId>']");

    private String searchPath;
  
    /**
    *  Page PG_SearchResult.
    */
    private PG_SearchResult(final String psearchPath) {
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