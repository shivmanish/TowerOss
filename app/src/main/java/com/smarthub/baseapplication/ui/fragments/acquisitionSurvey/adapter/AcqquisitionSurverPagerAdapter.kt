package com.smarthub.baseapplication.ui.fragments.acquisitionSurvey.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.serviceRequest.acquisitionSurvey.AcquisitionSurveyModel
import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestActivity
import com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment.AcquisitionSurveyFragment
import com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment.AssignACQTeamFragment
import com.smarthub.baseapplication.ui.site_agreement.fragment.Agreements

class AcqquisitionSurverPagerAdapter (manager: FragmentManager, var data : AcquisitionSurveyModel) : FragmentPagerAdapter(manager) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> AssignACQTeamFragment(data, ServicesRequestActivity.Id!!)
            1-> AcquisitionSurveyFragment(data, ServicesRequestActivity.Id!!)
            2 -> Agreements(data, ServicesRequestActivity.Id!!)
            else -> AssignACQTeamFragment(data, ServicesRequestActivity.Id!!)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            1 -> {
                return "Assign ACQ Team"
            }
            2 -> {
                return "Acquisition Survery"
            }

//            3-> {
//                return "Soft Acquisition"
//            }
            3 -> {
                return "Site Agreements"
            }

        }
        return super.getPageTitle(position)
    }

}