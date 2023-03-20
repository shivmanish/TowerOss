package com.smarthub.baseapplication.ui.fragments.utilites.viewDialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SmpsRectifireViewDialougeBinding
import com.smarthub.baseapplication.databinding.TowerPoViewDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqPODetail
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityRectifierModule
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class SmpsRectifireViewDialouge (contentLayoutId: Int, var data: UtilityRectifierModule) : BottomSheetDialogFragment(contentLayoutId){

    lateinit var binding: SmpsRectifireViewDialougeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SmpsRectifireViewDialougeBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }
        binding.SlotNumber.text=data.SlotNo.toString()
        binding.SerialNumber.text=data.SerialNumber
        binding.Rating.text=data.RatingAmp
        binding.Model.text=data.Model
        binding.remark.text=data.Remark
        binding.InstallationDate.text=Utils.getFormatedDate(data.InstallationDate,"dd-MMM-yyyy")
        binding.ManufacturingMonthYear.text=Utils.getFormatedDateMonthYear(data.InstallationDate,"MMM-yyyy")


    }

    override fun getTheme() = R.style.NewDialogTask




}