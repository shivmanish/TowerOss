package com.smarthub.baseapplication.ui.fragments.siteAcquisition.tabFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerConnectionFragBinding
import com.smarthub.baseapplication.databinding.SiteAcqTeamNonSwitLayoutBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.NewPowerFuelAllData
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerConsumableMaterial
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelAuthorityPayments
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelPODetail
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.AssignACQTeamDAta
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcqModel
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcquiAllData
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter.PowerConnecFragAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge.PowerConsumableViewDialouge
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge.PowerFuelAuthPaymentViewDialouge
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge.PowerFuelPoViewDialouge
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters.AssignACQTeamFragAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AssignACQTeamFragment(var acqTeamData:NewSiteAcquiAllData?, var parentIndex:Int): BaseFragment(),AssignACQTeamFragAdapter.AssignACQTeamListListener{
    lateinit var viewmodel: HomeViewModel
    lateinit var binding : SiteAcqTeamNonSwitLayoutBinding
    lateinit var adapter:AssignACQTeamFragAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = SiteAcqTeamNonSwitLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= AssignACQTeamFragAdapter(this@AssignACQTeamFragment,this@AssignACQTeamFragment,acqTeamData)
        binding.listItem.adapter = adapter

        if (viewmodel.siteAgreementModel?.hasActiveObservers() == true) {
            viewmodel.siteAgreementModel?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.siteAgreementModel?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SiteAgreemnets Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("SiteAgreemnets Fragment card Data fetched successfully")
                hideLoader()
                try {
                    acqTeamData=it.data.SAcqSiteAcquisition?.get(parentIndex)
                    adapter.setData(it.data.SAcqSiteAcquisition?.get(parentIndex)?.SAcqAssignACQTeam?.get(0))
                } catch (e: java.lang.Exception) {
                    AppLogger.log("SiteAgreemnets Fragment error : ${e.localizedMessage}")
                }
                AppLogger.log("SiteAgreemnets size :${it.data.SAcqSiteAcquisition?.size}")
            } else if (it != null) {
                AppLogger.log("SiteAgreemnets Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SiteAgreemnets Fragment Something went wrong")

            }
        }



//        binding.swipeLayout.setOnRefreshListener {
//            binding.swipeLayout.isRefreshing=false
//            viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
//        }
        viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
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

    override fun updateTeamClicked(data: AssignACQTeamDAta?) {
        showLoader()
        val dataModel = UpdateSiteAcquiAllData()
        val tempList:ArrayList<AssignACQTeamDAta> =ArrayList()
        tempList.clear()
        tempList.add(data!!)
        dataModel.SAcqAssignACQTeam=tempList
        dataModel.id=acqTeamData?.id
        viewmodel.updateSiteAcq(dataModel)
        if (viewmodel.updateSiteAcqDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateSiteAcqDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateSiteAcqDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SiteAgreemnets Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.SAcqAssignACQTeam==200) {
                AppLogger.log("SiteAgreemnets Fragment card Data fetched successfully")
                viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully",Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("SiteAgreemnets Fragment Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("SiteAgreemnets Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SiteAgreemnets Fragment Something went wrong")

            }
        }
    }




}
