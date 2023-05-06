package com.smarthub.baseapplication.ui.fragments.rfequipment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AddNewRfSurveyDialougeBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfSurvey
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfSurvey1
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AddNewRfSurveyDialouge (var fullData:RfSurvey?, var listner: AddRfSurveyDataListener): BaseBottomSheetDialogFragment() {
    lateinit var binding : AddNewRfSurveyDialougeBinding
    lateinit var viewmodel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AddNewRfSurveyDialougeBinding.inflate(inflater)
        viewmodel= ViewModelProvider(this)[HomeViewModel::class.java]
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.5).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }

        setDatePickerView(binding.SurveyDateEdit)
        setDatePickerView(binding.DateEdit)

        binding.AddAction.setOnClickListener {
            showProgressLayout()
            val tempRfSurveyData= RfSurvey1()
            val tempRfSurveyAllData = RfSurvey()
            tempRfSurveyData.let {
                it.locLongitude=binding.LongitudeEdit.text.toString()
                it.locLongitude=binding.LatitudeEdit.text.toString()
                it.SurveyDate=Utils.getFullFormatedDate(binding.SurveyDateEdit.text.toString())
                it.ExtraDate=Utils.getFullFormatedDate(binding.DateEdit.text.toString())
                it.remark=binding.remarksEdit.text.toString()
                it.Description=binding.DescriptionEdit.text.toString()
            }
            if (fullData!=null){
                tempRfSurveyAllData.id=fullData?.id
            }
            tempRfSurveyAllData.RfSurvey1= arrayListOf(tempRfSurveyData)
            viewmodel.updateRfSurvey(tempRfSurveyAllData)
        }

        if (viewmodel.updateRfSurveyDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateRfSurveyDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateRfSurveyDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                showProgressLayout()
                AppLogger.log("AddNewRfSurveyDialouge Fragment data adding in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status?.RfSurvey==200) {
                listner.addNewData()
                hideProgressLayout()
                dismiss()
                Toast.makeText(context,"Data Added successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideProgressLayout()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("AddNewRfSurveyDialouge Fragment Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("AddNewRfSurveyDialouge Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("AddNewRfSurveyDialouge Fragment Something went wrong")

            }
        }
        hideProgressLayout()
    }

    override fun getTheme() = R.style.NewDialogTask


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = false
        return dialog
    }

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