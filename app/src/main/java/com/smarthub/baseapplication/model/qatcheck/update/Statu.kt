package com.smarthub.baseapplication.model.qatcheck.update

data class Statu(
    val QATMainLaunch: String,
    val error: String,
    val data: Result,
    val success: Boolean
)