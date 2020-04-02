package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_Login implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_Login {

        img_Okta_GPS("//img[@alt='Graphic Link GPS QA']"), tf_Uname("//input[@id='okta-signin-username']"), tf_Password("//input[@id='okta-signin-password']"), btn_LoginBtn("//input[@id='okta-signin-submit']"), ele_lblUserNameError("//*[@id=\"form17\"]/div[1]/div[2]/div[1]/div/p"), ele_lblPasswordError("//*[@id=\"form17\"]/div[1]/div[2]/div[2]/div/p"), tf_Username("//input[@id='okta-signin-username' and @type='text']"), img_GPS_Image("//img[@alt='Graphic Link GPS QA']"), win_DashboardWindow("index=<index>");

    private String searchPath;
  
    /**
    *  Page PG_Login.
    */
    private PG_Login(final String psearchPath) {
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