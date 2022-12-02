package com.smarthub.baseapplication.ui.utilites


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.databinding.Spd1TabUtilitiesFragmentBinding
import com.smarthub.baseapplication.ui.site_lease_acquisition.fragment.*
import com.smarthub.baseapplication.ui.utilites.fragment.*

class UtilitiesNocAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return SMPS1UitilitiesFrag()
            }
            1 -> {
                return SMPS2UitilitiesFrag()
            }
            else -> {
                return SMPS1UitilitiesFrag()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "SMPS1"
            }
            1 -> {
                return "SMPS2"
            }

        }
        return super.getPageTitle(position)
    }

}