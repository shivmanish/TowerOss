package com.smarthub.baseapplication.ui.site_agreement


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment.AssignACQTeamFragment
import com.smarthub.baseapplication.ui.site_agreement.fragment.*
import com.smarthub.baseapplication.ui.site_agreement.siteagreements_tab.SANomonalsFrag

class SiteLeaseAcquisitionAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 1
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return SANomonalsFrag()
            }
            1 -> {
                return Payment()
            }

            else -> {
                return SANomonalsFrag()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Nominals"
            }
            1 -> {
                return "Payments"
            }

        }
        return super.getPageTitle(position)
    }

}