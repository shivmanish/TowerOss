package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.earthing.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TowerConsumableViewDialougeBinding
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilConsumableMaterial
import com.smarthub.baseapplication.utils.Utils

class EarthingConsumableViewDialouge (var data: TwrCivilConsumableMaterial) : BottomSheetDialogFragment(){

    lateinit var binding: TowerConsumableViewDialougeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }
        binding.ItemName.text=data.ItemName
        binding.Type.text=data.ItemType
        binding.Make.text=data.Make
        binding.Model.text=data.Model
        binding.UsedQty.text=data.UsedQty
        binding.UoM.text=data.UOM
        binding.InstallationDate.text= Utils.getFormatedDate(data.InstallationDate,"dd-MMM-yyyy")

    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = TowerConsumableViewDialougeBinding.inflate(inflater)
        return binding.root
    }



}