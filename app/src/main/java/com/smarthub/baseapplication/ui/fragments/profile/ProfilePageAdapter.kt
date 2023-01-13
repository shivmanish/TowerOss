package com.smarthub.baseapplication.ui.fragments.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.smarthub.baseapplication.model.profile.viewProfile.ProfileDetails
import com.smarthub.baseapplication.model.profile.viewProfile.newData.ProfileData

class ProfilePageAdapter (var manager: FragmentManager) : FragmentPagerAdapter(manager) {
    override fun getCount(): Int {
        return 6
    }

    var profiledata: ProfileData?=null

    fun setdata(profileDetails: ProfileData){
        profiledata=profileDetails
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> OfficialDetailFragment(profiledata)
            1 -> ManagerInfoFragment(profiledata)
            2 -> RoleGeographiFragment(profiledata)
            3 -> AddressFragment(profiledata)
            4 -> UserRoleTabFragment(profiledata)
            5 -> HistoryFragment(profiledata)
            else -> OfficialDetailFragment(profiledata)
        }
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