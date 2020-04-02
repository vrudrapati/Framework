package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_GpsLogin implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_GpsLogin {

        tf_Username("//*[@id=\"okta-signin-username\"]"), tf_Password("//*[@id=\"okta-signin-password\"]"), btn_Siginbutton("//*[@id=\"okta-signin-submit\"]"), ele_ddProgrammingdropdown("//*[@id=\"programmingMenuId\"]/span"), lnk_SearchPrograms("//*[@id=\"programSearchClickedFomMenuId\"]/a"), ele_ddSeriesfield("//*[@id=\"series\"]"), btn_SearchButton("//*[@id=\"idOrTitleSearchButton\"]"), btn_ExpandAll("//*[@id=\"expandCollapseAll\"]"), ele_tabRightsTab("//*[@id=\"programDetailsTabStripId\"]/ul/li[5]/span[2]"), ele_tabMenutab("//*[@id=\"show_menu\"]"), Thesomme("//*[@id=\"episodeGrid_4242_noseason\"]/div[2]/table/tbody/tr/td[4]"), tf_ProgramID("//INPUT[@id='pplID']"), MissingAt("//*[@id=\"episodeGrid_1747_noseason\"]/div[2]/table/tbody/tr/td[2]"), chk_Worldwide("//*[@id=\"worldwideLiId_1\"]/span/span[1]/div"), chk_WesternHemisphere("//*[@id=\"hemispheresLiId_225\"]/span/span[1]/span[2]"), chk_NorthAmerica("//*[@id=\"continentsLiId_2\"]/span/span[1]/span[2]"), chk_canada("//*[@id=\"regionsLiId_4\"]/span/span[1]/span[2]"), chk_Carribean("//*[@id=\"regionsLiId_15\"]/span/span[1]/span[2]");

    private String searchPath;
  
    /**
    *  Page PG_GpsLogin.
    */
    private PG_GpsLogin(final String psearchPath) {
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