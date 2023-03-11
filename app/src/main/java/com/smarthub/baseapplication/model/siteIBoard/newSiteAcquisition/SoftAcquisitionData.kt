package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SoftAcquisitionData(
    val SAcqPayeeAccountDetail: ArrayList<SAcqPayeeAccountDetail>,
    val SAcqSoftAcquisitionAgreementTerm: ArrayList<SoftAcqAgreementTerm>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)