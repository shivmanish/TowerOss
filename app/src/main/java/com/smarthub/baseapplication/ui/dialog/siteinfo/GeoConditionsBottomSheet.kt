package com.smarthub.baseapplication.ui.dialog.siteinfo

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.GeoConditionsDetailsBottomSheetBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteInfo.siteInfoData.GeoCondition
import com.smarthub.baseapplication.network.pojo.site_info.GeoConditionModel
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.GeoConditionUpdateModel
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class GeoConditionsBottomSheet(contentLayoutId: Int, var id : String, var dropdownData: GeoConditionModel, var geoCondition: GeoCondition, var viewModel: HomeViewModel) : BaseBottomSheetDialogFragment() {
    var basicinfoModel: BasicinfoModel? = null
    var geoConditionUpdateModel: GeoConditionUpdateModel? = null
    lateinit var binding: GeoConditionsDetailsBottomSheetBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = GeoConditionsDetailsBottomSheetBinding.bind(view)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.75).toInt()
        basicinfoModel = BasicinfoModel()
        geoConditionUpdateModel = GeoConditionUpdateModel()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        binding.update.setOnClickListener {
            geoConditionUpdateModel?.let{
                it.id = geoCondition.id.toString()
                it.Altitude = binding.textAltitude.text.toString()
                it.Floodzone = binding.floodZoneSpinner.selectedValue.id
                it.Seismiczone = binding.seismecZoneSpinner.selectedValue.id
                it.Potentialthreat = binding.potentioalThreatSpinner.selectedValue.id
                it.Terraintype = binding.terrainTypeSpinner.selectedValue.id
                it.Windzone = binding.windZoneSpinner.selectedValue.id
                it.TempratureZone = binding.textTempZone.text.toString()
            }
            basicinfoModel?.GeoCondition = geoConditionUpdateModel!!
            basicinfoModel?.id = id
            viewModel.updateBasicInfo(basicinfoModel!!)
        }
        binding.potentioalThreatSpinner.setSpinnerData(dropdownData.potentialthreat.data, geoCondition.Potentialthreat)
        binding.textAltitude.setText(geoCondition.Altitude)
        binding.windZoneSpinner.setSpinnerData(dropdownData.windzone.data, geoCondition.Windzone)
        binding.seismecZoneSpinner.setSpinnerData(dropdownData.seismiczone.data, geoCondition.Seismiczone)
        binding.floodZoneSpinner.setSpinnerData(dropdownData.floodzone.data, geoCondition.Floodzone)
        binding.textTempZone.setText(geoCondition.TempratureZone)
        binding.terrainTypeSpinner.setSpinnerData(dropdownData.terraintype.data, geoCondition.Terraintype)

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = GeoConditionsDetailsBottomSheetBinding.inflate(inflater)
        return binding.root
    }


}