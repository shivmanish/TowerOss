package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class AcquisitionSurveyData(
    var SAcqExternalStructure: ArrayList<SAcqExternalStructure>?=null,
    var SAcqFeasibilityDetail: ArrayList<SAcqFeasibilityDetail>?=null,
    var SAcqPowerConnectionFeasibility: ArrayList<SAcqPowerConnectionFeasibility>?=null,
    var SAcqPropertyDetail: ArrayList<SAcqPropertyDetail>?=null,
    var SAcqPropertyOwnerDetail: ArrayList<SAcqPropertyOwnerDetail>?=null,
    var created_at: String?=null,
    var id: Int?=null,
    var isActive: Boolean?=null,
    var modified_at: String?=null
)