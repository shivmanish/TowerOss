package com.smarthub.baseapplication.ui.fragments.utilites.utilityCables

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.UtilityAddNewCableBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.*
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate.UpdateUtilityEquipmentAllData
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AddNewUtilityCableDialouge (var fullData:UtilityEquipmentAllData?, var listner:AddUtilityCableDataListener) : BaseBottomSheetDialogFragment(){

    lateinit var binding: UtilityAddNewCableBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = UtilityAddNewCableBinding.inflate(inflater)
        viewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.70).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        
        AppPreferences.getInstance().setDropDown(binding.VendorNameEdit,DropDowns.VendorCompany.name)
        AppPreferences.getInstance().setDropDown(binding.CableNameEdit,DropDowns.CableName.name)


        setDatePickerView(binding.InstallationDateEdit)


//
        binding.Submit.setOnClickListener {
            showProgressLayout()
            val cableData= UtilityCableDetail()
            cableData.let {
                it.CableName= arrayListOf(binding.CableNameEdit.selectedValue.id.toInt())
                it.CableType=binding.TypeEdit.text.toString()
                it.UsedFor=binding.UsedForEdit.text.toString()
                it.Length=binding.LengthEdit.text.toString()
                it.InstallationDate=Utils.getFullFormatedDate(binding.InstallationDateEdit.text.toString())
                it.VendorCompany= arrayListOf(binding.VendorNameEdit.selectedValue.id.toInt())
                it.VendorCode=binding.VendorCodeEdit.text.toString()
                it.Remark=binding.remarksEdit.text.toString()
            }

            val utilityAllDataModel = UpdateUtilityEquipmentAllData()
            utilityAllDataModel.CableDetail= arrayListOf(cableData)
            if (fullData!=null)
                utilityAllDataModel.id=fullData?.id
            viewmodel.updateUtilityEquip(utilityAllDataModel)
        }

        if (viewmodel.updateUtilityDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateUtilityDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateUtilityDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                showProgressLayout()
                AppLogger.log("AddNewUtilityPowerBoxDialouge Fragment data Updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.CableDetail==200) {
                listner.addNewData()
                hideProgressLayout()
                dismiss()
                Toast.makeText(context,"Data Added successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideProgressLayout()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("AddNewUtilityPowerBoxDialouge Fragment Something went wrong in updating data")
            }
            else if (it != null) {
                AppLogger.log("AddNewUtilityPowerBoxDialouge Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("AddNewUtilityPowerBoxDialouge Fragment Something went wrong in updating data")

            }
        }
        hideProgressLayout()
    }

    override fun getTheme() = R.style.NewDialogTask


    fun showProgressLayout(){
        if (binding.progressLayout.visibility != View.VISIBLE)
            binding.progressLayout.visibility = View.VISIBLE
    }
    fun hideProgressLayout(){
        if (binding.progressLayout.visibility == View.VISIBLE)
            binding.progressLayout.visibility = View.GONE
    }


    interface AddUtilityCableDataListener{
        fun addNewData()
    }

}