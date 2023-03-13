package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SoftAcquisitionData(
    var SAcqPayeeAccountDetail: ArrayList<SAcqPayeeAccountDetail>?=null,
    var SAcqSoftAcquisitionAgreementTerm: ArrayList<SoftAcqAgreementTerm>?=null,
    var created_at: String?=null,
    var id: Int,
    var isActive: Boolean?=null,
    var modified_at: String?=null
)