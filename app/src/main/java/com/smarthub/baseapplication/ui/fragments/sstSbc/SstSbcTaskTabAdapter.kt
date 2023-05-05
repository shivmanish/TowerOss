package com.smarthub.baseapplication.ui.fragments.sstSbc

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcAllData
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tabFragments.AcquisitionSurveyFragment
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tabFragments.AgreementFragment
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tabFragments.AssignACQTeamFragment
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tabFragments.SoftAcquisitionFragment
import com.smarthub.baseapplication.ui.fragments.sstSbc.tabFragments.RfSurveyValidationFragment
import com.smarthub.baseapplication.ui.fragments.sstSbc.tabFragments.SstSbcReportFragment
import com.smarthub.baseapplication.ui.fragments.sstSbc.tabFragments.SstSbcTeamFragment

class SstSbcTaskTabAdapter(manager: FragmentManager, var data: SstSbcAllData?, var parentIndex:Int?, var list:ArrayList<String>): FragmentPagerAdapter(manager) {


    override fun getCount(): Int {
        return list.size
    }


    override fun getItem(position: Int): Fragment {
        return when(list[position].replace(" ","")) {
            "101" -> {
                SstSbcTeamFragment(data, parentIndex)
            }
            "102" -> {
                SstSbcReportFragment(data, parentIndex)
            }
            "103" -> {
                RfSurveyValidationFragment(data, parentIndex!!)
            }
            else -> {
                SstSbcTeamFragment(data, parentIndex)
            }
        }
    }


    override fun getPageTitle(position: Int): CharSequence? {
        when(list[position].replace(" ","")) {
            "101" -> {
                return "Assign Team"
            }
            "102" -> {
                return "Test Report"
            }
            "103" -> {
                return "Rf Survey Validation"
            }
        }
        return super.getPageTitle(position)
    }

}