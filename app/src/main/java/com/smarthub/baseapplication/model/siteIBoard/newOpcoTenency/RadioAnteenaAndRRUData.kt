package com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency

data class RadioAnteenaAndRRUData(
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String,
    val RadioAntennaAndRRURadioAntennaDetail: ArrayList<RadioAnteenaDetails>,
    val RadioAntennaAndRRURRUDetail: ArrayList<RRUDetails>,
    val RadioAntennaAndRRUCableDetail: ArrayList<RadioAntennaAndRRUCableDetail>
)