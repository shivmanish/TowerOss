package com.smarthub.baseapplication.ui.dialog.siteinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BuildingDetailsBotomSheetBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.serviceRequest.AcquisitionSurvey.ASAquisitionSurvey
import com.smarthub.baseapplication.model.serviceRequest.AcquisitionSurvey.ASAquisitionSurveyBuildingDetail
import com.smarthub.baseapplication.model.serviceRequest.AcquisitionSurvey.ASBoundryStructureDetail
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class BoundaryStructureDetailsBottomSheet(
    contentLayoutId: Int,
    var buildingDetailData: ASBoundryStructureDetail?,
    var serviceRequestAllData: ServiceRequestAllDataItem?,
    var viewmodel: HomeViewModel?,
    var Id: String?
) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding : BuildingDetailsBotomSheetBinding
var basicinfoModel:BasicinfoModel? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = BuildingDetailsBotomSheetBinding.bind(view)
        basicinfoModel = BasicinfoModel()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
buildingDetailData!!.let {
//    binding.editBuildingLatLong.setText(it.Latitude+""+it.Longitude)
//    binding.EditAddress.setText(it.BuildingAddress)
//    binding.editTypicalFloorArea.setText(it.TypicalFloorArea)
//    binding.editYearofConstruction.setText(it.ConstructionYear)
//    binding.spinBuildingType.data =  AppPreferences.getInstance().getDropDown(DropDowns.BuildingBuildType.name)
//    binding.spinGate.data =  AppPreferences.getInstance().getDropDown(DropDowns.GateAndFence.name)
//    binding.spinSiteAccessArea.data =  AppPreferences.getInstance().getDropDown(DropDowns.SiteAccessArea.name)


    binding.spinNoofFloors.onItemSelectedListener = object :
        AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
//            buildingDetailData!!.NoOfFloors =
//                AppPreferences.getInstance().getDropDown(DropDowns.NoOfFloors.name)
//                    .get(position).id

        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            TODO("Not yet implemented")
        }
    }

    binding.include.update.setOnClickListener {
        buildingDetailData!!.apply {
//            this.BuildingAddress = binding.EditAddress.text.toString()
//            this.TypicalFloorArea = binding.editTypicalFloorArea.text.toString()
//            ConstructionYear = binding.editYearofConstruction.text.toString()
        }

        val mServiceRequestAllDataItem = ServiceRequestAllDataItem()
        mServiceRequestAllDataItem.id = serviceRequestAllData!!.id
        mServiceRequestAllDataItem.ASAcquitionSurvey = ArrayList()

        val acquitionSurvey = ASAquisitionSurvey()
        acquitionSurvey.ASBoundryStructureDetail = ArrayList()
        acquitionSurvey.ASBoundryStructureDetail?.add(buildingDetailData!!)
        acquitionSurvey.id =       buildingDetailData!!.id
        mServiceRequestAllDataItem.ASAcquitionSurvey?.add(acquitionSurvey)
        val serviceRequestList = ServiceRequestAllData()
        serviceRequestList.add(mServiceRequestAllDataItem)

        basicinfoModel?.ServiceRequestMain = serviceRequestList
        basicinfoModel?.id = Id!!
        viewmodel!!.updateBasicInfo(basicinfoModel!!)
    }




}


    }
    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BuildingDetailsBotomSheetBinding.inflate(inflater)
        return binding.root
    }

}