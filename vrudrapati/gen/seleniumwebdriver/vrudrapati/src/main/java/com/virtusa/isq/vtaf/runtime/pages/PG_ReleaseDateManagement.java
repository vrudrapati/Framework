package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_ReleaseDateManagement implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_ReleaseDateManagement {

        btn_GlobalUpdate("//button[@id=\"<idf_btnID>\"]"), ele_lblVerfiyData("//th[text()='<idf_TableHeader>']/following::tr[1]/td[contains(text(),'<idf_SearchedInfo>')]"), ele_TableHeader("//th[text()='<idf_TableHeader>']/following::tr[1]"), ele_InterimDate("//th[text()=\"<idf_TableHeader>\"]//following::td[text()=\"<idf_TableData1>\"]/../td[text()=\"<idf_TableData2>\"]"), ele_OldInterimDate("//th[text()=\"<idf_TableHeader>\"]//following::td[text()=\"<idf_TableData1>\"]/../td[<idf_IndexNo>]");

    private String searchPath;
  
    /**
    *  Page PG_ReleaseDateManagement.
    */
    private PG_ReleaseDateManagement(final String psearchPath) {
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