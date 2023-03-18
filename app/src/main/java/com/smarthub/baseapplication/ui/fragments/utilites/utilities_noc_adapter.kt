package com.smarthub.baseapplication.ui.utilites


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.ui.utilites.fragment.SMPS2UitilitiesFrag

class UtilitiesNocAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> SMPS2UitilitiesFrag()
            1 -> SMPS2UitilitiesFrag()
            else -> SMPS2UitilitiesFrag()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> return "SMPS1"
            1 -> return "SMPS2"

        }
        return super.getPageTitle(position)
    }

}