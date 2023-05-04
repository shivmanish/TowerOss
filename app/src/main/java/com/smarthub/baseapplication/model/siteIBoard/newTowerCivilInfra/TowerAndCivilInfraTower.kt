package com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra

import com.smarthub.baseapplication.model.siteIBoard.Attachments

class TowerAndCivilInfraTower{
    var ConsumableMaterial: ArrayList<TwrCivilConsumableMaterial>?=null
    var InstallationAndAcceptence: List<TwrInstallationAndAcceptence>?=null
    var PODetail: ArrayList<TwrCivilPODetail>?=null
    var PreventiveMaintenance: ArrayList<PreventiveMaintenance>?=null
    var TowerAndCivilInfraTowerExcavation: ArrayList<TowerExcavation>?=null
    var TowerAndCivilInfraTowerPcc: ArrayList<TowerPcc>?=null
    var TowerAndCivilInfraTowerBarBending: ArrayList<TowerBarBending>?=null
    var TowerAndCivilInfraTowerRaftShaft: ArrayList<TowerRaftShaft>?=null
    var TowerAndCivilInfraTowerCubeTesting: ArrayList<TowerCubeTesting>?=null
    var TowerAndCivilInfraTowerLiftCast1: ArrayList<TowerLiftCast1>?=null
    var TowerAndCivilInfraTowerLiftCast2: ArrayList<TowerLiftCast2>?=null
    var TowerAndCivilInfraTowerTemplateFixing: ArrayList<TowerTemplateFixing>?=null
    var TowerAndCivilInfraTowerTowerEarthing: ArrayList<TowerTowerEarthing>?=null
    var TowerAndCivilInfraTowerErection: ArrayList<TowerErection>?=null
    var TowerAndCivilInfraTowerTowerDetail: List<TwrCivilInfraTowerDetail>?=null
    var attachment: ArrayList<Attachments>?=null
    var created_at: String?=null
    var id: Int?=null
    var isActive: Boolean?=null
    var modified_at: String?=null
}