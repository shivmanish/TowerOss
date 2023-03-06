package com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.NewPowerFuelAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.fragment.PowerConnectionFragment
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.fragment.PowerFuelBillPaymentsFragment
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.fragment.PowerFuelBillsFragment
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tabFragments.AssignACQTeamFragment

class SiteAcquisitionTabAdapter(manager: FragmentManager, var data: NewSiteAcquiAllData?, var parentIndex:Int): FragmentPagerAdapter(manager) {


    override fun getCount(): Int {
        return 4
    }


    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return AssignACQTeamFragment(data, parentIndex)
            }
            1 -> {
               return AssignACQTeamFragment(data,parentIndex)
            }
            2-> {
              return AssignACQTeamFragment(data,parentIndex)
            }
            3-> {
              return AssignACQTeamFragment(data,parentIndex)
            }
            else -> {
                return AssignACQTeamFragment(data,parentIndex)
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Assign ACQ Team"
            }
            1 -> {
                return "Acquisition Survey"
            }
            2 -> {
                return "Soft Acquisition"
            }
            3 -> {
                return "Agreement"
            }
        }
        return super.getPageTitle(position)
    }

}