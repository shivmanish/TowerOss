package com.smarthub.baseapplication.ui.fragments.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.activities.SearchActivity
import com.smarthub.baseapplication.databinding.FragmentHomeBinding
import com.smarthub.baseapplication.ui.fragments.customer_tab.OpcoTanacyFragment
import com.smarthub.baseapplication.ui.fragments.customer_tab.SiteInfoNewFragment
import com.smarthub.baseapplication.ui.fragments.sitedetail.BlackhaulFrag
import com.smarthub.baseapplication.ui.site_lease_acquisition.SiteLeaseAcqusitionFragment
import com.smarthub.baseapplication.ui.utilites.fragment.UtilitiesNocMainTabFragment
import com.smarthub.baseapplication.viewmodels.MainViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    var mainViewModel : MainViewModel ?=null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel?.isActionbarHide?.value = true
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.searchBoxLayout.setOnClickListener {
//            set menu selection for site iBoard

        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homePager.adapter = HomeViewPagerAdapter(childFragmentManager,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        binding.homeTab.setupWithViewPager(binding.homePager)
        binding.notificationLayout.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNotificationsFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    internal inner class HomeViewPagerAdapter(manager: FragmentManager, behaviour:Int) :
        FragmentPagerAdapter(manager,behaviour) {

        override fun getItem(position: Int): Fragment {
            return when(position){
                0-> SiteLeaseAcqusitionFragment()
                else -> MyTaskHomeFragment()
            }
        }


        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return if (position==0) "My Tasks" else "My Team Tasks"
        }

    }
}