package com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency

data class BackhaulLinkData(
    val BackBackhaulLinkFiberOpticCable: ArrayList<BackhaulLinkFiberOpticCable>,
    val BackBackhaulLinkFiberOpticEquipment: ArrayList<BackBackhaulLinkFiberOpticEquipment>,
    val BackBackhaulLinkMWAntenna: ArrayList<BackBackhaulLinkMWAntenna>,
    val BackBackhaulLinkMWODU: ArrayList<BackhaulLinkMWODU>,
    val BackhaulLinkFiberLinkInfo: ArrayList<BackhaulLinkFiberLinkInfo>,
    val BackhaulLinkMWIDU: ArrayList<BackhaulLinkMWIDU>,
    val BackhaulLinkMWLinkInfo: ArrayList<BackhaulLinkMWLinkInfo>,
    val BackhaullinkFiberCableDetail: ArrayList<BackhaullinkFiberCableDetail>,
    val BackhaullinkMWCableDetail: ArrayList<BackhaullinkMWCableDetail>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val BackhaulNodeCategory: Int,
    val Remark: String,
    val modified_at: String
)