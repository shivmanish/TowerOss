package com.smarthub.baseapplication.ui.fragments.siteAcquisition.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OutsidePremisesViewDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqOutsidePremise
import com.smarthub.baseapplication.utils.DropDowns

class OutsidePremisesViewDialouge (contentLayoutId: Int, var data: SAcqOutsidePremise) : BottomSheetDialogFragment(contentLayoutId){

    lateinit var binding: OutsidePremisesViewDialougeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }
        if (data.ExternalStructureType!=null && data.ExternalStructureType?.isNotEmpty()==true)
            AppPreferences.getInstance().setDropDown(binding.ExternalStructureType, DropDowns.ExternalStructureType.name,data.ExternalStructureType?.get(0).toString())
       if (data.Direction!=null && data.Direction?.isNotEmpty()==true)
            AppPreferences.getInstance().setDropDown(binding.DirectionFromCentre, DropDowns.Direction.name,data.Direction?.get(0).toString())
        if (data.MajorShadowCasting!=null && data.MajorShadowCasting!!>=0)
            AppPreferences.getInstance().setDropDown(binding.MajorShadowCasting, DropDowns.MajorShadowCasting.name,data.MajorShadowCasting.toString())
        binding.DistanceFromBoundary.text=data.DistanceFromBoundry
        binding.HeightAGL.text=data.Height
        binding.remark.text=data.remark

    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = OutsidePremisesViewDialougeBinding.inflate(inflater)
        return binding.root
    }



}