package com.smarthub.baseapplication.model.siteInfo

data class Ownername(
    val Max_Tower_site_limit: Int,
    val Max_user_limit: Int,
    val createdby: String,
    val createddate: String,
    val emailextension: List<Int>,
    val expdate: String,
    val id: Int,
    val metadataname: List<Int>,
    val modifieddate: String,
    val ownercode: String,
    val ownername: String,
    val startdate: String,
    val status: Boolean,
    val updatedby: String,
    val url: String
)