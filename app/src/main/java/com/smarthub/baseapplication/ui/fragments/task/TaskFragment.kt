package com.smarthub.baseapplication.ui.fragments.task

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentTaskBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.home.HomeResponse
import com.smarthub.baseapplication.ui.fragments.home.HomeFragment
import com.smarthub.baseapplication.ui.fragments.home.MyTaskFragment
import com.smarthub.baseapplication.ui.fragments.home.MyTeamTaskFragment
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskAssignToDialouge
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel
import com.smarthub.baseapplication.viewmodels.MainViewModel

class TaskFragment : Fragment(), TaskItemAdapter.itemClickListner {

    private lateinit var binding: FragmentTaskBinding
    private lateinit var homeViewModel:HomeViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
//        homeViewModel.isActionBarHide(false)
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.listItem.adapter = TaskItemAdapter(this@TaskFragment)

        binding.addItem.setOnClickListener {
            val intent = Intent(requireActivity(),TaskActivity::class.java)
            requireActivity().startActivity(intent)
        }
        binding.addMore.setOnClickListener {
            val intent = Intent(requireActivity(),TaskActivity::class.java)
            requireActivity().startActivity(intent)
        }

        binding.homePager.adapter = TaskViewPagerAdapter(childFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        binding.homeTab.setupWithViewPager(binding.homePager)

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
    }

    private fun mapUIData(data: HomeResponse){
        if (data.Sitestatus !=null && data.Sitestatus.isNotEmpty()){
//            for (item in data.Sitestatus){
//                if (item.name == "RFI")
//                    binding.totalRfi.text = item.Totalcount
//                if (item.name == "Under Construction")
//                    binding.totalInProgress.text = item.Totalcount
//                if (item.name == "On-Air")
//                    binding.totalOnAir.text = item.Totalcount
//            }
        }else if (data.Sitestatus!=null)
            AppLogger.log("empty list for siteStatus")
        else AppLogger.log("null data for siteStatus")

        if (data.MyTeamTask!=null && data.MyTeamTask.isNotEmpty()){
            homeViewModel.updateMyTeamTask(data.MyTeamTask)
        }else if (data.MyTeamTask!=null) AppLogger.log("empty list for MyTeamTask")
        else AppLogger.log("empty list for MyTeamTask")

        if (data.MyTask!=null && data.MyTask.isNotEmpty()){
            homeViewModel.updateMyTask(data.MyTask)
        }else if (data.MyTask!=null) {
            AppLogger.log("empty list for MyTask")
            homeViewModel.updateMyTask(null)
        }else AppLogger.log("empty list for MyTask")
    }

    override fun taskAssignDialouge() {
        val bottomSheetDialogFragment = TaskAssignToDialouge(R.layout.task_assignment_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
        Toast.makeText(requireContext(),"Commercial Item clicked", Toast.LENGTH_SHORT).show()
    }

    internal inner class TaskViewPagerAdapter(manager: FragmentManager, behaviour:Int) : FragmentPagerAdapter(manager,behaviour) {

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