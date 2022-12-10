package com.smarthub.baseapplication.ui.dialog.siteinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OperationsInfoDetailsBottomSheetBinding
import com.smarthub.baseapplication.model.siteInfo.OperationalInfo
import com.smarthub.baseapplication.network.pojo.site_info.BasicInfoModelDropDown
import com.smarthub.baseapplication.network.pojo.site_info.OperationalInfoModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoServiceData
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.OperationalInfoUploadModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.viewmodel.BasicInfoDialougeViewmodel

class OperationsInfoBottomSheet(
    contentLayoutId: Int,
    var dropdowndata: OperationalInfoModel,
    var operationalInfoList: List<OperationalInfo>
) : BottomSheetDialogFragment(contentLayoutId) {
    var basicinfoModel: BasicinfoModel? = null
    var operationalInfoUploadModel: OperationalInfoUploadModel? = null
    lateinit var binding : OperationsInfoDetailsBottomSheetBinding
    lateinit var viewModel: BasicInfoDialougeViewmodel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = OperationsInfoDetailsBottomSheetBinding.bind(view)
        basicinfoModel = BasicinfoModel()
        operationalInfoUploadModel = OperationalInfoUploadModel()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        binding.update.setOnClickListener {
            operationalInfoUploadModel!!.let{
               it.Costcentre = "1"
               it.DesignedDcLoad= binding.designDcLoad.text.toString()
               it.DismantlinglDate= binding.dismanting.text.toString()
               it.Hubsite= ""
               it.InstalledDcLoad= binding.installedDcLoad.text.toString()
               it.Ldca= ""
               it.OperatingTemp= binding.operationTemp.text.toString()
               it.Powersource= binding.powerSource.text.toString()
               it.RFCDate= binding.txtRFCDate.text.toString()
               it.RFIDate= binding.txtRFIDate.text.toString()
               it.RFSDate= binding.txtRFSDate.text.toString()
               it.Scda= ""
               it.Sharingfeasibility= ""
               it.Sitebillingstatus= ""
               it.Towncategory= ""
            }
            basicinfoModel!!.operationalInfoUploadModel = operationalInfoUploadModel!!
            viewModel.updateData(basicinfoModel!!)
        }


        viewModel = ViewModelProvider(requireActivity()).get(BasicInfoDialougeViewmodel::class.java)
        var operationalInfo:OperationalInfo = operationalInfoList.get(0)
        binding.txtRFCDate.text = operationalInfo.RFCDate
        binding.txtRFIDate.text = operationalInfo.RFIDate
        binding.txtRFSDate.text = operationalInfo.RFSDate
        binding.siteBillingStatus.setSpinnerData(dropdowndata.sitebillingstatus.data,operationalInfo.Sitebillingstatus.get(0).name)
        binding.costCenter.setSpinnerData(dropdowndata.costcentre.data,operationalInfo.Costcentre.get(0).name)
        binding.operatorSharing.setSpinnerData(dropdowndata.sharingfeasibility.data, operationalInfo.Sharingfeasibility.get(0).name)
        binding.powerSource.setText(operationalInfo.Powersource)
        binding.designDcLoad.setText(operationalInfo.DesignedDcLoad)
        binding.installedDcLoad.setText(operationalInfo.InstalledDcLoad)
        binding.operationTemp.setText(operationalInfo.OperatingTemp)
        binding.townCategorySpinner.setSpinnerData(dropdowndata.towncategor.data,operationalInfo.Towncategory.get(0).name)
        binding.hubCitySpinner.setSpinnerData(dropdowndata.hubsite.data, operationalInfo.Hubsite.get(0).name)
        binding.ldcaSpinner.setSpinnerData(dropdowndata.ldca.data,operationalInfo.Ldca.get(0).name)
        binding.scdaSpinner.setSpinnerData(dropdowndata.scda.data, operationalInfo.Scda.get(0).name)
        binding.dismanting.setText(operationalInfo.DismantlinglDate)

    }

    override fun getTheme() = R.style.NewDialogTask



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = OperationsInfoDetailsBottomSheetBinding.inflate(inflater)
        return binding.root
    }

}