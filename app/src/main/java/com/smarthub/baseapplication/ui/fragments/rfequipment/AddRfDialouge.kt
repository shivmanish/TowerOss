package com.smarthub.baseapplication.ui.fragments.rfequipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.RfAddNewDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.dropdown.DropDownItem
import com.smarthub.baseapplication.ui.alert.model.request.GetUserList
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfSurvey
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfSurveyAssignTeam
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel
import com.smarthub.baseapplication.widgets.CustomSpinner

class AddRfDialouge (var id:String,var listner:AddRfSurveyDataListener) : BaseBottomSheetDialogFragment(){

    lateinit var binding: RfAddNewDialougeBinding
    lateinit var viewmodel: HomeViewModel
    lateinit var alertViewmodel: AlertViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = RfAddNewDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        alertViewmodel= ViewModelProvider(this)[AlertViewModel::class.java]

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.70).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        AppPreferences.getInstance().setDropDown(binding.geographyLevel,DropDowns.GeographyLevel.name)

        binding.Add.setOnClickListener {
            showProgressLayout()
            val data= RfSurveyAssignTeam()
            val dataModel = RfSurvey()
            data.let {
                it.remark = binding.remarksEdit.text.toString()
                it.ExecutiveName =
                    binding.excutiveName.selectedValue.First_Name + " " + binding.excutiveName.selectedValue.Last_Name
                it.Department = binding.department.getSelectedArray()
                it.GeographyLevel = binding.geographyLevel.selectedValue.name
                it.ExecutiveMobile = binding.AcquisitionExecutiveNumberEdit.text.toString()
            }
            dataModel.RfSurveyAssignTeam = arrayListOf(data)
            viewmodel.updateRfSurvey(dataModel)
        }
        binding.geographyLevel.itemSelectedListener=object : CustomSpinner.ItemSelectedListener{
            override fun itemSelected(geographySelected: DropDownItem) {
                viewmodel.getDepartment(geographySelected.name)
            }
        }
        binding.department.itemSelectedListener=object : CustomSpinner.ItemSelectedListener{
            override fun itemSelected(departmentName: DropDownItem) {
                alertViewmodel.getDepartmentUsers(GetUserList(departmentName.name,
                    AppController.getInstance().ownerName))
            }
        }

        if (viewmodel.updateRfSurveyDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateRfSurveyDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateRfSurveyDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                showProgressLayout()
                AppLogger.log("AddRfDialouge data adding in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status?.RfSurvey==200 ) {
                listner.addNewData()
                hideProgressLayout()
                dismiss()
                Toast.makeText(context,"Data Added successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS ){
                hideProgressLayout()
                Toast.makeText(context,"Something went wrong in add data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("AddRfDialouge Something went wrong in adding data")
            }
            else if (it != null) {
                AppLogger.log("AddRfDialouge error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("AddRfDialouge Something went wrong in adding data")

            }
        }
        hideProgressLayout()
        if (viewmodel.departmentDataDataResponse?.hasActiveObservers()==true)
            viewmodel.departmentDataDataResponse?.removeObservers(viewLifecycleOwner)
        viewmodel.departmentDataDataResponse?.observe(viewLifecycleOwner){
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("AddRfDialouge departmentDataDataResponse loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("AddRfDialouge departmentDataDataResponse loaded successfull ")
                binding.department.setSpinnerData(it.data.Department.data)
            }else AppLogger.log("Department not fetched")
        }


        if (alertViewmodel.userDataResponseLiveData.hasActiveObservers())
            alertViewmodel.userDataResponseLiveData.removeObservers(viewLifecycleOwner)
        alertViewmodel.userDataResponseLiveData.observe(viewLifecycleOwner){
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("AddRfDialouge userDataResponseLiveData loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("AddRfDialouge userDataResponseLiveData loaded successfull ")
                binding.excutiveName.setSpinnerData(it.data,null,binding.AcquisitionExecutiveNumberEdit)
            }else AppLogger.log("Department not fetched")
        }
    }

    override fun getTheme() = R.style.NewDialogTask


    fun showProgressLayout(){
        if (Utils.isNetworkConnected()) {
            if (binding.progressLayout.visibility != View.VISIBLE)
                binding.progressLayout.visibility = View.VISIBLE
        }
    }
    fun hideProgressLayout(){
        if (binding.progressLayout.visibility == View.VISIBLE)
            binding.progressLayout.visibility = View.GONE
    }


    interface AddRfSurveyDataListener{
        fun addNewData()
    }

}