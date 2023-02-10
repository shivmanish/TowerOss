package com.smarthub.baseapplication.model.logs

import com.google.gson.annotations.SerializedName

data class LogsDataModel(
    @SerializedName("finaldata")
    val item: List<LogsDataList>,

    @SerializedName("error")
    val error: Error,

    @SerializedName("Status")
    val Status: ArrayList<PostLogDataResponse>
)