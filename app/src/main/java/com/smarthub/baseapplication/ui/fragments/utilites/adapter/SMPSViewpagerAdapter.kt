package com.smarthub.baseapplication.ui.fragments.utilites.adapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentAllData
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentSmp
import com.smarthub.baseapplication.ui.fragments.utilites.fragment.SMPS1UitilitiesFrag

class SMPSViewpagerAdapter(fm: FragmentManager, var list:ArrayList<UtilityEquipmentSmp>?,var smpsAllDataId: Int?) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
         return list?.size!!
    }

    override fun getItem(position: Int): Fragment {
        val smpsfragone = SMPS1UitilitiesFrag(list?.get(position),position,smpsAllDataId)
        return smpsfragone
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "SMPS #${position+1}"

    }

}