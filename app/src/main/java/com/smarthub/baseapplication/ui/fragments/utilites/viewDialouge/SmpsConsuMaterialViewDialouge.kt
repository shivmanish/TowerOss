package com.smarthub.baseapplication.ui.fragments.utilites.viewDialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.UtilitySmpsconsuMaterialViewDialougeBinding
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityConsumableMaterial
import com.smarthub.baseapplication.utils.Utils

class SmpsConsuMaterialViewDialouge (contentLayoutId: Int, var data: UtilityConsumableMaterial) : BottomSheetDialogFragment(contentLayoutId){

    lateinit var binding: UtilitySmpsconsuMaterialViewDialougeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = UtilitySmpsconsuMaterialViewDialougeBinding.inflate(inflater)
        return binding.root
    }
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
        binding.InstallationDate.text=Utils.getFormatedDate(data.InstallationDate,"dd-MMM-yyyy")


    }

    override fun getTheme() = R.style.NewDialogTask




}