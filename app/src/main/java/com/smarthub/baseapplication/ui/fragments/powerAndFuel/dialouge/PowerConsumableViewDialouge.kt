package com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TowerConsumableViewDialougeBinding
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerConsumableMaterial
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilConsumableMaterial
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.utils.Utils

class PowerConsumableViewDialouge (contentLayoutId: Int, var data: PowerConsumableMaterial) : BottomSheetDialogFragment(contentLayoutId){

    lateinit var binding: TowerConsumableViewDialougeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = TowerConsumableViewDialougeBinding.bind(view)
        binding.canecl.setOnClickListener {
            dismiss()
        }

        binding.ItemName.text=data.ItemName
        binding.Type.text=data.ItemType
        binding.Make.text=data.Make
        binding.Model.text=data.Model
        binding.UsedQty.text=data.UsedQty
        binding.UoM.text=data.UOM
        binding.InstallationDate.text=Utils.getFormatedDate(data.InstallationDate.substring(0,10),"dd-MMM-yyyy")

    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = TowerConsumableViewDialougeBinding.inflate(inflater)
        return binding.root
    }



}