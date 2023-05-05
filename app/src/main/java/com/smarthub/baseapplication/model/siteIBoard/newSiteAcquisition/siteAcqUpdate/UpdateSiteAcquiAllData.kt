package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate

import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.*

data class UpdateSiteAcquiAllData(
    var SAcqAcquitionSurvey: ArrayList<AcquisitionSurveyData>?=null,
    var SAcqAgreement: ArrayList<SiteAcqAgreement>?=null,
    var SAcqAssignACQTeam: ArrayList<AssignACQTeamDAta>?=null,
    var SAcqSoftAcquisition: ArrayList<SoftAcquisitionData>?=null,
    var SAcqSiteFeasibility: ArrayList<SAcqFeasibilityDetail>?=null,
    var SAcqSiteApproval: ArrayList<SAcqSiteApproval>?=null,
    var id: Int?=null,
)