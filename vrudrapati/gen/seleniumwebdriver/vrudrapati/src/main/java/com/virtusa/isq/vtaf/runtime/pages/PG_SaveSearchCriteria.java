package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_SaveSearchCriteria implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_SaveSearchCriteria {

        ele_lblHeader("//h2[text()='<idf_HeaderText>']"), ele_SaveNewSearchCriteria("//span[text()='<idf_SpanText>']"), ele_Option("//li[text()='<idf_OptionText>']"), ele_SavedSearches("//label[text()='<idf_SelectSavestext>']/..//input[@placeholder=\"<idf_SavedSearchesText>\"]"), ele_SavedSearchOption("(//li[contains(text(),'<idf_SavedSearchesOption>')])[<idf_Index>]"), ele_DownArrow("//label[text()=\"Save Search Criteria\"]/..//span[text()=\"select\"]"), ele_OptionFromList("//ul[@id=\"searchCriteriaSelectId_listbox\"]//li[text()=\"<idf_SelectFromList>\"]"), ele_EnterCriteriaName("xpath=(//label[text()=\"Select Saved Exhibit\"]/..//input)[1]"), ele_SelectCriteriaNameOption("//ul[@id=\"savedSearchInPopUpId_listbox\"]//li[contains(text(),\"<idf_SelectCriteriaNameOption>\")]"), btn_SaveSearchCriteria("//button[@id=\"<idf_IDOfButton>\"]"), tf_SearchCriteriaName("xpath=(//label[text()=\"Enter Criteria Name\"]/..//input)[1]"), tf_SelectToDelete("//label[text()=\"Select Saved Exhibit\"]/..//div//input"), ele_SelectToDeleteOption("//ul[@id=\"deleteSearchInPopUpId_listbox\"]//li[contains(text(),\"<idf_SelectCriteriaNameOption>\")]");

    private String searchPath;
  
    /**
    *  Page PG_SaveSearchCriteria.
    */
    private PG_SaveSearchCriteria(final String psearchPath) {
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