package com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AddNewCardOpcoTenencyBinding

class AddNewOpcoCardAdapter (contentLayoutId: Int): BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : AddNewCardOpcoTenencyBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.Cancle.setOnClickListener {
            dismiss()
        }
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AddNewCardOpcoTenencyBinding.inflate(inflater)
        return binding.root
    }
}