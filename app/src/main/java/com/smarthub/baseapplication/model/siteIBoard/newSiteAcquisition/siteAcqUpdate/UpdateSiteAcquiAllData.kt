package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate

import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.AcquisitionSurveyData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.AssignACQTeamDAta
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SiteAcqAgreement
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SoftAcquisitionData

data class UpdateSiteAcquiAllData(
    var SAcqAcquitionSurvey: ArrayList<AcquisitionSurveyData>?=null,
    var SAcqAgreement: ArrayList<SiteAcqAgreement>?=null,
    var SAcqAssignACQTeam: ArrayList<AssignACQTeamDAta>?=null,
    var SAcqSoftAcquisition: ArrayList<SoftAcquisitionData>?=null,
    var id: Int?=null,
)