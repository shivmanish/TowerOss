package com.smarthub.baseapplication.ui.dialog.utils

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AddMoreBotomSheetDailogBinding
import com.smarthub.baseapplication.databinding.BasicInfoDetailsBottomSheetBinding
import com.smarthub.baseapplication.databinding.DgEqipmentDialogLayoutBinding
import com.smarthub.baseapplication.network.pojo.site_info.BasicInfoModelDropDown
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoServiceData
import com.smarthub.baseapplication.ui.dialog.siteinfo.viewmodel.BasicInfoDialougeViewmodel
import com.smarthub.baseapplication.ui.logs.LogsActivity


class CommonBottomSheetDialog(contentLayoutId: Int) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding: AddMoreBotomSheetDailogBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AddMoreBotomSheetDailogBinding.bind(view)

        binding.canecl.setOnClickListener {
            dismiss()
        }

        binding.cardLogs.setOnClickListener {
            val intent = Intent(requireContext(),LogsActivity::class.java)
            startActivity(intent)
            dismiss()
        }
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AddMoreBotomSheetDailogBinding.inflate(inflater)
        return binding.root
    }

}