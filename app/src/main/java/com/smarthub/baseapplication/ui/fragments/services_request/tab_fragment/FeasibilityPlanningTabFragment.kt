package com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FeasibilityPlanningTabFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.serviceRequest.*
import com.smarthub.baseapplication.ui.dialog.services_request.BackhualPlanningBottomSheet
import com.smarthub.baseapplication.ui.dialog.services_request.PowerMsbPlanningBottomSheet
import com.smarthub.baseapplication.ui.dialog.services_request.RPadioAntennasBottomSheet
import com.smarthub.baseapplication.ui.dialog.services_request.SiteDetailsBottomSheet
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestActivity
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.FeasibilityoplanningAdapter
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.OpcoTssrAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class FeasibilityPlanningTabFragment(var data: ServiceRequestAllDataItem?, var Id: String?) :
    BaseFragment(),
    FeasibilityoplanningAdapter.FeasibilityoplanningLisListener {
    var binding: FeasibilityPlanningTabFragmentBinding? = null
    lateinit var viewmodel: HomeViewModel
    lateinit var adapter: OpcoTssrAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding = FeasibilityPlanningTabFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    fun fetchData() {
        if (viewmodel?.serviceRequestModelResponse?.hasActiveObservers() == true) {
            viewmodel?.serviceRequestModelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.serviceRequestModelResponse?.observe(viewLifecycleOwner) {
            binding?.swipeLayout!!.isRefreshing = false
            if (it != null && it.status == Resource.Status.LOADING) {
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("Service request Fragment card Data fetched successfully")
                AppLogger.log("size :${it.data.ServiceRequestMain?.size}")
            } else if (it != null) {
//                Toast.makeText(
//                    requireContext(),
//                    "Service request Fragment error :${it.message}, data : ${it.data}",
//                    Toast.LENGTH_SHORT
//                ).show()
                AppLogger.log("Service request Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("Service Request Fragment Something went wrong")
//                Toast.makeText(
//                    requireContext(),
//                    "Service Request Fragment Something went wrong",
//                    Toast.LENGTH_SHORT
//                ).show()
            }
        }
        binding?.swipeLayout!!.setOnRefreshListener {
            viewmodel.serviceRequestAll(ServicesRequestActivity.Id!!)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listItem?.adapter = FeasibilityoplanningAdapter(
            requireContext(),
            this@FeasibilityPlanningTabFragment,
            data!!
        )
    }

    override fun attachmentItemClicked() {

    }

    override fun detailsItemClicked(
        siteDetails: SiteDetail?,
        serviceRequestAllData: ServiceRequestAllDataItem
    ) {
        val bottomSheetDialogFragment =
            SiteDetailsBottomSheet(
                R.layout.site_detail_bootom_sheet_dialog,
                Id!!,
                viewmodel,
                siteDetails,
                serviceRequestAllData
            )
        bottomSheetDialogFragment.show(childFragmentManager, "category")
    }

    override fun requestinfoClicked(
        radioAntena: RadioAntennaX?,
        serviceRequestAllData: ServiceRequestAllDataItem
    ) {
        val bottomSheetDialogFragment =
            RPadioAntennasBottomSheet(
                R.layout.fp_antina_botom_sheet_dialog,
                Id!!,
                viewmodel,
                radioAntena,
                serviceRequestAllData
            )
        bottomSheetDialogFragment.show(childFragmentManager, "category")
    }

    override fun operationInfoDetailsItemClicked(
        powerAndMcb: PowerAndMCB?,
        serviceRequestAllData: ServiceRequestAllDataItem
    ) {
        val bottomSheetDialogFragment =
            PowerMsbPlanningBottomSheet(
                R.layout.power_msb_planning_bootom_shhet_dialog,
                Id!!,
                viewmodel,
                powerAndMcb,
                serviceRequestAllData
            )
        bottomSheetDialogFragment.show(childFragmentManager, "category")
    }

    override fun geoConditionsDetailsItemClicked(
        backHaul: BackHaul?,
        serviceRequestAllData: ServiceRequestAllDataItem
    ) {
        val bottomSheetDialogFragment =
            BackhualPlanningBottomSheet(
                R.layout.backhaul_planning_bootom_sheet_dialog,
                Id!!,
                viewmodel,
                backHaul,
                serviceRequestAllData
            )
        bottomSheetDialogFragment.show(childFragmentManager, "category")
    }

    override fun siteAccessDetailsItemClicked() {
//        val bottomSheetDialogFragment = BachhualLinkBottomSheet(R.layout.backhaul_link_list_item)
//        bottomSheetDialogFragment.show(childFragmentManager, "category")
    }


}