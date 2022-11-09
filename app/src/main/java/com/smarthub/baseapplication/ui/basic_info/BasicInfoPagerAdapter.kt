package com.smarthub.baseapplication.ui.basic_info


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.basic_info.fragment.BasicInfo
import com.smarthub.baseapplication.ui.basic_info.fragment.GeoConditionalInfo
import com.smarthub.baseapplication.ui.basic_info.fragment.OperationalInfo
import com.smarthub.baseapplication.ui.basic_info.fragment.SafatyAccessFragment

class BasicInfoPagerAdapter(fm: FragmentManager,var siteInfoDropDownData: SiteInfoDropDownData) :
    FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                val basicInfo = BasicInfo()
                val bundle = Bundle()
                bundle.putSerializable("data", siteInfoDropDownData.basicInfoModel)
                basicInfo.arguments = bundle
                return basicInfo
            }
            1 -> {
                val operationalInfo = OperationalInfo()
                val bundle = Bundle()
                bundle.putSerializable("data", siteInfoDropDownData.operationalInfo)
                operationalInfo.arguments = bundle
                return operationalInfo
            }
            2 -> {
                val geoConditionalInfo = GeoConditionalInfo()
                val bundle = Bundle()
                bundle.putSerializable("data", siteInfoDropDownData.geoCondition)
                geoConditionalInfo.arguments = bundle
                return GeoConditionalInfo()
            }
            3 -> {
                val safatyAccessFragment = SafatyAccessFragment()
                val bundle = Bundle()
                bundle.putSerializable("data", siteInfoDropDownData.safetyAndAccess)
                safatyAccessFragment.arguments = bundle
                return SafatyAccessFragment()
            }
            else -> {
                val basicInfo = BasicInfo()
                val bundle = Bundle()
                bundle.putSerializable("data", siteInfoDropDownData.basicInfoModel)
                basicInfo.arguments = bundle
                return basicInfo
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return "Basic Info"
            }
            1 -> {
                return "Operational Info"
            }
            2 -> {
                return "Geo Conditions"
            }
            3 -> {
                return "Safaty/Access"
            }
        }
        return super.getPageTitle(position)
    }

}