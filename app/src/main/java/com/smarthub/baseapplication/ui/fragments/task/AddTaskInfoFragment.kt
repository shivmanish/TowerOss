package com.smarthub.baseapplication.ui.fragments.task

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentAddTaskInfoBinding
import com.smarthub.baseapplication.model.dropdown.DropDownItem
import com.smarthub.baseapplication.model.register.dropdown.DropdownParam
import com.smarthub.baseapplication.ui.alert.dialog.AlertUserListBottomSheet
import com.smarthub.baseapplication.ui.alert.dialog.SubmitAletrBottomSheet
import com.smarthub.baseapplication.ui.alert.model.request.GetUserList
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponseItem
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.MainViewModel
import com.smarthub.baseapplication.widgets.CustomSpinner
import com.smarthub.baseapplication.widgets.CustomUserSpinner

class AddTaskInfoFragment : BaseFragment() {
    lateinit var viewmodel: AlertViewModel
    lateinit var taskViewmodel: TaskViewModel
    lateinit var binding: FragmentAddTaskInfoBinding
    private lateinit var mainViewModel:MainViewModel
    var taskForASingleSiteList=ArrayList<DropDownItem>()
    var PRiorityList=ArrayList<DropDownItem>()
    var departmentList=ArrayList<DropDownItem>()
    var AssignToList= ArrayList<UserDataResponseItem>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this).get(AlertViewModel::class.java)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        taskViewmodel=ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        mainViewModel.isActionBarHide(false)
        binding = FragmentAddTaskInfoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         binding.next.setOnClickListener{
             taskViewmodel.processTemplatemanual.Taskname=binding.TaskName.text.toString()
             taskViewmodel.processTemplatemanual.Taskinstruction=binding.taskInstruction.text.toString()
             taskViewmodel.processTemplatemanual.startdate=binding.startDate.text.toString()
             taskViewmodel.processTemplatemanual.enddate=binding.endDate.text.toString()
             taskViewmodel.processTemplatemanual.SLA=Integer.parseInt(binding.sla.text.toString())
             taskViewmodel.processTemplatemanual.Weightage=binding.Weightage.text.toString()
             taskViewmodel.processTemplatemanual.Taskname=binding.TaskName.text.toString()
             taskViewmodel.processTemplatemanual.AssigneeDepartment=binding.assigneeDepartment.selectedValue.name
             taskViewmodel.processTemplatemanual.actorname=binding.assignTo.selectedValue.phone
             taskViewmodel.processTemplatemanual.priority=binding.priority.selectedValue.name
            findNavController().navigate(R.id.actionToMoveSecondFrag)
        }
        binding.cancel.setOnClickListener {
            requireActivity().finish()
        }

        setDatePickerView(binding.startDate)
        setDatePickerView(binding.endDate)

        binding.endDate.addTextChangedListener(object : TextWatcher{
            var previoustext:String=""
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                previoustext=binding.endDate.text.toString()
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val cmp=Utils.dateDiffrence(binding.startDate.text.toString(),binding.endDate.text.toString())
                if(cmp<0){
                    Toast.makeText(context,"Invalid End date", Toast.LENGTH_SHORT).show()
                    binding.sla.text="0"
                }
                else{
                    binding.sla.text=String.format("%02d",cmp)
                }
            }

        })


        taskForASingleSiteList.add(DropDownItem("Yes","0"))
        taskForASingleSiteList.add(DropDownItem("No","1"))
        binding.taskForASingleSite.setSpinnerData(taskForASingleSiteList)
        PRiorityList.addAll(listOf(
            DropDownItem("Critical","0"),
            DropDownItem("Low","1"),
            DropDownItem("Medium","2"),
            DropDownItem("High","3")
        ))
        binding.priority.setSpinnerData(PRiorityList)
        binding.assigneeDepartment.setOnItemSelectionListener(object : CustomSpinner.ItemSelectedListener{
            override fun itemSelected(departmentName: DropDownItem) {
                AppLogger.log("setOnItemSelectedListener :${departmentName.name}")
                Toast.makeText(context,"setOnItemSelectedListener ${departmentName.name}",Toast.LENGTH_SHORT).show()
                viewmodel.getUser(GetUserList(departmentName.name))
                observerData()
            }
        })
        binding.assignTo.setOnItemSelectionListener(object : CustomUserSpinner.ItemSelectedListener{
            override fun itemSelected(item: UserDataResponseItem) {
                AppLogger.log("Assign To setOnItemSelectedListener :${binding.assignTo.selectedValue.phone} ${item.last_name}")
                Toast.makeText(context,"Assign To setOnItemSelectedListener ${item.first_name}",Toast.LENGTH_SHORT).show()

            }
        })

        if (viewmodel.departmentDropdown.hasActiveObservers())
            viewmodel.departmentDropdown.removeObservers(viewLifecycleOwner)
        viewmodel.departmentDropdown.observe(viewLifecycleOwner) {
            if (it?.data != null) {
                departmentList.clear()
                var i=0
                for (x in it.data.department){
                    departmentList.add(DropDownItem("$x","$i"))
                    i+=1
                }
                binding?.assigneeDepartment?.setSpinnerData(departmentList)
            }else Toast.makeText(requireContext(),"Department not fetched",Toast.LENGTH_LONG).show()
        }
        viewmodel.getDepartments(DropdownParam("SMRT","department"))

    }
    private fun observerData() {
        if (viewmodel.userDataResponseLiveData.hasActiveObservers())
            viewmodel.userDataResponseLiveData.removeObservers(viewLifecycleOwner)
        viewmodel.userDataResponseLiveData.observe(viewLifecycleOwner, Observer {
            if (it?.data != null) {
               AssignToList.clear()
               AssignToList.addAll(it.data)
                binding.assignTo.setSpinnerData(AssignToList)
            }else Toast.makeText(requireContext(),"Department not fetched",Toast.LENGTH_LONG).show()

        })
    }

}