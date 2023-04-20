package com.smarthub.baseapplication.ui.fragments.siteAcquisition.tabFragments

import android.app.Activity
import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.math.BigDecimal
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.trackermodule.locationpicker.LocationPickerActivity
import com.example.trackermodule.locationpicker.MapUtility
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerConnectionFragBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.*
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcquiAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.AllsiteInfoDataModel
import com.smarthub.baseapplication.ui.fragments.AttachmentCommonDialogBottomSheet
import com.smarthub.baseapplication.ui.fragments.AttachmentConditionsDialogBottomSheet
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters.AcqSurveyFragAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.dialouge.*
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel
import java.math.RoundingMode
import java.util.*


class AcquisitionSurveyFragment(var acqSurveyData:NewSiteAcquiAllData?, var parentIndex:Int): BaseFragment(),AcqSurveyFragAdapter.AcqSurveyListListener{
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
        adapter= AcqSurveyFragAdapter(this@AcquisitionSurveyFragment,this@AcquisitionSurveyFragment,acqSurveyData)
        binding.listItem.adapter = adapter

        if (viewmodel.siteAgreementModel?.hasActiveObservers() == true) {
            viewmodel.siteAgreementModel?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.siteAgreementModel?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                showLoader()
                AppLogger.log("SiteAgreemnets AqcSurvey Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                hideLoader()
                AppLogger.log("SiteAgreemnets AqcSurvey Data fetched successfully")
                try {
                    acqSurveyData=it.data.SAcqSiteAcquisition?.get(parentIndex)
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

        if (viewmodel.serviceRequestModelResponse?.hasActiveObservers() == true) {
            viewmodel.serviceRequestModelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.serviceRequestModelResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                showLoader()
                AppLogger.log("SiteAgreemnets AqcSurvey Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                hideLoader()
                AppLogger.log("SiteAgreemnets AqcSurvey serviceRequestModelResponse Data fetched successfully")
                try {
                    AppController.getInstance().NominalAddress=it.data.ServiceRequestMain?.get(0)?.ServiceRequest?.get(0)?.SRDetails?.get(0)
                } catch (e: java.lang.Exception) {
                    AppLogger.log("SiteAgreemnets AqcSurvey Fragment serviceRequestModelResponse error : ${e.localizedMessage}")
                }
                AppLogger.log("SiteAgreemnets AqcSurvey serviceRequestModelResponse size :${it.data.ServiceRequestMain?.get(parentIndex)?.ServiceRequest?.size}")
            } else if (it != null) {
                AppLogger.log("SiteAgreemnets AqcSurvey Fragment serviceRequestModelResponse error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SiteAgreemnets AqcSurvey Fragment serviceRequestModelResponse Something went wrong")

            }
        }


        viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
        viewmodel.serviceRequestAll(AppController.getInstance().siteid)
        binding.swipeLayout.setOnRefreshListener {
            binding.swipeLayout.isRefreshing=false
            viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
            viewmodel.serviceRequestAll(AppController.getInstance().siteid)
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

    override fun editInsidePremisesClicked(position: Int, data: SAcqInsidePremise) {
        val bm = InsidePremisesEditDialouge(data,acqSurveyData,
            object : InsidePremisesEditDialouge.AcqInsidePremisesUpdateListener {
                override fun updatedData() {
                    viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                }

            },position)
        bm.show(childFragmentManager,"sdg")
    }

    override fun viewOutsidePremisesClicked(position: Int, data: SAcqOutsidePremise) {
        val bm = OutsidePremisesViewDialouge(R.layout.outside_premises_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun editOutsidePremisesClicked(position: Int, data: SAcqOutsidePremise) {
        val bm = OutSidePremisesEditDialouge(data,acqSurveyData,
            object : OutSidePremisesEditDialouge.AcqOutsidePremisesUpdateListener {
                override fun updatedData() {
                    viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                }

            },position)
        bm.show(childFragmentManager,"sdg")
    }

    override fun viewPropertyOwnerClicked(position: Int, data: SAcqPropertyOwnerDetail) {
        val bm = PropertyOwnerViewDialouge(R.layout.acq_property_owner_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun editPropertyOwnerClicked(position: Int, data: SAcqPropertyOwnerDetail) {
        val bm = PropertyOwnerEditDialouge(data,acqSurveyData,
            object : PropertyOwnerEditDialouge.AcqPropertyOwnerUpdateListener {
                override fun updatedData() {
                    viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                }

            },position)
        bm.show(childFragmentManager,"sdg")
    }

    override fun editLocationMarkingClicked(position: Int, data: SAcqLocationMarking) {
        val bm = LocationMarkingEditDialouge(data,acqSurveyData,
            object : LocationMarkingEditDialouge.AcqLocationMarkUpdateListener {
                override fun updatedData() {
                    viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                }

            },position)
        bm.show(childFragmentManager,"sdg")
    }

    override fun viewLocationMarkingClicked(position: Int, data: SAcqLocationMarking) {
        val bm = LocationMarkingViewDialouge(data)
        bm.show(childFragmentManager, "category")
    }

    override fun textChangeListner(data1: Float?, data2: Float?, textview: TextView) {
        if (data1!=null && data2!=null)
            textview.text=(data1*data2).toString()
        else if (data1!=null)
            textview.text=data1.toString()
        else if (data2!=null)
            textview.text=data2.toString()
    }

    override fun addAttachment(categoryId:Int) {
        val bm = AttachmentConditionsDialogBottomSheet("SAcqAcquitionSurvey",acqSurveyData?.SAcqAcquitionSurvey?.get(0)?.id.toString(),categoryId,
            object : AttachmentConditionsDialogBottomSheet.AddAttachmentListner {
                override fun attachmentAdded(){
                    viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }

    override fun updateItemClicked(data: AcquisitionSurveyData) {
        showLoader()
        val dataModel = UpdateSiteAcquiAllData()
        val tempList:ArrayList<AcquisitionSurveyData> =ArrayList()
        tempList.clear()
        tempList.add(data)
        dataModel.SAcqAcquitionSurvey=tempList
        dataModel.id=acqSurveyData?.id
        viewmodel.updateSiteAcq(dataModel)
        if (viewmodel.updateSiteAcqDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateSiteAcqDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateSiteAcqDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SiteAgreemnets Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && (it.data.status.SAcqPowerConnectionFeasibility==200 || it.data.status.SAcqFeasibilityDetail==200 || it.data.status.SAcqPropertyDetail==200)) {
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

    override fun updateActualAdderessAddress(data: AllsiteInfoDataModel?) {
        showLoader()
        viewmodel.updateSiteInfo(data)
        if (viewmodel.updateSiteInfoDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateSiteInfoDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateSiteInfoDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("AcquisitionSurveyFragment address data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status?.Siteaddress==200 ) {
                AppLogger.log("AcquisitionSurveyFragment address card Data updated successfully")
                viewmodel.siteInfoRequestAll(AppController.getInstance().siteid)
//                Toast.makeText(context,"Data Updated successfully",Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("AcquisitionSurveyFragment address Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("AcquisitionSurveyFragment address error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("AcquisitionSurveyFragment address Something went wrong")

            }
        }
    }
    private val startForCropResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        when (result.resultCode) {
            Activity.RESULT_OK -> {
                var actualLat:Double?=0.0
                var actualLong:Double?=0.0
                result.data?.getStringExtra(MapUtility.ADDRESS)?.let {
                    this.address2?.text=it
                    AppLogger.log("Address=====>$it")
                }
                result.data?.getDoubleExtra(MapUtility.LATITUDE,0.0)?.let {
                    this.siteLat?.text=String.format("%.6f", it)
                    AppLogger.log("siteLat=====>$it")
                    actualLat=it
                }
                result.data?.getDoubleExtra(MapUtility.LONGITUDE,0.0)?.let {
                    this.siteLong?.text=String.format("%.6f", it)
                    AppLogger.log("siteLong=====>$it")
                    actualLong=it
                }
                if (actualLat!=null && actualLong!=null){
                    Utils.calculateDistanceLatLong(this.nominalSiteLat!!,this.nominalSiteLong!!,
                        actualLat.toString(),actualLong.toString(),
                        this.distance)
                }
                val geocoder = Geocoder(requireContext(), Locale.getDefault())

                //get location from lat long if address string is null
                val addresses = geocoder.getFromLocation(actualLat!!, actualLong!!, 1)
                if (addresses != null && addresses.size > 0){
                    val postalCode = addresses[0].postalCode
                    this.postalCode?.text=postalCode
                    try {
                        val addressline2 = addresses[0].getAddressLine(0)+","+ addresses[0].adminArea+","+addresses[0].countryName
                        this.address2?.text=addressline2
                        AppLogger.log("addressline2=====>$addressline2")
                    }catch (e:Exception){
                        AppLogger.log("${e.localizedMessage}")
                    }
                }
            }
            else -> {
                Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }
    var distance: TextView?=null
    var address2: TextView?=null
    var siteLat: TextView?=null
    var siteLong: TextView?=null
    var postalCode: TextView?=null
    var nominalSiteLat: String?=null
    var nominalSiteLong: String?=null
    override fun initiateAddressActivity(
        distance: TextView?, address2: TextView?, siteLat: TextView, siteLong: TextView,
        postalCode: TextView,
        nominalSiteLat: String?,
        nominalSiteLong: String?,
    )
    {
        this.distance=distance
        this.address2=address2
        this.siteLat=siteLat
        this.siteLong=siteLong
        this.postalCode=postalCode
        this.nominalSiteLat=nominalSiteLat
        this.nominalSiteLong=nominalSiteLong
        val intent = Intent(requireContext(), LocationPickerActivity::class.java)
        startForCropResult.launch(intent)

    }




}
