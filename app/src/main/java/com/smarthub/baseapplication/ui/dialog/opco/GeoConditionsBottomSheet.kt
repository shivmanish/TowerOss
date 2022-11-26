package com.smarthub.baseapplication.ui.dialog.opco

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.databinding.BasicInfoDetailsBottomSheetBinding
import com.smarthub.baseapplication.databinding.DetailsBottomSheetViewBinding

class GeoConditionsBottomSheet(contentLayoutId: Int) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding : BasicInfoDetailsBottomSheetBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = BasicInfoDetailsBottomSheetBinding.bind(view)

    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BasicInfoDetailsBottomSheetBinding.inflate(inflater)
        return binding.root
    }

}