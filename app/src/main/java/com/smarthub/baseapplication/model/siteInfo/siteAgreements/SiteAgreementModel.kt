package com.smarthub.baseapplication.model.siteInfo.siteAgreements

import com.google.gson.annotations.SerializedName

class SiteAgreementModel {
    @SerializedName("finaldata")
    var item : List<SiteAgreementsData>?=null

    @SerializedName("error")
    var error : Any?=null
}