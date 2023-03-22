package com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tabFragments.AcquisitionSurveyFragment
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tabFragments.AgreementFragment
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tabFragments.AssignACQTeamFragment
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tabFragments.SoftAcquisitionFragment

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
               return AcquisitionSurveyFragment(data,parentIndex)
            }
            2-> {
              return SoftAcquisitionFragment(data,parentIndex)
            }
            3-> {
              return AgreementFragment(data,parentIndex)
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