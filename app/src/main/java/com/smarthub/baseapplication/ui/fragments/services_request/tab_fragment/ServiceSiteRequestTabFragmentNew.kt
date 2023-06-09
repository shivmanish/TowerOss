package com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ServiceRequestInfoBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.serviceRequest.*
import com.smarthub.baseapplication.ui.dialog.services_request.*
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicesRequestAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class ServiceRequestTabFragmentNew(var data : ServiceRequestAllDataItem?, var Id: String) : BaseFragment(), ServicesRequestAdapter.ServicesRequestLisListener {
    var binding : ServiceRequestInfoBinding?=null
    lateinit var viewmodel: HomeViewModel
    lateinit var adapter: ServicesRequestAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ServiceRequestInfoBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter=ServicesRequestAdapter(requireContext(),this@ServiceRequestTabFragmentNew,data!!)
        binding?.listItem?.adapter = adapter

        if (viewmodel.serviceRequestModelResponse?.hasActiveObservers() == true){
            viewmodel.serviceRequestModelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.serviceRequestModelResponse?.observe(viewLifecycleOwner) {

            if (it!=null && it.status == Resource.Status.LOADING){
                binding?.swipeLayout!!.isRefreshing = true
                return@observe
            }
            binding?.swipeLayout!!.isRefreshing = false
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                if (it.data.ServiceRequestMain?.isNotEmpty() == true) {
                    adapter.updateData(it.data.ServiceRequestMain[0])
//                    Toast.makeText(requireContext(), "Service request Data fetched successfully", Toast.LENGTH_SHORT).show()
                    AppLogger.log("Service request Fragment card Data fetched successfully")
                    AppLogger.log("size :${it.data.ServiceRequestMain.size}")
                }else Toast.makeText(requireContext(), "No Data Found found", Toast.LENGTH_SHORT).show()

            }else if (it!=null) {
//                Toast.makeText(requireContext(),"Service request Fragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("Service request Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("Service Request Fragment Something went wrong")
//                Toast.makeText(requireContext(),"Service Request Fragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

        binding?.swipeLayout!!.setOnRefreshListener {
            viewmodel.serviceRequestAll(Id)
        }

    }

    override fun onDestroy() {
        if (viewmodel.serviceRequestAllData?.hasActiveObservers() == true){
            viewmodel.serviceRequestAllData?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }
    override fun attachmentItemClicked() {
    }
    override fun editSrDetailsItemClicked(srDetailsData: SRDetails,serviceRequestAllData: ServiceRequestAllDataItem) {
        val bottomSheetDialogFragment = SRDetailsBottomSheet(R.layout.sr_details_bottom_sheet_dialog,viewmodel,
            Id,srDetailsData,serviceRequestAllData)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun editBackhaulLinkItemClicked() {
        val bottomSheetDialogFragment = BachhualLinkBottomSheet(R.layout.backhaul_link_list_item)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }

    override fun editEquipmentClicked(equipmant: Equipment?, serviceRequestAllData: ServiceRequestAllDataItem?, s: String?) {
        val bottomSheetDialogFragment = EditEquipmentBottomSheet(R.layout.backhaul_link_list_item,equipmant,serviceRequestAllData,viewmodel,Id)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }

    override fun viewEquipmentClicked(position: Int) {
        val bottomSheetDialogFragment = EditEquipmentBottomSheet(R.layout.backhaul_link_list_item,null,null,viewmodel,Id)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }

    override fun editRadioAnteenaClicked(
        radioanteena: RadioAntenna,
        serviceRequestAllData: ServiceRequestAllDataItem,
        s: String) {
        val bottomSheetDialogFragment = RadioAnteenaBottomSheet(R.layout.radio_antena_dialouge,radioanteena,serviceRequestAllData,viewmodel,Id)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }

    override fun viewRadioAnteenaClicked(position: Int) {
        val bottomSheetDialogFragment = EditEquipmentBottomSheet(R.layout.backhaul_link_list_item,null,null,viewmodel,Id)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }

    override fun editRequestInfoClicked(
        requestinfo: RequesterInfo,
        serviceRequestAllData: ServiceRequestAllDataItem?
    ) {
        val bottomSheetDialogFragment = RequestInfoBottomSheet(R.layout.request_info_bottom_sheet_dialog,requestinfo,serviceRequestAllData,viewmodel,Id)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }


}