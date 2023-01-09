package com.smarthub.baseapplication.ui.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.DashboardActivity
import com.smarthub.baseapplication.databinding.FragmentHomeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.home.HomeResponse
import com.smarthub.baseapplication.ui.dialog.home.AdNewSiteInfoBottomSheet
import com.smarthub.baseapplication.ui.dialog.siteinfo.OperationsInfoBottomSheet
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.search.SearchFragmentDirections
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private val binding get() = _binding!!
    lateinit var homeViewModel : HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        setDataDropDownObserver()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.searchBoxLayout.setOnClickListener {
//            set menu selection for site iBoard

        }
        binding.addMore.setOnClickListener{
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")
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

        binding.addNewSite.setOnClickListener { clickNewSiteData() }

        binding.searchBoxLayout.setOnClickListener {
            (requireActivity() as DashboardActivity).openSearchMenu()
        }

        if (homeViewModel.homeData()?.hasActiveObservers() == true)
            homeViewModel.homeData()?.removeObservers(viewLifecycleOwner)
        homeViewModel.homeData()?.observe(viewLifecycleOwner){
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

        homeViewModel.fetchHomeData()
        homeViewModel.fetchSiteDropDownData()
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

    private fun clickNewSiteData(){
        val bottomSheetDialogFragment = AdNewSiteInfoBottomSheet(R.layout.operations_info_details_bottom_sheet,homeViewModel,
        object : AdNewSiteInfoBottomSheet.AdNewSiteSheetListener{
            override fun siteCreated(id: String) {
                findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToSiteDetailFragment(id))
            }
        })
        bottomSheetDialogFragment.show(childFragmentManager, "category")

    }

    private fun setDataDropDownObserver() {

        if (homeViewModel.dropDownResponseNew?.hasActiveObservers() == true)
            homeViewModel.dropDownResponseNew?.removeObservers(viewLifecycleOwner)
        homeViewModel.dropDownResponseNew?.observe(viewLifecycleOwner) {
//            hideLoader()
            if (it != null) {
                if (it.status == Resource.Status.SUCCESS && it.data != null) {
                    AppPreferences.getInstance().saveDropDownData(it.data)
                    return@observe
                } else {
                    Log.d("status", "${it.message}")
                    Toast.makeText(context, "error:" + it.message, Toast.LENGTH_LONG).show()
                }
            } else {
                Log.d("status", AppConstants.GENERIC_ERROR)
                Toast.makeText(context, AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
            }
        }
        homeViewModel.fetchDropDownNew()
    }


}