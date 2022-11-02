package com.smarthub.baseapplication.ui.utilites


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.databinding.Spd1TabUtilitiesFragmentBinding
import com.smarthub.baseapplication.ui.site_lease_acquisition.fragment.*
import com.smarthub.baseapplication.ui.utilites.fragment.*

class UtilitiesNocAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 5
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return AC1UtilitesFrag()
            }
            1 -> {
                return BatteryBank1TabUtilitesFrag()
            }
            2 -> {
                return FireExtinguisher1UtilitesFrag()
            }
            3 -> {
                return DG1TabUtilitesFrag()
            }
            else -> {
                return SPD1TabUtilitesFrag()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "AC"
            }
            1 -> {
                return "Battery Bank"
            }
            2 -> {
                return "Fire Extinguisher"
            }
            3 -> {
                return "DG"
            }
            4 -> {
                return "SPD1"
            }
        }
        return super.getPageTitle(position)
    }

}