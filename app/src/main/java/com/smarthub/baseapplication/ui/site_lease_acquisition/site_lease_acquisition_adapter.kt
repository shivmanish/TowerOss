package com.smarthub.baseapplication.ui.site_lease_acquisition


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.ui.basic_info.fragment.BasicInfo
import com.smarthub.baseapplication.ui.site_lease_acquisition.fragment.Agreements
import com.smarthub.baseapplication.ui.site_lease_acquisition.fragment.Feasibility
import com.smarthub.baseapplication.ui.site_lease_acquisition.fragment.Nominals
import com.smarthub.baseapplication.ui.site_lease_acquisition.fragment.TeamVendor

class SiteLeaseAcquisitionAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 5
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return Nominals()
            }
            1 -> {
                return TeamVendor()
            }
            2 -> {
                return Agreements()
            }
            3 -> {
                return Feasibility()
            }
            else -> {
                return Nominals()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Nominals"
            }
            1 -> {
                return "Team/ Vendor"
            }
            2 -> {
                return "Feasibility"
            }
            3 -> {
                return "Agreements"
            }
            4 -> {
                return "Nominals"
            }
        }
        return super.getPageTitle(position)
    }

}