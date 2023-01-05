package com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpcoInfoFregmentBinding
import com.smarthub.baseapplication.databinding.ServiceRequestInfoBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.serviceRequest.SRDetails
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.ui.dialog.services_request.*
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestActivity.Companion.Id
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicesRequestAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class ServiceRequestTabFragment(var data : ServiceRequestAllDataItem?, var Id: String) : BaseFragment(), ServicesRequestAdapter.ServicesRequestLisListener {
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
        adapter=ServicesRequestAdapter(requireContext(),this@ServiceRequestTabFragment,data!!)
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
                if (it.data.item?.isNotEmpty() == true) {
                    adapter.updateData(it.data.item!![0].ServiceRequestMain[0])
                    Toast.makeText(requireContext(), "Service request Data fetched successfully", Toast.LENGTH_SHORT).show()
                    AppLogger.log("Service request Fragment card Data fetched successfully")
                    AppLogger.log("size :${it.data.item?.size}")
                }else Toast.makeText(requireContext(), "Empty Data found", Toast.LENGTH_SHORT).show()

            }else if (it!=null) {
                Toast.makeText(requireContext(),"Service request Fragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("Service request Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("Service Request Fragment Something went wrong")
                Toast.makeText(requireContext(),"Service Request Fragment Something went wrong", Toast.LENGTH_SHORT).show()
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

    override fun editEquipmentClicked(position: Int) {
        Toast.makeText(requireContext(),"SR Equipment  Item clicked for edit", Toast.LENGTH_SHORT).show()

    }

    override fun viewEquipmentClicked(position: Int) {
        Toast.makeText(requireContext(),"SR Equipment  Item clicked for view", Toast.LENGTH_SHORT).show()
    }

    override fun editRadioAnteenaClicked(position: Int) {
        Toast.makeText(requireContext(),"SR Radio Anteena  Item clicked for edit", Toast.LENGTH_SHORT).show()
    }

    override fun viewRadioAnteenaClicked(position: Int) {
        Toast.makeText(requireContext(),"SR Radio Anteena  Item clicked for view", Toast.LENGTH_SHORT).show()
    }

    override fun editRequestInfoClicked() {
        val bottomSheetDialogFragment = RequestInfoBottomSheet(R.layout.request_info_bottom_sheet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }


}