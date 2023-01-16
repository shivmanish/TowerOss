package com.smarthub.baseapplication.ui.fragments.noc.bottomSheetAdapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CreateNocSiteInfoDialogeBinding

class CreateNocBottomSheet (contentLayoutId: Int): BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : CreateNocSiteInfoDialogeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cancle.setOnClickListener {
            dismiss()
        }
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CreateNocSiteInfoDialogeBinding.inflate(inflater)
        return binding.root
    }
}