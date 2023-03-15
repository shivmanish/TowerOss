package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

import com.smarthub.baseapplication.model.siteIBoard.Attachments

data class SiteAcqAgreement(
    var SAcqAgreementDetail: ArrayList<SAcqAgreementDetail>?=null,
    var SAcqPODetail: ArrayList<SAcqPODetail>?=null,
    var attachment: ArrayList<Attachments>?=null,
//    var created_at: String,
    var id: Int,
//    var isActive: Boolean,
//    var modified_at: String
)