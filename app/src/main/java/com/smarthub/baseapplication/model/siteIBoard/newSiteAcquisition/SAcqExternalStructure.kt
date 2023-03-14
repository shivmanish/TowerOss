package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqExternalStructure(
    var SAcqInsidePremise: ArrayList<SAcqInsidePremise>?=null,
    var SAcqOutsidePremise: ArrayList<SAcqOutsidePremise>?=null,
//    var created_at: String,
    var id: Int?=null,
//    var isActive: Boolean,
//    var modified_at: String
)