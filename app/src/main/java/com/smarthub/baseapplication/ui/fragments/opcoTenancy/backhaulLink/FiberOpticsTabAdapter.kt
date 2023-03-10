package com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackhaulLinkData

class FiberOpticsTabAdapter(manager: FragmentManager, var data: BackhaulLinkData?, var parentIndex:Int): FragmentPagerAdapter(manager) {


    override fun getCount(): Int {
        return 4
    }


    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return FiberOpticsLinkInfoFragment(data, parentIndex)
            }
            1 -> {
               return FiberOpticsCableFragment(data,parentIndex)
            }
            2-> {
              return FiberOpticsEquipmentFragment(data,parentIndex)
            }
            3-> {
              return FiberCableDetailsFragment(data,parentIndex)
            }
            else -> {
                return FiberOpticsLinkInfoFragment(data,parentIndex)
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Link info"
            }
            1 -> {
                return "Fiber Optic Cable"
            }
            2 -> {
                return "Fiber Optic Equipment"
            }
            3 -> {
                return "Cables"
            }
        }
        return super.getPageTitle(position)
    }

}