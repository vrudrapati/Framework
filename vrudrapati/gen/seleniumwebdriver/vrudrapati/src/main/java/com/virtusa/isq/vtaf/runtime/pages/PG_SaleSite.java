package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_SaleSite implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_SaleSite {

        ele_lblSignIn("//span[text()='sign in']"), tf_SaleSiteUserName("xpath=(//input[@name=\"<idf_SaleSiteUserName>\"])[1]"), tf_SaleSitePassword("xpath=(//input[@name=\"<idf_SaleSitePassWord>\"])[1]"), btn_SignIn("xpath=(//button[@class=\"ae-button-primary float-right signin-btn\"and text()=\"Sign In\"])[1]"), ele_lblSaleSiteTab("xpath=(//span[text()='<idf_TabName>'])[2]"), ele_lblSeriesName("xpath=(//div[text()='<idf_SeriesName>'])[1]"), ele_ddCollectionDropDown("//span[text()='<idf_Collections>']/../../following-sibling::span/span"), ele_lblCollectionName("//li[@role=\"option\" and  text()=\"<idf_CollectionName>\"][1]");

    private String searchPath;
  
    /**
    *  Page PG_SaleSite.
    */
    private PG_SaleSite(final String psearchPath) {
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