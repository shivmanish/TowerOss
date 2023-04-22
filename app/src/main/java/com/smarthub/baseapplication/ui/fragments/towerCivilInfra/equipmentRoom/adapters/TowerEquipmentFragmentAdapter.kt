package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.equipmentRoom.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.NewTowerCivilAllData
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TowerAndCivilInfraEquipmentRoom
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.equipmentRoom.TowerEquipmentInfoFragment

class TowerEquipmentFragmentAdapter (fm: FragmentManager, var list:ArrayList<TowerAndCivilInfraEquipmentRoom>?, var fullData: NewTowerCivilAllData?) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return list?.size!!
    }

    override fun getItem(position: Int): Fragment {
        return TowerEquipmentInfoFragment(list!![position],fullData,position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Equipment Room${position+1}"
    }

}