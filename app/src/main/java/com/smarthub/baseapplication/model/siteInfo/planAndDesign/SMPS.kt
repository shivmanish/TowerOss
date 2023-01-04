package com.smarthub.baseapplication.model.siteInfo.planAndDesign

data class SMPS(
    val CabinetSizeB: String,
    val CabinetSizeH: String,
    val CabinetSizeL: String,
    val Make: String,
    val Model: String,
    val OverallWeight: String,
    val PlannedLoad: List<Any>,
    val RatingAndCapacity: String,
    val RectifierModule: List<Any>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)