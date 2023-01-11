package com.smarthub.baseapplication.ui.fragments.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ProfilePageAdapter (var manager: FragmentManager) : FragmentPagerAdapter(manager) {
    override fun getCount(): Int {
        return 6
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> OfficialDetailFragment(manager)
            1 -> ManagerInfoFragment(manager)
            2 -> RoleGeographiFragment(manager)
            3 -> AddressFragment(manager)
            4 -> UserRoleTabFragment()
            5 -> HistoryFragment(manager)
            else -> OfficialDetailFragment(manager)
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