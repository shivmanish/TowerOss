package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class AcquisitionSurveyData(
    val SAcqExternalStructure: ArrayList<SAcqExternalStructure>,
    val SAcqFeasibilityDetail: ArrayList<SAcqFeasibilityDetail>,
    val SAcqPowerConnectionFeasibility: ArrayList<SAcqPowerConnectionFeasibility>,
    val SAcqPropertyDetail: ArrayList<SAcqPropertyDetail>,
    val SAcqPropertyOwnerDetail: ArrayList<SAcqPropertyOwnerDetail>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)