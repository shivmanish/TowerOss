package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class NewSiteAcquiAllData(
    val SAcqAcquitionSurvey: ArrayList<AcquisitionSurveyData>,
    val SAcqAgreement: ArrayList<SiteAcqAgreement>,
    val SAcqAssignACQTeam: ArrayList<AssignACQTeamDAta>,
    val SAcqSoftAcquisition: ArrayList<SoftAcquisitionData>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)