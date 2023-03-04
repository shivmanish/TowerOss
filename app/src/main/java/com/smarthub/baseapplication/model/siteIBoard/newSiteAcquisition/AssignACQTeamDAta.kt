package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class AssignACQTeamDAta(
    val AcquisitionBudget: String,
    val AcquisitionMode: List<Int>,
    val AcquisitionTargetDate: String,
    val Acquisitiontype: List<Int>,
    val ExecutiveName: String,
    val LeadName: String,
    val POAmount: String,
    val PODate: String,
    val POLineItemNo: Int,
    val PONumber: String,
    val Remark: String,
    val VendorCode: String,
    val VendorCompany: List<Int>,
    val VendorExecutiveEmailId: String,
    val VendorExecutiveMobile: String,
    val VendorExecutiveName: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)