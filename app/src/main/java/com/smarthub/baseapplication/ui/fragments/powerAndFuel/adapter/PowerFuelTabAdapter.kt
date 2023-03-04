package com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.NewPowerFuelAllData
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.fragment.*
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.pojo.PowerAndFuel

class PowerFuelTabAdapter(manager: FragmentManager,var data: NewPowerFuelAllData?,var parentIndex:Int): FragmentPagerAdapter(manager) {


    override fun getCount(): Int {
        return 3
    }


    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return PowerConnectionFragment(data, parentIndex)
            }
            1 -> {
               return PowerFuelBillsFragment(data,parentIndex)
            }
            2-> {
              return PowerFuelBillPaymentsFragment(data,parentIndex)
            }
            else -> {
                return PowerConnectionFragment(data,parentIndex)
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Power Connection"
            }
            1 -> {
                return "Power Bills"
            }
            2 -> {
                return "Bill Payments"
            }
        }
        return super.getPageTitle(position)
    }

}