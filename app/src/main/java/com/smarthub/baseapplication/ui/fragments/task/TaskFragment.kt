package com.smarthub.baseapplication.ui.fragments.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentTaskBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.home.HomeResponse
import com.smarthub.baseapplication.model.home.MyTeamTask
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.home.MyTaskFragment
import com.smarthub.baseapplication.ui.fragments.home.MyTeamTaskFragment
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskAssignToDialouge
import com.smarthub.baseapplication.ui.fragments.task.editdialog.AssignTaskDialouge
import com.smarthub.baseapplication.ui.fragments.task.editdialog.ViewTaskBottomSheet
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class TaskFragment : Fragment(), TaskItemAdapter.itemClickListner ,TaskListener{

    private lateinit var binding: FragmentTaskBinding
    private lateinit var homeViewModel:HomeViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addMore.setOnClickListener {
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")
        }
        val rotate = RotateAnimation(
            0F, 360F,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        binding.homePager.adapter = TaskViewPagerAdapter(childFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,this@TaskFragment)
        binding.homeTab.setupWithViewPager(binding.homePager)

        if (homeViewModel.homeData()?.hasActiveObservers() == true)
            homeViewModel.homeData()?.removeObservers(viewLifecycleOwner)
        homeViewModel.homeData()?.observe(viewLifecycleOwner){
            binding.refreshLayout.isRefreshing = false
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
        binding.refreshLayout.setOnRefreshListener {
            homeViewModel.fetchHomeData()
        }
        homeViewModel.fetchHomeData()
        if (AppPreferences.getInstance().offlineTask.hasActiveObservers())
            AppPreferences.getInstance().offlineTask.removeObservers(viewLifecycleOwner)
        AppPreferences.getInstance().offlineTask.observe(viewLifecycleOwner){
            binding.tasks.text = "$it"
            if (it==0){
                binding.refresh.clearAnimation()
            }
        }
        binding.refresh.setOnClickListener {
            AppPreferences.getInstance().callAPI()
            rotate.duration = 900
            rotate.repeatCount = Animation.INFINITE
            binding.refresh.startAnimation(rotate)
        }
        binding.tasks.text = AppPreferences.getInstance().offlineTaskList.size.toString()
    }

    private fun mapUIData(data: HomeResponse){
        if (data.Sitestatus !=null && data.Sitestatus.isNotEmpty()){

        }else if (data.Sitestatus!=null)
            AppLogger.log("empty list for siteStatus")
        else AppLogger.log("null data for siteStatus")

        if (data.MyTeamTask!=null){
            homeViewModel.updateMyTeamTask(data.MyTeamTask)
        }
        if (data.MyTask!=null){
            homeViewModel.updateMyTask(data.MyTask)
        }
    }

    override fun taskAssignDialouge() {
        val bottomSheetDialogFragment = TaskAssignToDialouge(R.layout.task_assignment_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
//        Toast.makeText(requireContext(),"Commercial Item clicked", Toast.LENGTH_SHORT).show()
    }

    internal inner class TaskViewPagerAdapter(manager: FragmentManager, behaviour:Int,var listener: TaskListener) : FragmentPagerAdapter(manager,behaviour) {

        override fun getItem(position: Int): Fragment {
            return when(position){
                0-> MyTaskFragment(listener)
                else -> MyTeamTaskFragment(listener)
            }
        }


        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return if (position==0) "My Tasks" else "My Team Tasks"
        }

    }

    override fun closeTask(task: MyTeamTask,route:String) {
        val bottomSheetDialogFragment = ViewTaskBottomSheet(R.layout.close_task_bottom_sheet, task,homeViewModel,route)
        bottomSheetDialogFragment.show(childFragmentManager, "category")
    }

    override fun assignTask(task : MyTeamTask) {
        val bm = AssignTaskDialouge(R.layout.assign_task_dialouge,task)
        bm.show(childFragmentManager, "category")
    }


}