package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class ProgramSearchForAssetCreationPage implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum ProgramSearchForAssetCreationPage {

        ProgramSearchForAssetCreationPageVerification("//div[@id='programSearchContent']"), programtitle("//input[@id='programTitle']"), Expandall("//*[@id=\"expandCollapseAll\"]"), Searchbutton("//*[@id=\"titleOrpplidOrbarcodeId\"]");

    private String searchPath;
  
    /**
    *  Page ProgramSearchForAssetCreationPage.
    */
    private ProgramSearchForAssetCreationPage(final String psearchPath) {
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