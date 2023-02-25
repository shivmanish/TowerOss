package com.smarthub.baseapplication.model.siteInfo.newSiteInfoDataModel

data class OprationalInfoData(
    val AvailableOpco: Any,
    val BackhaulNodeCategory: Int,
    val DesignedDcLoad: Any,
    val DismantlinglDate: String,
    val FeasibleOpcoSharing: Any,
    val InstalledDcLoad: Any,
    val OperatingTempMax: Any,
    val OperatingTempMin: Any,
    val RFCDate: String,
    val RFIDate: String,
    val RFSDate: String,
    val SiteAgreementDate: String,
    val Sitebillingstatus: List<Int>,
    val TowerorPoleHeight: Any,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)