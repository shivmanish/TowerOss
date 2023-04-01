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
    lateinit var model:TaskDropDownModel
    var currentSelectedTab:Int =-1
    var selectedTabItemList:ArrayList<Boolean> = ArrayList()
    var selectedTaskList:ArrayList<String> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CaptureSiteFragmentDataBinding.inflate(inflater)
        viewmodel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        val json = Utils.getJsonDataFromAsset(requireContext(),"taskDropDown.json")
        model = Gson().fromJson(json,TaskDropDownModel::class.java)
        if (viewmodel.processTemplatemanual.Where!=null && viewmodel.processTemplatemanual.Where?.isNotEmpty()==true)
            setSelectedTabAndItsData(model)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.adapter = CaptureSiteAdapter(requireContext(),model,this,currentSelectedTab,selectedTabItemList)
        binding.next.setOnClickListener {
            nextClicked()
        }
    }

    private fun nextClicked() {
        viewmodel.processTemplatemanual.Where=selectedTaskList
        viewmodel.processTemplatemanual.pictures=null
        viewmodel.processTemplatemanual.documents=null
        viewmodel.processTemplatemanual.remark=null
        AppLogger.log("total data===> ${viewmodel.processTemplatemanual}")
        findNavController().navigate(TaskSecondFragmentDirections.actionToMoveThirdFrag())
    }
    override fun selectedSites(selectedSites: ArrayList<String>) {
        selectedTaskList=selectedSites
        viewmodel.processTemplatemanual.Where=selectedTaskList
        AppLogger.log("selected sites===> $selectedTaskList")
    }

    fun setSelectedTabAndItsData(data:TaskDropDownModel){
        selectedTaskList=viewmodel.processTemplatemanual.Where as ArrayList<String>
        selectedTabItemList.clear()
        for (item in data){
            if (item.id== selectedTaskList[0].toInt().div(10)){
                currentSelectedTab=data.indexOf(item)
                for (subItem in item.tabs){
                    if (selectedTaskList.contains(subItem.id.toString())){
                        selectedTabItemList.add(true)
                    }
                    else
                        selectedTabItemList.add(false)
                }
            }
        }
    }

}