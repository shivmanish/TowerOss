package com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.fragment.EbBillsFragment
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.fragment.EbConnectionFragment
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.fragment.EbPaymentFragment
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.pojo.PowerAndFuel

class PowerConneDetailActivityAdapter(manager: FragmentManager,var data: PowerAndFuel): FragmentPagerAdapter(manager) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                val ebConnectionFragment =  EbConnectionFragment()
                ebConnectionFragment.setDatavalue(data)
                return ebConnectionFragment
            }
            1 -> {
                val ebBillsFragment =  EbBillsFragment()
                ebBillsFragment.setDatavalue(data)
                return ebBillsFragment
            }
            2-> {
                val ebPaymentFragment =  EbPaymentFragment()
                ebPaymentFragment.setDatavalue(data)
                return ebPaymentFragment
            }

            else -> {
                val ebConnectionFragment =  EbConnectionFragment()
                ebConnectionFragment.setDatavalue(data)
                return ebConnectionFragment
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "EB Connection"
            }
            1 -> {
                return "EB Bills"
            }
            2 -> {
                return "EB Payments"
            }
        }
        return super.getPageTitle(position)
    }

}