package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.ui.fragments.customer_tab.OpcoSiteInfoFramgment
import com.smarthub.baseapplication.ui.fragments.customer_tab.RfEquipmentFragment

class TowerPageAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return OpcoSiteInfoFramgment()
            }
            1 -> {
                return RfEquipmentFragment()
            }
            else -> {
                return OpcoSiteInfoFramgment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Tower1"
            }
            1 -> {
                return "Tower2"
            }


        }
        return super.getPageTitle(position)
    }

}