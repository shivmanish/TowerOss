package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TowerEarthingAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                TowerEarthingInfoFragment()
            }
            1 -> {
                TowerEarthingInfoFragment()
            }
            else -> {
                TowerEarthingInfoFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Earthing"
            }
            1 -> {
                return "Earthing 2"
            }


        }
        return super.getPageTitle(position)
    }

}