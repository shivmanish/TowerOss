package com.smarthub.baseapplication.ui.utilites.adapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.ui.utilites.fragment.*

class SMPSViewpagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                val smpsfragone = SMPS1UitilitiesFrag()
                return smpsfragone
            }
            1 -> {
                val smpsfragtwo = SMPS2UitilitiesFrag()
                return smpsfragtwo
            }
            else -> {
                val smpsfragone = SMPS1UitilitiesFrag()
                return smpsfragone
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return "SMPS #1"
            }
            1 -> {
                return "SMPS #2"
            }
        }
        return super.getPageTitle(position)
    }

}