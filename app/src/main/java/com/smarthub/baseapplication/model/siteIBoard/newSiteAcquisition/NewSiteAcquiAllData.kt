package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class NewSiteAcquiAllData(
    var SAcqAcquitionSurvey: ArrayList<AcquisitionSurveyData>?,
    var SAcqAgreement: ArrayList<SiteAcqAgreement>?,
    var SAcqAssignACQTeam: ArrayList<AssignACQTeamDAta>?,
    var SAcqSoftAcquisition: ArrayList<SoftAcquisitionData>?,
    var created_at: String,
    var id: Int,
    var isActive: Boolean,
    var modified_at: String
)