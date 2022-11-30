package com.smarthub.baseapplication.ui.dialog.siteinfo

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BuildingDetailsBotomSheetBinding
import com.smarthub.baseapplication.databinding.DetailsBottomSheetViewBinding

class BuildingDetailsBottomSheet(contentLayoutId: Int) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding : BuildingDetailsBotomSheetBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = BuildingDetailsBotomSheetBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
    }
    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BuildingDetailsBotomSheetBinding.inflate(inflater)
        return binding.root
    }

}