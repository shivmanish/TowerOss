package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqPowerConnectionFeasibility(
    val AvgAvailability: String,
    val ConsumerNo: String,
    val EBApplicationStatus: Int,
    val EBAvailability: Int,
    val MeterSerialNo: String,
    val MeterType: Int,
    val NearestEBPole: String,
    val PowerRating: String,
    val PowerSupplier: String,
    val Remark: String,
    val SolarFeasibility: Int,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)