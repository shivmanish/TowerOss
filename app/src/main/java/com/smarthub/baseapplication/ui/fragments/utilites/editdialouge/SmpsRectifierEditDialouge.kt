package com.smarthub.baseapplication.ui.fragments.utilites.editdialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.UtilitySmpsRectifireEditDialougeBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentSmp
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityRectifierModule
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate.UpdateUtilityEquipmentAllData
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class SmpsRectifierEditDialouge (var data: UtilityRectifierModule, var fullData: UtilityEquipmentSmp?,var smpsAllDataId: Int?, var listner:SmpsRectifierUpdateListener) : BaseBottomSheetDialogFragment(){

    lateinit var binding: UtilitySmpsRectifireEditDialougeBinding
    lateinit var viewmodel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = UtilitySmpsRectifireEditDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.60).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        binding.SlotNumberEdit.setText(data.SlotNo.toString())
        binding.SerialNumberEditEdit.setText(data.SerialNumber)
        binding.RatingEdit.setText(data.RatingAmp)
        binding.ModelEdit.setText(data.Model)
        binding.remarksEdit.setText(data.Remark)
        binding.InstallationDateEdit.text=Utils.getFormatedDate(data.InstallationDate,"dd-MMM-yyyy")
        binding.ManufacturingMonthYearEdit.text=Utils.getFormatedDateMonthYear(data.InstallationDate,"MMM-yyyy")

        setDatePickerView( binding.InstallationDateEdit)
        setDatePickerView( binding.ManufacturingMonthYearEdit)

        binding.update.setOnClickListener {
            showProgressLayout()
            data.let {
                it.SlotNo=binding.SlotNumberEdit.text.toString().toIntOrNull()
                it.SerialNumber=binding.SerialNumberEditEdit.text.toString()
                it.RatingAmp=binding.RatingEdit.text.toString()
                it.Model=binding.ModelEdit.text.toString()
                it.Remark=binding.remarksEdit.text.toString()
                it.InstallationDate=Utils.getFullFormatedDate(binding.InstallationDateEdit.text.toString())
                it.ManufacturedOn=Utils.getFullFormatedDate(binding.ManufacturingMonthYearEdit.text.toString())


                val tempSmpsData= UtilityEquipmentSmp()
                tempSmpsData.RectifierModule= arrayListOf(it)
                if (fullData!=null)
                    tempSmpsData.id=fullData?.id
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
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.RectifierModule==200) {
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


    interface SmpsRectifierUpdateListener{
        fun updatedData()
    }

}