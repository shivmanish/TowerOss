package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.FilterdTwrData
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerAndCivilInfraTowerModel

class TowerPageAdapter (fm: FragmentManager, var list:ArrayList<FilterdTwrData>?, var id:String?) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return list?.size!!
    }

    override fun getItem(position: Int): Fragment {
        return TowerInfoFragment(list?.get(position))
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return "Tower${position+1}"


    }

}
