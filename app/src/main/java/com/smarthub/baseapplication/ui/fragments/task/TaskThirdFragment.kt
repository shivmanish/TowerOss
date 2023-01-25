package com.smarthub.baseapplication.ui.fragments.task

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.smarthub.baseapplication.databinding.TaskThirdFragmnetBinding
import com.smarthub.baseapplication.model.home.MyTeamTask
import com.smarthub.baseapplication.model.taskModel.TaskInfoItem
import com.smarthub.baseapplication.utils.AppLogger

class TaskThirdFragment:Fragment() {
    lateinit var binding:TaskThirdFragmnetBinding
    lateinit var viewmodel: TaskViewModel
    private lateinit var progressDialog : ProgressDialog
    var taskInfo : TaskInfoItem? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        binding = TaskThirdFragmnetBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(true)
        if(requireActivity().intent.hasExtra("data")){
            var data = Gson().fromJson(requireActivity().intent.getStringExtra("data"), MyTeamTask::class.java)
            viewmodel.getTaskById(data.id1)
            taskInfoObserver()
        }
        if(taskInfo!=null){
            binding.notificationSwitch.isChecked= taskInfo?.NotificationSettingfornewaction=="True"
            binding.AutoEsclationSwitch.isChecked= taskInfo?.Automaticescalationofoverdueitems=="True"
            binding.reminderSwitch.isChecked= taskInfo?.Reminderofoutstandingactions=="True"
        }
        binding.Submit.setOnClickListener {
            if (!progressDialog.isShowing)
                progressDialog.show()
            viewmodel.processTemplatemanual.NotificationSettingfornewaction=binding.notificationSwitch.isChecked
            viewmodel.processTemplatemanual.Automaticescalationofoverdueitems=binding.AutoEsclationSwitch.isChecked
            viewmodel.processTemplatemanual.Reminderofoutstandingactions=binding.reminderSwitch.isChecked
            if(taskInfo!=null)
                viewmodel.processTemplatemanual.Taskid=taskInfo?.id?.toInt()!!
            AppLogger.log("all data: ${viewmodel.processTemplatemanual}")
            viewmodel.createNewTask()

            if (viewmodel.getCreateNewTask()?.hasActiveObservers() == true)
                viewmodel.getCreateNewTask()?.removeObservers(viewLifecycleOwner)
            viewmodel.getCreateNewTask()?.observe(viewLifecycleOwner){
                if (progressDialog.isShowing){
                    progressDialog.dismiss()
                }
                if (it!=null && it.Message == "Data updated"){
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