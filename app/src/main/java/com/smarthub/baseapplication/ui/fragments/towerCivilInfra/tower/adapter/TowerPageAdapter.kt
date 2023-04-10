package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tower.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.NewTowerCivilAllData
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TowerAndCivilInfraTower
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tower.TowerInfoFragment

class TowerPageAdapter (fm: FragmentManager, var list:ArrayList<TowerAndCivilInfraTower>?, var fullData: NewTowerCivilAllData?) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return list?.size!!
    }

    override fun getItem(position: Int): Fragment {
        return TowerInfoFragment(list?.get(position),fullData,position)
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return "Tower${position+1}"


    }

}
