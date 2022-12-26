package com.smarthub.baseapplication.ui.fragments.opcoTenancy


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem

class OpcoTenancyPageAdapter(fm:FragmentManager, var opcodata: OpcoDataItem?) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 5

    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return OpcoSiteInfoFramgment(opcodata!!)
            }

            1 -> {
                return RfEquipmentFragment(opcodata!!)
            }
            2 -> {
                return BackhaulFragment(opcodata!!)
            }
            3 -> {
                return RfAntinaFragment(opcodata!!)
            }
            4 -> {
                return PowerLoadFragment(opcodata!!)
            }
            else -> {
                return OpcoSiteInfoFramgment(opcodata!!)
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