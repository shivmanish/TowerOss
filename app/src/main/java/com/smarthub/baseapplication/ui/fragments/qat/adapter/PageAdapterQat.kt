package com.smarthub.baseapplication.ui.fragments.qat.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.ui.fragments.customer_tab.faq.Fragment2_faq
import com.smarthub.baseapplication.ui.fragments.qat.QatNestedItemFragment


class PageAdapterQat(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    private val pageTitles = arrayOf("Electrical/Civil Material","Category 2")
    override fun getCount(): Int {
        return pageTitles.size
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> {return QatNestedItemFragment() }
            1 -> {return QatNestedItemFragment() }
            else -> {return QatNestedItemFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> {return pageTitles[0]}
            1 -> {return pageTitles[1]}
        }
        return super.getPageTitle(position)
    }

}