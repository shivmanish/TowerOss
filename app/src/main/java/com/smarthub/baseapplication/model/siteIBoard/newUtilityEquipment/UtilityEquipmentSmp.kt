package com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment

import com.smarthub.baseapplication.model.siteIBoard.Attachments

class UtilityEquipmentSmp{
    var ConnectedLoad: ArrayList<UtilityConnectedLoad>?=null
    var ConsumableMaterial: ArrayList<UtilityConsumableMaterial>?=null
    var Equipment: ArrayList<UtilitySMPSEquipment>?=null
    var InstallationAndAcceptence: ArrayList<UtiltyInstallationAcceptence>?=null
    var PODetail: ArrayList<UtilityPoDetails>?=null
    var PreventiveMaintenance: ArrayList<UtilityPreventiveMaintenance>?=null
    var RectifierModule: ArrayList<UtilityRectifierModule>?=null
    var attachment: ArrayList<Attachments>?=null
//    var created_at: String?=null
    var id: Int?=null
//    var isActive: Boolean?=null
//    var modified_at: String
}