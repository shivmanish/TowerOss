package com.smarthub.baseapplication.ui.dialog.siteinfo.pojo

data class BasicinfoModel(
    var Basicinfo: BasicinfoServiceData? = null,
    var geoConditionUpdateModel: GeoConditionUpdateModel? = null,
    var operationalInfoUploadModel: OperationalInfoUploadModel? = null ,
    var safetyAndAccessUpdateModel: SafetyAndAccessUpdateModel? = null,
    var id: String = "392",
    var ownername: String = "SMRT"
)