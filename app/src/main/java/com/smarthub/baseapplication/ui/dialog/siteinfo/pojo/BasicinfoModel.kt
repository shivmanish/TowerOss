package com.smarthub.baseapplication.ui.dialog.siteinfo.pojo

import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData
import com.smarthub.baseapplication.model.siteInfo.SafetyAndAcces
import com.smarthub.baseapplication.model.siteInfo.SiteBasicinfo
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.utils.AppController

data class BasicinfoModel(
    var Basicinfo:  SiteBasicinfo? = null,
    var GeoCondition: GeoConditionUpdateModel? = null,
    var OperationalInfo: OperationalInfoUploadModel? = null,
    var SafetyAndAccess: SafetyAndAcces? = null,
    var id: String = "448",
    var ownername: String = AppController.getInstance().ownerName,
    val Operator: OpcoDataItem? = null,
    var ServiceRequestMain: ServiceRequestAllData? = null
)