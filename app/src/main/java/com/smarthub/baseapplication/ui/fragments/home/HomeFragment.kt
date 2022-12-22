package com.smarthub.baseapplication.ui.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.smarthub.baseapplication.activities.DashboardActivity
import com.smarthub.baseapplication.databinding.FragmentHomeBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.home.HomeResponse
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private val binding get() = _binding!!
    var homeViewModel : HomeViewModel ?=null
    private var _binding: FragmentHomeBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

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
            Log.d("notification Nvigate","navigated from home to navigation fragment")
        }

        binding.searchBoxLayout.setOnClickListener {
            (requireActivity() as DashboardActivity).openSearchMenu()
        }

        if (homeViewModel?.homeData()?.hasActiveObservers() == true)
            homeViewModel?.homeData()?.removeObservers(viewLifecycleOwner)
        homeViewModel?.homeData()?.observe(viewLifecycleOwner){
            if (it!=null && it.status == Resource.Status.SUCCESS){
                if (it.data!=null){
                    AppLogger.log("fetched data:"+ Gson().toJson(it))
                    mapUIData(it.data)
                }else{
                    AppLogger.log("null fetched data:")
                }
            }else{
                AppLogger.log("data not fetched")
            }
        }

        homeViewModel?.fetchHomeData()
        homeViewModel?.fetchSiteDropDownData()
    }

    private fun mapUIData(data: HomeResponse){
        if (data.Sitestatus !=null && data.Sitestatus.isNotEmpty()){
            for (item in data.Sitestatus){
                if (item.name == "RFI")
                    binding.totalRfi.text = item.Totalcount
                if (item.name == "Under Construction")
                    binding.totalInProgress.text = item.Totalcount
                if (item.name == "On-Air")
                    binding.totalOnAir.text = item.Totalcount
            }
        }else if (data.Sitestatus!=null)
            AppLogger.log("empty list for siteStatus")
        else AppLogger.log("null data for siteStatus")

        if (data.MyTeamTask!=null && data.MyTeamTask.isNotEmpty()){
            homeViewModel?.updateMyTeamTask(data.MyTeamTask)
        }else if (data.MyTeamTask!=null) AppLogger.log("empty list for MyTeamTask")
        else AppLogger.log("empty list for MyTeamTask")

        if (data.MyTask!=null && data.MyTask.isNotEmpty()){
            homeViewModel?.updateMyTask(data.MyTask)
        }else if (data.MyTask!=null) {
            AppLogger.log("empty list for MyTask")
            homeViewModel?.updateMyTask(null)
        }else AppLogger.log("empty list for MyTask")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    internal inner class HomeViewPagerAdapter(manager: FragmentManager, behaviour:Int) : FragmentPagerAdapter(manager,behaviour) {

        override fun getItem(position: Int): Fragment {
            return when(position){
                0-> MyTaskFragment()
                else -> MyTeamTaskFragment()
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