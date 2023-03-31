package com.smarthub.baseapplication.ui.fragments.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TaskSecondFragmnetBinding
import com.smarthub.baseapplication.model.home.MyTeamTask
import com.smarthub.baseapplication.model.taskModel.TaskInfoItem
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskViewpagerAdapter
import com.smarthub.baseapplication.utils.AppLogger

class TaskSecondFragment:Fragment(){

    lateinit var binding:TaskSecondFragmnetBinding
    lateinit var viewmodel: TaskViewModel
    var taskInfo : TaskInfoItem? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TaskSecondFragmnetBinding.inflate(inflater)
        viewmodel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        if(requireActivity().intent.hasExtra("data")){
            val data = Gson().fromJson(requireActivity().intent.getStringExtra("data"), MyTeamTask::class.java)
            viewmodel.getTaskById(data.id1)
            taskInfoObserver()
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewpager.adapter = TaskViewpagerAdapter(childFragmentManager,taskInfo,viewmodel.processTemplatemanual.siteid,requireContext())
        binding.tabs.setupWithViewPager(binding.viewpager)
//        if (viewmodel.processTemplatemanual.siteid=="" && binding.tabs.selectedTabPosition==1){
//            binding.tabs.tabTextColors= R.color.grey
//        }
        binding.next.setOnClickListener {
            AppLogger.log("final model for second step===> : ${viewmodel.processTemplatemanual}")
            nextClicked()
        }
        binding.actionsLayout.visibility=View.GONE
        binding.cancel.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun nextClicked() {
        findNavController().navigate(TaskSecondFragmentDirections.actionToMoveThirdFrag())
    }

    fun taskInfoObserver(){
        if (viewmodel.getTaskInfoResponse!!.hasActiveObservers())
            viewmodel.getTaskInfoResponse!!.removeObservers(viewLifecycleOwner)
        viewmodel.getTaskInfoResponse!!.observe(viewLifecycleOwner, Observer {
            if (it?.data != null) {
                try {
                    taskInfo=it.data.get(0)
                }catch (e:Exception){
                    AppLogger.log("Somthing went wront to fetch task info ${e.localizedMessage}")
                }
            }else AppLogger.log("Department not fetched")

        })
    }
}