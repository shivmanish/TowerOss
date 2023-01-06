package com.smarthub.baseapplication.model.serviceRequest.softAqusition

data class SoftAcquisition(
    val AgreementTerms: List<AgreementTerm>,
    val PropertyOwnerAndPaymentDetails: List<PropertyOwnerAndPaymentDetail>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)