package com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency

data class LoadMeasermentData(
    val LoadCurrent: String,
    val LoadVoltage: String,
    val LoadWattage: String,
    val MeasurementDate: String,
    val MeasurementPoint: String,
    val OpcoExecutiveName: String,
    val PowerAttachement: Any,
    val PowerType: Int,
    val Remark: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)