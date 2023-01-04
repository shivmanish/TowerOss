package com.smarthub.baseapplication.model.siteInfo.oprationInfo

data class UpdateOperationInfo(
    val Costcentre: String = "1",
    val DesignedDcLoad: String,
    val DismantlinglDate: String = "2011-10-01 15:26",
    val Hubsite: String = "1",
    val InstalledDcLoad: String,
    val Ldca: String = "1",
    val OperatingTemp: String,
    val Powersource: String = "Powersource",
    val RFCDate: String = "2011-10-01 15:26",
    val RFIDate: String = "2011-10-01 15:26",
    val RFSDate: String = "2011-10-01 15:26",
    val Scda: String = "1",
    val Sharingfeasibility: String,
    val Sitebillingstatus: String = "1",
    val Towncategory: String = "1"
)