package com.smarthub.baseapplication.model.serviceRequest

data class ServiceRequest(
    var Attachments: List<Any>?=null,
    var BackHaulLinks: List<BackHaulLink>?=null,
    var Equipments: List<Equipment>?=null,
    var RadioAntennas: List<RadioAntenna>?=null,
    var RequesterInfo: List<RequesterInfo>?=null,
    var SRDetails: ArrayList<SRDetails>?=null,

    var created_at: String?=null,
    var id: Int=0,
    var isActive: Boolean?=null,
    var modified_at: String?=null
)