package com.smarthub.baseapplication.ui.fragments.siteAcquisition.tabFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerConnectionFragBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.NewPowerFuelAllData
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerConsumableMaterial
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelAuthorityPayments
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelPODetail
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqInsidePremise
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqOutsidePremise
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqPropertyOwnerDetail
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter.PowerConnecFragAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge.PowerConsumableViewDialouge
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge.PowerFuelAuthPaymentViewDialouge
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge.PowerFuelPoViewDialouge
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters.AcqSurveyFragAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters.AssignACQTeamFragAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.dialouge.InsidePremisesViewDialouge
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.dialouge.OutsidePremisesViewDialouge
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.dialouge.PropertyOwnerViewDialouge
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AcquisitionSurveyFragment(var acqTeamData:NewSiteAcquiAllData?, var parentIndex:Int): BaseFragment(),AcqSurveyFragAdapter.AcqSurveyListListener{
    lateinit var viewmodel: HomeViewModel
    lateinit var binding : PowerConnectionFragBinding
    lateinit var adapter:AcqSurveyFragAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = PowerConnectionFragBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= AcqSurveyFragAdapter(requireContext(),this@AcquisitionSurveyFragment,acqTeamData)
        binding.listItem.adapter = adapter

        if (viewmodel.siteAgreementModel?.hasActiveObservers() == true) {
            viewmodel.siteAgreementModel?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.siteAgreementModel?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SiteAgreemnets AqcSurvey Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("SiteAgreemnets AqcSurvey Data fetched successfully")
                try {
                    adapter.setData(it.data.SAcqSiteAcquisition?.get(parentIndex)?.SAcqAcquitionSurvey?.get(0))
                } catch (e: java.lang.Exception) {
                    AppLogger.log("SiteAgreemnets AqcSurvey Fragment error : ${e.localizedMessage}")
                }
                AppLogger.log("SiteAgreemnets AqcSurvey size :${it.data.SAcqSiteAcquisition?.get(parentIndex)?.SAcqAcquitionSurvey?.size}")
            } else if (it != null) {
                AppLogger.log("SiteAgreemnets AqcSurvey Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SiteAgreemnets AqcSurvey Fragment Something went wrong")

            }
        }



        binding.swipeLayout.setOnRefreshListener {
            binding.swipeLayout.isRefreshing=false
            viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
        }
    }

    override fun onDestroy() {
        if (viewmodel.siteAgreementModel?.hasActiveObservers() == true){
            viewmodel.siteAgreementModel?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }

    override fun attachmentItemClicked() {
       AppLogger.log("Attachment clicked")
    }

    override fun viewInsidePremisesClicked(position: Int, data: SAcqInsidePremise) {
        val bm = InsidePremisesViewDialouge(R.layout.inside_premises_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun viewOutsidePremisesClicked(position: Int, data: SAcqOutsidePremise) {
        val bm = OutsidePremisesViewDialouge(R.layout.outside_premises_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun viewPropertyOwnerClicked(position: Int, data: SAcqPropertyOwnerDetail) {
        val bm = PropertyOwnerViewDialouge(R.layout.acq_property_owner_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }


}
