package com.smarthub.baseapplication.model.serviceRequest

data class Equipment(
    var CabinetSize: String? = null,
    var EquipmentWeight: String? = null,
    var InputPower: String? = null,
    var MaxPowerRating: String? = null,
    var OperatingTemp: String? = null,
    var Technology: String? = null,
    var Voltage: String? = null,
    var created_at: String? = null,
    var id: Int? = null,
    var isActive: Boolean? = null,
    var modified_at: String ?=null,
    var Type:String? =null
)