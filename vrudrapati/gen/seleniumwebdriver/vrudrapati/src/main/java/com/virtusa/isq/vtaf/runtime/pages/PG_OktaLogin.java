package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_OktaLogin implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_OktaLogin {

        tf_OktaUserName("//input[@id='okta-signin-username']"), tf_OktaPassword("//input[@id='okta-signin-password']"), btn_SignIn("//input[@id='okta-signin-submit']");

    private String searchPath;
  
    /**
    *  Page PG_OktaLogin.
    */
    private PG_OktaLogin(final String psearchPath) {
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