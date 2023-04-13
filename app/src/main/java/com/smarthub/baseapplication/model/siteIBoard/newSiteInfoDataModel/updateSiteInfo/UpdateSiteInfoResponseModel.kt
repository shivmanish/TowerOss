package com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.updateSiteInfo

import com.google.gson.annotations.SerializedName

class UpdateSiteInfoResponseModel{
    @SerializedName("data")
    val data: Data?=null
    @SerializedName("error")
    val error: Error?=null
    @SerializedName("status")
    val status: Status?=null
}