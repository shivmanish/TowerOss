package com.smarthub.baseapplication.model.siteInfo.planAndDesign

data class BatteryBank(
    val BatteryModule: List<Any>,
    val CabinetSizeB: String,
    val CabinetSizeH: String,
    val CabinetSizeL: String,
    val Make: String,
    val Model: String,
    val OverallWeight: String,
    val RatingAndCapacity: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)