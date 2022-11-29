package com.smarthub.baseapplication.model.siteInfo

data class OwnernameXX(
    val Max_Tower_site_limit: Int,
    val Max_user_limit: Int,
    val createdby: String,
    val createddate: String,
    val emailextension: List<Emailextension>,
    val expdate: String,
    val id: Int,
//    val metadataname: List<MetadatanameX>,
    val modifieddate: String,
    val ownercode: String,
    val ownername: String,
    val startdate: String,
    val status: Boolean,
    val updatedby: String,
    val url: String
)