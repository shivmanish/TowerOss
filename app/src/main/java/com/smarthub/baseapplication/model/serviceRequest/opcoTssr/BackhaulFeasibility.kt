package com.smarthub.baseapplication.model.serviceRequest.opcoTssr

data class BackhaulFeasibility(
    var BackHaulFeasibilityRemark: String?=null,
    var BackHaulNodeType: String?=null,
    var Fiber: ArrayList<Fiber>?=null,
    var MicrowaveOrUBR: ArrayList<MicrowaveOrUBR>?=null,
    var OffSetPoleRequired: String?=null,
    var created_at: String?=null,
    var id: String?=null,
    var isActive: String?=null,
    var modified_at: String?=null
)