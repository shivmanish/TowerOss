package com.smarthub.baseapplication.model.siteInfo.qat

data class SaveCheckpointData(
    var CheckPoint: String?=null,
    var Description: String?=null,
    var FieldValue: String?=null,
    var QATFieldType1: String?=null,
    var QATFieldType1Value: String?=null,
    var QATFieldType2: String?=null,
    var QATFieldType2Value: String?=null,
    var QATObservation: String?=null,
    var QATObservationValue: String?=null,
    var QATSubItem: String?=null,
    var Remark: String?=null,
    var id: String?=null,
    var isActive: Boolean?=null
)