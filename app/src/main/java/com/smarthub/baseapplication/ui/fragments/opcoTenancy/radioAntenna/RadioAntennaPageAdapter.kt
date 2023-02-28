package com.smarthub.baseapplication.ui.fragments.opcoTenancy.radioAntenna


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.RadioAnteenaAndRRUData

class RadioAntennaPageAdapter(fm:FragmentManager, var opcodata: RadioAnteenaAndRRUData?) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3

    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return RadioAntinaDetailsFragment(opcodata)
            }

            1 -> {
                return RRUDetailsFragment(opcodata)
            }
            2 -> {
                return CableDetailsFragment(opcodata)
            }
            else -> {
                return RadioAntinaDetailsFragment(opcodata)
            }

        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Radio Antenna Details"
            }

            1 -> {
                return "RRU Details"
            }
            2 -> {
                return "Cable Details"
            }
        }
        return super.getPageTitle(position)
    }

}