package com.smarthub.baseapplication.model.serviceRequest.log

data class LogSearchData(
    val error: Error,
    val finaldata: List<Finaldata>
)