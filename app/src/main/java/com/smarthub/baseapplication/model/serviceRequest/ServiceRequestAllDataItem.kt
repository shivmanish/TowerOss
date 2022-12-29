package com.smarthub.baseapplication.model.serviceRequest

data class ServiceRequestAllDataItem(
    val ASAcquitionSurvey: List<Any>,
    val AssignACQTeam: List<AssignACQTeam>,
    val FeasibilityPlanning: List<Any>,
//    val OpcoTSSR: OpcoTSSR,
    val SPApprovalAndSO: List<Any>,
    val ServiceRequest: List<ServiceRequest>,
    val SiteProposal: List<Any>,
    val SoftAcquisition: List<Any>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)