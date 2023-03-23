package com.smarthub.baseapplication.ui.fragments.utilites.fireExtinguisher.editDialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SmpsConsumMaterialEditDialougeBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityConsumableMaterial
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentAC
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate.UpdateUtilityEquipmentAllData
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class FireExtConsumMaterialEditDialouge (var data: UtilityConsumableMaterial, var fullData: UtilityEquipmentAC?, var AllDataId: Int?, var listner:ACConsumMaterialUpdateListener) : BaseBottomSheetDialogFragment(){

    lateinit var binding: SmpsConsumMaterialEditDialougeBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SmpsConsumMaterialEditDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.60).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        binding.ItemNameEdit.setText(data.ItemName.toString())
        binding.TypeEdit.setText(data.ItemType)
        binding.MakeEdit.setText(data.Make)
        binding.ModelEdit.setText(data.Model)
        binding.UoMEdit.setText(data.UOM)
        binding.UsedQtyEdit.setText(data.UsedQty)
        binding.InstallationDateEdit.text=Utils.getFormatedDate(data.InstallationDate,"dd-MMM-yyyy")

        setDatePickerView( binding.InstallationDateEdit)

        binding.update.setOnClickListener {
            showProgressLayout()
            data.let {
                it.ItemName=binding.ItemNameEdit.text.toString()
                it.ItemType=binding.TypeEdit.text.toString()
                it.Make=binding.MakeEdit.text.toString()
                it.Model=binding.ModelEdit.text.toString()
                it.UOM=binding.UoMEdit.text.toString()
                it.UsedQty=binding.UsedQtyEdit.text.toString()
                it.InstallationDate=Utils.getFullFormatedDate(binding.InstallationDateEdit.text.toString())

                val tempACData= UtilityEquipmentAC()
                tempACData.ConsumableMaterial= arrayListOf(it)
                if (fullData!=null)
                    tempACData.id=fullData?.id
                val utilityAllDataModel = UpdateUtilityEquipmentAllData()
                utilityAllDataModel.UtilityEquipmentAC= arrayListOf(tempACData)
                if (AllDataId!=null)
                    utilityAllDataModel.id=AllDataId
                viewmodel.updateUtilityEquip(utilityAllDataModel)

            }


        }

        if (viewmodel.updateUtilityDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateUtilityDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateUtilityDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("ACConsumMaterialEditDialouge data updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.UtilityEquipmentAC==200) {
                AppLogger.log("ACConsumMaterialEditDialouge Data Updated successfully")
                listner.updatedData()
                hideProgressLayout()
                dismiss()
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideProgressLayout()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("ACConsumMaterialEditDialouge  Something went wrong in updating data")
            }
            else if (it != null) {
                AppLogger.log("ACConsumMaterialEditDialouge error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("ACConsumMaterialEditDialouge Something went wrong in updating data")
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


    interface ACConsumMaterialUpdateListener{
        fun updatedData()
    }

}