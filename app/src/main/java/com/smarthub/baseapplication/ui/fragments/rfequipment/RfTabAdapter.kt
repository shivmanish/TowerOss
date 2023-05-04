package com.smarthub.baseapplication.ui.fragments.rfequipment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcAllData
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfSurvey
import com.smarthub.baseapplication.ui.fragments.sstSbc.tabFragments.SstSbcReportFragment
import com.smarthub.baseapplication.ui.fragments.sstSbc.tabFragments.SstSbcTeamFragment

class RfTabAdapter(manager: FragmentManager, var data: RfSurvey?, var parentIndex:Int): FragmentPagerAdapter(manager) {


    override fun getCount(): Int {
        return 3
    }


    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                AssignRfTeamFragment(data, parentIndex)
            }
            1 -> {
                AssignRfTeamFragment(data,parentIndex)
            }
            2-> AssignRfTeamFragment(data,parentIndex)
            else -> {
                AssignRfTeamFragment(data,parentIndex)
            }
        }
    }


    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Assign RF Survey Team"
            }
            1 -> {
                return "RF Survey"
            }
            2->{
                return "RF Survey Validation"
            }
        }
        return super.getPageTitle(position)
    }

}