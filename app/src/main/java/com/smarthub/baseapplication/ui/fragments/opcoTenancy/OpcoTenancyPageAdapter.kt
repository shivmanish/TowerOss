package com.smarthub.baseapplication.ui.fragments.opcoTenancy


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.OpcoTenencyAllData
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.radioAntenna.NewRfAntinaFragment

class OpcoTenancyPageAdapter(fm:FragmentManager, var opcodata: OpcoTenencyAllData?) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 5

    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return OpcoSiteInfoFramgment(opcodata?.Opcoinfo)
            }

            1 -> {
                return RfEquipmentViewPagerFragment(opcodata)
            }
//            2 -> {
//                return BackhaulFragment(opcodata!!)
//            }
            3 -> {
                return NewRfAntinaFragment(opcodata)
            }
            4 -> {
                return PowerLoadFragment(opcodata)
            }
            else -> {
                return OpcoSiteInfoFramgment(opcodata?.Opcoinfo)
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
                return "Equipment"
            }
            2 -> {
                return "Backhaul Link"
            }
            3 -> {
                return "Radio Antenna & RRU"
            }
            4 -> {
                return "Power Load"
            }
        }
        return super.getPageTitle(position)
    }

}