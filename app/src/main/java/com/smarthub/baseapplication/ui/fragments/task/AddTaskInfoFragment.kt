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
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.MainViewModel

class AddTaskInfoFragment : BaseFragment() {
    lateinit var viewmodel: AlertViewModel
    lateinit var binding: FragmentAddTaskInfoBinding
    private lateinit var mainViewModel:MainViewModel
    var taskForASingleSiteList=ArrayList<DropDownItem>()
    var PRiorityList=ArrayList<DropDownItem>()
    var departmentList=ArrayList<DropDownItem>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this).get(AlertViewModel::class.java)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        mainViewModel.isActionBarHide(false)
        binding = FragmentAddTaskInfoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerData()
         binding.next.setOnClickListener{
            findNavController().navigate(
                R.id.actionToMoveSecondFrag
            )
        }
        binding.cancel.setOnClickListener {
            findNavController().popBackStack()
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
                    binding.endDate.text=previoustext
                    Toast.makeText(context,"Invalid End date", Toast.LENGTH_SHORT).show()
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

    }
    private fun observerData() {
        if (viewmodel.userDataResponseLiveData.hasActiveObservers())
            viewmodel.userDataResponseLiveData.removeObservers(viewLifecycleOwner)
        viewmodel.userDataResponseLiveData.observe(viewLifecycleOwner, Observer {
            viewmodel.userDataList.clear()
            viewmodel.userDataList.addAll(it.data!!)

        })


        viewmodel.getDepartments(DropdownParam("SMRT","department"))
    }

}