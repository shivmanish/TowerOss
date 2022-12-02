package com.smarthub.baseapplication.ui.utilites.adapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.ui.utilites.fragment.BatteryFragment

class BatteryViewpagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                val batteryfragone = BatteryFragment()
                return batteryfragone
            }
            1 -> {
                val batteryfragtwo = BatteryFragment()
                return batteryfragtwo
            }
            else -> {
                val batteryfragone = BatteryFragment()
                return batteryfragone
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return "Battery Bank#1"
            }
            1 -> {
                return "Battery Bank#2"
            }
        }
        return super.getPageTitle(position)
    }

}