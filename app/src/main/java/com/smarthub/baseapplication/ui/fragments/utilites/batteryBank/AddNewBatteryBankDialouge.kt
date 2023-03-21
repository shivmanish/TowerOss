package com.smarthub.baseapplication.ui.fragments.utilites.batteryBank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AddNewBatteryBankDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentAllData
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentBatteryBank
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilitySMPSEquipment
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate.UpdateUtilityEquipmentAllData
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AddNewBatteryBankDialouge (var fullData:ArrayList<UtilityEquipmentAllData>?, var listner:AddBatteryBankDataListener) : BaseBottomSheetDialogFragment(){

    lateinit var binding: AddNewBatteryBankDialougeBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AddNewBatteryBankDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.80).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        
        AppPreferences.getInstance().setDropDown(binding.OperationalStatusEdit,DropDowns.OperationStatus.name)


        setDatePickerView(binding.WarrantyExpiryDateEdit)
        setMonthYearView(binding.ManufacturingMonthYearEdit)


//
        binding.Submit.setOnClickListener {
            showProgressLayout()
            val data=UtilitySMPSEquipment()
            data.let {
                it.Type=binding.TypeEdit.text.toString()
                it.SerialNumber=binding.SerialNumberEdit.text.toString()
                it.Make=binding.MakeEdit.text.toString()
                it.Model=Utils.getFullFormatedDate(binding.ModelEdit.text.toString())
                it.VoltageMax=binding.maxVoltageRatingEdit.text.toString()
                it.VoltageMin=binding.minVoltageRatingEdit.text.toString()
                it.CapacityRating=binding.CapacityRatingEdit.text.toString()
                it.SizeL=binding.SizeLEdit.text.toString()
                it.SizeB=binding.SizeBEdit.text.toString()
                it.SizeH=binding.SizeHEdit.text.toString()
                it.Weight=binding.WeightEdit.text.toString()
                it.ManufacturedOn=Utils.getFullFormatedDate(binding.ManufacturingMonthYearEdit.text.toString())
                it.WarrantyExpiryDate=Utils.getFullFormatedDate(binding.WarrantyExpiryDateEdit.text.toString())
                it.WarrantyPeriod=binding.WarrantyPeriodEdit.text.toString()
                it.InstalledLocationType=binding.InstallationLocationTypeEdit.text.toString().toIntOrNull()
                it.RackUSpaceUsed=binding.BatteryCellCountEdit.text.toString().toIntOrNull()
                it.OperationStatus = arrayListOf(binding.OperationalStatusEdit.selectedValue.id.toInt())
                it.Remark=binding.remarksEdit.text.toString()

                val utilitySMPSData= UtilityEquipmentBatteryBank()
                utilitySMPSData.Equipment= arrayListOf(it)
                val utilityAllDataModel = UpdateUtilityEquipmentAllData()
                utilityAllDataModel.UtilityEquipmentBatteryBank= arrayListOf(utilitySMPSData)
                if (fullData?.isNotEmpty()==true)
                    utilityAllDataModel.id=fullData?.get(0)?.id
                viewmodel.updateUtilityEquip(utilityAllDataModel)
            }
        }

        if (viewmodel.updateUtilityDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateUtilityDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateUtilityDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                showProgressLayout()
                AppLogger.log("SiteAgreemnets Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.UtilityEquipmentBatteryBank==200) {
                listner.addNewData()
                hideProgressLayout()
                dismiss()
                Toast.makeText(context,"Data Added successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideProgressLayout()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("SiteAgreemnets Fragment Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("SiteAgreemnets Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SiteAgreemnets Fragment Something went wrong")

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


    interface AddBatteryBankDataListener{
        fun addNewData()
    }

}