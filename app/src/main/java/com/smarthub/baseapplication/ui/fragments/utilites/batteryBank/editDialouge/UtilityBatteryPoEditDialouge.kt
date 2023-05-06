package com.smarthub.baseapplication.ui.fragments.utilites.batteryBank.editDialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AcqPoEditDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentBatteryBank
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityPoDetails
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate.UpdateUtilityEquipmentAllData
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class UtilityBatteryPoEditDialouge (var data: UtilityPoDetails, var fullData: UtilityEquipmentBatteryBank?, var smpsAllDataId: Int?, var listner: UtilityPoUpdateListener) : BaseBottomSheetDialogFragment(){

    lateinit var binding: AcqPoEditDialougeBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AcqPoEditDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.70).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        binding.PoItemEdit.setText(data.POItem)
        binding.PoNumberEdit.setText(data.PONumber)
        binding.PoAmountEdit.setText(data.POAmount)
        binding.PoLineNumberEdit.setText(data.POLineNo.toString())
        binding.remarksEdit.setText(data.remark)
        if (data.VendorCompany?.isNotEmpty()==true)
            AppPreferences.getInstance().setDropDown(binding.VendorNameEdit, DropDowns.VendorCompany.name, data.VendorCompany?.get(0).toString(),binding.VendorCodeEdit)
        else
            AppPreferences.getInstance().setDropDown(binding.VendorNameEdit, DropDowns.VendorCompany.name,binding.VendorCodeEdit)

        binding.PoDateEdit.text=Utils.getFormatedDate(data.PODate,"dd-MMM-yyyy")

        setDatePickerView( binding.PoDateEdit)

        binding.update.setOnClickListener {
            showProgressLayout()

            data.let {
                it.POAmount=if (binding.PoAmountEdit.text.toString().isNullOrEmpty()) "0" else binding.PoAmountEdit.text.toString()
                it.POItem=binding.PoItemEdit.text.toString()
                it.VendorCode=binding.VendorCodeEdit.text.toString()
                it.PONumber=binding.PoNumberEdit.text.toString()
                it.remark=binding.remarksEdit.text.toString()
                it.POLineNo=binding.PoLineNumberEdit.text.toString().toIntOrNull()
                it.PODate=Utils.getFullFormatedDate(binding.PoDateEdit.text.toString())
                it.VendorCompany= arrayListOf(binding.VendorNameEdit.selectedValue.id.toInt())

                val tempBatteryData= UtilityEquipmentBatteryBank()
                tempBatteryData.PODetail= arrayListOf(it)
                if (fullData!=null)
                    tempBatteryData.id=fullData?.id
                val utilityAllDataModel = UpdateUtilityEquipmentAllData()
                utilityAllDataModel.UtilityEquipmentBatteryBank= arrayListOf(tempBatteryData)
                if (smpsAllDataId!=null)
                    utilityAllDataModel.id=smpsAllDataId
                viewmodel.updateUtilityEquip(utilityAllDataModel)
            }
        }

        if (viewmodel.updateUtilityDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateUtilityDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateUtilityDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SmpsRectifierEditDialouge data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.UtilityEquipmentBatteryBank==200) {
                AppLogger.log("SmpsRectifierEditDialouge card Data fetched successfully")
                listner.updatedData()
                hideProgressLayout()
                dismiss()
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideProgressLayout()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("SmpsRectifierEditDialouge  Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("SmpsRectifierEditDialouge error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SmpsRectifierEditDialouge Something went wrong")

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


    interface UtilityPoUpdateListener{
        fun updatedData()
    }

}