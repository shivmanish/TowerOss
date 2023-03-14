package com.smarthub.baseapplication.ui.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.DashboardActivity
import com.smarthub.baseapplication.databinding.FragmentHomeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.home.HomeResponse
import com.smarthub.baseapplication.model.home.MyTeamTask
import com.smarthub.baseapplication.ui.dialog.home.AdNewSiteInfoBottomSheet
import com.smarthub.baseapplication.ui.dialog.utils.AttachmentDialogBottomSheet
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.task.TaskListener
import com.smarthub.baseapplication.ui.fragments.task.editdialog.AssignTaskDialouge
import com.smarthub.baseapplication.ui.fragments.task.editdialog.ViewTaskBottomSheet
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class HomeFragment : Fragment(),TaskListener {

    lateinit var adapterList : MyTaskItemAdapter
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
        binding.notificationLayout.setOnClickListener {
            val addImageTest  = AttachmentDialogBottomSheet("TowerAndCivilInfraTowerTowerDetail","68")
            addImageTest.show(childFragmentManager,"add image")
//            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNotificationsFragment())
//            Log.d("notification Nvigate","navigated from home to navigation fragment")
        }
        binding.addNewSite.setOnClickListener {
            clickNewSiteData()
        }

        binding.searchBoxLayout.setOnClickListener {
            (requireActivity() as DashboardActivity).openSearchMenu()
        }
        binding.seeAllTask.setOnClickListener {
            (requireActivity() as DashboardActivity).openTaskMenu()
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

        binding.taskList.setHasFixedSize(true)
        adapterList = MyTaskItemAdapter(this@HomeFragment,"home_navigation")
        binding.taskList.adapter = adapterList

        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        if (homeViewModel.myTask?.hasActiveObservers() == true)
            homeViewModel.myTask?.removeObservers(viewLifecycleOwner)
        homeViewModel.myTask?.observe(viewLifecycleOwner){
            AppLogger.log("my Task data fetched")
            if (it!=null && it.isNotEmpty()){
                val list :ArrayList<Any> = ArrayList()
                list.addAll(it)
                AppPreferences.getInstance().saveMyTeamTask(it)
                if(list.size<3)
                    binding.taskList.layoutParams.height=ViewGroup.LayoutParams.WRAP_CONTENT
                adapterList.updateList(list)
                binding.tastCount.text = it.size.toString()
            }else{
                val data = AppPreferences.getInstance().myTeamTask
                if (data.isNotEmpty()){
                    val list :ArrayList<Any> = ArrayList()
                    list.addAll(data)
                    if(list.size<3)
                        binding.taskList.layoutParams.height=ViewGroup.LayoutParams.WRAP_CONTENT
                    adapterList.updateList(list)
                    binding.tastCount.text = data.size.toString()
                }else {
                    binding.taskList.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    binding.seeAllTask.visibility = View.GONE
                    adapterList.addItem("no_data")
                }
            }
        }
        var b = Utils.isNetworkConnected(requireContext())
        AppLogger.log("home screen network:$b")
        if (b) {
            homeViewModel.fetchHomeData()
            homeViewModel.fetchSiteDropDownData()
//            adapterList.addItem("loading")
        }else{
            val data = AppPreferences.getInstance().myTeamTask
            if (data.isNotEmpty()){
                val list :ArrayList<Any> = ArrayList()
                list.addAll(data)
                if(list.size<3)
                    binding.taskList.layoutParams.height=ViewGroup.LayoutParams.WRAP_CONTENT
                adapterList.updateList(list)
                binding.tastCount.text = data.size.toString()
            }
        }
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
        }
        else if (data.Sitestatus!=null)
            AppLogger.log("empty list for siteStatus")
        else AppLogger.log("null data for siteStatus")

        if (data.MyTeamTask!=null){
            homeViewModel.updateMyTeamTask(data.MyTeamTask)
        }
//        else if (data.MyTeamTask!=null) {
//            homeViewModel.updateMyTeamTask(data.MyTeamTask)
//            AppLogger.log("empty list for MyTeamTask")
//        }
        else AppLogger.log("empty list for MyTeamTask")

        if (data.MyTask!=null && data.MyTask.isNotEmpty()){
            homeViewModel.updateMyTask(data.MyTask)
        }else if (data.MyTask!=null) {
            homeViewModel.updateMyTask(data.MyTask)
            AppLogger.log("empty list for MyTask")
        }else AppLogger.log("empty list for MyTask")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
            if (it != null) {
                if (it.status == Resource.Status.SUCCESS && it.data != null) {
                    AppPreferences.getInstance().saveDropDownData(it.data)
                    return@observe
                } else {
                    Log.d("status", "${it.message}")
//                    Toast.makeText(context, "error:" + it.message, Toast.LENGTH_LONG).show()
                }
            } else {
                Log.d("status", AppConstants.GENERIC_ERROR)
//                Toast.makeText(context, AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
            }
        }
        homeViewModel.fetchDropDownNew()
    }

    override fun closeTask(task: MyTeamTask,route:String) {
        val bottomSheetDialogFragment = ViewTaskBottomSheet(R.layout.basic_info_details_bottom_sheet, task,homeViewModel,route)
        bottomSheetDialogFragment.show(childFragmentManager, "category")
    }

    override fun assignTask(task : MyTeamTask) {
        val bm = AssignTaskDialouge(R.layout.assign_task_dialouge,task)
        bm.show(childFragmentManager, "category")
    }


}