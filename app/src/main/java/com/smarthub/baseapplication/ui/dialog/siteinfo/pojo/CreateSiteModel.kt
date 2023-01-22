package com.smarthub.baseapplication.ui.dialog.siteinfo.pojo

import com.smarthub.baseapplication.utils.AppController

data class CreateSiteModel(
    var Basicinfo:  BasicinfoData? = null,
    var ownername: String = AppController.getInstance().ownerName
)