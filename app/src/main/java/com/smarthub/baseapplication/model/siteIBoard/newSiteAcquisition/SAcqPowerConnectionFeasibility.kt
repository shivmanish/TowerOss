package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqPowerConnectionFeasibility(
    var AvgAvailability: String,
    var ConsumerNo: String,
    var EBApplicationStatus: Int,
    var EBAvailability: Int,
    var MeterSerialNo: String,
    var MeterType: Int,
    var NearestEBPole: String,
    var PowerRating: String,
    var PowerSupplier: String,
    var Remark: String,
    var SolarFeasibility: Int,
//    var created_at: String,
    var id: Int?=null,
//    var isActive: Boolean,
//    var modified_at: String
)