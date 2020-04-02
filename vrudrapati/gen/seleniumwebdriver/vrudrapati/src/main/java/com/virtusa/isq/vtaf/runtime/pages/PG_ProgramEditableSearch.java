package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_ProgramEditableSearch implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_ProgramEditableSearch {

        tf_ProgramTitle("//INPUT[@id='programTitle']"), tf_ProgramID("//INPUT[@id='pplID']"), tf_PieceID("//INPUT[@id='programOrPieceID']"), btn_Search("//BUTTON[@id='idOrTitleSearchButton']"), ele_ddSeriesicon("//SPAN[@id='seriesIconId_0']"), ele_ddSeasonicon("//SPAN[@id='seasonIconId_0']"), tf_PieceIDText("//*[@id=\"episodeGrid_1541_471\"]/div[2]/table/tbody/tr/td[4]"), tf_ProgramNameText("//*[@id=\"episodeGrid_1541_471\"]/div[2]/table/tbody/tr/td[5]"), ele_ddSelectFieldValue("//label[text()=\"<FieldValueName>\"]//following::div//ul[@id=\"bulk_releasestatus_field_listbox\"]/li[text()=\"<idf_BulkUpdateFiledValue>\"]"), tf_CommonLabelText("//label[text()='<idf_CommonTitleLabel>']/..//input"), chk_CheckBoxOnEditableSearchResult("//span/input[@type=\"checkbox\"and @id='<idf_CheckBoxID>']");

    private String searchPath;
  
    /**
    *  Page PG_ProgramEditableSearch.
    */
    private PG_ProgramEditableSearch(final String psearchPath) {
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