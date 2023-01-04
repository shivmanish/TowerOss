package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerAndCivilInfraPoleModel

class PoleFragPageAdapter (fm: FragmentManager, var list:ArrayList<TowerAndCivilInfraPoleModel>, var id:String?) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Fragment {
        return PoleInfoFragment(list[position],id,0)
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return "Pole${position+1}"


    }

}
