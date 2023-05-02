package com.smarthub.baseapplication.ui.fragments.task

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.smarthub.baseapplication.databinding.TaskThirdFragmnetBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.home.MyTeamTask
import com.smarthub.baseapplication.model.qatcheck.QATMainLaunchNew
import com.smarthub.baseapplication.model.qatcheck.QalLaunchModel
import com.smarthub.baseapplication.model.taskModel.TaskInfoItem
import com.smarthub.baseapplication.model.workflow.TaskDataListItem
import com.smarthub.baseapplication.model.workflow.TaskDataUpdateModel
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger

class TaskThirdFragment:BaseFragment() {
    lateinit var binding:TaskThirdFragmnetBinding
    lateinit var viewmodel: TaskViewModel
//    private lateinit var progressDialog : ProgressDialog
    var taskInfo : TaskInfoItem? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        if(requireActivity().intent.hasExtra("data")){
            val data = Gson().fromJson(requireActivity().intent.getStringExtra("data"), MyTeamTask::class.java)
            taskInfoObserver()
            viewmodel.getTaskById(data.id1)
        }
        binding = TaskThirdFragmnetBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        progressDialog = ProgressDialog(requireContext())
//        progressDialog.setMessage("Please Wait...")
//        progressDialog.setCanceledOnTouchOutside(true)
        if(taskInfo!=null){
            viewmodel.processTemplatemanual.NotificationSettingfornewaction= taskInfo?.NotificationSettingfornewaction=="True"
            viewmodel.processTemplatemanual.Automaticescalationofoverdueitems= taskInfo?.Automaticescalationofoverdueitems=="True"
            viewmodel.processTemplatemanual.Reminderofoutstandingactions= taskInfo?.Reminderofoutstandingactions=="True"

        }
        setPriviousFilledData()
        binding.Submit.setOnClickListener {
            showLoader()
            viewmodel.processTemplatemanual.NotificationSettingfornewaction=binding.notificationSwitch.isChecked
            viewmodel.processTemplatemanual.Automaticescalationofoverdueitems=binding.AutoEsclationSwitch.isChecked
            viewmodel.processTemplatemanual.Reminderofoutstandingactions=binding.reminderSwitch.isChecked
            viewmodel.processTemplatemanual.NotificationSettingGeoTracking=binding.GeoTrackingSwitch.isChecked
            viewmodel.processTemplatemanual.NotificationSettingGeoFencing=binding.GeoFencingDistanceSwitch.isChecked
            if (viewmodel.processTemplatemanual.Distance.isNullOrEmpty())
                viewmodel.processTemplatemanual.Distance = "0.0"
            if (!binding.GeoFencingDistanceEdit.text.toString().isNullOrEmpty())
                viewmodel.processTemplatemanual.Distance=binding.GeoFencingDistanceEdit.text.toString()
            if(taskInfo!=null)
                viewmodel.processTemplatemanual.Taskid=taskInfo?.id?.toInt()!!
            AppLogger.log("all data: ${Gson().toJson(viewmodel.processTemplatemanual)}")
            viewmodel.createNewTask(viewmodel.processTemplatemanual)

            if (viewmodel.getCreateNewTask()?.hasActiveObservers() == true)
                viewmodel.getCreateNewTask()?.removeObservers(viewLifecycleOwner)
            viewmodel.getCreateNewTask()?.observe(viewLifecycleOwner){
                hideLoader()
                if (it!=null && it.Error == ""){
//                    call UI update api for task
//                    createQatCardItem(it)
                    Toast.makeText(context,"Task Created SuccessFully",Toast.LENGTH_LONG).show()
                    requireActivity().finish()
                }
                else {
                    Toast.makeText(context,"Something went wrong ",Toast.LENGTH_LONG).show()
                    AppLogger.log("Something went wrong in createTask fragment: ${it.Error}")
                }
            }
        }

        binding.cancel.setOnClickListener {
            requireActivity().finish()
        }
        binding.notificationSwitch.setOnCheckedChangeListener{buttonView, isChecked ->
            viewmodel.processTemplatemanual.NotificationSettingfornewaction=isChecked
        }
        binding.AutoEsclationSwitch.setOnCheckedChangeListener{buttonView, isChecked ->
            viewmodel.processTemplatemanual.Automaticescalationofoverdueitems=isChecked
        }
        binding.reminderSwitch.setOnCheckedChangeListener{buttonView, isChecked ->
            viewmodel.processTemplatemanual.Reminderofoutstandingactions=isChecked
        }

        binding.GeoTrackingSwitch.setOnCheckedChangeListener{buttonView, isChecked ->
            viewmodel.processTemplatemanual.NotificationSettingGeoTracking=isChecked
            if (isChecked){
                binding.GeoFEncingLayout.visibility=View.VISIBLE
            }
            else
                binding.GeoFEncingLayout.visibility=View.GONE
        }

        binding.GeoFencingDistanceSwitch.setOnCheckedChangeListener{buttonView, isChecked ->
            viewmodel.processTemplatemanual.NotificationSettingGeoFencing=isChecked
            if (isChecked){
                binding.GeoFencingDistanceEditLayout.visibility=View.VISIBLE
            }
            else
                binding.GeoFencingDistanceEditLayout.visibility=View.GONE
        }

        binding.GeoFencingDistanceEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                val changedText=binding.GeoFencingDistanceEdit.text
                viewmodel.processTemplatemanual.Distance=changedText.toString()
            }
        })


    }

    private fun createQatCardItem(taskDetailData: TaskDataListItem?){

        if (viewmodel.updateTaskDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateTaskDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateTaskDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("TaskSearchTabNewFragment TaskData Updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS  ) {
                AppLogger.log("TaskSearchTabNewFragment Task Data Updated successfully")
                Toast.makeText(context,"Task Created SuccessFully",Toast.LENGTH_LONG).show()
                requireActivity().finish()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("TaskSearchTabNewFragment Task Data Updated successfully")
//                AppLogger.log("TaskSearchTabNewFragment Something went wrong in Updating Task Data")
            }
            else if (it != null) {
                AppLogger.log("TaskSearchTabNewFragment error :${it.message}, data : ${it.data}")
            } else {
//                AppLogger.log("TaskSearchTabNewFragment Something went wrong in Updating Task Data")

            }
        }

        if (viewmodel.qatUpdateModel?.hasActiveObservers() == true){
            viewmodel.qatUpdateModel?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.qatUpdateModel?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("TaskSearchTabNewFragment data creating in progress ")
                return@observe
            }
            hideLoader()
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("TaskSearchTabNewFragment card Data Created successfully")
                if (it.data.Status.isNotEmpty()){
                    val data = it.data.Status[0].data.result?.get(0)?.QATMainLaunch?.get(0)
                    taskDetailData?.ModuleId=data?.id.toString()
                    taskDetailData?.ModuleName=data!!.Instruction
                    val tempTaskDataUpdate= TaskDataUpdateModel()
                    tempTaskDataUpdate.ModuleId=data.id.toInt()
                    tempTaskDataUpdate.ModuleName=data.Instruction
                    tempTaskDataUpdate.updatemodule=taskDetailData?.id
                    viewmodel.updateTaskDataWithDataId(tempTaskDataUpdate,taskDetailData?.id!!)
//                    setUpQatData()
                }

            }
            else if (it != null) {
                AppLogger.log("TaskSearchTabNewFragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("TaskSearchTabNewFragment Something went wrong in creating Data")

            }
        }
//        viewmodel.qatUpdateModel?.observe(viewLifecycleOwner) {
//            if (it!=null && it.status == Resource.Status.LOADING){
//
//                return@observe
//            }
//            hideLoader()
//            if (it?.data != null && it.status == Resource.Status.SUCCESS){
//                AppLogger.log("Service request Fragment card Data fetched successfully")
//                Toast.makeText(context,"Task Created SuccessFully",Toast.LENGTH_LONG).show()
//                requireActivity().finish()
//            }
//            else {
//                AppLogger.log("Service Request Fragment Something went wrong")
//                Toast.makeText(requireContext(),"Service Request Fragment Something went wrong", Toast.LENGTH_SHORT).show()
//            }
//        }

        val list =  ArrayList<String>()
        list.add("Test")
        val item = QATMainLaunchNew(
            AssignedTo = "9236120050",
            GeoLevel = "1",
            "",
            "",
            list,
            "2011-10-01",
            true,
            "1"
        )
        val qATMainLaunchNew = ArrayList<QATMainLaunchNew>()
        qATMainLaunchNew.add(item)
        var data = QalLaunchModel(qATMainLaunchNew, AppController.getInstance().siteid, AppController.getInstance().ownerName)
        viewmodel.qatLaunchMain(data)
    }

    fun setPriviousFilledData(){
        AppLogger.log("TaskThirdFragment processTemplatemanual====>:${viewmodel.processTemplatemanual}")
        if (viewmodel.processTemplatemanual.NotificationSettingfornewaction!=null)
            binding.notificationSwitch.isChecked=viewmodel.processTemplatemanual.NotificationSettingfornewaction==true
        if (viewmodel.processTemplatemanual.Automaticescalationofoverdueitems!=null)
            binding.AutoEsclationSwitch.isChecked=viewmodel.processTemplatemanual.Automaticescalationofoverdueitems==true
        if (viewmodel.processTemplatemanual.Reminderofoutstandingactions!=null)
                binding.reminderSwitch.isChecked=viewmodel.processTemplatemanual.Reminderofoutstandingactions==true
        if (viewmodel.processTemplatemanual.NotificationSettingGeoTracking!=null)
                binding.GeoTrackingSwitch.isChecked=viewmodel.processTemplatemanual.NotificationSettingGeoTracking==true
        if (binding.GeoTrackingSwitch.isChecked){
            binding.GeoFEncingLayout.visibility=View.VISIBLE
            if (viewmodel.processTemplatemanual.NotificationSettingGeoFencing!=null)
                binding.GeoFencingDistanceSwitch.isChecked=viewmodel.processTemplatemanual.NotificationSettingGeoFencing==true
            if (binding.GeoFencingDistanceSwitch.isChecked){
                binding.GeoFencingDistanceEditLayout.visibility=View.VISIBLE
                binding.GeoFencingDistanceEdit.setText(viewmodel.processTemplatemanual.Distance)
            }
            else
                binding.GeoFencingDistanceEditLayout.visibility=View.GONE
        }
        else
            binding.GeoFEncingLayout.visibility=View.GONE
    }

    private fun taskInfoObserver(){
        if (viewmodel.getTaskInfoResponse!!.hasActiveObservers())
            viewmodel.getTaskInfoResponse!!.removeObservers(viewLifecycleOwner)
        viewmodel.getTaskInfoResponse!!.observe(viewLifecycleOwner, Observer {
            if (it?.data != null) {
                try {
                    taskInfo= it.data[0]
                }catch (e:Exception){
                    AppLogger.log("Somthing went wront to fetch task info ${e.localizedMessage}")
                }
            }else AppLogger.log("Department not fetched")

        })
    }
}