package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_AddRights implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_AddRights {

        ele_ddSelectTerritories("css=div[id=\"territory\"]"), chk_WestrenHemisphere("//div[@id='_easyui_tree_2']//span[2]"), ele_ddSelectLanguage("//ul[@unselectable='on' and contains(@id,'language_listbox')]//li[contains(text(),'<RightsLanguage>')]"), ele_ddSelectMedia("//ul[@unselectable='on' and contains(@id,'media_listbox')]//li[contains(text(),'<RightsMedia>')]"), btn_AddRightsBtn("//button[text()='Add Rights']"), ele_lblSelectCountry("/html/body/div[5]/div/ul/li/ul/li[1]/ul/li[7]/div"), btn_SelectingRights("//input[@id='checkAll']"), btn_CheckAllRights("//th[@rowspan='1' and @data-index='0']//input[@type='checkbox' and @id='checkAll']"), btn_SaveRightsBtn("//button[@id='saveRights']");

    private String searchPath;
  
    /**
    *  Page PG_AddRights.
    */
    private PG_AddRights(final String psearchPath) {
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