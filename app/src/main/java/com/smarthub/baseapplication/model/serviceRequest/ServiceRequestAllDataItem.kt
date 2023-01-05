package com.smarthub.baseapplication.model.serviceRequest

import com.smarthub.baseapplication.model.serviceRequest.AcquisitionSurvey.ASAquisitionSurvey
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.OpcoTSSR
import com.smarthub.baseapplication.model.serviceRequest.softAqusition.SoftAcquisition
import com.smarthub.baseapplication.ui.fragments.powerConnection.pojo.PowerAndFuel

data class ServiceRequestAllDataItem(
    var ASAcquitionSurvey: List<ASAquisitionSurvey>?=null,
    var AssignACQTeam: List<AssignACQTeam>?=null,
    var FeasibilityPlanning: List<FeasibilityPlanning>?=null,
    var OpcoTSSR: List<OpcoTSSR>?=null,
    var SPApprovarAndSO: List<Any>?=null,
    var ServiceRequest: ArrayList<ServiceRequest>?=null,
    var PowerAndFuel: List<PowerAndFuel>?=null,
    var SiteProposal: List<Any>?=null,
    var SoftAcquisition: List<SoftAcquisition>?=null,
    var created_at: String?=null,
    var id: Int ?= 448,
    var isActive: Boolean ?=null,
    var modified_at: String?=null
)