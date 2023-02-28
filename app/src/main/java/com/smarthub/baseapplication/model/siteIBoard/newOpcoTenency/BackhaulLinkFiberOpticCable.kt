package com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency

data class BackhaulLinkFiberOpticCable(
    val AcceptanceDate: String,
    val AcceptanceStatus: List<Int>,
    val BillableLength: String,
    val CableLength: String,
    val CableType: String,
    val FMSPortNo: String,
    val InstallationDate: String,
    val LMLength: String,
    val LayingType: List<Int>,
    val Make: String,
    val OTDRLength: String,
    val UsedFiberPair: Int,
    val VendorCompany: List<Int>,
    val ZeroManHoleLatitude: String,
    val ZeroManHoleLongitude: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)