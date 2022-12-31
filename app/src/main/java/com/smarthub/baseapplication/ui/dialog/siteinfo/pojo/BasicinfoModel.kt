package com.smarthub.baseapplication.ui.dialog.siteinfo.pojo

data class BasicinfoModel(
    var Basicinfo:  BasicinfoServiceData? = null,
    var GeoCondition: GeoConditionUpdateModel? = null,
    var OperationalInfo: OperationalInfoUploadModel? = null ,
    var SafetyAndAccess: SafetyAndAccessUpdateModel? = null,
    var id: String? = null ,
    var ownername: String = "SMRT"
)