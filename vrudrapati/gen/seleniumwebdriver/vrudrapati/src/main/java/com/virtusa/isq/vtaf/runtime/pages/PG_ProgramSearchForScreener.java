package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_ProgramSearchForScreener implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_ProgramSearchForScreener {

        ele_ddBulkUpdateFieldValue("//label[text()='<idf_fieldValue>']/../../../../../../../../../../../../..//li[text()='<idf_SelectedValue>']");

    private String searchPath;
  
    /**
    *  Page PG_ProgramSearchForScreener.
    */
    private PG_ProgramSearchForScreener(final String psearchPath) {
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