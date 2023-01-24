package com.smarthub.baseapplication.ui.fragments.task.editdialog

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
import com.smarthub.baseapplication.model.dropdown.DropDownItem
import com.smarthub.baseapplication.model.home.MyTeamTask
import com.smarthub.baseapplication.model.register.dropdown.DropdownParam
import com.smarthub.baseapplication.ui.alert.model.request.GetUserList
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponseItem
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.widgets.CustomSpinner
import com.smarthub.baseapplication.widgets.CustomUserSpinner

class AssignTaskDialouge(contentLayoutId: Int,var task : MyTeamTask?) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding: AssignTaskDialougeBinding
    lateinit var viewmodel: AlertViewModel
    var departmentList=ArrayList<DropDownItem>()
    var AssignToList= ArrayList<UserDataResponseItem>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AssignTaskDialougeBinding.inflate(inflater)
        viewmodel = ViewModelProvider(this).get(AlertViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AssignTaskDialougeBinding.bind(view)

        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        binding.closeTaskBtn.setOnClickListener {
            dismiss()
        }
        binding.taskName.text=task?.Taskname
        binding.WorkOrderNumber.text=task?.workorderid

        binding.assigneeDepartment.setOnItemSelectionListener(object : CustomSpinner.ItemSelectedListener{
            override fun itemSelected(departmentName: DropDownItem) {
                AppLogger.log("setOnItemSelectedListener :${departmentName.name}")
               // Toast.makeText(context,"setOnItemSelectedListener ${departmentName.name}",Toast.LENGTH_SHORT).show()
                viewmodel.getUser(GetUserList(departmentName.name,AppController.getInstance().ownerName))
                observerData()
            }
        })

        binding.AssignTo.setOnItemSelectionListener(object : CustomUserSpinner.ItemSelectedListener{
            override fun itemSelected(item: UserDataResponseItem) {
                AppLogger.log("Assign To setOnItemSelectedListener :${binding.AssignTo.selectedValue.phone} ${item.last_name}")
               // Toast.makeText(context,"Assign To setOnItemSelectedListener ${item.first_name}",Toast.LENGTH_SHORT).show()

            }
        })

        if (viewmodel.departmentDropdown.hasActiveObservers())
            viewmodel.departmentDropdown.removeObservers(viewLifecycleOwner)
        viewmodel.departmentDropdown.observe(viewLifecycleOwner) {
            if (it?.data != null) {
                departmentList.clear()
                var i=0
                for (x in it.data.department){
                    departmentList.add(DropDownItem(x,"$i"))
                    i+=1
                }
                binding.assigneeDepartment.setSpinnerData(departmentList)
            }else AppLogger.log("Department not fetched")
              //  Toast.makeText(requireContext(),"Department not fetched", Toast.LENGTH_LONG).show()
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
                binding.AssignTo.setSpinnerData(AssignToList)
            }else AppLogger.log("Department not fetched")
               // Toast.makeText(requireContext(),"Department not fetched",Toast.LENGTH_LONG).show()

        })
    }

}