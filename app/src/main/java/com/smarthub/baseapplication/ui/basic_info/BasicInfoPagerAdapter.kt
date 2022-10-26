package com.smarthub.baseapplication.ui.basic_info


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.ui.basic_info.fragment.BasicInfo

class BasicInfoPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return BasicInfo()
            }
            1 -> {
                return BasicInfo()
            }
            2 -> {
                return BasicInfo()
            }
            3 -> {
                return BasicInfo()
            }
            else -> {
                return BasicInfo()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Basic Info"
            }
            1 -> {
                return "Operational Info"
            }
            2 -> {
                return "Geo Conditions"
            }
            3 -> {
                return "Safaty/Access"
            }
        }
        return super.getPageTitle(position)
    }

}