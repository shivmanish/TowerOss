package com.smarthub.baseapplication.model.serviceRequest

data class OpcoTSSR(
    val Attachments: List<Any>,
    val BackHaulFeasibility: List<Any>,
    val Equipments: List<Any>,
    val PowerAndMCB: List<Any>,
    val RFFeasibility: List<RFFeasibility>,
    val TSSRExecutiveInfo: List<TSSRExecutiveInfo>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)