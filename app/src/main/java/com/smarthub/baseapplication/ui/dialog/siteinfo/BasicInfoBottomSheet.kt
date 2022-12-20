package com.smarthub.baseapplication.ui.dialog.siteinfo

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BasicInfoDetailsBottomSheetBinding
import com.smarthub.baseapplication.network.pojo.site_info.BasicInfoModelDropDown
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoServiceData
import com.smarthub.baseapplication.ui.dialog.siteinfo.viewmodel.BasicInfoDialougeViewmodel
import com.smarthub.baseapplication.utils.Utils

class BasicInfoBottomSheet(contentLayoutId: Int, val data: BasicInfoModelDropDown, val basicinfodata: com.smarthub.baseapplication.model.siteInfo.Basicinfo) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding: BasicInfoDetailsBottomSheetBinding
    lateinit var viewModel: BasicInfoDialougeViewmodel
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
        viewModel = ViewModelProvider(requireActivity())[BasicInfoDialougeViewmodel::class.java]
        if (viewModel.basicinfoModel?.hasActiveObservers() == true){
            viewModel.basicinfoModel?.removeObservers(viewLifecycleOwner)
        }
        viewModel.basicinfoModel?.observe(viewLifecycleOwner) {
            dialog!!.dismiss()
            dialog!!.cancel()
        }
        binding.update.setOnClickListener {
            basicinfo!!.let{

                it.Buildingtype = binding.txBuildingType.text.toString()
                it.Locationzone= binding.txtLocationZone.text.toString()
                it.MaintenancePoint= ""
                it.Projectname= binding.txtProjectName.text.toString()
                it.aliasName= ""
                it.id = basicinfodata.id
                it.siteID= binding.txSiteID.text.toString()
                it.siteInChargeName= binding.txtSiteInChargeName.text.toString()
                it.siteInChargeNumber= binding.txtSiteInChargeNumber.text.toString()
                it.siteName= binding.txSiteName.text.toString()
                it.siteaddress= binding.address.text.toString()
            }
            basicinfoModel!!.basicinfo = basicinfo!!
            viewModel.updateData(basicinfoModel!!)
        }
        binding.txSiteName.setText(basicinfodata.siteName)
        binding.txSiteID.setText(basicinfodata.siteID)
        binding.siteStatus.setSpinnerData(data.sitestatus.data, basicinfodata.Sitestatus[0].name)
        binding.siteCategory.setSpinnerData(data.sitecategory.data, basicinfodata.Sitecategory[0].name)
        binding.siteType.setSpinnerData(data.sitetype.data, basicinfodata.Sitetype[0].name)
        binding.siteOwnership.setSpinnerData(data.siteownership.data, basicinfodata.Siteownership[0].name)
        binding.txBuildingType.setText(basicinfodata.Buildingtype[0].name)
        binding.txtLocationZone.setText(basicinfodata.Locationzone[0].name)
        binding.txtMaintenanceZone.setText(basicinfodata.MaintenancePoint[0].maintenancepoint)
        binding.txtProjectName.setText(basicinfodata.Projectname[0].name)
        binding.txtSiteInChargeName.setText(basicinfodata.siteInChargeName)
        binding.txtSiteInChargeNumber.setText(basicinfodata.siteInChargeNumber)
        binding.txtRentEscalation.setText("")
        binding.address.setText(basicinfodata.siteaddress)

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