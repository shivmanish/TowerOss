package com.smarthub.baseapplication.ui.fragments.task.adapter


import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.taskModel.TaskInfoItem
import com.smarthub.baseapplication.ui.fragments.task.ActivityCaptureSiteFragment
import com.smarthub.baseapplication.ui.fragments.task.EmptyFragment
import com.smarthub.baseapplication.ui.fragments.task.PhotoDocumentFragment

class TaskViewpagerAdapter(fm: FragmentManager,var taskInfoData: TaskInfoItem?,var siteId:String?,var context:Context) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                val photoDocumentFragment = PhotoDocumentFragment(taskInfoData)
                photoDocumentFragment
            }
            1 -> {
                if (siteId!=null && siteId!=""){
                    ActivityCaptureSiteFragment()
                }
                else
                    EmptyFragment()
            }
            else -> {
                val photoDocumentFragment = PhotoDocumentFragment(taskInfoData)
                photoDocumentFragment
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