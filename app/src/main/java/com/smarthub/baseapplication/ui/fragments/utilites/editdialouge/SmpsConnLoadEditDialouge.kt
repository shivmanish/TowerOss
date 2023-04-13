package com.smarthub.baseapplication.ui.fragments.utilites.editdialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SmpsConnLoadEditDialougeBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityConnectedLoad
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentSmp
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate.UpdateUtilityEquipmentAllData
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class SmpsConnLoadEditDialouge (var data: UtilityConnectedLoad, var fullData: UtilityEquipmentSmp?, var smpsAllDataId: Int?, var listner:SmpsConnLoadUpdateListener) : BaseBottomSheetDialogFragment(){

    lateinit var binding: SmpsConnLoadEditDialougeBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SmpsConnLoadEditDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.60).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        binding.MCBNumberEdit.setText(data.MCBNumber.toString())
        binding.ConnectedEquipmentEdit.setText(data.ConnectedEquipment)
        binding.MCBRatingEdit.setText(data.RatingAmp)
        binding.ActualReadingEdit.setText(data.ActualReading)
        binding.remarksEdit.setText(data.remark)
        binding.InstallationDateEdit.text=Utils.getFormatedDate(data.InstallationDate,"dd-MMM-yyyy")

        setDatePickerView( binding.InstallationDateEdit)

        binding.update.setOnClickListener {
            showProgressLayout()
            data.let {
                it.MCBNumber=binding.MCBNumberEdit.text.toString().toIntOrNull()
                it.ConnectedEquipment=binding.ConnectedEquipmentEdit.text.toString()
                it.RatingAmp=binding.MCBRatingEdit.text.toString()
                it.ActualReading=binding.ActualReadingEdit.text.toString()
                it.remark=binding.remarksEdit.text.toString()
                it.InstallationDate=Utils.getFullFormatedDate(binding.InstallationDateEdit.text.toString())

                // add po data to agreement model list
                val tempSmpsData= UtilityEquipmentSmp()
                tempSmpsData.ConnectedLoad= arrayListOf(it)
                if (fullData!=null)
                    tempSmpsData.id=fullData?.id
                //add add agreement model to update rootModel
                val utilityAllDataModel = UpdateUtilityEquipmentAllData()
                utilityAllDataModel.UtilityEquipmentSmps= arrayListOf(tempSmpsData)
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
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.UtilityEquipmentSmps==200) {
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


    interface SmpsConnLoadUpdateListener{
        fun updatedData()
    }

}