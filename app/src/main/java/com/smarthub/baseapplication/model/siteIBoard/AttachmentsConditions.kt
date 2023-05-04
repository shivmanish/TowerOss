package com.smarthub.baseapplication.model.siteIBoard

data class AttachmentsConditions(
    val Limit: Int?=null,
    val Section: Int,
    val SubSection: Int,
    val created_at: String?=null,
    val created_by: AttachmentsCreatedBy?=null,
    val id: Int,
    val isActive: Boolean?=null,
    val isDefault: Boolean?=null,
    val isMandatory: Boolean?=null,
    val licenseeCompany: Int?=null,
    val modified_at: String?=null,
    val modified_by: AttachmentModifiedBy?=null,
    val name: String,
    val shortName: Any?=null
)