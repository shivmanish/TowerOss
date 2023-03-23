package com.smarthub.baseapplication.ui.fragments.utilites.fireExtinguisher.adapters
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentFireExtinguisher
import com.smarthub.baseapplication.ui.fragments.utilites.fireExtinguisher.FireExtinguisherFragment

class FireExtViewpagerAdapter(fm: FragmentManager, var list:ArrayList<UtilityEquipmentFireExtinguisher>?, var fireExtAllDataId: Int? ) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return list?.size!!
    }

    override fun getItem(position: Int): Fragment {
        return FireExtinguisherFragment(list?.get(position), position, fireExtAllDataId)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "Fire Extinguisher #${position+1}"

    }

}