package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.addCardBottomSheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TowerCivilAddEarthingBinding
import com.smarthub.baseapplication.databinding.TowerCivilAddTowerBinding
import com.smarthub.baseapplication.ui.dialog.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.Utils

class EarthingAddNew (contentLayoutId: Int): BaseBottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : TowerCivilAddEarthingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TowerCivilAddEarthingBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.65).toInt()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        setDatePickerView(binding.operationalDate)
        setDatePickerView(binding.installationDate)
        binding.Add.setOnClickListener {
            dismiss()
        }

        binding.titleText.text="Add Earthing"
    }

    override fun getTheme() = R.style.NewDialogTask


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = false
        return dialog
    }
}