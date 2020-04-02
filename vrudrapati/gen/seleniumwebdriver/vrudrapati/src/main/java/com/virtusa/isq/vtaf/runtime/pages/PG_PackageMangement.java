package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_PackageMangement implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_PackageMangement {

        tf_PackageManagementTextField("//h1[text()='<idf_PageHeader>']/../..//a[text()='Collection Name']/../../../../../../..//input[@id='channelDropIdundefined']"), ele_ddPacakagemanagementDropDown("//a[text()='<idf_ColomNameDropDown>']/../../../../../../..//span[text()='select']"), tf_ClickOnCollectionName("//a[text()='<idf_CollumnHeadingName>']//following::tr[last()]/td[<idf_IndexNumber>]"), lst_SelectOption("//ul[@id='channelDropIdnull_listbox']/li[text()='<idf_SelectOption>']"), chk_SearchResultCheckBox("xpath=(//td/input[@type=\"checkbox\"and @id='<idf_CheckElementNumber>'])[<idf_Index>]");

    private String searchPath;
  
    /**
    *  Page PG_PackageMangement.
    */
    private PG_PackageMangement(final String psearchPath) {
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