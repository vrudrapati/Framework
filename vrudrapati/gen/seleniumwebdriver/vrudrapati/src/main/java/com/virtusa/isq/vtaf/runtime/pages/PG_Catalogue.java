package com.virtusa.isq.vtaf.runtime.pages;

/**
 *  Class PG_Catalogue implements corresponding UI page
 *  UI objects in the page are stored in the class.
 */

public enum PG_Catalogue {

        btn_Toggle("//div[@class=\"toggle-btn\"]//div[contains(text(),'<idtf_ToggleName>')]"), ele_DocumentIcon("//div[@class=\"tplEpisodeKeys\"]//span[@title='<idtf_DocumentType>']"), ele_PopupHeader("//p[text()='<idtf_PopupHeader>']"), ele_TermYearInPopUp("//span[text()='<idtf_TermYearType>']"), btn_ItemCartButton("//div[text()='<idtf_ItemCartButtonText>']"), ele_CurrentTermYear("//a[@class='current']"), ele_NextTermYear("//a[@class='next']"), ele_lblJumpTo("//h4[contains(text(),\"JUMP TO\")]"), lnk_lblJumpToSectionItem("//h4[contains(text(),\"JUMP TO\")]/..//a[contains(text(),\"<idtf_JumpToItemName>\")]"), lnk_SearchedSeriesName("//a[@class=\"tplSeriesName tplSeriesLink\" and text()=\"<idtf_SeriesName>\"]"), btn_ScreenerPlayButton("//a[@id=\"player-link\"]//span[@class='play']"), ele_ScreenerVideoPopUp("//div[@id='jOverlay']/../div[@id='jOverlayContent']"), ele_EpisodeId("//span[@class=\"tplEpisodeID\" and contains(text(),'E<idtf_EpisodesId>')]"), ele_DocumentDownloadIcon("//a//span[@title='<idtf_DocumentType>']"), img_Series("//img[@title='<idtf_SeriesTitle>']"), btn_AddAvailableEpisodesToCart("//span[text()='<idtf_ButtonText>']"), lnk_AddToCart("//a[@class='col col-lg-3 col-md-3 col-sm-3 action-col blue pull-right tplAction action-add2cart']"), tf_SearchByDate("//input[@id='<idtf_TextFeildId>']"), ele_SeriesDetailsPageHeader("//a[contains(text(),'<idtf_SeriesDetailsPageHeaderName>')]"), lnk_Season("//li//a[contains(text(),'<idtf_SeasonName>')]"), lnk_Episode("//span[contains(text(),'E<idtf_Episode>')]/../..//a[@class='tplEpisodeLink tplEpisodeName']"), ele_EpisodeIdInDetails("//h4[text()='Episode ID:']/..//p[contains(text(),'<idtf_EpisodeId>')]"), ele_lblTotalHoursInSeriesDetailsPage("//div[@class='total-hours']/p"), ele_PremierDateText("//span[@class='tplPremierDate']"), ele_PremierDateLabel("//span[@class='tplPremierDate' and text()='US Premiere Date: <idtf_Date>']"), ele_lblAutoSuggesionSearchedResult("xpath=(//ul[@id='sidebar-autoc']//a[contains(text(),'<idtf_AutoSuggesionSearchedResult>')])[1]"), ele_PremierDateLabelInEpisode("//h4[text()='US Premiere Date:']//..//p[contains(text(),'<idtf_PremiereDate>')]"), ele_lblNetworkOptionInFilter("//div[@class='network collapsible collapsed']"), ele_NetworkNameLabel("//label[contains(normalize-space(),'<idtf_NetworkName>')]  "), img_NetworkLogo("(//img[@title='AE'])['<idtf_EpisodeIndex>']"), ele_SeriesContainer("//ul[@class='series-wrp']"), img_NetworkLogoName("//ul[@class=\"series-wrp\"]//img[@title=\"<idtf_NetworkName>\"]"), ele_ChannelName("//p[normalize-space(text())='<idtf_ChannelName>']"), ele_lblCurrent("//span[(text())=\"CURRENT:\"]"), ele_lblNext("//span[(text())=\"NEXT:\"]"), tf_PopUpText("//p[contains(.,'<idtf_Text>')]"), ele_DurationInSearchedSeries("//a[text()='<idtf_SeiresName>']/../..//span[@class='tplHours']"), ele_EpisodeCountInSearchSeries("//a[text()='<idtf_SeiresName>']/../..//span[@class=\"tplEpisodes\"]"), ele_DurationInSeriesPage("//div[@class='total-hours']//p[contains(text(),'<idtf_Hours>')]"), ele_EpisodeCountInSeriesPage("//div[@class='episode-count']//p[contains(text(),'<idtf_EpisodeCount>')]"), ele_lblImageText("//h3[text()='Images']"), ele_lblImageBox("xpath=(//img[@class=\"image-preview\"])[1]"), lnk_EpisodAddToCartLinkWithImage("//a[@class=\"tplEpisodeLink tplEpisodeName\" and text()=\"<idtf_EpisodeName>\"]/../../..//span[@class=\"<idtf_ImageValue>\"]/..//span[normalize-space(text()=\"<idtf_AddCartorOrderLokedText>\")]"), btn_SEPbutton("xpath=(//span[text()='<idtf_SepButtonText>'])[1]"), ele_PremierDateLabelInEpisodeArea("//h4[text()='US Premiere Date:']"), ele_lblSeriesDescription("//h3[text()=\"Series Description\"]"), tf_ButtonName("//img[@title=\"Add Season to cart\"]/..//span"), ele_lblUsPremiereDateNull("//span[@class='tplPremierDate' and text()='']"), lnk_SearchedNameInCataloguePage("//div[@class='series border-radius active']/../../../../..//a[@class=\"tplSeriesName tplSeriesLink\" and contains(text(),\"<idtf_SearchedName>\")]"), lnk_SearchedNameInEpisodeSection("//div[@class='episodes border-radius active']/../../../../..//a[@class=\"tplEpisodeLink tplEpisodeName\" and contains(text(),'<idtf_SearchedName>')]"), ele_lblEpisodeCountInCataloguePage("//div[@class='episodes border-radius active']/span"), ele_lblUsPremereDate("//h4[text()='<idf_PremereDateTextHeader>']//following-sibling::p[contains(text(),'<idf_PremereDate>')][1]"), img_ImageBox("//a[text()='Download']/..//img");

    private String searchPath;
  
    /**
    *  Page PG_Catalogue.
    */
    private PG_Catalogue(final String psearchPath) {
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