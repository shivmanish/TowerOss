package com.smarthub.baseapplication.model.serviceRequest

data class FeasibilityPlanning(
    val BackHaul: List<BackHaul>,
    val Equipments: List<EquipmentXX>,
    val PowerAndMCB: List<PowerAndMCB>,
    val RadioAntenna: List<RadioAntennaX>,
    val SiteDetails: List<SiteDetail>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)