package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SoftAcqAgreementTerm(
    var AcquisitionArea: String,
    var Acquisitiontype: ArrayList<Int>,
    var AgreementPeriod: String,
    var AnnualRentAmount: String,
    var EBBillLimitmax: String,
    var EBBillLimitmin: String,
    var EBBillingBasis: Int,
    var EBInclusiveInRental: Int,
    var EBPUnitRate: String,
    var LockInPeriod: String,
    var OnetimeAmount: String,
    var PeriodicRentAmount: String,
    var PropertyOwnership: ArrayList<Int>,
    var PropertyType: Int,
    var Remark: String,
    var RentEscalation: String,
    var RentEscalationPeriod: String,
    var Rentpaymentperiod: Int,
    var SecurityDepositAmount: String,
    var created_at: String?=null,
    var id: Int,
    var isActive: Boolean?=null,
    var modified_at: String?=null
)