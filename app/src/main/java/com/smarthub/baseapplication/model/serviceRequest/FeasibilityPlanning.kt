package com.smarthub.baseapplication.model.serviceRequest

data class FeasibilityPlanning(
    var BackHaul: ArrayList<BackHaul>?=null,
    var Equipments: ArrayList<EquipmentXX>?=null,
    var PowerAndMCB: ArrayList<PowerAndMCB>?=null,
    var RadioAntenna: ArrayList<RadioAntennaX>?=null,
    var SiteDetails: ArrayList<SiteDetail>?=null,
    var created_at: String?=null,
    var id: String?=null,
    var isActive: String?=null,
    var modified_at: String?=null,
)