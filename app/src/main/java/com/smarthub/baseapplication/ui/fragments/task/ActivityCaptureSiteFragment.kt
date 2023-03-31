package com.smarthub.baseapplication.ui.fragments.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.smarthub.baseapplication.databinding.CaptureSiteFragmentDataBinding
import com.smarthub.baseapplication.model.taskModel.dropdown.TaskDropDownModel
import com.smarthub.baseapplication.ui.fragments.task.adapter.CaptureSiteAdapter
import com.smarthub.baseapplication.ui.fragments.task.adapter.CapturedSite
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils

class ActivityCaptureSiteFragment: Fragment(),CapturedSite {
    lateinit var binding:CaptureSiteFragmentDataBinding
    lateinit var viewmodel: TaskViewModel
    var selectedTaskList:ArrayList<String> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CaptureSiteFragmentDataBinding.inflate(inflater)
        viewmodel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.layoutManager = LinearLayoutManager(context)
        val json = Utils.getJsonDataFromAsset(requireContext(),"taskDropDown.json")
        val model = Gson().fromJson(json,TaskDropDownModel::class.java)
        binding.list.adapter = CaptureSiteAdapter(requireContext(),model,this)
        binding.next.setOnClickListener {
            nextClicked()
        }
    }

    private fun nextClicked() {
        viewmodel.processTemplatemanual.Where=selectedTaskList
        viewmodel.processTemplatemanual.pictures=false
        viewmodel.processTemplatemanual.documents=false
        viewmodel.processTemplatemanual.remark=""
        AppLogger.log("total data===> ${viewmodel.processTemplatemanual}")
        findNavController().navigate(TaskSecondFragmentDirections.actionToMoveThirdFrag())
    }
    override fun selectedSites(selectedSites: ArrayList<String>) {
        selectedTaskList=selectedSites
        AppLogger.log("selected sites===> $selectedTaskList")
    }
}