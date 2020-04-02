package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_BrowserstackLogin implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_BrowserstackLogin {

        SignIn("//a[@href='/users/sign_in' and text()='Sign in']"), EmailId("//input[@id='user_email_login']"), Password("//input[@id='user_password']"), SignMeInBtn("//input[@id='user_submit']"), InstallBtn("//a[@href='#' and text()='Install']"), Windows10Main("//li[text()='Windows' and @data-parentos='windows']"), Windows10Sub("//li[text()='10' and @data-os='win10' and @data-object-key='windows']"), FirefoxVersion52("html/body/div[2]/section/div[2]/div[1]/div/div[2]/div[3]/ul/li[1]/a");

    private String searchPath;
  
    /**
    *  Page PG_BrowserstackLogin.
    */
    private PG_BrowserstackLogin(final String psearchPath) {
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