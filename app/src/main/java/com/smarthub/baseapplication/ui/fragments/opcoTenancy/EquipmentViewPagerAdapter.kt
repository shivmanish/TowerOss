package com.smarthub.baseapplication.ui.fragments.opcoTenancy


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.OpcoTenencyAllData
import com.smarthub.baseapplication.ui.fragments.task.ActivityCaptureSiteFragment
import com.smarthub.baseapplication.ui.fragments.task.PhotoDocumentFragment

class EquipmentViewPagerAdapter(fm: FragmentManager,var opcoData: OpcoTenencyAllData?) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                val photoDocumentFragment = RfEquipmentFragment(opcoData)
                photoDocumentFragment
            }
            1 -> {
                val capturefragment = CableDetailFragment(opcoData)
                capturefragment
            }
            else -> {
                val photoDocumentFragment = RfEquipmentFragment(opcoData)
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