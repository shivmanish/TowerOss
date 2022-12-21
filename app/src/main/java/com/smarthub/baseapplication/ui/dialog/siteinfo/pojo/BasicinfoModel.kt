package com.smarthub.baseapplication.ui.dialog.siteinfo.pojo

data class BasicinfoModel(
    var Basicinfo: BasicinfoServiceData? = null,
    var GeoConditionUpdateModel: GeoConditionUpdateModel? = null,
    var OperationalInfo: OperationalInfoUploadModel? = null ,
    var safetyAndAccessUpdateModel: SafetyAndAccessUpdateModel? = null,
    var id: String = "392",
    var ownername: String = "SMRT"
)