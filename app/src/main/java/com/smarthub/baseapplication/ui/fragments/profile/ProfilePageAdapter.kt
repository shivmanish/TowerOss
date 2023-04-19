package com.smarthub.baseapplication.ui.fragments.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.profile.viewProfile.ProfileDetails
import com.smarthub.baseapplication.model.profile.viewProfile.newData.ProfileData

class ProfilePageAdapter (var manager: FragmentManager,var fragmentlist : ArrayList<Fragment>) : FragmentPagerAdapter(manager) {
    override fun getCount(): Int {
        return fragmentlist.size
    }

    var profiledata: ProfileData?=null

    fun setdata(profileDetails: ProfileData){
        profiledata=profileDetails
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
       return fragmentlist.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Official Details"
            }
            1 -> {
                return "Manager Info"
            }
            2 -> {
                return "Role Geography"
            }
            3 -> {
                return "Address"
            }
            4 -> {
                return "Role & Permissions"
            }
            5 -> {
                return "History"
            }

        }
        return super.getPageTitle(position)
    }

}