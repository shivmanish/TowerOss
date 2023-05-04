package com.smarthub.baseapplication.ui.fragments.sstSbc.tabFragments

import android.app.Activity
import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.trackermodule.locationpicker.LocationPickerActivity
import com.example.trackermodule.locationpicker.MapUtility
import com.smarthub.baseapplication.databinding.SiteAcqTeamNonSwitLayoutBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.AllsiteInfoDataModel
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcAllData
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcTestReport
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.AttachmentCommonDialogBottomSheet
import com.smarthub.baseapplication.ui.fragments.sstSbc.adapter.SstSbcReportFragAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel
import java.util.*

class SstSbcReportFragment(var sstSbcData:SstSbcAllData?, var parentIndex:Int?): BaseFragment(),SstSbcReportFragAdapter.SstSbcReportListListener{
    lateinit var viewmodel: HomeViewModel
    lateinit var binding : SiteAcqTeamNonSwitLayoutBinding
    lateinit var adapter:SstSbcReportFragAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = SiteAcqTeamNonSwitLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= SstSbcReportFragAdapter(this@SstSbcReportFragment,this@SstSbcReportFragment,sstSbcData)
        binding.listItem.adapter = adapter

        if (viewmodel.sstSbcModelResponse?.hasActiveObservers() == true) {
            viewmodel.sstSbcModelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.sstSbcModelResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SstSbcTeamFragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("SstSbcTeamFragment card Data fetched successfully")
                hideLoader()
                try {
                    sstSbcData=it.data.SstSbc?.get(parentIndex!!)
                    adapter.setData(it.data.SstSbc?.get(parentIndex!!)?.SstSbcTestReport?.get(0))
                } catch (e: java.lang.Exception) {
                    AppLogger.log("SstSbcTeamFragment error : ${e.localizedMessage}")
                }
                AppLogger.log("SiteAgreemnets size :${it.data.SstSbc?.size}")
            } else if (it != null) {
                AppLogger.log("SstSbcTeamFragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SstSbcTeamFragment Something went wrong")

            }
        }



        viewmodel.fetchSstSbcModelRequest(AppController.getInstance().siteid)
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

    override fun addAttachment() {
        val bm = AttachmentCommonDialogBottomSheet("SstSbcTestReport",sstSbcData?.SstSbcTestReport?.get(0)?.id.toString(),
            object : AttachmentCommonDialogBottomSheet.AddAttachmentListner {
                override fun attachmentAdded(){
                    viewmodel.fetchSstSbcModelRequest(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }

    override fun updateTeamClicked(data: SstSbcTestReport) {
        showLoader()
        val dataModel = SstSbcAllData()
        dataModel.SstSbcTestReport= arrayListOf(data)
        dataModel.id=sstSbcData?.id
        viewmodel.updateSstSbc(dataModel)
        if (viewmodel.updateSstSbcDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateSstSbcDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateSstSbcDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SstSbcTeamFragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.SstSbcTestReport==200) {
                AppLogger.log("SstSbcTeamFragment card Data fetched successfully")
                viewmodel.fetchSstSbcModelRequest(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully",Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("SstSbcTeamFragment Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("SstSbcTeamFragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SstSbcTeamFragment Something went wrong")

            }
        }
    }

    override fun updateAddress(data: AllsiteInfoDataModel?) {
        showLoader()
        viewmodel.updateSiteInfo(data)
        if (viewmodel.updateSiteInfoDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateSiteInfoDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateSiteInfoDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SstSbcReportFragment address data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status?.Siteaddress==200 ) {
                AppLogger.log("SstSbcReportFragment address card Data updated successfully")
                viewmodel.siteInfoRequestAll(AppController.getInstance().siteid)
//                Toast.makeText(context,"Data Updated successfully",Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("SstSbcReportFragment address Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("SstSbcReportFragment address error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SstSbcReportFragment address Something went wrong")

            }
        }
    }
    private val startAddressResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        when (result.resultCode) {
            Activity.RESULT_OK -> {
                var actualLat:Double?=0.0
                var actualLong:Double?=0.0
                result.data?.getStringExtra(MapUtility.ADDRESS)?.let {
                    this.address2?.text=it
                    AppLogger.log("SstSbcReportFragment Address=====>$it")
                }
                result.data?.getDoubleExtra(MapUtility.LATITUDE,0.0)?.let {
                    this.siteLat?.text=String.format("%.6f", it)
                    AppLogger.log("SstSbcReportFragment siteLat=====>$it")
                    actualLat=it
                }
                result.data?.getDoubleExtra(MapUtility.LONGITUDE,0.0)?.let {
                    this.siteLong?.text=String.format("%.6f", it)
                    AppLogger.log("SstSbcReportFragment siteLong=====>$it")
                    actualLong=it
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
                        AppLogger.log("SstSbcReportFragment addressline2=====>$addressline2")
                    }catch (e:Exception){
                        AppLogger.log("${e.localizedMessage}")
                    }
                }
            }
            else -> {
                AppLogger.log( "SstSbcReportFragment Task Cancelled")
            }
        }
    }
    var address2: TextView?=null
    var siteLat: TextView?=null
    var siteLong: TextView?=null
    var postalCode: TextView?=null
    override fun initiateAddressActivity(address2: TextView?, siteLat: TextView, siteLong: TextView, postalCode: TextView, ) {
        this.address2=address2
        this.siteLat=siteLat
        this.siteLong=siteLong
        this.postalCode=postalCode
        val intent = Intent(requireContext(), LocationPickerActivity::class.java)
        var buldle=Bundle()
        intent.putExtra(MapUtility.LATITUDE,19.28133100)
        intent.putExtra(MapUtility.LONGITUDE,72.9780900)
        startAddressResult.launch(intent)
    }


}
