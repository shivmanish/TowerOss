package com.smarthub.baseapplication.ui.fragments.utilites.ac.adapters
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentAC
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentDG
import com.smarthub.baseapplication.ui.fragments.utilites.ac.ACFragment
import com.smarthub.baseapplication.ui.fragments.utilites.dg.DGFragment

class ACViewpagerAdapter(fm: FragmentManager, var list:ArrayList<UtilityEquipmentAC>?, var smpsAllDataId: Int? ) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return list?.size!!
    }

    override fun getItem(position: Int): Fragment {
        return ACFragment(list?.get(position), position, smpsAllDataId)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "AC #${position+1}"

    }

}