package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqExternalStructure(
    val SAcqInsidePremise: ArrayList<SAcqInsidePremise>,
    val SAcqOutsidePremise: ArrayList<SAcqOutsidePremise>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)