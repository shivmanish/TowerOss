package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class AssignACQTeamDAta(
    var AcquisitionBudget: String,
    var AcquisitionMode: ArrayList<Int>,
    var AcquisitionTargetDate: String,
    var Acquisitiontype: ArrayList<Int>,
    var ExecutiveName: String,
    var LeadName: String,
    var POAmount: String,
    var PODate: String,
    var POLineItemNo: Int,
    var PONumber: String,
    var Remark: String,
    var VendorCode: String,
    var VendorCompany: ArrayList<Int>,
    var VendorExecutiveEmailId: String,
    var VendorExecutiveMobile: String,
    var VendorExecutiveName: String,
    var created_at: String?=null,
    var id: Int,
    var isActive: Boolean?=null,
    var modified_at: String?=null
)