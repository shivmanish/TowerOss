package com.smarthub.baseapplication.ui.utilites.adapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteInfo.utilitiesEquip.BatteryBank
import com.smarthub.baseapplication.model.siteInfo.utilitiesEquip.UtilitieSmp
import com.smarthub.baseapplication.ui.utilites.fragment.BatteryFragment

class BatteryViewpagerAdapter(fm: FragmentManager, var list:ArrayList<BatteryBank>?, var id:String) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return list?.size!!
    }

    override fun getItem(position: Int): Fragment {
       return BatteryFragment(list?.get(position),id)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Battery Bank #${position+1}"
    }

}