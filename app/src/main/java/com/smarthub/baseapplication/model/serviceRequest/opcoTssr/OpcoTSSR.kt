package com.smarthub.baseapplication.model.serviceRequest.opcoTssr

data class OpcoTSSR(
    val Attachments: List<Any>,
    val BackHaulFeasibility: List<BackhaulFeasibility>,
    val Equipments: List<Equipment>,
    val PowerAndMCB: List<PowerAndMcb>,
    val RFFeasibility: List<RFFeasibility>,
    val TSSRExecutiveInfo: List<TSSRExecutiveInfo>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)
