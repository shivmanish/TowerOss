package com.smarthub.baseapplication.model.serviceRequest

data class RFFeasibility(
    val OffSetPoleReqd: List<OffSetPoleReqd>,
    val RFFeasibiltyRemarks: String,
    val RRUCount: List<RRUCount>,
    val SectorCount: List<SectorCount>,
    val SectorsOrCellDetails: List<SectorsOrCellDetail>,
    val Technology: List<Technology>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)