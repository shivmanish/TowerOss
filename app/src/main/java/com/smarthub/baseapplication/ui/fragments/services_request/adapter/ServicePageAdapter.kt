package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem

import com.smarthub.baseapplication.ui.fragments.opcoTenancy.OpcoSiteInfoFramgment
import com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment.*
import com.smarthub.baseapplication.ui.site_agreement.fragment.Agreements
import com.smarthub.baseapplication.ui.site_agreement.fragment.Feasibility
import com.smarthub.baseapplication.ui.site_agreement.fragment.TeamVendor

class ServicePageAdapter(manager: FragmentManager,var data : ServiceRequestAllDataItem) : FragmentPagerAdapter(manager) {
    override fun getCount(): Int {
        return 8
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> return ServiceRequestTabFragment(data)
            1 -> return TeamVendor()
            2-> return Feasibility()
            3 -> return OppoTssrTabFragment()
            4 -> return FeasibilityPlanningTabFragment()
            5 -> return Agreements()
            6-> return SiteProposalTabFragment()
            7 -> return SPApprovalTabFragment()
            else -> return ServiceRequestTabFragment(data)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Site Request"
            }
            1 -> {
                return "Assign ACQ Team"
            }
            2 -> {
                return "Acquisition Survery"
            }
            3 -> {
                return "OPCO TSSR"
            }
            4 -> {
                return "Feasibility Plan"
            }
            5-> {
                return "Soft Acquisition"
            }
            6 -> {
                return "Site Proposal"
            }

            7-> {
                return "SP Approval / SO"
            }
        }
        return super.getPageTitle(position)
    }

}