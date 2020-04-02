package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_ProgramSearchForAssetCreationPage implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_ProgramSearchForAssetCreationPage {

        ele_ProgramSearchForAssetCreationPageVerification("//div[@id='programSearchContent']"), tf_Programtitle("//input[@id='programTitle']"), btn_Expandall("//*[@id=\"expandCollapseAll\"]"), btn_Searchbutton("//*[@id=\"titleOrpplidOrbarcodeId\"]");

    private String searchPath;
  
    /**
    *  Page PG_ProgramSearchForAssetCreationPage.
    */
    private PG_ProgramSearchForAssetCreationPage(final String psearchPath) {
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