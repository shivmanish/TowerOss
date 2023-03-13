package com.smarthub.baseapplication.ui.fragments.siteAcquisition.tabFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerConnectionFragBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqPayeeAccountDetail
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SoftAcquisitionData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcquiAllData
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters.SoftAcquisitionFragAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.dialouge.PayeeAccDetailsViewDialouge
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class SoftAcquisitionFragment(var softAcqData:NewSiteAcquiAllData?, var parentIndex:Int): BaseFragment(),SoftAcquisitionFragAdapter.SoftAcqListListener{
    lateinit var viewmodel: HomeViewModel
    lateinit var binding : PowerConnectionFragBinding
//    lateinit var binding : SiteAcqTeamNonSwitLayoutBinding
    lateinit var adapter:SoftAcquisitionFragAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = PowerConnectionFragBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= SoftAcquisitionFragAdapter(requireContext(),this@SoftAcquisitionFragment,softAcqData)
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
                hideLoader()
                AppLogger.log("SiteAgreemnets AqcSurvey Data fetched successfully")
                try {
                    softAcqData=it.data.SAcqSiteAcquisition?.get(parentIndex)
                    adapter.setData(it.data.SAcqSiteAcquisition?.get(parentIndex)?.SAcqSoftAcquisition?.get(0))
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



        viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
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

    override fun viewPayeeAccountClicked(position: Int, data: SAcqPayeeAccountDetail) {
        val bm = PayeeAccDetailsViewDialouge(R.layout.acq_payee_acc_view_dialouge,data)
        bm.show(childFragmentManager,"sdg")
    }

    override fun updateAgreementTermClicked(data: SoftAcquisitionData) {
        showLoader()
        val dataModel = UpdateSiteAcquiAllData()
        val tempList:ArrayList<SoftAcquisitionData> =ArrayList()
        tempList.clear()
        tempList.add(data)
        dataModel.SAcqSoftAcquisition=tempList
        dataModel.id=softAcqData?.id
        viewmodel.updateSiteAcq(dataModel)
        if (viewmodel.updateSiteAcqDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateSiteAcqDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateSiteAcqDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SiteAgreemnets Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.SAcqSoftAcquisitionAgreementTerm==200) {
                AppLogger.log("SiteAgreemnets Fragment card Data fetched successfully")
                viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("SiteAgreemnets Fragment Something went wrong")
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
            }
            else if (it != null) {
                AppLogger.log("SiteAgreemnets Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SiteAgreemnets Fragment Something went wrong")

            }
        }
    }


}
