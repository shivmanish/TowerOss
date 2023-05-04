package com.smarthub.baseapplication.ui.fragments.task.editdialog

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AssignTaskDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.dropdown.DropDownItem
import com.smarthub.baseapplication.model.home.MyTeamTask
import com.smarthub.baseapplication.model.register.dropdown.DropdownParam
import com.smarthub.baseapplication.model.taskModel.GeoGraphyLevelPostData
import com.smarthub.baseapplication.model.taskModel.assignTask.Assigntask
import com.smarthub.baseapplication.ui.alert.model.request.GetUserList
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponseItem
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.ui.fragments.task.TaskViewModel
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.viewmodels.HomeViewModel
import com.smarthub.baseapplication.widgets.CustomSpinner

class AssignTaskDialouge(contentLayoutId: Int,var task : MyTeamTask?,var homeViewModel: HomeViewModel) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding: AssignTaskDialougeBinding
    lateinit var viewmodel: AlertViewModel
    lateinit var taskViewmodel: TaskViewModel
    lateinit var homeviewmodel: HomeViewModel
    private lateinit var progressDialog : ProgressDialog
    var departmentList=ArrayList<DropDownItem>()
    var geoGraphyLevelList=ArrayList<DropDownItem>()
    var AssignToList= ArrayList<UserDataResponseItem>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AssignTaskDialougeBinding.inflate(inflater)
        viewmodel = ViewModelProvider(this).get(AlertViewModel::class.java)
        taskViewmodel = ViewModelProvider(this).get(TaskViewModel::class.java)
        homeviewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        taskViewmodel.getGeoGraphyData(GeoGraphyLevelPostData(""))
        observerGeographyData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(true)
        binding = AssignTaskDialougeBinding.bind(view)

        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        binding.submitTaskBtn.setOnClickListener {
            if (!progressDialog.isShowing)
                progressDialog.show()
            if(task!=null){
                val tempData=Assigntask()
                tempData.let {
                    it.id=task?.id1
                    it.workorderid=task?.workorderid
                    it.AssigneeDepartment=binding.assigneeDepartment.selectedValue.name
                    it.Taskname=task?.Taskname
                    it.actorname=binding.AssignTo.selectedValue.username
                }
                AppLogger.log("before updated Data for task Assign : $task")
                AppLogger.log("updated Data for task Assign : $tempData")
                taskViewmodel.taskAssign(tempData)
                AppLogger.log("updated Data Successfully : $tempData")

            }
            if (taskViewmodel.taskAssignResponse?.hasActiveObservers() == true)
                taskViewmodel.taskAssignResponse?.removeObservers(viewLifecycleOwner)
            taskViewmodel.taskAssignResponse?.observe(viewLifecycleOwner){
                if (progressDialog.isShowing){
                    progressDialog.dismiss()
                }
                if (it!=null && it.Message == "Data updated"){
                    Toast.makeText(context,"Task Assigned SuccessFully",Toast.LENGTH_LONG).show()
                    homeViewModel.fetchDropDownNew()
                    dismiss()
                }
                else {
                    Toast.makeText(context,"Something went wrong, Please Try Again ",Toast.LENGTH_LONG).show()
                    AppLogger.log("Something went wrong in Task Assign bottom sheet fragment: ${it.Error}")
                }
            }
        }

//        AppPreferences.getInstance().setDropDown(binding.AssigneeGeographyLevel, DropDowns.GeographyLevel.name)
        binding.taskName.text=task?.Taskname
        binding.WorkOrderNumber.text=task?.workorderid
        binding.AssigneeGeographyLevel.itemSelectedListener=object : CustomSpinner.ItemSelectedListener{
            override fun itemSelected(geographySelected: DropDownItem) {
                homeviewmodel.getDepartment(geographySelected.name)
            }
        }
        binding.assigneeDepartment.setOnItemSelectionListener(object : CustomSpinner.ItemSelectedListener{
            override fun itemSelected(departmentName: DropDownItem) {
                AppLogger.log("setOnItemSelectedListener :${departmentName.name}")
               // Toast.makeText(context,"setOnItemSelectedListener ${departmentName.name}",Toast.LENGTH_SHORT).show()
                viewmodel.getDepartmentUsers(GetUserList(departmentName.name,AppController.getInstance().ownerName))
                observerData()
            }
        })

//        if (viewmodel.departmentDropdown.hasActiveObservers())
//            viewmodel.departmentDropdown.removeObservers(viewLifecycleOwner)
//        viewmodel.departmentDropdown.observe(viewLifecycleOwner) {
//            if (it?.data != null) {
//                departmentList.clear()
//                var i=0
//                for (x in it.data.department){
//                    departmentList.add(DropDownItem(x,"$i"))
//                    i+=1
//                }
//                binding.assigneeDepartment.setSpinnerData(departmentList)
//            }else AppLogger.log("Department not fetched")
//              //  Toast.makeText(requireContext(),"Department not fetched", Toast.LENGTH_LONG).show()
//        }
        if (homeviewmodel.departmentDataDataResponse?.hasActiveObservers()==true)
            homeviewmodel.departmentDataDataResponse?.removeObservers(viewLifecycleOwner)
        homeviewmodel.departmentDataDataResponse?.observe(viewLifecycleOwner){
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("AddTaskInfoFragment departmentDataDataResponse loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("AddTaskInfoFragment departmentDataDataResponse loaded successfull ")
                if (task!=null)
                    binding.assigneeDepartment.setSpinnerData(it.data.Department.data,task?.AssigneeDepartment)
                else
                    binding.assigneeDepartment.setSpinnerData(it.data.Department.data)
            }else AppLogger.log("Department not fetched")
        }
        viewmodel.getDepartments(DropdownParam(AppController.getInstance().ownerName,"department"))

    }


    override fun getTheme() = R.style.NewDialogTask



//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val dialog = BottomSheetDialog(requireContext(), theme)
//        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
//        dialog.behavior.skipCollapsed = false
//        return dialog
//    }

    private fun observerData() {
        if (viewmodel.userDataResponseLiveData.hasActiveObservers())
            viewmodel.userDataResponseLiveData.removeObservers(viewLifecycleOwner)
        viewmodel.userDataResponseLiveData.observe(viewLifecycleOwner, Observer {
            if (it?.data != null) {
                AssignToList.clear()
                AssignToList.addAll(it.data)
                if(task != null)
                    binding.AssignTo.setSpinnerDataByPhonNumber(it.data,task?.actorname)
                else
                    binding.AssignTo.setSpinnerData(it.data)
            }else AppLogger.log("Department not fetched")
               // Toast.makeText(requireContext(),"Department not fetched",Toast.LENGTH_LONG).show()
        })
    }
    private fun observerGeographyData() {
        if (taskViewmodel.geoGraphyLevelDataResponse?.hasActiveObservers()==true)
            taskViewmodel.geoGraphyLevelDataResponse?.removeObservers(viewLifecycleOwner)
        taskViewmodel.geoGraphyLevelDataResponse?.observe(viewLifecycleOwner, Observer {
            if (it?.data != null) {
                geoGraphyLevelList.clear()
                var i=0
                for (x in it.data.Data){
                    geoGraphyLevelList.add(DropDownItem(x,"$i"))
                    i+=1
                }
                AppLogger.log("GeoGraphy Level: ${geoGraphyLevelList}")
                binding.AssigneeGeographyLevel.setSpinnerData(geoGraphyLevelList)
            }else AppLogger.log("Department not fetched")
            // Toast.makeText(requireContext(),"Department not fetched",Toast.LENGTH_LONG).show()

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (taskViewmodel.taskAssignResponse?.hasActiveObservers() == true)
            taskViewmodel.taskAssignResponse?.removeObservers(viewLifecycleOwner)
        if (viewmodel.departmentDropdown.hasActiveObservers())
            viewmodel.departmentDropdown.removeObservers(viewLifecycleOwner)
        if (taskViewmodel.geoGraphyLevelDataResponse!!.hasActiveObservers())
            taskViewmodel.geoGraphyLevelDataResponse!!.removeObservers(viewLifecycleOwner)
        if (viewmodel.userDataResponseLiveData.hasActiveObservers())
            viewmodel.userDataResponseLiveData.removeObservers(viewLifecycleOwner)
    }

}