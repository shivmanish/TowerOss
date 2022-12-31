package com.smarthub.baseapplication.model.serviceRequest.opcoTssr

data class RFFeasibility(
    val OffSetPoleReqd: String,
    val RFFeasibiltyRemarks: String,
    val RRUCount: String,
    val SectorCount: String,
    val SectorsOrCellDetails: List<SectorsOrCellDetail>,
    val Technology: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)
