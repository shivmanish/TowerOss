package com.example.example

import com.google.gson.annotations.SerializedName


data class PODetails(

    @SerializedName("id") var id: String? = null,
    @SerializedName("isActive") var isActive: String? = null,
    @SerializedName("modified_at") var modifiedAt: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("VendorName") var VendorName: String? = null,
    @SerializedName("PONumber") var PONumber: String? = null,
    @SerializedName("PODate") var PODate: String? = null,
    @SerializedName("POLineNumber") var POLineNumber: String? = null,
    @SerializedName("POAmount") var POAmount: String? = null

)