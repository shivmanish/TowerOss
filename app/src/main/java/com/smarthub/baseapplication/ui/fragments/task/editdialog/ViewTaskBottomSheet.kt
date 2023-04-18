package com.smarthub.baseapplication.ui.fragments.task.editdialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.TaskDetailActivity
import com.smarthub.baseapplication.databinding.CloseTaskBottomSheetBinding
import com.smarthub.baseapplication.model.home.MyTeamTask
import com.smarthub.baseapplication.ui.alert.model.request.GetUserList
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponseItem
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.ui.fragments.task.routes
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
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
            intent.putExtra("url", task.id1)
            if (task.Where.isNotEmpty())
                intent.putExtra("where", task.Where)
            intent.putExtra("siteId", task.siteid)
            intent.putExtra("Distance", task.Distance)
            intent.putExtra("NotificationSettingGeoFencing", task.NotificationSettingGeoFencing)
            intent.putExtra("Trackingflag", task.Trackingflag)
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
                    if(x.username==task.actorname){
                        binding.assignTo.text= "${x.First_Name} ${x.Last_Name}"
                        assignTo="${x.First_Name} ${x.Last_Name}"
                        AppLogger.log("user  :assign to : $assignTo  first name: ${x.First_Name} last name: ${x.Last_Name} text: ${binding.assignTo.text}")
                        return@Observer
                    }
                }
                AppLogger.log("user not found list : $AssignToList")

            }else AppLogger.log("Department not fetched")
        })
    }





}