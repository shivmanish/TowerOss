package com.smarthub.baseapplication.ui.fragments.noc


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocCompAllData

class NocCompPageAdapter(fm:FragmentManager, var nocdata: NocCompAllData?,var childIndex:Int?) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 4

    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return NocDetailsFragment(nocdata,childIndex)
            }

            1 -> {
                return NocAuthorityDetailsFragment(nocdata,childIndex)
            }

            2 -> {
                return NocPoDetailsFragment(nocdata,childIndex)
            }
            3 -> {
                return NocFeePaymentFragment(nocdata,childIndex)
            }
            else -> {
                return NocDetailsFragment(nocdata,childIndex)
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