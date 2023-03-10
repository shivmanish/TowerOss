package com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackhaulLinkData

class MwUbrTabAdapter(manager: FragmentManager, var data: BackhaulLinkData?,var parentIndex:Int): FragmentPagerAdapter(manager) {


    override fun getCount(): Int {
        return 5
    }


    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return MwUbrLinkInfoFragment(data, parentIndex)
            }
            1 -> {
               return MwUbrIDUFragment(data,parentIndex)
            }
            2-> {
              return MwUbrODUFragment(data,parentIndex)
            }
            3-> {
              return MwUbrAntennaFragment(data,parentIndex)
            }
            4-> {
              return MwUbrCablesFragment(data,parentIndex)
            }
            else -> {
                return MwUbrLinkInfoFragment(data,parentIndex)
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Link info"
            }
            1 -> {
                return "IDU"
            }
            2 -> {
                return "ODU"
            }
            3 -> {
                return "Antenna"
            }
            4 -> {
                return "Cables"
            }
        }
        return super.getPageTitle(position)
    }

}