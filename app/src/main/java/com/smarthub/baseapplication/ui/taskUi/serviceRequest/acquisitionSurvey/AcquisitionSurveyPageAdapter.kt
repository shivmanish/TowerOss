package com.smarthub.baseapplication.ui.taskUi.serviceRequest.acquisitionSurvey

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.ui.fragments.task.task_tab.TaskOPCOTabFragment

class AcquisitionSurveyPageAdapter (fm: FragmentManager, var id:String?) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return TaskOPCOTabFragment(id!!)
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return "Tower${position+1}"


    }

}
