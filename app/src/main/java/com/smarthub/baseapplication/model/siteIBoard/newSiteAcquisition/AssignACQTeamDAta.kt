package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

import com.smarthub.baseapplication.model.siteIBoard.Attachments

 class AssignACQTeamDAta {
    var AcquisitionBudget: String? = "0"
    var GeographyLevel: String? = null
    var AcquisitionMode: ArrayList<Int>? = null
    var AcquisitionTargetDate: String? = null
    var Acquisitiontype: ArrayList<Int>? = null
    var Department: ArrayList<Int>? = null
    var ExecutiveName: String? = null
    var ExecutiveMobile: String? = null
    var LeadName: String?=null
    var POAmount: String? = "0"
    var PODate: String? = null
    var POLineItemNo: Int? = null
    var PONumber: String? = null
    var remark: String? = null
    var VendorCode: String? = null
    var VendorCompany: ArrayList<Int>? = null
    var VendorExecutiveEmailId: String? = null
    var VendorExecutiveMobile: String? = null
    var VendorExecutiveName: String? = null
    var attachment: ArrayList<Attachments>? = null
    var created_at: String? = null
    var id: Int? = null
    var isActive: Boolean? = null
    var modified_at: String? = null
}