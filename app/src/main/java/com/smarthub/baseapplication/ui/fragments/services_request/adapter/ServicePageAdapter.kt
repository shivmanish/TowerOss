package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.smarthub.baseapplication.ui.fragments.customer_tab.OpcoSiteInfoFramgment
import com.smarthub.baseapplication.ui.fragments.customer_tab.backhaul.BackhaulFragment
import com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment.*
import com.smarthub.baseapplication.ui.site_lease_acquisition.fragment.Agreements
import com.smarthub.baseapplication.ui.site_lease_acquisition.fragment.Feasibility
import com.smarthub.baseapplication.ui.site_lease_acquisition.fragment.TeamVendor

class ServicePageAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    override fun getCount(): Int {
        return 8
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return ServiceRequestTabFragment()
            }
            1 -> {
                return OppoTssrTabFragment()
            }
            2 -> {
                return TeamVendor()
            }
            3-> {
                return Feasibility()
            }
            4 -> {
                return FeasibilityPlanningTabFragment()
            }
            5 -> {
                return SiteProposalTabFragment()
            }
            6-> {
                return SPApprovalTabFragment()
            }
            7 -> {
                return Agreements()
            }

            else -> {
                return OpcoSiteInfoFramgment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Site Request"
            }

            1 -> {
                return "OPCO TSSR"
            }
            2 -> {
                return "Assign ACQ Team"
            }
            3 -> {
                return "Acquisition Survery"
            }
            4 -> {
                return "Feasibility Plan"
            }
            5 -> {
                return "Site Proposal"
            }
            6 -> {
                return "SP Approval / SO"
            }
            7-> {
                return "Soft Acquisition"
            }



        }
        return super.getPageTitle(position)
    }

}