package com.smarthub.baseapplication.model.siteInfo.opcoInfo

data class PowerLoadData(
    val LoadCurrent: String,
    val LoadVoltage: String,
    val LoadWattage: String,
    val MeasurementPoint: String,
    val PowerType: List<String>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String,
    val operatorExecutiveName: String,
    val remark: String
)