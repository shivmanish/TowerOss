package com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel

data class OprationalInfoData(
    val AvailableOpco: String,
    val BackhaulNodeCategory: Int,
    val DesignedDcLoad: String,
    val DismantlinglDate: String,
    val FeasibleOpcoSharing: String,
    val InstalledDcLoad: String,
    val OperatingTempMax: String,
    val OperatingTempMin: String,
    val PowerConnectionType: List<Int>,
    val RFCDate: String,
    val RFIDate: String,
    val RFSDate: String,
    val SiteAgreementDate: String,
    val Sitebillingstatus: List<Int>,
    val TempSetting: String,
    val TowerorPoleHeight: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)