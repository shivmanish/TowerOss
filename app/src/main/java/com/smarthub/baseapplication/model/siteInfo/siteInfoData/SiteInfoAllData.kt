package com.smarthub.baseapplication.model.siteInfo.siteInfoData


class SiteInfoAllData(
    val Basicinfo: ArrayList<SiteBasicinfo>,
    val GeoCondition: ArrayList<GeoCondition>,
    val OperationalInfo: ArrayList<OperationalInfo>,
    val SafetyAndAccess: ArrayList<SafetyAndAcces>,
    val id: Int,
    val isActive: Boolean,
    val ownername:ArrayList<String>
)