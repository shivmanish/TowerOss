package com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.opcoInfo

import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpcoInfoSiteDialougeLayoutBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.Opcoinfo
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.updateOpcoTenency.updateOpcoDataItem
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel
import com.smarthub.baseapplication.viewmodels.SiteInfoViewModel

class OpcoSiteInfoEditDialouge (contentLayoutId: Int,var opcoInfo: Opcoinfo,var id:String, var listner:OpcoInfoListener): BaseBottomSheetDialogFragment() {
    lateinit var binding : OpcoInfoSiteDialougeLayoutBinding
    lateinit var viewModel:SiteInfoViewModel
    lateinit var homeViewModel: HomeViewModel
    var dataModel: updateOpcoDataItem?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = OpcoInfoSiteDialougeLayoutBinding.inflate(inflater)
        viewModel = ViewModelProvider(requireActivity())[SiteInfoViewModel::class.java]
        homeViewModel=ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataModel= updateOpcoDataItem()
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.85).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        binding.cancelTxt.setOnClickListener {
            Utils.dialogYesOrNo(requireContext(),"Cancel !! ","Do you want to discard changes"
            ) { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        dialog.dismiss()
                        Handler().postDelayed({
                            dismiss()
                        }, 200)
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                        dialog.dismiss()
                    }
                }
            }
        }

        binding.OPCOName.text=opcoInfo.OpcoName.toEditable()
        binding.OPCOSiteID.text=opcoInfo.OpcoSiteID.toEditable()
        binding.OPCOSiteName.text=opcoInfo.OpcoSiteName.toEditable()
        binding.CommittedNWA.text=opcoInfo.committedNWA
        binding.RFIAcceptanceDate.text=opcoInfo.rfiAcceptanceDate
        binding.RFRDate.text=opcoInfo.rfrDate
        binding.OPCOSignOffDate.text=opcoInfo.Opcosignoffdate
        AppPreferences.getInstance().setDropDown(binding.opcoSiteStatus,DropDowns.Opcositestatus.name,opcoInfo.Opcositestatus)
        AppPreferences.getInstance().setDropDown(binding.opcoSiteType,DropDowns.Opcositetype.name,opcoInfo.Opcositetype)
        AppPreferences.getInstance().setDropDown(binding.opcoNetworkType,DropDowns.Operatornetworktype.name,opcoInfo.Operatornetworktype)
        AppPreferences.getInstance().setDropDown(binding.AlarmExtension,DropDowns.Alarmsextension.name,opcoInfo.Alarmsextension)
        AppPreferences.getInstance().setDropDown(binding.RFTechnology,DropDowns.Rftechnology.name,opcoInfo.Rftechnology)
        AppPreferences.getInstance().setDropDown(binding.TelecomEquipmentType,DropDowns.Telecomequipmenttype.name,opcoInfo.Telecomequipmenttype)
        AppPreferences.getInstance().setDropDown(binding.RRUCount,DropDowns.Rrucount.name,opcoInfo.Rrucount)
        AppPreferences.getInstance().setDropDown(binding.SectorCount,DropDowns.Sectorcount.name,opcoInfo.Sectorcount)
        AppPreferences.getInstance().setDropDown(binding.RackCount,DropDowns.Rackcount.name,opcoInfo.Rackcount)
        AppPreferences.getInstance().setDropDown(binding.AnteenaCount,DropDowns.Antenacount.name,opcoInfo.Antenacount)
        AppPreferences.getInstance().setDropDown(binding.AnteenaSlotUsed,DropDowns.Antenaslotused.name,opcoInfo.Antenaslotused)

        setDatePickerView(binding.RFIAcceptanceDate)
        setDatePickerView(binding.RFRDate)
        setDatePickerView(binding.OPCOSignOffDate)

        binding.update.setOnClickListener {
            opcoInfo.let {
                it.OpcoSiteID=binding.OPCOSiteID.text.toString()
                it.OpcoName=binding.OPCOName.text.toString()
                it.OpcoSiteName=binding.OPCOSiteName.text.toString()
                it.committedNWA=binding.CommittedNWA.text.toString()
                it.rfiAcceptanceDate=binding.RFIAcceptanceDate.text.toString()
                it.rfrDate=binding.RFRDate.text.toString()
                it.Opcosignoffdate=binding.OPCOSignOffDate.text.toString()
                it.Opcositestatus=binding.opcoSiteStatus.selectedValue.id
                it.Opcositetype=binding.opcoSiteType.selectedValue.id
                it.Operatornetworktype=binding.opcoNetworkType.selectedValue.id
                it.Alarmsextension=binding.AlarmExtension.selectedValue.id
                it.Rftechnology=binding.RFTechnology.selectedValue.id
                it.Telecomequipmenttype=binding.TelecomEquipmentType.selectedValue.id
                it.Rrucount=binding.RRUCount.selectedValue.id
                it.Sectorcount=binding.SectorCount.selectedValue.id
                it.Rackcount=binding.RackCount.selectedValue.id
                it.Antenacount=binding.AnteenaCount.selectedValue.id
                it.Antenaslotused=binding.AnteenaSlotUsed.selectedValue.id
            }
            dataModel?.Opcoinfo=opcoInfo
            dataModel?.id=id
            AppLogger.log("opcoInfo data for update: ${dataModel?.Opcoinfo}, whole Data: ${dataModel}")
            viewModel.updateOpcoTenency(dataModel!!)
        }
        hideProgressLayout()
        if (viewModel.opcoTenencyUpdateResoponse?.hasActiveObservers() == true)
            viewModel.opcoTenencyUpdateResoponse?.removeObservers(viewLifecycleOwner)
        viewModel.opcoTenencyUpdateResoponse?.observe(viewLifecycleOwner){
            if (it!=null){
                if (it.status == Resource.Status.LOADING){
                    showProgressLayout()
                }else{
                    hideProgressLayout()
                }
                if (it.status == Resource.Status.SUCCESS && it.data?.Status?.isNotEmpty() == true){
                    AppLogger.log("Successfully updated all fields")
                    dismiss()
                    dataModel?.Opcoinfo?.let { it1 -> listner.updatedData(it1) }
//                    homeViewModel.opcoTenancyRequestAll(AppController.getInstance().siteid)
                }else{
                    AppLogger.log("UnExpected Error found")

                }
            }else{
                AppLogger.log("Something went wrong")
            }
        }
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

    interface OpcoInfoListener{
        fun updatedData(data : Opcoinfo)
    }
}