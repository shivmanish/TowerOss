package com.smarthub.baseapplication.ui.utilites.adapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteInfo.utilitiesEquip.UtilitieSmp
import com.smarthub.baseapplication.ui.utilites.fragment.*

class SMPSViewpagerAdapter(fm: FragmentManager,var list:ArrayList<UtilitieSmp>?,var id:String) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return list?.size!!
    }

    override fun getItem(position: Int): Fragment {
        val smpsfragone = SMPS1UitilitiesFrag(list?.get(position),id)
        return smpsfragone
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "SMPS #${position+1}"

    }

}