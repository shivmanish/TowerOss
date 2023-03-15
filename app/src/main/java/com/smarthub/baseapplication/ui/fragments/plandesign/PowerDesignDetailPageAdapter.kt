package com.smarthub.baseapplication.ui.fragments.plandesign

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanAndDesignDataItem
import com.smarthub.baseapplication.ui.fragments.plandesign.fragment.*


class PowerDesignDetailPageAdapter (manager: FragmentManager, var data : PlanAndDesignDataItem?, Id: String?) : FragmentPagerAdapter(manager) {


    fun updateData(updatedData:PlanAndDesignDataItem?){
        data=updatedData
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return 5
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
//            0 -> EquipRoomFragment(data?.PlanningAndDesignEquipRoomEquipmentRoom)
            0 -> TowerCivilFragment(data?.PlanningAndDesignTowerAndCivil)
            1 -> EquipRoomFragment(data?.PlanningAndDesignEquipRoomEquipmentRoom)
            2-> PowerFragment(data?.PlanningAndDesignPowerRequirements)
            3 -> EquipUtilityFragment(data?.UtilityEquip)
            4 -> NocCompFragment(data?.NOCAndComp)
            else -> EquipRoomFragment(data?.PlanningAndDesignEquipRoomEquipmentRoom)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
//
            0 -> {
                return "Tower & Civil"
            }
            1 -> {
                return "Equipment Room"
            }
            2 -> {
                return "Power"
            }
            3 -> {
                return "Utility Equip"
            }
            4 -> {
                return "NOC & Comp"
            }

        }
        return super.getPageTitle(position)
    }

}