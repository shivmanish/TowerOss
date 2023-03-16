package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SiteAcqAgreement(
    var SAcqAgreementDetail: ArrayList<SAcqAgreementDetail>?=null,
    var SAcqPODetail: ArrayList<SAcqPODetail>?=null,
//    var created_at: String,
    var id: Int,
//    var isActive: Boolean,
//    var modified_at: String
)