package com.smarthub.baseapplication.ui.customer


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class CustomerPageAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return CustomerInfo()
            }
            1 -> {
                return CustomerInfo()
            }
            2 -> {
                return CustomerInfo()
            }
            3 -> {
                return CustomerInfo()
            }
            else -> {
                return CustomerInfo()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "OPCO Info"
            }
            1 -> {
                return "Commercials"
            }
            2 -> {
                return "RF Equipment"
            }
            3 -> {
                return "Backhaul"
            }
        }
        return super.getPageTitle(position)
    }

}