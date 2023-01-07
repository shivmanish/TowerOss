package com.example.example

import com.google.gson.annotations.SerializedName


data class SiteacquisitionAgreements(

    @SerializedName("id") var id: String? = null,
    @SerializedName("isActive") var isActive: String? = null,
    @SerializedName("modified_at") var modifiedAt: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("AgreementType") var AgreementType: String? = null,
    @SerializedName("RegistrationDate") var RegistrationDate: String? = null,
    @SerializedName("AgreementPeriod") var AgreementPeriod: String? = null,
    @SerializedName("LockInPeriod") var LockInPeriod: String? = null,
    @SerializedName("AgreementEffectiveDate") var AgreementEffectiveDate: String? = null,
    @SerializedName("AgreementExpiryDate") var AgreementExpiryDate: String? = null,
    @SerializedName("RentStartDate") var RentStartDate: String? = null,
    @SerializedName("InitialAnnualRentAmount") var InitialAnnualRentAmount: String? = null,
    @SerializedName("PeriodicRentPayableAmount") var PeriodicRentPayableAmount: String? = null,
    @SerializedName("RentEscalation") var RentEscalation: String? = null,
    @SerializedName("RentEscalationPeriod") var RentEscalationPeriod: String? = null,
    @SerializedName("LastescalationDate") var LastescalationDate: String? = null,
    @SerializedName("LastRevisedRentAmount") var LastRevisedRentAmount: String? = null,
    @SerializedName("EBPerUnitRate") var EBPerUnitRate: String? = null,
    @SerializedName("OnetimeAmount") var OnetimeAmount: String? = null,
    @SerializedName("SecurityDepositAmount") var SecurityDepositAmount: String? = null,
    @SerializedName("RooftopacquiredArea") var RooftopacquiredArea: String? = null,
    @SerializedName("RooftopUsableArea") var RooftopUsableArea: String? = null,
    @SerializedName("GroundacquiredArea") var GroundacquiredArea: String? = null,
    @SerializedName("GroundUsableArea") var GroundUsableArea: String? = null,
    @SerializedName("RegistrationNumber") var RegistrationNumber: String? = null,
    @SerializedName("BookingCostCentre") var BookingCostCentre: String? = null,
    @SerializedName("RentPaymentFrequency") var RentPaymentFrequency: String? = null,
    @SerializedName("EBInclusiveinRental") var EBInclusiveinRental: String? = null,
    @SerializedName("EBBillLimit") var EBBillLimit: String? = null,
    @SerializedName("EBBillingBasis") var EBBillingBasis: String? = null,
    @SerializedName("PropertyOwnership") var PropertyOwnership: String? = null,
    @SerializedName("PropertyAcquired") var PropertyAcquired: String? = null,
    @SerializedName("PropertyOwnerPaymentDetails") var PropertyOwnerPaymentDetails: ArrayList<PropertyOwnerPaymentDetails> = arrayListOf(),
    @SerializedName("PODetails") var PODetails: ArrayList<PODetails> = arrayListOf()

)