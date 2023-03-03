package com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.OpcoTenencyAllData
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.BackhaulFragment
import com.smarthub.baseapplication.ui.fragments.task.ActivityCaptureSiteFragment
import com.smarthub.baseapplication.ui.fragments.task.PhotoDocumentFragment

class BckhaulTabFragAdapter(fm: FragmentManager, var opcoData: OpcoTenencyAllData?) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                val photoDocumentFragment = BackhaulFragment(opcoData)
                photoDocumentFragment
            }
            1 -> {
                val capturefragment = BackhaulFragment(opcoData)
                capturefragment
            }
            else -> {
                val photoDocumentFragment = BackhaulFragment(opcoData)
                photoDocumentFragment
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return "Equipment Details"
            }
            1 -> {
                return "Cable Details"
            }
        }
        return super.getPageTitle(position)
    }

}