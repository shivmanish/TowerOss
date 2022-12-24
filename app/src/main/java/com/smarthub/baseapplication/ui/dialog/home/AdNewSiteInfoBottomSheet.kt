package com.smarthub.baseapplication.ui.dialog.home

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AdNewSiteInfoBottomSheetBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.dropdown.DropDownItem
import com.smarthub.baseapplication.model.serviceRequest.new_site.GenerateSiteIdResponse
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoServiceData
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AdNewSiteInfoBottomSheet(contentLayoutId: Int, var viewModel: HomeViewModel) :
    BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding: AdNewSiteInfoBottomSheetBinding
    var tempGenerateSiteId = ""
    var basicinfo: BasicinfoServiceData? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var data: ArrayList<DropDownItem> = ArrayList()
        data.add(DropDownItem("Name1", "1"))
        data.add(DropDownItem("Name2", "2"))
        data.add(DropDownItem("Name3", "3"))
        data.add(DropDownItem("Name4", "4"))
        binding.siteType.setSpinnerData(data)

//        basicinfoModel = BasicinfoModel()
//        basicinfo = BasicinfoServiceData()
        binding = AdNewSiteInfoBottomSheetBinding.bind(view)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight() * 0.75).toInt()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        if (viewModel.basicinfoModel?.hasActiveObservers() == true) {
            viewModel.basicinfoModel?.removeObservers(viewLifecycleOwner)
        }
        viewModel.basicinfoModel?.observe(viewLifecycleOwner) {
            dialog!!.dismiss()
            dialog!!.cancel()
        }
        binding.update.setOnClickListener {
           basicinfo = BasicinfoServiceData()
            basicinfo?.let {
                it.Buildingtype = binding.txBuildingType.text.toString()
                it.Locationzone = binding.txtLocationZone.text.toString()
                it.MaintenancePoint = ""
                it.Projectname = binding.txtProjectName.text.toString()
                it.aliasName = ""
                it.siteID = binding.txSiteID.text.toString()
                it.siteInChargeName = binding.txtSiteInChargeName.text.toString()
                it.siteInChargeNumber = binding.txtSiteInChargeNumber.text.toString()
                it.siteName = binding.txSiteName.text.toString()
            }
            val datamodel = BasicinfoModel()
            datamodel.Basicinfo = basicinfo
            viewModel.updateBasicInfo(datamodel)

        }

        var check = 0
        binding.siteType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                if (++check > 1) {
                    val item = data[position]
                    AppLogger.log("item :${item.name}")
                    tempGenerateSiteId = "${binding.txSiteName.text}-${binding.txSiteID.text}-" +
                            "${binding.siteStatus.selectedValue.name}-${binding.siteCategory.selectedValue.name}"
                    viewModel.generateSiteId(GenerateSiteIdResponse(tempGenerateSiteId))
                    AppLogger.log("item :${data[position].name}")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        binding.cancelTxt.setOnClickListener {
            dismiss()
        }

        hideProgressLayout()
        if (viewModel.generateSiteId?.hasActiveObservers() == true)
            viewModel.generateSiteId?.removeObservers(viewLifecycleOwner)
        viewModel.generateSiteId?.observe(viewLifecycleOwner) {
            if (it != null) {
                if (it.status == Resource.Status.LOADING) {
                    showProgressLayout()
                } else {
                    hideProgressLayout()
                }
                if (it.status == Resource.Status.SUCCESS) {
                    AppLogger.log("Successfully updated all fields")
//                    dismiss()
                    binding.txBuildingType.text = it.data?.Generatid
                } else {
                    AppLogger.log("UnExpected Error found")
                }
            } else {
                AppLogger.log("Something went wrong")
            }
        }

        if (viewModel.basicInfoUpdate?.hasActiveObservers() == true)
            viewModel.basicInfoUpdate?.removeObservers(viewLifecycleOwner)
        viewModel.basicInfoUpdate?.observe(viewLifecycleOwner) {
            if (it != null) {
                dialog!!.dismiss()
                dialog!!.cancel()
            } else {
                AppLogger.log("Something went wrong")
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
//        if (viewModel.basicinfoModel?.hasActiveObservers() == true)
//            viewModel.basicinfoModel?.removeObservers(viewLifecycleOwner)
    }

    fun showProgressLayout() {
        if (binding.progressLayout.visibility != View.VISIBLE)
            binding.progressLayout.visibility = View.VISIBLE
    }

    fun hideProgressLayout() {
        if (binding.progressLayout.visibility == View.VISIBLE)
            binding.progressLayout.visibility = View.GONE
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AdNewSiteInfoBottomSheetBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = false
        return dialog
    }

}