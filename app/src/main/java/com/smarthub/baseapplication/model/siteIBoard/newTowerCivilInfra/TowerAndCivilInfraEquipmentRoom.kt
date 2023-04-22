package com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra

import com.smarthub.baseapplication.model.siteIBoard.Attachments

class TowerAndCivilInfraEquipmentRoom{
    var ConsumableMaterial: ArrayList<TwrCivilConsumableMaterial>?=null
    var InstallationAndAcceptence: ArrayList<TwrInstallationAndAcceptence>?=null
    var PODetail: ArrayList<TwrCivilPODetail>?=null
    var PreventiveMaintenance: ArrayList<PreventiveMaintenance>?=null
    var TowerAndCivilInfraEquipmentRoomEquipmentRoomDetail: ArrayList<TwrCivilEquipmentRoomDetail>?=null
    var attachment: ArrayList<Attachments>?=null
    var created_at: String?=null
    var id: Int?=null
    var isActive: Boolean?=null
    var modified_at: String?=null
}