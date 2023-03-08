package com.smarthub.baseapplication.model.serviceRequest.acquisitionSurvey

import com.smarthub.baseapplication.model.serviceRequest.AssignACQTeam
import com.smarthub.baseapplication.model.serviceRequest.softAqusition.SoftAcquisition

data class AcquisitionSurveyModel(
    var SAcqAssignACQTeam: ArrayList<AssignACQTeam>?=null,
    var SAcqASAcquitionSurvey: ArrayList<ASAquisitionSurvey>?=null,
    var SAcqSoftAcquisition: ArrayList<SoftAcquisition>?=null,
)