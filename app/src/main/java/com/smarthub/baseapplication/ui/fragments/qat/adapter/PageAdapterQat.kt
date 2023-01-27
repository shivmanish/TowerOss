package com.smarthub.baseapplication.ui.fragments.qat.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteInfo.qat.QatCardItem
import com.smarthub.baseapplication.model.siteInfo.qat.QatTemplateModel
import com.smarthub.baseapplication.ui.fragments.customer_tab.faq.Fragment2_faq
import com.smarthub.baseapplication.ui.fragments.qat.QatNestedItemFragment


class PageAdapterQat(fm:FragmentManager,var data: List<QatTemplateModel>) : FragmentPagerAdapter(fm) {
//    private val pageTitles = arrayOf("Electrical/Civil Material","Category 2")
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Fragment {
        return QatNestedItemFragment(data[position])
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> {return data[0].}
            1 -> {return pageTitles[1]}
        }
        return super.getPageTitle(position)
    }

}