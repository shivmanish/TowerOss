package com.smarthub.baseapplication.ui.fragments.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class profilePageAdapter (manager: FragmentManager) : FragmentPagerAdapter(manager) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> OfficialDetailFragment()
            1 -> OfficialDetailFragment()
            else -> OfficialDetailFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Official Details"
            }
            1 -> {
                return "Manager Info"
            }

        }
        return super.getPageTitle(position)
    }

}