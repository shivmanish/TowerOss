package com.smarthub.baseapplication.ui.fragments.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.smarthub.baseapplication.databinding.CaptureSiteFragmentDataBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.dropdown.DropDownItem
import com.smarthub.baseapplication.model.siteInfo.qat.QatCardItem
import com.smarthub.baseapplication.model.taskModel.dropdown.TaskDropDownModel
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.task.adapter.CaptureSiteAdapter
import com.smarthub.baseapplication.ui.fragments.task.adapter.CapturedSite
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class ActivityCaptureSiteFragment: BaseFragment(),CapturedSite {
    lateinit var binding:CaptureSiteFragmentDataBinding
    lateinit var viewmodel: TaskViewModel
    lateinit var homeViewmodel: HomeViewModel
    lateinit var model:TaskDropDownModel
    var currentSelectedTab:Int =-1
    var selectedTabItemList:ArrayList<Boolean> = ArrayList()
    var selectedQatId:String = ""
    var selectedTaskList:ArrayList<String> = ArrayList()
    var selectedTaskTabList:ArrayList<Any> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CaptureSiteFragmentDataBinding.inflate(inflater)
        viewmodel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        homeViewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        val json = Utils.getJsonDataFromAsset(requireContext(),"taskDropDown.json")
        model = Gson().fromJson(json,TaskDropDownModel::class.java)
        if (viewmodel.processTemplatemanual.Where!=null && viewmodel.processTemplatemanual.Where?.isNotEmpty()==true)
            setSelectedTabAndItsData(model)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.next.setOnClickListener {
            nextClicked()
        }
        if (homeViewmodel.QatMainTempletResponse?.hasActiveObservers() == true){
            homeViewmodel.QatMainTempletResponse?.removeObservers(viewLifecycleOwner)
        }
        homeViewmodel.QatMainTempletResponse?.observe(viewLifecycleOwner) {
            AppLogger.log("QatMain AllData fetched successfully")
            if (it!=null && it.status == Resource.Status.LOADING){
                showLoader()
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.item?.isNotEmpty()==true){
                AppLogger.log("QatMain Data fetched successfully")
                val qatDropdownList=createQatDropdownList(it.data.item?.get(0)?.QATTemplateMain)
                hideLoader()
                AppLogger.log("QatDropdown List ====>:${Gson().toJson(qatDropdownList)}")
                binding.list.adapter = CaptureSiteAdapter(requireContext(),model,this,currentSelectedTab,selectedTabItemList,selectedQatId,qatDropdownList)
            }else if (it!=null) {
                AppLogger.log("Service request Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("Service Request Fragment Something went wrong")
            }
        }
        homeViewmodel.qatRequestAll(viewmodel.processTemplatemanual.siteid!!)

    }

    private fun nextClicked() {
        viewmodel.processTemplatemanual.Where=selectedTaskTabList
        viewmodel.processTemplatemanual.pictures=null
        viewmodel.processTemplatemanual.documents=null
        viewmodel.processTemplatemanual.remark=null
        AppLogger.log("total data===> ${viewmodel.processTemplatemanual}")
        findNavController().navigate(TaskSecondFragmentDirections.actionToMoveThirdFrag())
    }
    override fun selectedSites(selectedSites: ArrayList<Any>) {
        selectedTaskTabList=selectedSites
        selectedTaskList= selectedSites as ArrayList<String>
        viewmodel.processTemplatemanual.Where=selectedTaskTabList
        AppLogger.log("selected sites===> $selectedTaskTabList")
    }

    fun setSelectedTabAndItsData(data:TaskDropDownModel){
        selectedTaskList=viewmodel.processTemplatemanual.Where as ArrayList<String>
        selectedTabItemList.clear()
        if (selectedTaskList.isNotEmpty() && selectedTaskList[0].startsWith("q")){
            currentSelectedTab=data.size
            selectedQatId=selectedTaskList[0].replace("q_","")
        }
        else{
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

    fun createQatDropdownList(data:ArrayList<QatCardItem>?):ArrayList<DropDownItem>{
        val dropDownDataList:ArrayList<DropDownItem> =ArrayList()
        if (data!=null){
            for (item in data){
                val dropDownData = DropDownItem("","")
                dropDownData.id=item.id
                dropDownData.name=item.Name
                dropDownDataList.add(dropDownData)
            }
        }
        AppLogger.log("dropDownDataList Data size===>${dropDownDataList.size}")
        AppLogger.log("QatTempletMain Data size===>${data?.size}")
        return dropDownDataList
    }

}