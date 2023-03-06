package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SiteAcqAgreement(
    val SAcqAgreementDetail: ArrayList<SAcqAgreementDetail>,
    val SAcqPODetail: ArrayList<SAcqPODetail>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)