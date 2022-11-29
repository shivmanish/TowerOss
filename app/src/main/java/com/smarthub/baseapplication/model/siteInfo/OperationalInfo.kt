package com.smarthub.baseapplication.model.siteInfo

data class OperationalInfo(
    val Costcentre: List<Costcentre>,
    val DesignedDcLoad: String,
    val DismantlinglDate: String,
    val Hubsite: List<Hubsite>,
    val InstalledDcLoad: String,
    val Ldca: List<Ldca>,
    val OperatingTemp: String,
    val Powersource: String,
    val RFCDate: String,
    val RFIDate: String,
    val RFSDate: String,
    val Scda: List<Scda>,
    val Sharingfeasibility: List<Sharingfeasibility>,
    val Sitebillingstatus: List<Sitebillingstatu>,
    val Towncategory: List<Towncategory>,
    val createddate: String,
    val id: Int,
    val isActive: Boolean,
    val modifieddate: String
)