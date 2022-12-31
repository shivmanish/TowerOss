package com.smarthub.baseapplication.model.serviceRequest

import com.smarthub.baseapplication.model.serviceRequest.AcquisitionSurvey.ASAquisitionSurvey
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.OpcoTSSR
import com.smarthub.baseapplication.ui.fragments.powerConnection.pojo.PowerAndFuel

data class ServiceRequestAllDataItem(
    val ASAcquitionSurvey: List<ASAquisitionSurvey>,
    val AssignACQTeam: List<AssignACQTeam>,
    val FeasibilityPlanning: List<FeasibilityPlanning>,
    val OpcoTSSR: List<OpcoTSSR>,
    val SPApprovalAndSO: List<Any>,
    val ServiceRequest: List<ServiceRequest>,
    val PowerAndFuel: List<PowerAndFuel>,
    val SiteProposal: List<Any>,
    val SoftAcquisition: List<Any>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)