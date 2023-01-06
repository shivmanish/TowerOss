package com.smarthub.baseapplication.ui.fragments.powerAndFuel.pojo

data class PowerAndFuelEBConnection(
    val AuthorityPODetails: List<AuthorityPODetail>,
    val PowerAndFuelEBConnectionConsumable: List<Any>,
    val PowerAndFuelEBConnectionDNAndPayments: List<PowerAndFuelEBConnectionDNAndPayment>,
    val PowerAndFuelEBConnectionEBDetails: List<PowerAndFuelEBConnectionEBDetail>,
    val PowerAndFuelEBConnectionTariffsDetails: List<PowerAndFuelEBConnectionTariffsDetail>,
    val TowerAndCivilInfraTowerInstallationAndAcceptance: List<TowerAndCivilInfraTowerInstallationAndAcceptance>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
):java.io.Serializable