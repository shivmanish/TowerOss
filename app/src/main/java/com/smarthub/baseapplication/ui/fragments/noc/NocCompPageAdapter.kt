package com.smarthub.baseapplication.ui.fragments.noc


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocCompAllData
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.OpcoTenencyAllData
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.radioAntenna.NewRfAntinaFragment

class NocCompPageAdapter(fm:FragmentManager, var nocdata: NocCompAllData?) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 4

    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return NocCompFragment(nocdata)
            }

            1 -> {
                return NocCompFragment(nocdata)
            }

            2 -> {
                return NocCompFragment(nocdata)
            }
            3 -> {
                return NocCompFragment(nocdata)
            }
            else -> {
                return NocCompFragment(nocdata)
            }

        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Application Details"
            }

            1 -> {
                return "Authority Details"
            }
            2 -> {
                return "PO Details"
            }
            3 -> {
                return "Fee & Payment Details"
            }
        }
        return super.getPageTitle(position)
    }

}