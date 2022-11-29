package com.smarthub.baseapplication.model.siteInfo

data class BasicInfoModelItem(
    val Basicinfo: List<Basicinfo>,
    val Filesstorage: List<Any>,
    val GeoCondition: List<GeoCondition>,
    val OperationalInfo: List<OperationalInfo>,
    val Operator: List<Operator>,
    val SafetyAndAccess: List<SafetyAndAcces>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String,
    val ownername: List<Any>,
    val siteacquisition: List<Any>,
    val utilities: List<Any>
)