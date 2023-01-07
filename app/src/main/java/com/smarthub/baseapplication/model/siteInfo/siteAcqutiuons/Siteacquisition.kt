package com.smarthub.baseapplication.model.siteInfo.siteAcqutiuons

import com.example.example.SiteacquisitionAgreements
import com.example.example.SiteacquisitionPayment
import com.google.gson.annotations.SerializedName


data class Siteacquisition(

    @SerializedName("id") var id: String? = null,
    @SerializedName("isActive") var isActive: String? = null,
    @SerializedName("modified_at") var modifiedAt: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("SiteacquisitionAgreements") var SiteacquisitionAgreements: ArrayList<SiteacquisitionAgreements> = arrayListOf(),
    @SerializedName("SiteacquisitionPayment") var SiteacquisitionPayment: ArrayList<SiteacquisitionPayment> = arrayListOf()

)