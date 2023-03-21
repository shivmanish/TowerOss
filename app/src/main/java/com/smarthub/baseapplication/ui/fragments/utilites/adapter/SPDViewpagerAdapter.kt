package com.smarthub.baseapplication.ui.utilites.adapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SPDViewpagerAdapter(fm: FragmentManager, val fragmentlist:ArrayList<Fragment>, val titels:ArrayList<String> ) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return fragmentlist.size
    }

    override fun getItem(position: Int): Fragment {
       return fragmentlist.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titels.get(position)
    }

}