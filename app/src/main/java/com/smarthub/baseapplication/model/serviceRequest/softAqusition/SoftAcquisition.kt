package com.smarthub.baseapplication.model.serviceRequest.softAqusition

data class SoftAcquisition(
    var AgreementTerms: ArrayList<AgreementTerm>?= null,
    var PropertyOwnerAndPaymentDetails: ArrayList<PropertyOwnerAndPaymentDetail>?= null,
    var created_at: String?= null,
    var id: String?= null,
    var isActive: String?= null,
    var modified_at: String?= null
)