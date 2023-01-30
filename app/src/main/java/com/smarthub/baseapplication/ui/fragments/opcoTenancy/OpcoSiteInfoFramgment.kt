package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpcoInfoFregmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.Opcoinfo
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.opcoInfo.OpcoSiteInfoEditDialouge
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.opcoInfo.OperationsItemsEditDialouge
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.SiteInfoViewModel

class OpcoSiteInfoFramgment(var opcodata: OpcoDataItem?) :BaseFragment(), OpcoSiteInfoFragAdapter.OpcoInfoLisListener {
    var binding : OpcoInfoFregmentBinding?=null
    lateinit var viewModel: SiteInfoViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(requireActivity())[SiteInfoViewModel::class.java]
        binding = OpcoInfoFregmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listItem?.adapter = OpcoSiteInfoFragAdapter(this@OpcoSiteInfoFramgment,opcodata!!)
    }

    override fun attachmentItemClicked() {

    }

    override fun operationsItemClicked() {
        val bottomSheetDialogFragment = OperationsItemsEditDialouge(R.layout.opco_operations_team_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }

    override fun opcoSiteInfoItemClicked(data: Opcoinfo) {
        val bottomSheetDialogFragment = OpcoSiteInfoEditDialouge(R.layout.opco_info_site_dialouge_layout,data,opcodata?.id.toString())
//        bottomSheetDialogFragment.onDismiss(
//            object :DialogInterface{
//                override fun cancel() {
//                    if (viewModel.opcoTenencyUpdateResoponse?.hasActiveObservers() == true)
//                        viewModel.opcoTenencyUpdateResoponse?.removeObservers(viewLifecycleOwner)
//                }
//
//                override fun dismiss() {
//                    if (viewModel.opcoTenencyUpdateResoponse?.hasActiveObservers() == true)
//                        viewModel.opcoTenencyUpdateResoponse?.removeObservers(viewLifecycleOwner)
//                }
//
//            }
//        )
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }


}