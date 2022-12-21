package com.smarthub.baseapplication.ui.dialog.siteinfo

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OperationsInfoDetailsBottomSheetBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteInfo.OperationalInfo
import com.smarthub.baseapplication.network.pojo.site_info.OperationalInfoModel
import com.smarthub.baseapplication.ui.dialog.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.OperationalInfoUploadModel
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class OperationsInfoBottomSheet(contentLayoutId: Int,var id : String, var dropdowndata: OperationalInfoModel, var operationalInfo : OperationalInfo, var viewModel: HomeViewModel) : BaseBottomSheetDialogFragment(contentLayoutId) {
    var basicinfoModel: BasicinfoModel? = null
    var operationalInfoUploadModel: OperationalInfoUploadModel? = null
    lateinit var binding : OperationsInfoDetailsBottomSheetBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = OperationsInfoDetailsBottomSheetBinding.bind(view)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.75).toInt()
        basicinfoModel = BasicinfoModel()
        operationalInfoUploadModel = OperationalInfoUploadModel()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        binding.update.setOnClickListener {
            operationalInfoUploadModel!!.let{
                it.id = operationalInfo.id.toString()
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
            basicinfoModel?.OperationalInfo = operationalInfoUploadModel!!
            basicinfoModel?.id = id
            viewModel.updateBasicInfo(basicinfoModel!!)
        }

        val operationalInfo:OperationalInfo = operationalInfo
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

        hideProgressLayout()
        if (viewModel.basicInfoUpdate?.hasActiveObservers() == true)
            viewModel.basicInfoUpdate?.removeObservers(viewLifecycleOwner)
        viewModel.basicInfoUpdate?.observe(viewLifecycleOwner){
            if (it!=null){
                if (it.status == Resource.Status.LOADING){
                    showProgressLayout()
                }else{
                    hideProgressLayout()
                }
                if (it.status == Resource.Status.SUCCESS && it.data?.Status?.isNotEmpty() == true){
                    AppLogger.log("Successfully updated all fields")
                    dismiss()
                    viewModel.fetchSiteInfoData(id)
                }else{
                    AppLogger.log("UnExpected Error found")
                }
            }else{
                AppLogger.log("Something went wrong")
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if (viewModel.basicinfoModel?.hasActiveObservers() == true)
            viewModel.basicinfoModel?.removeObservers(viewLifecycleOwner)
    }

    fun showProgressLayout(){
        if (binding.progressLayout.visibility != View.VISIBLE)
            binding.progressLayout.visibility = View.VISIBLE
    }
    fun hideProgressLayout(){
        if (binding.progressLayout.visibility == View.VISIBLE)
            binding.progressLayout.visibility = View.GONE
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = OperationsInfoDetailsBottomSheetBinding.inflate(inflater)
        return binding.root
    }

}