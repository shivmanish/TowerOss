package com.smarthub.baseapplication.model.siteIBoard.newPowerFuel

class PowerConnectionAllData{
    var ConsumableMaterial: ArrayList<PowerConsumableMaterial>?=null
    var InstallationAndAcceptence: ArrayList<PowerInstallationAndAcceptence>?=null
    var PODetail: ArrayList<PowerFuelPODetail>?=null
    var PowerAndFuelEBConnectionEBDetail: ArrayList<PowerConnectionDetail>?=null
    var PowerAndFuelEBConnectionPayment: ArrayList<PowerFuelAuthorityPayments>?=null
    var PowerAndFuelEBConnectionTariffDetail: ArrayList<PowerFuelTariffDetails>?=null
    var created_at: String?=null
    var id: Int?=null
    var isActive: Boolean?=null
    var modified_at: String?=null
}