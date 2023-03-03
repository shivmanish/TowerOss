package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.PreventiveMaintenance
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilConsumableMaterial
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilPODetail

interface TwrCivilInfraClickedItems {
    fun attachmentItemClicked()
    fun EditInstallationAcceptence()
    fun EditTowerItem()
    fun editPoClicked(position:Int)
    fun viewPoClicked(position:Int,data: TwrCivilPODetail)
    fun editConsumableClicked(position:Int)
    fun viewConsumableClicked(position:Int,data: TwrCivilConsumableMaterial)
    fun viewMaintenenceClicked(position:Int,data: PreventiveMaintenance)
    fun editOffsetClicked(position:Int)
    fun viewOffsetClicked(position:Int)
}