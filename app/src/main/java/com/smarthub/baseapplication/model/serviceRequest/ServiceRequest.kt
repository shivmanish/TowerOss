package com.smarthub.baseapplication.model.serviceRequest

import com.smarthub.baseapplication.ui.fragments.powerConnection.pojo.PowerAndFuel

data class ServiceRequest(
    val Attachments: List<Any>,
    val BackHaulLinks: List<BackHaulLink>,
    val Equipments: List<Equipment>,
    val RadioAntennas: List<RadioAntenna>,
    val RequesterInfo: List<RequesterInfo>,
    val SRDetails: List<SRDetails>,

    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)