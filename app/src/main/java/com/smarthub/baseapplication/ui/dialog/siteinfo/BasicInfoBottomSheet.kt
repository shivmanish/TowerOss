package com.smarthub.baseapplication.ui.dialog.siteinfo

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.BasicInfoDetailsBottomSheetBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.basicInfo.Basicinfo
import com.smarthub.baseapplication.model.siteInfo.SiteBasicinfo
import com.smarthub.baseapplication.network.pojo.site_info.BasicInfoModelDropDown
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoServiceData
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class BasicInfoBottomSheet(contentLayoutId: Int, val data: BasicInfoModelDropDown,
                           val basicinfodata: SiteBasicinfo,var id : String, var viewModel: HomeViewModel) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding: BasicInfoDetailsBottomSheetBinding
    var basicinfoModel: BasicinfoModel? = null
    var basicinfo: BasicinfoServiceData? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        basicinfoModel = BasicinfoModel()
        basicinfo = BasicinfoServiceData()
        binding = BasicInfoDetailsBottomSheetBinding.bind(view)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.75).toInt()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        if (viewModel.basicinfoModel?.hasActiveObservers() == true){
            viewModel.basicinfoModel?.removeObservers(viewLifecycleOwner)
        }
        viewModel.basicinfoModel?.observe(viewLifecycleOwner) {
            dialog!!.dismiss()
            dialog!!.cancel()
        }
        binding.txSiteName.setText(basicinfodata.siteName)
        binding.txSiteID.setText(basicinfodata.siteID)
        binding.siteStatus.setSpinnerData(data.sitestatus.data, basicinfodata.Sitestatus)
        binding.siteCategory.setSpinnerData(data.sitecategory.data, basicinfodata.Sitecategory)
        binding.siteType.setSpinnerData(data.sitetype.data, basicinfodata.Sitetype)
        binding.siteOwnership.setSpinnerData(data.siteownership.data, basicinfodata.Siteownership)
        binding.txBuildingType.setText(basicinfodata.Buildingtype)
        binding.txtLocationZone.setText(basicinfodata.Locationzone)
        binding.txtMaintenanceZone.setText(basicinfodata.MaintenancePoint)
        binding.txtProjectName.setText(basicinfodata.Projectname)
        binding.txtSiteInChargeName.setText(basicinfodata.siteInChargeName)
        binding.txtSiteInChargeNumber.setText(basicinfodata.siteInChargeNumber)
        binding.txtRentEscalation.setText("")
        binding.address.setText(basicinfodata.siteaddress)

        binding.update.setOnClickListener {
            basicinfo?.let{

                it.Buildingtype = binding.txBuildingType.text.toString()
                it.Locationzone= binding.txtLocationZone.text.toString()
                it.MaintenancePoint= ""
                it.Projectname= binding.txtProjectName.text.toString()
                it.aliasName= ""
                it.id = basicinfodata.id.toString()
                it.siteID= binding.txSiteID.text.toString()
                it.siteInChargeName= binding.txtSiteInChargeName.text.toString()
                it.siteInChargeNumber= binding.txtSiteInChargeNumber.text.toString()
                it.siteName= binding.txSiteName.text.toString()
                it.siteaddress= binding.address.text.toString()
            }
            basicinfoModel?.Basicinfo = basicinfo!!
            basicinfoModel?.id = id
            viewModel.updateData(basicinfoModel!!)
        }
        binding.cancelTxt.setOnClickListener {
            dismiss()
        }

        hideProgressLayout()
        if (viewModel.basicinfoModel?.hasActiveObservers() == true)
            viewModel.basicinfoModel?.removeObservers(viewLifecycleOwner)
        viewModel.basicinfoModel?.observe(viewLifecycleOwner){
            if (it!=null){
                if (it.status == Resource.Status.LOADING){
                    showProgressLayout()
                }else{
                    hideProgressLayout()
                }
                if (it.status == Resource.Status.SUCCESS){
                    AppLogger.log("Successfully updated all fields")
                    dismiss()
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = BasicInfoDetailsBottomSheetBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = false
        return dialog
    }

}