package com.example.example

import com.google.gson.annotations.SerializedName


data class SiteacquisitionPayment(

    @SerializedName("id") var id: String? = null,
    @SerializedName("isActive") var isActive: String? = null,
    @SerializedName("modified_at") var modifiedAt: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("InvoiceNumber") var InvoiceNumber: String? = null,
    @SerializedName("Date") var Date: String? = null,
    @SerializedName("LineItemNumber") var LineItemNumber: String? = null,
    @SerializedName("PayeeName") var PayeeName: String? = null,
    @SerializedName("Amount") var Amount: String? = null,
    @SerializedName("NetPayable") var NetPayable: String? = null,
    @SerializedName("DueDate") var DueDate: String? = null,
    @SerializedName("PaymentDate") var PaymentDate: String? = null,
    @SerializedName("PaymentAmount") var PaymentAmount: String? = null,
    @SerializedName("Status") var Status: String? = null

)