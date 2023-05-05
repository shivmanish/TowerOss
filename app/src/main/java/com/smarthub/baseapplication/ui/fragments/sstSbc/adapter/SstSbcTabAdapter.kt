package com.smarthub.baseapplication.ui.fragments.sstSbc.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcAllData
import com.smarthub.baseapplication.ui.fragments.sstSbc.tabFragments.RfSurveyValidationFragment
import com.smarthub.baseapplication.ui.fragments.sstSbc.tabFragments.SstSbcReportFragment
import com.smarthub.baseapplication.ui.fragments.sstSbc.tabFragments.SstSbcTeamFragment

class SstSbcTabAdapter(manager: FragmentManager, var data: SstSbcAllData?, var parentIndex:Int): FragmentPagerAdapter(manager) {


    override fun getCount(): Int {
        return 3
    }


    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                SstSbcTeamFragment(data, parentIndex)
            }
            1 -> {
                SstSbcReportFragment(data,parentIndex)
            }
            2 -> {
                RfSurveyValidationFragment(data,parentIndex)
            }
            else -> {
                SstSbcTeamFragment(data,parentIndex)
            }
        }
    }


    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Assign Team"
            }
            1 -> {
                return "Test Report"
            }
            2 -> {
                return "Rf Survey Validation"
            }
        }
        return super.getPageTitle(position)
    }

}