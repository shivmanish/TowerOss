package com.smarthub.baseapplication.model.siteInfo.siteInfoData

data class OperationalInfo(
    val Costcentre: String?,
    val DesignedDcLoad: String,
    val DismantlinglDate: String,
    val Hubsite: String?,
    val InstalledDcLoad: String,
    val Ldca:String?,
    val OperatingTemp: String,
    val Powersource: String,
    val RFCDate: String,
    val RFIDate: String,
    val RFSDate: String,
    val Scda: String?,
    val Sharingfeasibility: String?,
    val Sitebillingstatus: String?,
    val Towncategory: String?,
    val createddate: String,
    val id: Int,
    val isActive: Boolean,
    val modifieddate: String
)