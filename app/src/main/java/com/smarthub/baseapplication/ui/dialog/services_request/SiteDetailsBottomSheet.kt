package com.smarthub.baseapplication.ui.dialog.services_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SiteDetailBootomSheetDialogBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.serviceRequest.*
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class SiteDetailsBottomSheet(
    contentLayoutId: Int,
    var id:String,
    var viewmodel: HomeViewModel,
    var siteDetails: SiteDetail?,
    var serviceRequestAllData: ServiceRequestAllDataItem
) : BottomSheetDialogFragment(contentLayoutId) {
    var basicinfoModel: BasicinfoModel? = null
    lateinit var binding : SiteDetailBootomSheetDialogBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        basicinfoModel = BasicinfoModel()
        binding = SiteDetailBootomSheetDialogBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }

        try {
            siteDetails.let {
                binding.editSiteId.setText(it?.OpcoSiteID)
                binding.editOPCOSiteName.setText(it?.OpcoSiteName)
                binding.editOPCOSiteType.setText("")
                binding.editSiteCategory.setText("")
                binding.editTOCOUID.setText(it?.TocoUID)
                binding.editOPCOSiteType.setText("")
//                binding.spinTocositetype
//                binding.spinTocositestatus
//                binding.spinBuildingtype
                binding.editAccucationmode.setText("")
                binding.editnominalLatlong.setText(it?.Nominallatitude)
                binding.editExisitngTenants.setText(it?.Sitelatitude)
                binding.editSiteLatlong.setText(it?.ExistingTenants)
                binding.editAddress.setText(it?.Address)
            }
        }catch (e:Exception){
            AppLogger.log("Error in Feasibility planing Adapter ${e.localizedMessage}")
        }


        binding.include.update.setOnClickListener {
            siteDetails?.let {
               

                val mServiceRequestAllDataItem = ServiceRequestAllDataItem()
                mServiceRequestAllDataItem.id = serviceRequestAllData?.id
                mServiceRequestAllDataItem.FeasibilityPlanning = ArrayList()
                
                val fesibiliity = FeasibilityPlanning()
                fesibiliity.SiteDetails = ArrayList()
                fesibiliity.SiteDetails!!.add(it)
                fesibiliity.id = serviceRequestAllData?.FeasibilityPlanning!![0].id
                mServiceRequestAllDataItem.FeasibilityPlanning?.add(fesibiliity)

                val serviceRequestList = ServiceRequestAllData()
                serviceRequestList.add(mServiceRequestAllDataItem)

                basicinfoModel?.ServiceRequestMain = serviceRequestList
                basicinfoModel?.id = id
                viewmodel?.updateBasicInfo(basicinfoModel!!)
            }
        }

    }
    override fun getTheme() = R.style.NewDialogTask
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = SiteDetailBootomSheetDialogBinding.inflate(inflater)
        return binding.root
    }
}