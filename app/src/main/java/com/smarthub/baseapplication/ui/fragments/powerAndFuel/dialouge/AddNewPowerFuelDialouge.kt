package com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerFuelAddnewDialougeBinding
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.Utils

class AddNewPowerFuelDialouge (contentLayoutId: Int): BaseBottomSheetDialogFragment() {
    lateinit var binding : PowerFuelAddnewDialougeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = PowerFuelAddnewDialougeBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.8).toInt()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        binding.Add.setOnClickListener {
            dismiss()
        }

    }

    override fun getTheme() = R.style.NewDialogTask


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = false
        return dialog
    }
}