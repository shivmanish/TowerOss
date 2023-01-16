package com.smarthub.baseapplication.ui.alert.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AdNewSiteInfoBottomSheetBinding
import com.smarthub.baseapplication.databinding.AlertListBottomSheetBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.serviceRequest.new_site.GenerateSiteIdResponse
import com.smarthub.baseapplication.ui.alert.adapter.SelectCallBack
import com.smarthub.baseapplication.ui.alert.adapter.UserListAdapter
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.ui.dialog.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.ui.dialog.home.AddSiteHelper
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoData
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.CreateSiteModel
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AlertUserListBottomSheet(contentLayoutId: Int, var viewModel: AlertViewModel,var listner:SelectCallBack) : BaseBottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding: AlertListBottomSheetBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AlertListBottomSheetBinding.inflate(inflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = UserListAdapter(viewModel.userDataList,viewModel.selecteduserposition, listner )

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = false
        return dialog
    }
}