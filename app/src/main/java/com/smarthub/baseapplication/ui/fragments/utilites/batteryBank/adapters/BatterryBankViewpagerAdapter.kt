package com.smarthub.baseapplication.ui.fragments.utilites.batteryBank.adapters
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentBatteryBank
import com.smarthub.baseapplication.ui.fragments.utilites.batteryBank.BatteryFragment

class BatterryBankViewpagerAdapter(fm: FragmentManager, var list:ArrayList<UtilityEquipmentBatteryBank>?, var smpsAllDataId: Int?) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
         return list?.size!!
    }

    override fun getItem(position: Int): Fragment {
        return BatteryFragment(list?.get(position), position, smpsAllDataId)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "Battery Bank #${position+1}"

    }

}