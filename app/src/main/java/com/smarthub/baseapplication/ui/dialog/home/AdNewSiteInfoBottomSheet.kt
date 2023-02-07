package com.smarthub.baseapplication.ui.dialog.home

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AdNewSiteInfoBottomSheetBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.serviceRequest.new_site.GenerateSiteIdResponse
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoData
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.CreateSiteModel
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AdNewSiteInfoBottomSheet(contentLayoutId: Int, var viewModel: HomeViewModel,var addNewSiteSheetListener: AdNewSiteSheetListener) : BaseBottomSheetDialogFragment() {
    lateinit var addSiteHelper: AddSiteHelper
    lateinit var binding: AdNewSiteInfoBottomSheetBinding
    var tempGenerateSiteId = ""
    var basicinfo: BasicinfoData? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = AdNewSiteInfoBottomSheetBinding.bind(view)
        addSiteHelper = AddSiteHelper()
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight() * 0.75).toInt()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }

        setSpinnserData()
        binding.update.setOnClickListener {
            basicinfo = BasicinfoData()
            basicinfo?.let {
                it.siteName = binding.siteName.text.toString()
                it.MaintenancePoint = addSiteHelper.mentatinance_point!!.name
                it.aliasName = binding.aliasName.text.toString()
                it.siteID = binding.siteId.text.toString()
                it.siteName = binding.siteName.text.toString()
                it.National = addSiteHelper.national!!.name
                it.Region = addSiteHelper.region!!.name
                it.State = addSiteHelper.state!!.name
            }
            val datamodel = CreateSiteModel(basicinfo,AppController.getInstance().ownerName)
            viewModel.createSite(datamodel)
            showUploadProgress()

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
                    binding.generateIsLayout.visibility = View.INVISIBLE
                    showProgressLayout()
                } else {
                    hideProgressLayout()
                }
                if (it.status == Resource.Status.SUCCESS) {
                    AppLogger.log("Successfully updated all fields")
                    binding.siteId.text = it.data?.Generatid
                    binding.generateIsLayout.visibility = View.VISIBLE
                } else {
                    AppLogger.log("UnExpected Error found")
                }
            } else {
                binding.generateIsLayout.visibility = View.INVISIBLE
                AppLogger.log("Something went wrong")
            }
        }

        if (viewModel.siteInfoModelNew?.hasActiveObservers() == true)
            viewModel.siteInfoModelNew?.removeObservers(viewLifecycleOwner)
        viewModel.siteInfoModelNew?.observe(viewLifecycleOwner) {
            if (it?.data != null && it.data.item?.isNotEmpty() == true) {
                dialog!!.dismiss()
                addNewSiteSheetListener.siteCreated(it.data.item!![0].id.toString())
            } else {
                AppLogger.log("Something went wrong")
            }
        }
    }


    var isInit = false
    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if (viewModel.generateSiteId?.hasActiveObservers() == true)
            viewModel.generateSiteId?.removeObservers(viewLifecycleOwner)
        if (viewModel.basicinfoModel?.hasActiveObservers() == true)
            viewModel.basicinfoModel?.removeObservers(viewLifecycleOwner)
    }

    fun showProgressLayout() {
        if (binding.progress.visibility != View.VISIBLE)
            binding.progress.visibility = View.VISIBLE
    }

    fun hideProgressLayout() {
        if (binding.progress.visibility == View.VISIBLE)
            binding.progress.visibility = View.GONE
    }

    fun showUploadProgress() {
        if (binding.progressLayout.visibility != View.VISIBLE)
            binding.progressLayout.visibility = View.VISIBLE
    }

    fun hideUplodProgress() {
        if (binding.progressLayout.visibility == View.VISIBLE)
            binding.progressLayout.visibility = View.GONE
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AdNewSiteInfoBottomSheetBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = false
        return dialog
    }

    fun setSpinnserData() {
        binding.siteCode.setSpinnerData(DataProvider.getSiteCode())
        binding.cityCode.setSpinnerData(DataProvider.getCityCode())
        binding.siteType.setSpinnerData(DataProvider.getSiteType())
        binding.siteClass.setSpinnerData(DataProvider.getSiteClass())
        binding.national.setSpinnerData(DataProvider.getNational())
        binding.region.setSpinnerData(DataProvider.getRegion())
        binding.state.setSpinnerData(DataProvider.getState())
        binding.mentenancePoint.setSpinnerData(DataProvider.getMentainancePoint())

        binding.siteCode.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                addSiteHelper.siteCode = DataProvider.getSiteCode()[position]
            }

        }
        binding.cityCode.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                addSiteHelper.cityCode = DataProvider.getCityCode()[position]
            }

        }
        binding.siteType.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                addSiteHelper.siteTypeCode = DataProvider.getSiteCode()[position]
            }

        }
        binding.siteClass.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                addSiteHelper.siteClass = DataProvider.getSiteClass()[position]
                if (isInit)
                    fetchSiteId()
                isInit = true
            }

        }
        binding.national.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                addSiteHelper.national = DataProvider.getNational()[position]
            }

        }
        binding.region.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                addSiteHelper.region = DataProvider.getRegion()[position]
            }

        }
        binding.state.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                addSiteHelper.state = DataProvider.getState()[position]
            }

        }
        binding.mentenancePoint.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                addSiteHelper.mentatinance_point = DataProvider.getMentainancePoint()[position]
            }

        }

    }

    fun fetchSiteId() {
        try {
            tempGenerateSiteId = "${binding.companyCode.text}-${addSiteHelper.siteCode!!.name}-" + "${addSiteHelper.cityCode!!.name}-${addSiteHelper.siteTypeCode!!.name}-${addSiteHelper.siteClass!!.name}"
            viewModel.generateSiteId(GenerateSiteIdResponse(tempGenerateSiteId))
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    interface AdNewSiteSheetListener{
        fun siteCreated(id:String)
    }
}