package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TowerPoleFragmentAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return TowerPoleInfoFragment()
            }
            1 -> {
                return TowerPoleInfoFragment()
            }
            else -> {
                return TowerPoleInfoFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Equipment Room 1"
            }
            1 -> {
                return "Equipment Room 2"
            }


        }
        return super.getPageTitle(position)
    }

}