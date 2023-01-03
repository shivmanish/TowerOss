package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem

import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestActivity.Companion.Id
import com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment.*
import com.smarthub.baseapplication.ui.site_agreement.fragment.Agreements
import com.smarthub.baseapplication.ui.site_agreement.fragment.Feasibility
import com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment.AssignACQTeamFragment

class ServicePageAdapter(manager: FragmentManager,var data : ServiceRequestAllDataItem, Id: String?) : FragmentPagerAdapter(manager) {
    override fun getCount(): Int {
        return 8
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> ServiceRequestTabFragment(data,Id!!)
            1 -> AssignACQTeamFragment(data,Id!!)
            2-> AcquisitionSurveyFragment(data,Id!!)
            3 -> OppoTssrTabFragment(data,Id!!)
            4 -> FeasibilityPlanningTabFragment(data,Id!!)
            5 -> Agreements()
            6-> SiteProposalTabFragment()
            7 -> SPApprovalTabFragment()
            else -> ServiceRequestTabFragment(data, Id!!)
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