package com.smarthub.baseapplication.ui.taskUi.serviceRequest.srDetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.ui.fragments.siteInfo.DynamicSiteFragment
import com.smarthub.baseapplication.ui.taskUi.TaskDynamicSiteFragment

class SrDetauilsPageAdapter (fm: FragmentManager,var list:List<String>,var slectedTabs:ArrayList<String>) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Fragment {
        return TaskDynamicSiteFragment(slectedTabs,position)
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return list[position]
    }

}
