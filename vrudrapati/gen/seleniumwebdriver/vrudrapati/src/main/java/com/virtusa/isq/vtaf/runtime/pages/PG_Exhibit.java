package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_Exhibit implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_Exhibit {

        ele_ddExhibitname("//INPUT[@class='k-input']"), ele_Exhibitnamevalue("//TD[@role='gridcell'][contains(text(),'<ExhibitName>')]"), btn_ExporttoExcel("//BUTTON[@id='exportToExcelId']"), ele_SelectProgram("//INPUT[@id='1']"), btn_DeleteProgram("//BUTTON[@id='deleteProgramsId']"), ele_Deletesuccess("//*[@id=\"pitchListDetailsSuccessId\"]/div"), btn_AddtoExhibit("//BUTTON[@id='addToPitchListBtnClick']"), lnk_Exhibitlist("//SPAN[@unselectable='on'][text()='New Exhibit']"), btn_SaveExhibit("//INPUT[@id='savePitchListName']"), tf_Comments("//TEXTAREA[@id='pitchListComments']"), btn_SaveExhibitbtn("//*[@id=\"pitchListSaveClicked\"]"), ele_Exhibithome("//H1[@class='topm'][text()='Exhibit']"), btn_AddProgram("//BUTTON[@id='addProgramsBtn']"), ele_lblAddtoExistingExhibit("//SPAN[@unselectable='on'][text()='Add To Existing Exhibit']"), btn_SelectSaveExhibit("//*[@id=\"savedPitchListSelectDivId\"]/span/span/input"), ele_lblExhibitDetails("/html/body/div[1]/div/div[2]/div/div[1]/h1"), ele_ddSelecFromOption("//ul[@class=\"k-list k-reset\"]/li[contains(text(),'<idf_SelectFromList>')]"), ele_ddClickOnInputField("//label[text()='<idf_LabelName>']/..//input"), ele_lblExhibitName("//span[text()=\"Exhibit Name:\"]/../span[text()=\"<idf_ExhibitName>\"]");

    private String searchPath;
  
    /**
    *  Page PG_Exhibit.
    */
    private PG_Exhibit(final String psearchPath) {
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