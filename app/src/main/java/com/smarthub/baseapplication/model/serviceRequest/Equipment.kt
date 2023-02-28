package com.smarthub.baseapplication.model.serviceRequest

data class Equipment(
    var SizeL: String? = null,
    var SizeB: String? = null,
    var SizeH: String? = null,
    var Weight: String? = null,
    var InstallationType: Int,
    var EquipmentName: List<String>,
    var PowerType: String? = null,
    var MaxPowerRating: String? = null,
    var VoltageRangeMin: String? = null,
    var VoltageRangeMax: String? = null,
    var OperatingTempMin: String? = null,
    var OperatingTempMax: String? = null,
    var Technology: List<Int>?=null,
    var Voltage: String? = null,
    var created_at: String? = null,
    var id: Int? = null,
    var isActive: Boolean? = null,
    var modified_at: String ?=null,
    var Type:String? =null
)