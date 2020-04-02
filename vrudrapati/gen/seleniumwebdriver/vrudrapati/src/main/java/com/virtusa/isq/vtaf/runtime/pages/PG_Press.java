package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_Press implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_Press {

        ele_lblTerms("//div[@id=\"terms-of-use\"]//h2[contains(text(),\"Terms of Service\")]"), btn_Agree("//button[@type=\"submit\" and text()=\"I Agree\"]"), img_Home("//a[@title=\"Home\"]//img[@src=\"/themes/pressaetn/images/AETN_B1.gif\"]"), img_Channels("//a[@href='<idf_ChannelNames>']//img"), ele_ChannelSubTitles("//a[@href='<idf_ChannelURL>' and text()='<idf_TabName>']"), btn_LogOut("//a[@href=\"/user/logout\"]//preceding::a[@href=\"/user\" and text()='Welcome Back admin']"), ele_Welcome("//h1[contains(text(),'WELCOME')]"), tf_UserName("//input[@id=\"edit-name\"]"), tf_Password("//input[@id=\"edit-pass\"]"), btn_Sign("//input[@type=\"submit\"]");

    private String searchPath;
  
    /**
    *  Page PG_Press.
    */
    private PG_Press(final String psearchPath) {
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