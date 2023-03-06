package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem

import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestActivity.Companion.Id
import com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment.*
import com.smarthub.baseapplication.ui.site_agreement.fragment.Agreements
import com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment.AssignACQTeamFragment

class ServicePageAdapter(manager: FragmentManager,var data : ServiceRequestAllDataItem) : FragmentPagerAdapter(manager) {
    override fun getCount(): Int {
        return 5
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> ServiceRequestTabFragment(data,Id!!)
//            1 -> AssignACQTeamFragment(data,Id!!)
//            2-> AcquisitionSurveyFragment(data,Id!!)
            1 -> OppoTssrTabFragment(data,Id!!)
            2 -> FeasibilityPlanningTabFragment(data,Id!!)
//            5 -> Agreements(data,Id!!)
            3-> SiteProposalTabFragment()
            4 -> SPApprovalTabFragment(data,Id!!)
            else -> ServiceRequestTabFragment(data, Id!!)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Site Request"
            }
//            1 -> {
//                return "Assign ACQ Team"
//            }
//            2 -> {
//                return "Acquisition Survery"
//            }
            1 -> {
                return "OPCO TSSR"
            }
            2 -> {
                return "Feasibility Plan"
            }
//            5-> {
//                return "Soft Acquisition"
//            }
            3 -> {
                return "Site Proposal"
            }

            4-> {
                return "SP Approval / SO"
            }
        }
        return super.getPageTitle(position)
    }

}