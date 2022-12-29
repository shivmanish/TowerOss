package com.smarthub.baseapplication.ui.dialog.home

import com.smarthub.baseapplication.model.dropdown.DropDownItem

data class AddSiteHelper(
    var companyCode:String? = null,
    var siteCode:DropDownItem? = null,
    var cityCode:DropDownItem? = null,
    var siteTypeCode:DropDownItem? = null,
    var siteClass:DropDownItem? = null,
    var siteId:String? = null,
    var siteName:String? = null,
    var aliasName:String? = null,
    var national:DropDownItem? = null,
    var region:DropDownItem? = null,
    var state:DropDownItem? = null,
    var mentatinance_point:DropDownItem? = null
)
