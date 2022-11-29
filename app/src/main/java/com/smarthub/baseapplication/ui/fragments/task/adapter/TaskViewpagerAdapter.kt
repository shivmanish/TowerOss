package com.smarthub.baseapplication.ui.fragments.task.adapter


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.basic_info.fragment.BasicInfo
import com.smarthub.baseapplication.ui.basic_info.fragment.GeoConditionalInfo
import com.smarthub.baseapplication.ui.basic_info.fragment.OperationalInfo
import com.smarthub.baseapplication.ui.basic_info.fragment.SafatyAccessFragment
import com.smarthub.baseapplication.ui.fragments.task.ActivityCaptureSiteFragment
import com.smarthub.baseapplication.ui.fragments.task.PhotoDocumentFragment

class TaskViewpagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                val photoDocumentFragment = PhotoDocumentFragment()
                return photoDocumentFragment
            }
            1 -> {
                val capturefragment = ActivityCaptureSiteFragment()
                return capturefragment
            }
            else -> {
                val photoDocumentFragment = PhotoDocumentFragment()
                return photoDocumentFragment
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return "Photo/Document"
            }
            1 -> {
                return "Capture Site Data"
            }
        }
        return super.getPageTitle(position)
    }

}