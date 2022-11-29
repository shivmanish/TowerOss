package com.smarthub.baseapplication.model.siteInfo

data class Sodetail(
    val Discountpercentage: List<Discountpercentage>,
    val Discounttype: List<Discounttype>,
    val Escalationpercentage: List<Escalationpercentage>,
    val Feeesclationsperiod: List<Feeesclationsperiod>,
    val Lockingperiod: List<Lockingperiod>,
    val Monthlypremiumamount: List<Monthlypremiumamount>,
    val Premiumapplicable: List<Premiumapplicable>,
    val Remarks: String,
    val Sodetailsattachments: Any,
    val Sonumber: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String,
    val sodate: String,
    val solineitemnumber: Int,
    val sovalue: Int
)