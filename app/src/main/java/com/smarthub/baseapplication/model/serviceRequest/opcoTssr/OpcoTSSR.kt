package com.smarthub.baseapplication.model.serviceRequest.opcoTssr

data class OpcoTSSR(
    var Attachments: List<Any>?=null,
    var BackHaulFeasibility: ArrayList<BackhaulFeasibility>?=null,
    var Equipments: List<Equipment>?=null,
    var PowerAndMCB: List<PowerAndMcb>?=null,
    var RFFeasibility: ArrayList<RFFeasibility>?=null,
    var TSSRExecutiveInfo: List<TSSRExecutiveInfo>?=null,
    var created_at: String?=null,
    var id: Int?=null,
    var isActive: Boolean?=null,
    var modified_at: String?=null
)
