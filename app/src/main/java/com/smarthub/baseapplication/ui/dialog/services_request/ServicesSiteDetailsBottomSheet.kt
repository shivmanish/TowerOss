package com.smarthub.baseapplication.ui.dialog.services_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BasicInfoDetailsBottomSheetBinding
import com.smarthub.baseapplication.databinding.ServiceSiteBottomSheetDialogBinding
import com.smarthub.baseapplication.databinding.SiteDetailBootomSheetDialogBinding
import com.smarthub.baseapplication.databinding.SrDetailsBottomSheetDialogBinding
import com.smarthub.baseapplication.model.serviceRequest.SRDetail
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoServiceData
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class ServicesSiteDetailsBottomSheet(contentLayoutId: Int, var viewModel: HomeViewModel, var template: String) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding : ServiceSiteBottomSheetDialogBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ServiceSiteBottomSheetDialogBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }

        var srDetail: SRDetail? = null
  

        if (viewModel.getTaskDataResponse?.hasActiveObservers() == true)
            viewModel.getTaskDataResponse?.removeObservers(viewLifecycleOwner)
        viewModel.getTaskDataResponse?.observe(viewLifecycleOwner){

        }

        viewModel.fetchServiceRequestData("97")

        //EditText
        binding.editRequestDate.setText(srDetail?.RequestDate)
        binding.editExpectedDate.setText(srDetail?.ExpectedDate)
        binding.editOPCOSiteName.setText(srDetail?.OpcoSiteName)
        binding.editSearchRadius.setText(srDetail?.SearchRadius)
        binding.editSearchRadius.setText(srDetail?.SearchRadius)
        binding.editArea.setText(srDetail?.Area)

        srDetail?.id?.let { binding.editSiteId.setText(it) }
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ServiceSiteBottomSheetDialogBinding.inflate(inflater)
        return binding.root
    }

}