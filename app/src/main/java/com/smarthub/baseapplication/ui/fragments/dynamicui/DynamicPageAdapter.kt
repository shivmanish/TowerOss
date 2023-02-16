package com.smarthub.baseapplication.ui.fragments.dynamicui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class DynamicPageAdapter (fm: FragmentManager, var fragmentList :java.util.ArrayList<FragmentPojo>) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position).fragment!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
       return fragmentList.get(position).titel!!
    }

}