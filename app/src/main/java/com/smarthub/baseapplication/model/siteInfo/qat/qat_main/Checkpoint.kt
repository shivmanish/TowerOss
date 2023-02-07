package com.smarthub.baseapplication.model.siteInfo.qat.qat_main

data class Checkpoint(
    val CheckPoint: String,
    var Description: String,
    val FieldValue: String,
    val PunchPoint: List<Any>,
    val QATCategory: String,
    val QATCategory_id: String,
    val QATFieldType1: String,
    val QATFieldType1Value: String,
    val QATFieldType2: String,
    val QATFieldType2Value: String,
    val QATItem: String,
    val QATItem_id: String,
    val QATObservation: String,
    val QATObservationValue: String,
    val QATSubItem: String,
    val QATSubItem_id: String,
    var Remark: String,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)