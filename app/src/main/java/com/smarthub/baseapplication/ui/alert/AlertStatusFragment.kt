package com.smarthub.baseapplication.ui.alert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AlertStatusBinding
import com.smarthub.baseapplication.ui.alert.dialog.CreateAlertBottomSheet
import com.smarthub.baseapplication.ui.fragments.BaseFragment


class AlertStatusFragment : BaseFragment(),AlertStatusListener {

    lateinit var binding : AlertStatusBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AlertStatusBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.adapter = AlertStatusListAdapter(requireContext(),this@AlertStatusFragment )

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun detailsItemClicked() {
        val bottomSheetDialogFragment = CreateAlertBottomSheet(R.layout.create_alert_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }


}


