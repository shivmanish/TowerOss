package com.smarthub.baseapplication.ui.site_agreement.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.Siteacquisition
import com.smarthub.baseapplication.ui.site_agreement.siteagreements_tab.SANomonalsFrag
import com.smarthub.baseapplication.ui.site_agreement.siteagreements_tab.SAPaymentFrag


class SiteAgreemetAdapter(
    fm: FragmentManager,
    var data: Siteacquisition?, Id: String?) : FragmentPagerAdapter(fm) {


    fun updateData(updatedData: Siteacquisition){
        data=updatedData
        notifyDataSetChanged()
    }
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return SANomonalsFrag(data?.SiteacquisitionAgreements)
            }
            1 -> {
                return SAPaymentFrag(data?.SiteacquisitionPayment)
            }

            else -> {
                return SANomonalsFrag(data?.SiteacquisitionAgreements)
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return "Nominals"
            }
            1 -> {
                return "Payments"
            }

        }
        return super.getPageTitle(position)
    }

}