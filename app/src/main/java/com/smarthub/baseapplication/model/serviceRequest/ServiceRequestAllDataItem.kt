package com.smarthub.baseapplication.model.serviceRequest

import com.smarthub.baseapplication.model.serviceRequest.acquisitionSurvey.ASAquisitionSurvey
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.OpcoTSSR
import com.smarthub.baseapplication.model.serviceRequest.softAqusition.SoftAcquisition
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.pojo.PowerAndFuel

data class ServiceRequestAllDataItem(
    var ASAcquitionSurvey: ArrayList<ASAquisitionSurvey>?=null,
    var AssignACQTeam: ArrayList<AssignACQTeam>?=null,
    var FeasibilityPlanning: ArrayList<FeasibilityPlanning>?=null,
    var OpcoTSSR: ArrayList<OpcoTSSR>?=null,
    var SPApprovarAndSO: List<SPApprovalAndSO>?=null,
    var ServiceRequest: ArrayList<ServiceRequest>?=null,
    var PowerAndFuel: List<PowerAndFuel>?=null,
    var SiteProposal: List<SiteProposal>?=null,
    var SoftAcquisition: ArrayList<SoftAcquisition>?=null,
    var created_at: String?=null,
    var id: Int ?= 448,
    var isActive: Boolean ?=null,
    var modified_at: String?=null
)