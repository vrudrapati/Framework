package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_AddToExhibit implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_AddToExhibit {

        ele_Exhibit("//label[text()=\"<idf_LabelName>\"]/..//span[text()=\"<idf_ExhibitOption>\"]"), ele_SelectFromList("//ul[@id=\"<idf_ListBoxId>\"]/li[text()=\"<idf_ListBoxValue>\"]"), tf_ExhibitName("//label[text()=\"Exhibit\"]/../..//input[@placeholder=\"Exhibit Name\"]"), tf_Comments("//label[text()=\"Comments\"]/../textarea"), btn_Save("//button[@id=\"pitchListSaveClicked\"]");

    private String searchPath;
  
    /**
    *  Page PG_AddToExhibit.
    */
    private PG_AddToExhibit(final String psearchPath) {
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