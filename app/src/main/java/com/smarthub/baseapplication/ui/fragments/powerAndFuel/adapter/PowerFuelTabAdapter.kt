package com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.NewPowerFuelAllData
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.fragment.*
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.pojo.PowerAndFuel

class PowerFuelTabAdapter(manager: FragmentManager): FragmentPagerAdapter(manager) {

    var data: NewPowerFuelAllData?=null

    override fun getCount(): Int {
        return 3
    }

    fun setPowerFuelData(data: NewPowerFuelAllData?){
        this.data=data
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return PowerConnectionFragment(data)
            }
            1 -> {
               return PowerFuelBillsFragment(data)
            }
            2-> {
              return PowerConnectionFragment(data)
            }
            else -> {
                return PowerConnectionFragment(data)
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
                return "EB Payments"
            }
        }
        return super.getPageTitle(position)
    }

}