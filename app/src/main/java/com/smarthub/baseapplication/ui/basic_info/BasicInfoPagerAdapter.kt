package com.smarthub.baseapplication.ui.basic_info


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.ui.basic_info.fragment.BasicInfo
import com.smarthub.baseapplication.ui.basic_info.fragment.GeoConditionalInfo
import com.smarthub.baseapplication.ui.basic_info.fragment.OperationalInfo
import com.smarthub.baseapplication.ui.basic_info.fragment.SafatyAccessFragment

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
                return OperationalInfo()
            }
            2 -> {
                return GeoConditionalInfo()
            }
            3 -> {
                return SafatyAccessFragment()
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