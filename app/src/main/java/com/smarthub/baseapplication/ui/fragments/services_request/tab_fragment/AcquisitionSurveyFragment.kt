package com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AcquisitionSurveyFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.serviceRequest.acquisitionSurvey.ASAquisitionSurveyBuildingDetail
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.model.serviceRequest.acquisitionSurvey.AcquisitionSurveyModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.BuildingDetailsBottomSheet
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestActivity
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.AcquisitionSurveyFragAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AcquisitionSurveyFragment (var data : AcquisitionSurveyModel?, var Id: String?): BaseFragment(), AcquisitionSurveyFragAdapter.AcquisitionSurveyListItemListner {
    var binding : AcquisitionSurveyFragmentBinding?=null
    lateinit var viewmodel: HomeViewModel
    lateinit var adapter: AcquisitionSurveyFragAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AcquisitionSurveyFragmentBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding?.root


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= AcquisitionSurveyFragAdapter(requireContext(),this@AcquisitionSurveyFragment,data!!)
        binding?.acquisitionSurveyList?.adapter = adapter

        if (viewmodel.serviceRequestModelResponse?.hasActiveObservers() == true){
            viewmodel.serviceRequestModelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.serviceRequestModelResponse?.observe(viewLifecycleOwner) {
            binding?.swipeLayout!!.isRefreshing = false
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                AppLogger.log("Service request Fragment card Data fetched successfully")
                AppLogger.log("size :${it.data.ServiceRequestMain?.size}")
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
        binding?.swipeLayout?.isEnabled = false
    }

    override fun onDestroy() {
        if (viewmodel.serviceRequestAllData?.hasActiveObservers() == true){
            viewmodel.serviceRequestAllData?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }
    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"Item Clicked", Toast.LENGTH_SHORT).show()
    }
    override fun EditBuildingdetailsItemClicked(
        buildingDetailData: ASAquisitionSurveyBuildingDetail?,
        serviceRequestAllData: ServiceRequestAllDataItem?
    ) {
        var bottomSheetDialogFragment = BuildingDetailsBottomSheet(
            R.layout.teamvender_details_botom_sheet,
            buildingDetailData,
            serviceRequestAllData,
            viewmodel,
            Id
        )
        bottomSheetDialogFragment?.show(childFragmentManager,"category")
    }

    override fun editBoundryDetailsClicked(position: Int) {
        Toast.makeText(requireContext(),"Edit Boundry table Item Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun viewBoundryDetailsClicked(position: Int) {
        Toast.makeText(requireContext(),"View Boundry table Item Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun editPropertyOwnerDetailsClicked(position: Int) {
        Toast.makeText(requireContext(),"Edit Property Owner table Item Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun viewPropertyOwnerDetailsClicked(position: Int) {
        Toast.makeText(requireContext(),"View Property Owner table Item Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun editPoDetailsClicked(position: Int) {
        Toast.makeText(requireContext(),"Edit Po table Item Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun viewPoDetailsClicked(position: Int) {
        Toast.makeText(requireContext(),"view Po table Item Clicked", Toast.LENGTH_SHORT).show()
    }

}