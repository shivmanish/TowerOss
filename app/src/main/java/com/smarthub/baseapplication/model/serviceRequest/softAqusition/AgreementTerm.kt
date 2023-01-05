package com.smarthub.baseapplication.model.serviceRequest.softAqusition

data class AgreementTerm(
    val AgreementPeriod: String,
    val EBBillingBasis: String,
    val EBPerUnitRate: String,
    val InitialAnnualRentAmount: String,
    val LockInPeriod: String,
    val OnetimeAmount: String,
    val PeriodicRentPayableAmount: String,
    val PropertyOwnership: String,
    val RentEscalationPeriod: String,
    val SecurityDepositAmount: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)