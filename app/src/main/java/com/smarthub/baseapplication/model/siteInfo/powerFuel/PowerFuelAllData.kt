package com.smarthub.baseapplication.model.siteInfo.powerFuel

import com.smarthub.baseapplication.ui.fragments.powerAndFuel.pojo.PowerAndFuel

data class PowerFuelAllData(
    val PowerAndFuel: ArrayList<PowerAndFuel>,
    val id: Int,
    val isActive: Boolean
)
