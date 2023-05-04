package com.smarthub.baseapplication.ui.dialog.siteinfo.pojo

import com.smarthub.baseapplication.utils.AppController

class AddAttachmentModel{
    var file: String?=null
    var id: String? = null
    var siteId: Int? = AppController.getInstance().siteid.toIntOrNull()
    var category: Int? = 0
    var sourceSchemaName: String? = "TowerAndCivilInfraTowerTowerDetail"
    var sourceSchemaId: String? = "68"
    var type: String? = "1"
    var title: String? = "Image 1"
    var detail: String ?= "Detail 1"
    var place: String?=""
    var locLatitude: String?=""
    var locLongitude: String?=""
}