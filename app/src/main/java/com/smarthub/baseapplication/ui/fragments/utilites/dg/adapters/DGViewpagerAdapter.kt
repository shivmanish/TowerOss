package com.smarthub.baseapplication.ui.fragments.utilites.dg.adapters
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentDG
import com.smarthub.baseapplication.ui.fragments.utilites.dg.DGFragment

class DGViewpagerAdapter(fm: FragmentManager, var list:ArrayList<UtilityEquipmentDG>?, var smpsAllDataId: Int? ) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return list?.size!!
    }

    override fun getItem(position: Int): Fragment {
        return DGFragment(list?.get(position), position, smpsAllDataId)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "DG #${position+1}"

    }

}