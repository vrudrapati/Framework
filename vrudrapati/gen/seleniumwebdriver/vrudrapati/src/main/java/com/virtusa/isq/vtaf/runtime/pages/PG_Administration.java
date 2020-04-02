package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_Administration implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_Administration {

        ele_usernametext("xpath=(//input[@type='text'])[1]"), btn_Finduser("//button[@value='Find']"), ele_viewuserdetails("//img[@title='<Userdetails>']"), ele_checkbox("xpath=(//input[@type='checkbox'])[3]"), ele_disabledcheckbox("//input[@name='ss_site' and @value='False']"), ele_Menu("//h2[contains(text(),'<idf_menu>')]"), ele_Administration("//span[contains(text(),'<idf_administration>')]"), ele_usermanagement("//a[contains(text(),'<idf_usermanagement>')]"), ele_Saveinusermanagement("//a[contains(text(),'<idf_save>')]");

    private String searchPath;
  
    /**
    *  Page PG_Administration.
    */
    private PG_Administration(final String psearchPath) {
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