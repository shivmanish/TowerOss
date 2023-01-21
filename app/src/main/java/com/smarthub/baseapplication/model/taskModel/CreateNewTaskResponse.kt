package com.smarthub.baseapplication.model.taskModel

import com.google.gson.annotations.SerializedName

data class CreateNewTaskResponse(
    @SerializedName("Error")
    val Error: String,
    @SerializedName("Message")
    val Message: String
)