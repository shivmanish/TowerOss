package com.smarthub.baseapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.fragments.Fragment2_faq
import com.smarthub.baseapplication.fragments.Fragmentfaq_Demo_faq


class PageAdapter_faq(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    private val pageTitles = arrayOf("Ventos","Catering","Cancellations","Reservations","ABC","XYZ")
    override fun getCount(): Int {
        return pageTitles.size
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> {return Fragmentfaq_Demo_faq() }
            1 -> {return Fragment2_faq() }
            2 -> {return Fragmentfaq_Demo_faq()}
            else -> {return Fragmentfaq_Demo_faq()}
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> {return pageTitles[0]}
            1 -> {return pageTitles[1]}
            2 -> {return pageTitles[3]}
            else -> {return pageTitles[4]}
        }
        return super.getPageTitle(position)
    }

}