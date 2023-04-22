package com.smarthub.baseapplication.model.siteIBoard

import com.google.gson.annotations.SerializedName

class AttachmentConditionsDataModel{
    @SerializedName("Attachment")
    var Attachment : ArrayList<AttachmentsConditions>?=null

    @SerializedName("Status")
    var Status : Any?=null
    @SerializedName("TotalCount")
    var TotalCount : Any?=null
    @SerializedName("Error")
    var Error : Any?=null

}