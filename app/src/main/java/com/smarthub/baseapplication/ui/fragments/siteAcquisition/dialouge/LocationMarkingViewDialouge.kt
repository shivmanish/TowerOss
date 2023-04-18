package com.smarthub.baseapplication.ui.fragments.siteAcquisition.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AcqLocationMarkingViewDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqLocationMarking
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.DropDowns

class LocationMarkingViewDialouge ( var data: SAcqLocationMarking) : BaseBottomSheetDialogFragment(){

    lateinit var binding: AcqLocationMarkingViewDialougeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }
        if (data.DirectionA!=null && data.DirectionA?.isNotEmpty()==true)
            AppPreferences.getInstance().setDropDown(binding.BoundaryInDirectionA, DropDowns.Direction.name,data.DirectionA?.get(0).toString())
       if (data.DirectionB!=null && data.DirectionB?.isNotEmpty()==true)
            AppPreferences.getInstance().setDropDown(binding.BoundaryInDirectionB, DropDowns.Direction.name,data.DirectionB?.get(0).toString())
        if (data.MarkedInLayout!=null && data.MarkedInLayout !!>0)
            AppPreferences.getInstance().setDropDown(binding.MarkedInLayout, DropDowns.YesNoDropdown.name,data.MarkedInLayout.toString())

        binding.Object.text=data.Object
        binding.DistanceA.text=data.DistanceA
        binding.DistanceB.text=data.DistanceB
        binding.remark.text=data.remark

    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AcqLocationMarkingViewDialougeBinding.inflate(inflater)
        return binding.root
    }



}