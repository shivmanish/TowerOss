package com.smarthub.baseapplication.model.siteIBoard

data class Attachments(
    val created_at: String,
    val detail: String,
    val `file`: String,
    val fullPath: String,
    val id: Int,
    val category: Int,
    val siteId: Int,
    val isActive: Boolean,
    val modified_at: String,
    val sourceSchemaId: Int,
    val sourceSchemaName: String,
    val title: String,
    val type: Int
)