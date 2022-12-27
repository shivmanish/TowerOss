package com.smarthub.baseapplication.ui.site_agreement


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment.AssignACQTeamFragment
import com.smarthub.baseapplication.ui.site_agreement.fragment.*

class SiteLeaseAcquisitionAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return Nominals()
            }
            1 -> {
                return TeamVendorFragment()
            }
            2 -> {
                return Feasibility()
            }
            else -> {
                return Agreements()
            }
           /* else -> {
                return Payment()
            }*/
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
                return "Payments"
            }
        }
        return super.getPageTitle(position)
    }

}