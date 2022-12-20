package com.smarthub.baseapplication.ui.dialog.siteinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OperationsInfoDetailsBottomSheetBinding
import com.smarthub.baseapplication.model.siteInfo.OperationalInfo
import com.smarthub.baseapplication.network.pojo.site_info.OperationalInfoModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.OperationalInfoUploadModel
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class OperationsInfoBottomSheet(contentLayoutId: Int, var dropdowndata: OperationalInfoModel, var operationalInfoList: List<OperationalInfo>, var viewModel: HomeViewModel) : BottomSheetDialogFragment(contentLayoutId) {
    var basicinfoModel: BasicinfoModel? = null
    var operationalInfoUploadModel: OperationalInfoUploadModel? = null
    lateinit var binding : OperationsInfoDetailsBottomSheetBinding

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

        val operationalInfo:OperationalInfo = operationalInfoList[0]
        binding.txtRFCDate.text = operationalInfo.RFCDate
        binding.txtRFIDate.text = operationalInfo.RFIDate
        binding.txtRFSDate.text = operationalInfo.RFSDate
        binding.siteBillingStatus.setSpinnerData(dropdowndata.sitebillingstatus.data, operationalInfo.Sitebillingstatus)
        binding.costCenter.setSpinnerData(dropdowndata.costcentre.data, operationalInfo.Costcentre)
        binding.operatorSharing.setSpinnerData(dropdowndata.sharingfeasibility.data, operationalInfo.Sharingfeasibility)
        binding.powerSource.setText(operationalInfo.Powersource)
        binding.designDcLoad.setText(operationalInfo.DesignedDcLoad)
        binding.installedDcLoad.setText(operationalInfo.InstalledDcLoad)
        binding.operationTemp.setText(operationalInfo.OperatingTemp)
        binding.townCategorySpinner.setSpinnerData(dropdowndata.towncategor.data, operationalInfo.Towncategory)
        binding.hubCitySpinner.setSpinnerData(dropdowndata.hubsite.data, operationalInfo.Hubsite)
        binding.ldcaSpinner.setSpinnerData(dropdowndata.ldca.data, operationalInfo.Ldca)
        binding.scdaSpinner.setSpinnerData(dropdowndata.scda.data, operationalInfo.Scda)
        binding.dismanting.setText(operationalInfo.DismantlinglDate)

    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = OperationsInfoDetailsBottomSheetBinding.inflate(inflater)
        return binding.root
    }

}