package com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpsrTabFragmaentLayoutBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.ui.dialog.services_request.*
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestActivity
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.OpcoTssrAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel
import com.smarthub.baseapplication.viewmodels.SiteInfoViewModel
class OppoTssrTabFragment(var data : ServiceRequestAllDataItem?, Id: String?) : BaseFragment(), OpcoTssrAdapter.OpcoTssrLisListener {
    lateinit var adapter:OpcoTssrAdapter
    lateinit var viewmodel: HomeViewModel
    var binding : OpsrTabFragmaentLayoutBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding = OpsrTabFragmaentLayoutBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter=OpcoTssrAdapter(requireContext(),this@OppoTssrTabFragment,data!!)
        binding?.listItem?.adapter = adapter

        if (viewmodel?.serviceRequestModelResponse?.hasActiveObservers() == true){
            viewmodel?.serviceRequestModelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.serviceRequestModelResponse?.observe(viewLifecycleOwner) {
            binding?.swipeLayout!!.isRefreshing = false
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                AppLogger.log("Service request Fragment card Data fetched successfully")
                AppLogger.log("size :${it.data.item?.size}")
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
            viewmodel.serviceRequestAll(ServicesRequestActivity.Id!!)
        }
    }

    override fun onDestroy() {
        if (viewmodel?.serviceRequestModelResponse?.hasActiveObservers() == true)
            viewmodel?.serviceRequestModelResponse?.removeObservers(viewLifecycleOwner)
        super.onDestroy()
    }
    override fun attachmentItemClicked() {

    }
    override fun detailsItemClicked() {
        val bottomSheetDialogFragment = RFFeasibilityBottomSheet(R.layout.rf_feasibility_bottom_sheet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun operationInfoDetailsItemClicked() {
        val bottomSheetDialogFragment = EquipmentDetailsBottomSheetDialog(R.layout.equipment_bottom_sheet)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun geoConditionsDetailsItemClicked() {
        val bottomSheetDialogFragment = PowerMSBBottomSheet(R.layout.power_msb_bottom_sheet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun siteAccessDetailsItemClicked() {
        val bottomSheetDialogFragment = TssrExecutiveBottomSheet(R.layout.tssr_executive_bottom_sheet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }

    override fun editSectorCellsDetailsClicked(position: Int) {
        Toast.makeText(requireContext(),"Edit sector celss item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun viewSectorCellsDetailsClicked(position: Int) {
        Toast.makeText(requireContext(),"view sector celss item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun editBackhaulMicrowaveClicked(position: Int) {
        Toast.makeText(requireContext(),"Edit Backhaul microwave item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun viewBackhaulMicrowaveClicked(position: Int) {
        Toast.makeText(requireContext(),"View Backhaul microwave item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun editBackhaulFiberClicked(position: Int) {
        Toast.makeText(requireContext(),"Edit Backhaul Fiber item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun viewBackhaulFiberClicked(position: Int) {
        Toast.makeText(requireContext(),"View Backhaul Fiber item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun editEquipmentClicked(position: Int) {
        Toast.makeText(requireContext(),"Edit Equipment item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun viewEquipmentClicked(position: Int) {
        Toast.makeText(requireContext(),"View Equipment item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun editPowerMcbClicked(position: Int) {
        Toast.makeText(requireContext(),"Edit Power Mcb item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun viewPowerMcbClicked(position: Int) {
        Toast.makeText(requireContext(),"view Power Mcb item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun requestinfoClicked() {
        val bottomSheetDialogFragment = RequestInfoBottomSheet(R.layout.request_info_bottom_sheet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
}