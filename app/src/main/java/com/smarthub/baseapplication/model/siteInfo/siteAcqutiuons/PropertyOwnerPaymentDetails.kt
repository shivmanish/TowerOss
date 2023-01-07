package com.example.example

import com.google.gson.annotations.SerializedName


data class PropertyOwnerPaymentDetails(

    @SerializedName("id") var id: String? = null,
    @SerializedName("isActive") var isActive: String? = null,
    @SerializedName("modified_at") var modifiedAt: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("share") var share: String? = null,
    @SerializedName("PayeeName") var PayeeName: String? = null,
    @SerializedName("ACNumber") var ACNumber: String? = null,
    @SerializedName("PayeeBank") var PayeeBank: String? = null,
    @SerializedName("BankBranch") var BankBranch: String? = null,
    @SerializedName("BankIFCCode") var BankIFCCode: String? = null,
    @SerializedName("GSTNumber") var GSTNumber: String? = null,
    @SerializedName("PanNumber") var PanNumber: String? = null,
    @SerializedName("PayeeStatus") var PayeeStatus: String? = null

)