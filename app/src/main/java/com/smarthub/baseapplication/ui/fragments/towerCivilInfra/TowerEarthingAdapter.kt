package com.smarthub.baseapplication.ui.fragments.towerCivilInfra
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.FilterdTwrData

class TowerEarthingAdapter (fm: FragmentManager, var list:ArrayList<FilterdTwrData>?) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return list?.size!!
    }

    override fun getItem(position: Int): Fragment {
        return TowerEarthingInfoFragment(list!![position])
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "Earthing${position+1}"
    }

}