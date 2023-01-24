package com.smarthub.baseapplication.ui.fragments.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.smarthub.baseapplication.databinding.CaptureSiteFragmentDataBinding
import com.smarthub.baseapplication.databinding.PhotoDocumentFragmentBinding
import com.smarthub.baseapplication.model.home.MyTeamTask
import com.smarthub.baseapplication.model.taskModel.TaskInfoItem
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.task.adapter.CaptureSiteAdapter
import com.smarthub.baseapplication.utils.AppLogger

class PhotoDocumentFragment: BaseFragment() {
    lateinit var binding:PhotoDocumentFragmentBinding
    lateinit var viewmodel: TaskViewModel
    var taskInfo : TaskInfoItem? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PhotoDocumentFragmentBinding.inflate(inflater)
        viewmodel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(requireActivity().intent.hasExtra("data")){
            var data = Gson().fromJson(requireActivity().intent.getStringExtra("data"),MyTeamTask::class.java)
            viewmodel.getTaskById(data.id1)
            taskInfoObserver()
        }
        if(taskInfo!=null){
            binding.pictureBox.isChecked= taskInfo?.pictures=="True"
            binding.documentBox.isChecked= taskInfo?.documents=="True"
        }
        binding.next.setOnClickListener {
            viewmodel.processTemplatemanual.pictures=binding.pictureBox.isChecked
            viewmodel.processTemplatemanual.documents=binding.documentBox.isChecked
            viewmodel.processTemplatemanual.remark=binding.Remark.text.toString()
            AppLogger.log("all data: ${viewmodel.processTemplatemanual}")
            nextClicked()
        }
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