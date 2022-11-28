package com.smarthub.baseapplication.ui.fragments.customer_tab


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.ui.fragments.customer_tab.rfAntina.RfAntinaFragment
import com.smarthub.baseapplication.ui.fragments.customer_tab.backhaul.BackhaulFragment

class CustomerPageAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 5
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return CustomerInfo()
            }
            1 -> {
                return CommercialFragment()
            }
            2 -> {
                return RfEquipmentFragment()
            }
            3 -> {
                return BackhaulFragment()
            }
            4 -> {
                return RfAntinaFragment()
            }
            else -> {
                return CustomerInfo()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "OPCO Info"
            }
            1 -> {
                return "Commercials"
            }
            2 -> {
                return "RF Equipment"
            }
            3 -> {
                return "Backhaul"
            }
            4 -> {
                return "RF Anteena"
            }
        }
        return super.getPageTitle(position)
    }

}