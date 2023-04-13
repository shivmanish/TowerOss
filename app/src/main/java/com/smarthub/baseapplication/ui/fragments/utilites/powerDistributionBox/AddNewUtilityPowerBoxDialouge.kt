package com.smarthub.baseapplication.ui.fragments.utilites.powerDistributionBox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.UtilityAddNewPowerDistributionboxBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentAllData
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentPowerDistributionBox
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilitySMPSEquipment
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtiltyInstallationAcceptence
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate.UpdateUtilityEquipmentAllData
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AddNewUtilityPowerBoxDialouge (var fullData:UtilityEquipmentAllData?, var listner:AddUtilityPowerBoxDataListener) : BaseBottomSheetDialogFragment(){

    lateinit var binding: UtilityAddNewPowerDistributionboxBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = UtilityAddNewPowerDistributionboxBinding.inflate(inflater)
        viewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.75).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        
        AppPreferences.getInstance().setDropDown(binding.VendorNameEdit,DropDowns.VendorCompany.name)
        AppPreferences.getInstance().setDropDown(binding.TypeEdit,DropDowns.PDBType.name)


        setDatePickerView(binding.InstallationDateEdit)


//
        binding.Submit.setOnClickListener {
            showProgressLayout()
            val equipData=UtilitySMPSEquipment()
            val insData= UtiltyInstallationAcceptence()
            equipData.let {
                it.Type=binding.TypeEdit.selectedValue.id
                it.SerialNumber=binding.SerialNumberEdit.text.toString()
                it.Make=binding.MakeEdit.text.toString()
                it.Model=binding.ModelEdit.text.toString()
                it.CapacityRating=binding.CapacityRatingEdit.text.toString()
                it.Count=binding.CountEdit.text.toString().toIntOrNull()
                it.remark=binding.remarksEdit.text.toString()
                it.Weight=binding.WeightEdit.text.toString()
                it.SizeL=binding.SizeLEdit.text.toString()
                it.SizeB=binding.SizeBEdit.text.toString()
                it.SizeH=binding.SizeHEdit.text.toString()
            }
            insData.let {
                it.VendorCompany= arrayListOf(binding.VendorNameEdit.selectedValue.id.toInt())
                it.VendorCode= binding.VendorCodeEdit.text.toString()
                it.InstallationDate= Utils.getFullFormatedDate(binding.InstallationDateEdit.text.toString())
            }
            val utilityPowerData= UtilityEquipmentPowerDistributionBox()
            utilityPowerData.Equipment= arrayListOf(equipData)
            utilityPowerData.InstallationAndAcceptence= arrayListOf(insData)
            val utilityAllDataModel = UpdateUtilityEquipmentAllData()
            utilityAllDataModel.UtilityEquipmentPowerDistributionBox= arrayListOf(utilityPowerData)
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
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.UtilityEquipmentPowerDistributionBox==200) {
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


    interface AddUtilityPowerBoxDataListener{
        fun addNewData()
    }

}