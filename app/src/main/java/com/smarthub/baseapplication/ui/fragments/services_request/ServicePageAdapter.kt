package com.smarthub.baseapplication.ui.fragments.services_request

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.smarthub.baseapplication.ui.fragments.customer_tab.OpcoSiteInfoFramgment
import com.smarthub.baseapplication.ui.fragments.customer_tab.RfEquipmentFragment
import com.smarthub.baseapplication.ui.fragments.customer_tab.backhaul.BackhaulFragment
import com.smarthub.baseapplication.ui.fragments.customer_tab.powerload.PowerLoadFragment
import com.smarthub.baseapplication.ui.fragments.customer_tab.rfAntina.RfAntinaFragment
class ServicePageAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    override fun getCount(): Int {
        return 5
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return OpcoSiteInfoFramgment()
            }
//            1 -> {
//                return CommercialFragment()
//            }
            1 -> {
                return RfEquipmentFragment()
            }
            2 -> {
                return BackhaulFragment()
            }
            3 -> {
                return RfAntinaFragment()
            }
            4 -> {
                return PowerLoadFragment()
            }
            else -> {
                return OpcoSiteInfoFramgment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "OPCO Info"
            }
//            1 -> {
//                return "Commercials"
//            }
            1 -> {
                return "RF Equipment"
            }
            2 -> {
                return "Backhaul"
            }
            3 -> {
                return "RF Anteena"
            }
            4 -> {
                return "Power Load"
            }
        }
        return super.getPageTitle(position)
    }

}