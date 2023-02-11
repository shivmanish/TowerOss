package com.smarthub.baseapplication.model.serviceRequest

data class SiteProposal(
    val CommercialDetails: List<CommercialDetail>,
    val SPSubmissionToOpco: List<SPSubmissionToOpco>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)