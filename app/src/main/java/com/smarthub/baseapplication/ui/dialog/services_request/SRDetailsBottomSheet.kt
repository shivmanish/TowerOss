package com.smarthub.baseapplication.ui.dialog.services_request

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BasicInfoDetailsBottomSheetBinding
import com.smarthub.baseapplication.databinding.SrDetailsBottomSheetDialogBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.serviceRequest.SRDetails
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.ui.dialog.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class SRDetailsBottomSheet(contentLayoutId: Int, var viewModel: HomeViewModel,var id : String,var srDetailsData: SRDetails,var serviceRequestAllData: ServiceRequestAllDataItem) : BaseBottomSheetDialogFragment(contentLayoutId) {

    var basicinfoModel: BasicinfoModel? = null
    lateinit var binding : SrDetailsBottomSheetDialogBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SrDetailsBottomSheetDialogBinding.bind(view)
        basicinfoModel = BasicinfoModel()
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.95).toInt()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        binding.update.setOnClickListener {
            srDetailsData.let{
                it.SRType = System.currentTimeMillis().toString()
            }
            val list  = ArrayList<SRDetails>()
            val serviceRequestList = ServiceRequestAllData()
            serviceRequestList.add(serviceRequestAllData)
            list.add(srDetailsData)
            serviceRequestAllData.ServiceRequest[0].SRDetails = list
            basicinfoModel?.ServiceRequestMain = serviceRequestList
            basicinfoModel?.id = id
            viewModel.updateBasicInfo(basicinfoModel!!)
        }
        if (viewModel.basicInfoUpdate?.hasActiveObservers() == true)
            viewModel.basicInfoUpdate?.removeObservers(viewLifecycleOwner)
        viewModel.basicInfoUpdate?.observe(viewLifecycleOwner){
            if (it!=null){
                if (it.status == Resource.Status.LOADING){
                    showProgressLayout()
                }else{
                    hideProgressLayout()
                }
                if (it.status == Resource.Status.SUCCESS && it.data?.Status?.isNotEmpty() == true){
                    AppLogger.log("Successfully updated all fields")
                    dismiss()
                    viewModel.fetchSiteInfoData(id)
                }else{
                    AppLogger.log("UnExpected Error found")
                }
            }else{
                AppLogger.log("Something went wrong")
            }
        }
    }

    private fun showProgressLayout(){
        if (binding.progressLayout.visibility != View.VISIBLE)
            binding.progressLayout.visibility = View.VISIBLE
    }

    private fun hideProgressLayout(){
        if (binding.progressLayout.visibility == View.VISIBLE)
            binding.progressLayout.visibility = View.GONE
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = true
        return dialog
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SrDetailsBottomSheetDialogBinding.inflate(inflater)
        return binding.root
    }

}