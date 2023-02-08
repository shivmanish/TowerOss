package com.smarthub.baseapplication.ui.dialog.siteinfo

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.BasicInfoDetailsBottomSheetBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.basicInfo.Basicinfo
import com.smarthub.baseapplication.model.logs.ChangeLog
import com.smarthub.baseapplication.model.logs.LogsDataInfo
import com.smarthub.baseapplication.model.logs.PostLogData
import com.smarthub.baseapplication.model.siteInfo.SiteBasicinfo
import com.smarthub.baseapplication.network.pojo.site_info.BasicInfoModelDropDown
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoServiceData
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class BasicInfoBottomSheet(contentLayoutId: Int, val basicinfodata: SiteBasicinfo,var id : String, var viewModel: HomeViewModel) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding: BasicInfoDetailsBottomSheetBinding
    var basicinfoModel: BasicinfoModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        basicinfoModel = BasicinfoModel()
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
        AppPreferences.getInstance().setDropDown(binding.siteStatus,"Sitestatus",basicinfodata.Sitestatus)
        AppPreferences.getInstance().setDropDown(binding.siteCategory,"Sitecategory",basicinfodata.Sitecategory)
        AppPreferences.getInstance().setDropDown(binding.siteType,"Sitetype",basicinfodata.Sitetype)
        AppPreferences.getInstance().setDropDown(binding.siteOwnership,"Siteownership",basicinfodata.Siteownership)

        binding.txBuildingType.setText(basicinfodata.Buildingtype)
        binding.txtLocationZone.setText(basicinfodata.Locationzone)
        binding.txtMaintenanceZone.setText(basicinfodata.MaintenancePoint)
        binding.txtProjectName.setText(basicinfodata.Projectname)
        binding.txtSiteInChargeName.setText(basicinfodata.siteInChargeName)
        binding.txtSiteInChargeNumber.setText(basicinfodata.siteInChargeNumber)
        binding.txtRentEscalation.setText("")
        binding.address.setText(basicinfodata.siteaddress)

        binding.update.setOnClickListener {
            basicinfodata.let{
//                Toast.makeText(requireContext(),"Sitestatus:${binding.siteStatus.selectedValue.id}",Toast.LENGTH_SHORT).show()
                it.Sitestatus = binding.siteStatus.selectedValue.id
                it.Buildingtype = binding.txBuildingType.text.toString()
                it.Locationzone= binding.txtLocationZone.text.toString()
                it.MaintenancePoint= ""
                it.Projectname= binding.txtProjectName.text.toString()
                it.aliasName= ""
                it.National = binding.siteNational.selectedValue.id
                it.id = basicinfodata.id
                it.siteID= binding.txSiteID.text.toString()
                it.siteInChargeName= binding.txtSiteInChargeName.text.toString()
                it.siteInChargeNumber= binding.txtSiteInChargeNumber.text.toString()
                it.siteName= binding.txSiteName.text.toString()
                it.siteaddress= binding.address.text.toString()
            }
            basicinfoModel?.Basicinfo = basicinfodata
            basicinfoModel?.id = id
            viewModel.updateBasicInfo(basicinfoModel!!)
            updateLog("Basic Info On SiteInfo")
        }


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

        if (viewModel.loglivedata?.hasActiveObservers()==true)
            viewModel.loglivedata?.removeObservers(this)
        viewModel.loglivedata!!.observe(this, Observer {
            if (it?.data != null && it.status == Resource.Status.SUCCESS ) {
                try {
                    if (it?.data?.Status?.get(0)?.success!!){
                        AppLogger.log("Log Data updated successfully===>: ${it.data.item}")
                        AppLogger.log("size :${it.data.item!!.get(0).ChangeLog.size}")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            else{
                AppLogger.log("Somthing went wrong in post log data")
            }

        })
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

    fun updateLog(discription:String){
        var logData=PostLogData()
        var list =ArrayList<ChangeLog>()
        var changeLogs=ChangeLog(
            Activity = "Update",
            Description = discription,
            ChangesMade = "",
            IPAddressAngular = "",
            isActive = true
        )
        list.clear()
        list.add(changeLogs)
        logData.let {
            it.id=AppController.getInstance().siteid
            it.ChangeLog=list
        }
        AppLogger.log("log Data in Basic Info Bottom Sheet ====>$logData")
        viewModel.postChangeLog(logData)
    }

}