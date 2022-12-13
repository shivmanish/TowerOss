package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.smarthub.baseapplication.ui.fragments.customer_tab.OpcoSiteInfoFramgment
import com.smarthub.baseapplication.ui.fragments.customer_tab.RfEquipmentFragment
import com.smarthub.baseapplication.ui.fragments.customer_tab.backhaul.BackhaulFragment
import com.smarthub.baseapplication.ui.fragments.customer_tab.powerload.PowerLoadFragment
import com.smarthub.baseapplication.ui.fragments.customer_tab.rfAntina.RfAntinaFragment
import com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment.OppoTssrTabFragment
import com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment.SPApprovalTabFragment
import com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment.ServiceRequestTabFragment
import com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment.SoftAcquisitionTabFragment

class ServicePageAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    override fun getCount(): Int {
        return 6
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return ServiceRequestTabFragment()
            }
//            1 -> {
//                return CommercialFragment()
//            }
            1 -> {
                return OppoTssrTabFragment()
            }
            2 -> {
                return BackhaulFragment()
            }
            3 -> {
                return RfAntinaFragment()
            }
            4 -> {
                return SPApprovalTabFragment()
            }
            5 -> {
                return SoftAcquisitionTabFragment()
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
//            1 -> {
//                return "Commercials"
//            }
            1 -> {
                return "OPCO TSSR"
            }
            2 -> {
                return "Feasibility Plan"
            }
            3 -> {
                return "Site Proposal"
            }
            4 -> {
                return "Site Proposal"
            }  5 -> {
                return "SP Approval / SO"
            } 6 -> {
                return "Soft Acquisition"
            }
        }
        return super.getPageTitle(position)
    }

}