package com.smarthub.baseapplication.model.siteInfo

data class ColocationFee(
    val AddOnFeeEfectiveDate: String,
    val Addonfee: List<Addonfee>,
    val Addonmonthlyfee: String,
    val Basicipmonthlyfee: String,
    val Feeefectivedate: String,
    val Paymentfreq: List<Paymentfreq>,
    val Remarks: String,
    val TotalAnnualFee: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)