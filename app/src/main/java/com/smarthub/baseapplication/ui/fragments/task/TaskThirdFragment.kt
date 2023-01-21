package com.smarthub.baseapplication.ui.fragments.task

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.databinding.TaskThirdFragmnetBinding
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.ui.fragments.register.RegistrationSetPasswordDirections
import com.smarthub.baseapplication.utils.AppLogger

class TaskThirdFragment:Fragment() {
    lateinit var binding:TaskThirdFragmnetBinding
    lateinit var viewmodel: TaskViewModel
    private lateinit var progressDialog : ProgressDialog

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

        binding.Submit.setOnClickListener {
            if (!progressDialog.isShowing)
                progressDialog.show()
            viewmodel.processTemplatemanual.NotificationSettingfornewaction=binding.notificationSwitch.isChecked
            viewmodel.processTemplatemanual.Automaticescalationofoverdueitems=binding.AutoEsclationSwitch.isChecked
            viewmodel.processTemplatemanual.Reminderofoutstandingactions=binding.reminderSwitch.isChecked
            AppLogger.log("all data: ${viewmodel.processTemplatemanual}")
            viewmodel.createNewTask()

            if (viewmodel?.getCreateNewTask()?.hasActiveObservers() == true)
                viewmodel?.getCreateNewTask()?.removeObservers(viewLifecycleOwner)
            viewmodel?.getCreateNewTask()?.observe(viewLifecycleOwner){
                if (progressDialog.isShowing){
                    progressDialog.dismiss()
                }
                if (it!=null && it.Message == "Data updated"){
                    Toast.makeText(context,"Task Created SuccessFully",Toast.LENGTH_LONG).show()
                }
                else {
                    Toast.makeText(context,"Something went wrong ",Toast.LENGTH_LONG).show()
                    AppLogger.log("Something went wrong in createTask fragment: ${it.Error}")
                }
            }
        }
    }


}