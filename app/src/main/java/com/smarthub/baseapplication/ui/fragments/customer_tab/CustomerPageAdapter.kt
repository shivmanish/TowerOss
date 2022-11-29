package com.smarthub.baseapplication.ui.fragments.customer_tab


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.ui.fragments.customer_tab.rfAntina.RfAntinaFragment
import com.smarthub.baseapplication.ui.fragments.customer_tab.backhaul.BackhaulFragment

class CustomerPageAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 4
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
        }
        return super.getPageTitle(position)
    }

}