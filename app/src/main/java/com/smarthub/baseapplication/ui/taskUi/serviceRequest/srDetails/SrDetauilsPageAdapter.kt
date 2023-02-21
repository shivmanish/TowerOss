package com.smarthub.baseapplication.ui.taskUi.serviceRequest.srDetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.taskModel.dropdown.TabItemData
import com.smarthub.baseapplication.ui.dynamic.TitleItem
import com.smarthub.baseapplication.ui.fragments.siteInfo.DynamicSiteFragment
import com.smarthub.baseapplication.ui.taskUi.TaskDynamicSiteFragment

class SrDetauilsPageAdapter (fm: FragmentManager, var list:List<TitleItem>) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Fragment {
        return TaskDynamicSiteFragment(list[position],position)
    }


    override fun getPageTitle(position: Int): CharSequence {
        return list[position].title
    }

}
