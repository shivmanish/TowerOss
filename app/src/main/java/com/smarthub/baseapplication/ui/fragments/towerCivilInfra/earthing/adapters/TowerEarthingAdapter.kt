package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.earthing.adapters
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.NewTowerCivilAllData
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TowerAndCivilInfraEarthing
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.earthing.TowerEarthingInfoFragment

class TowerEarthingAdapter (fm: FragmentManager, var list:ArrayList<TowerAndCivilInfraEarthing>?,var fullData: NewTowerCivilAllData?) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return list?.size!!
    }

    override fun getItem(position: Int): Fragment {
        return TowerEarthingInfoFragment(list?.get(position),fullData,position)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "Earthing${position+1}"
    }

}