package com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency

data class OpcoTenencyAllData(
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String,
    val ownername: List<Any>,
    val Opcoinfo: ArrayList<NewOpcoInfoData>,
    val Commercial: ArrayList<NewCommercialAllData>,
    val RfEquipment: ArrayList<NewRfEquipmentData>,
    val CableDetail: ArrayList<CableDetailsData>,
    val RadioAntennaAndRRU: ArrayList<RadioAnteenaAndRRUData>,
    val BackhaulLink: ArrayList<BackhaulLinkData>,
    val Loadmeasurement: ArrayList<LoadMeasermentData>
)