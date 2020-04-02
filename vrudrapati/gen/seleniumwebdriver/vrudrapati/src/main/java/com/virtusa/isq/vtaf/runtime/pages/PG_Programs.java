package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_Programs implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_Programs {

        tf_ProgramTitle("//label[text()=\"Program Title\"]/following::input[@id='programTitle']"), tf_ProgramId("//label[text()=\"Program ID\"]/following::input[@id='pplID']"), tf_PriceId("//label[text()=\"Piece ID\"]/following::input[@id='programOrPieceID']"), btn_TitleSearch("//button[@id=\"idOrTitleSearchButton\"]"), btn_OptionsSearch("//button[@id=\"selectOptionsFormButton\"]"), dd_SearchFields("//select[@id=\"<idf_Value>\"]"), dd_SelectedValue("//select[@id='<idf_SelectId>']/option[text()='<idf_Value>']"), lnk_SeariesName("//div[@class=\"seariesName\"][text()='<idf_SeariesName>']"), ele_Test("//label[text()='<idf_SearchOptions>']/..//div/div"), ele_ClickDataGrid("//a[text()=\"Program Id\"]/../../../../../following::div//td[text()=\"<idf_ProgramID>\"]"), ele_SearchResults("//div[@class=\"seariesName\"]"), ele_DataGrid("//a[text()='<idf_TabHeader>']/following::tr['<idf_TableRow>']/td[text()='<idf_Result>']"), ele_DataGridItem("//a[text()='<idf_TabHeader>']/following::tr[<idf_TableRow>]/td[text()='<idf_Result>']"), tf_ProgramSearchCriteria("//div[@class='content_boxGrid']//label[contains(@for,'<idf_ProgrammeSearchCriteria>')]/../input|//div[@class='content_boxGrid']//label[contains(@for,'<idf_ProgrammeSearchCriteria>')]/../div"), btn_TitleSearch2("//button[@id=\"selectOptionsFormButton\"]"), tf_ProgramsSection2("//div[@class='content_boxGrid']//label[contains(text(),'<idf_ProgrammeSearchCriteria>')]/../div"), tf_ReleaseDate("//label[contains(@for,'releaseDate<idf_ToOrFrom')]/..//input"), ele_lblDropDownValue("//li[.//text()='<idf_DropDownItem>']"), ele_DataGridOneItem("xpath=(//a[text()='<idf_ColumnName>']/following::td[text()='<idf_CompanyName>'])[1]"), lst_ProgramSearchCriteria("//label[contains(text(),'<idf_SearchCriteria>')]/..//input"), ele_DivSeason("//label[text()='Season']/..//div[@class='k-widget k-multiselect k-header k-state-disabled']"), tf_SelectDate("//label[text()='<idf_DateLabel>']/..//input"), tf_SearchField("//input[@id=\"srch-term\"]");

    private String searchPath;
  
    /**
    *  Page PG_Programs.
    */
    private PG_Programs(final String psearchPath) {
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