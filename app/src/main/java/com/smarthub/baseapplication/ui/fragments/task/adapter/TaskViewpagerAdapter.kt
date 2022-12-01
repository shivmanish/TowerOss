package com.smarthub.baseapplication.ui.fragments.task.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.ui.fragments.task.ActivityCaptureSiteFragment
import com.smarthub.baseapplication.ui.fragments.task.PhotoDocumentFragment

class TaskViewpagerAdapter(fm: FragmentManager,var listener: PhotoDocumentFragment.PhotoDocumentListener) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                val photoDocumentFragment = PhotoDocumentFragment(listener)
                return photoDocumentFragment
            }
            1 -> {
                val capturefragment = ActivityCaptureSiteFragment()
                return capturefragment
            }
            else -> {
                val photoDocumentFragment = PhotoDocumentFragment(listener)
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