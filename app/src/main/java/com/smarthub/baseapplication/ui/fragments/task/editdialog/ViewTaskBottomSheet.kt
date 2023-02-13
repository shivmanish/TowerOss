package com.smarthub.baseapplication.ui.fragments.task.editdialog

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.TaskDetailActivity
import com.smarthub.baseapplication.databinding.CloseTaskBottomSheetBinding
import com.smarthub.baseapplication.model.home.MyTeamTask
import com.smarthub.baseapplication.model.taskModel.TaskInfoItem
import com.smarthub.baseapplication.ui.alert.model.request.GetUserList
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponseItem
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.ui.fragments.task.TaskViewModel
import com.smarthub.baseapplication.ui.fragments.task.routes
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel


class ViewTaskBottomSheet(contentLayoutId: Int, val task: MyTeamTask, var viewModel: HomeViewModel, var route:String) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding: CloseTaskBottomSheetBinding
    var AssignToList= ArrayList<UserDataResponseItem>()
    lateinit var viewmodel: AlertViewModel
    var assignTo:String?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this).get(AlertViewModel::class.java)
        binding = CloseTaskBottomSheetBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        if(route==routes.MyTeamTaskNavigator.name){
            binding.closeTaskBtn.visibility=View.GONE
        }
        observData()
        viewmodel.getUser(GetUserList(task.AssigneeDepartment, AppController.getInstance().ownerName))
        binding.closeTaskBtn.setOnClickListener {
            val intent = Intent (requireActivity(), TaskDetailActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("url", "${task.Taskid}")
            intent.putExtra("siteId","${task.siteid}")
            requireActivity().startActivity(intent)
        }

        binding.taskInstruction.text=task.Taskinstruction
        binding.siteId.text=task.sitename
        binding.startDate.text=task.startdate
        binding.endDate.text=task.enddate
        binding.Weightage.text=task.Weightage
        binding.sla.text=task.SLA
        binding.asigneeDepartment.text=task.AssigneeDepartment
       binding.switch1.isChecked=task.NotificationSettingfornewaction=="True"
        binding.switch2.isChecked=task.Reminderofoutstandingactions=="True"
        binding.switch3.isChecked=task.Automaticescalationofoverdueitems=="True"
        if(assignTo!=null){
            binding.assignTo.text=assignTo
        }




    }


    override fun getTheme() = R.style.NewDialogTask

    fun observData(){
        if (viewmodel.userDataResponseLiveData.hasActiveObservers())
            viewmodel.userDataResponseLiveData.removeObservers(viewLifecycleOwner)
        viewmodel.userDataResponseLiveData.observe(viewLifecycleOwner, Observer {
            if (it?.data != null) {
                AssignToList.clear()
                AssignToList.addAll(it.data)
                for(x in AssignToList){
                    if(x.phone==task.actorname){
                        binding.assignTo.text= "${x.first_name} ${x.last_name}"
                        assignTo="${x.first_name} ${x.last_name}"
                        AppLogger.log("user  :assign to : $assignTo  first name: ${x.first_name} last name: ${x.last_name} text: ${binding.assignTo.text}")
                        return@Observer
                    }
                }
                AppLogger.log("user not found list : $AssignToList")

            }else AppLogger.log("Department not fetched")
        })
    }





}