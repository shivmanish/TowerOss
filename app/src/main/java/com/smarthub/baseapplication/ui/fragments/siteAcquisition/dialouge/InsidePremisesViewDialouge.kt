package com.smarthub.baseapplication.ui.fragments.siteAcquisition.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.InsidePremisesViewDialougeBinding
import com.smarthub.baseapplication.databinding.TowerConsumableViewDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerConsumableMaterial
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqInsidePremise
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class InsidePremisesViewDialouge (contentLayoutId: Int, var data: SAcqInsidePremise) : BottomSheetDialogFragment(contentLayoutId){

    lateinit var binding: InsidePremisesViewDialougeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }
        if (data.ExternalStructureType.isNotEmpty())
            AppPreferences.getInstance().setDropDown(binding.ExternalStructureType, DropDowns.ExternalStructureType.name,data.ExternalStructureType.get(0).toString())
       if (data.Direction.isNotEmpty())
            AppPreferences.getInstance().setDropDown(binding.DirectionFromCentre, DropDowns.Direction.name,data.Direction.get(0).toString())
        if (data.LocationType>0)
            AppPreferences.getInstance().setDropDown(binding.LocationType, DropDowns.LocationType.name,data.LocationType.toString())
        binding.DistanceFromCentre.text=data.DistanceFromCentre
        binding.HeightAGL.text=data.Height
        binding.remark.text=data.remark

    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = InsidePremisesViewDialougeBinding.inflate(inflater)
        return binding.root
    }



}