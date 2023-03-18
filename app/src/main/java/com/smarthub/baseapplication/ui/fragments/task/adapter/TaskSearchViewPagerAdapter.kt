package com.smarthub.baseapplication.ui.fragments.task.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.noc.NocFragment
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.OpcoTanacyFragment
import com.smarthub.baseapplication.ui.fragments.plandesign.fragment.PlanDesignMainFrqagment
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.PowerConnection
import com.smarthub.baseapplication.ui.fragments.qat.QATMainFragment
import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestFrqagment
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.SiteAgreementFragment
import com.smarthub.baseapplication.ui.fragments.siteInfo.SiteInfoNewFragment
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.CivilInfraFragment
import com.smarthub.baseapplication.ui.fragments.utilites.fragment.UtilitiesNocMainTabFragment

class TaskSearchViewPagerAdapter(fm: FragmentManager, var tabNames:List<String>,var id:String) : FragmentPagerAdapter(fm) {

    var list : ArrayList<BaseFragment> = ArrayList(tabNames.size)
    init {
        for(i in 0 until tabNames?.size?.minus(1)!!){
            list.add(getCustomItemForLocal(i))
        }
    }

    private fun getCustomItemForLocal(position: Int) : BaseFragment {
        val f : BaseFragment =  when(list[position]){
            is SiteInfoNewFragment-> SiteInfoNewFragment(id)
            is ServicesRequestFrqagment-> ServicesRequestFrqagment(id)
            is OpcoTanacyFragment-> OpcoTanacyFragment(id)
            is PlanDesignMainFrqagment-> PlanDesignMainFrqagment(id)
            is SiteAgreementFragment-> SiteAgreementFragment(id)
            is UtilitiesNocMainTabFragment-> UtilitiesNocMainTabFragment(id)
            is NocFragment-> NocFragment(id)
            is CivilInfraFragment-> CivilInfraFragment(id)
            is PowerConnection-> PowerConnection(id)
            is QATMainFragment-> QATMainFragment(id)
            else -> SiteInfoNewFragment(id)
        }
        return f
    }

    override fun getItem(position: Int): Fragment {
        val f = getCustomItemForLocal(position)
        if (list.size>position) list[position] = f
        else list.add(f)
        return list[position]
    }

    fun getItemByPosition(position: Int): BaseFragment {
        return list[position]
    }

    override fun getCount(): Int {
        return tabNames?.size!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabNames?.get(position)
    }


}