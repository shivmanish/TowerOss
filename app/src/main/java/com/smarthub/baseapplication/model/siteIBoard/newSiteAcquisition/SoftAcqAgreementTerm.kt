package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SoftAcqAgreementTerm(
    val AcquisitionArea: String,
    val Acquisitiontype: List<Int>,
    val AgreementPeriod: String,
    val AnnualRentAmount: String,
    val EBBillLimitmax: String,
    val EBBillLimitmin: String,
    val EBBillingBasis: Int,
    val EBInclusiveInRental: Int,
    val EBPUnitRate: String,
    val LockInPeriod: String,
    val OnetimeAmount: String,
    val PeriodicRentAmount: String,
    val PropertyOwnership: List<Int>,
    val PropertyType: Int,
    val Remark: String,
    val RentEscalation: String,
    val RentEscalationPeriod: String,
    val Rentpaymentperiod: Int,
    val SecurityDepositAmount: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)