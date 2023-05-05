package com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tabFragments.*

class SiteAcquisitionTaskTabAdapter(manager: FragmentManager, var data: NewSiteAcquiAllData?, var parentIndex:Int,var list:ArrayList<String>): FragmentPagerAdapter(manager) {


    override fun getCount(): Int {
        return list.size
    }


    override fun getItem(position: Int): Fragment {
        when(list[position].replace(" ","")) {
            "21" -> {
                return AssignACQTeamFragment(data, parentIndex)
            }
            "22" -> {
               return AcquisitionSurveyFragment(data,parentIndex)
            }
            "23"-> {
              return SiteFeasibilityFragment(data,parentIndex)
            }
            "24"-> {
              return SoftAcquisitionFragment(data,parentIndex)
            }
            "25"-> {
              return AgreementFragment(data,parentIndex)
            }
            "26"-> {
              return SurveyApprovalFragment(data,parentIndex)
            }
            else -> {
                return AssignACQTeamFragment(data,parentIndex)
            }
        }
    }


    override fun getPageTitle(position: Int): CharSequence? {
        when(list[position].replace(" ","")) {
            "21" -> {
                return "Assign ACQ Team"
            }
            "22" -> {
                return "Acquisition Survey"
            }
            "23" -> {
                return "Soft Acquisition"
            }
            "24" -> {
                return "Agreement"
            }
        }
        return super.getPageTitle(position)
    }

}