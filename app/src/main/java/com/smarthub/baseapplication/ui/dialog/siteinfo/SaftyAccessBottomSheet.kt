package com.smarthub.baseapplication.ui.dialog.siteinfo

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SaftyAccessDetailsBottomSheetBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteInfo.SafetyAndAcces
import com.smarthub.baseapplication.network.pojo.site_info.SafetyAndAccessModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.GeoConditionUpdateModel
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.SafetyAndAccessUpdateModel
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class SaftyAccessBottomSheet(contentLayoutId:Int,var id : String, var dropdown: SafetyAndAccessModel, var safetyAndAccess: SafetyAndAcces, var viewModel: HomeViewModel) : BottomSheetDialogFragment(contentLayoutId) {

    var basicinfoModel: BasicinfoModel? = null
//    var geoConditionUpdateModel: SafetyAndAccessUpdateModel? = null
    lateinit var binding : SaftyAccessDetailsBottomSheetBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SaftyAccessDetailsBottomSheetBinding.bind(view)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.75).toInt()
        basicinfoModel = BasicinfoModel()
//        geoConditionUpdateModel = SafetyAndAccessUpdateModel()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }

        binding.update.setOnClickListener {
            safetyAndAccess.let{
                it.id = safetyAndAccess.id
                it.CautionSignage = binding.textCautionSignage.text.toString()
                it.DangerSignage = binding.dangerSignageSpinner.selectedValue.id
                it.SiteAccessArea = binding.siteAccessAreaSpinner.selectedValue.id
                it.GateAndFence = binding.textGate.text.toString()
                it.NearByFireStation = binding.textFireStation.text.toString()
                it.NearByPoliceStation = binding.textPoliceStation.text.toString()
                it.NearByPoliceStationNumber = safetyAndAccess.NearByPoliceStationNumber//binding.textTempZone.text.toString()
                it.Physicalsecurity = safetyAndAccess.Physicalsecurity//binding.textTempZone.text.toString()
                it.Siteaccess = binding.siteAccess.selectedValue.id
                it.Siteaccessmethodology = binding.textSiteAccesseethodology.text.toString()
                it.Videomonitoring = binding.videoMonitoringSpinner.selectedValue.id
            }
            basicinfoModel?.SafetyAndAccess = safetyAndAccess
            basicinfoModel?.id = id
            viewModel.updateBasicInfo(basicinfoModel!!)
        }

        val saftyAcess: SafetyAndAcces = safetyAndAccess
        binding.physicalSecurity.setText(saftyAcess.Physicalsecurity)
        binding.textGate.setText(saftyAcess.GateAndFence)
        binding.videoMonitoringSpinner.setSpinnerData(dropdown.videomonitoring.data,saftyAcess.Videomonitoring)
        binding.siteAccessAreaSpinner.setSpinnerData(dropdown.siteAccessArea.data ,saftyAcess.SiteAccessArea)
        binding.dangerSignageSpinner.setSpinnerData(dropdown.dangerSignage.data ,saftyAcess.DangerSignage)
        binding.textCautionSignage.setText(saftyAcess.CautionSignage)
        binding.siteAccess.setSpinnerData(dropdown.siteaccess.data ,saftyAcess.Siteaccess)

        binding.textSiteAccesseethodology.setText(saftyAcess.Siteaccessmethodology)
        binding.textPoliceNumber.setText(saftyAcess.NearByPoliceStationNumber)
        binding.textPoliceStation.setText( saftyAcess.NearByPoliceStation)
        binding.textFireStation.setText( saftyAcess.NearByFireStation)
        binding.textFireNumber.setText( saftyAcess.NearByFireStationNumber)

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
        binding = SaftyAccessDetailsBottomSheetBinding.inflate(inflater)
        return binding.root
    }

}