package com.smarthub.baseapplication.model.serviceRequest.opcoTssr

data class RFFeasibility(
    var OffSetPoleReqd: String?=null,
    var RFFeasibiltyRemarks: String?=null,
    var RRUCount: String?=null,
    var SectorCount: String?=null,
    var SectorsOrCellDetails: ArrayList<SectorsOrCellDetail>?=null,
    var Technology: String?=null,
    var created_at: String?=null,
    var id: Int?=null,
    var isActive: Boolean?=null,
    var modified_at: String?=null
)
