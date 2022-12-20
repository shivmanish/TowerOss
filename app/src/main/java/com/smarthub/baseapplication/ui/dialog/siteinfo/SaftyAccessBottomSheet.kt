package com.smarthub.baseapplication.ui.dialog.siteinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SaftyAccessDetailsBottomSheetBinding
import com.smarthub.baseapplication.model.siteInfo.SafetyAndAcces
import com.smarthub.baseapplication.network.pojo.site_info.SafetyAndAccessModel

class SaftyAccessBottomSheet(
    contentLayoutId: Int,
    var dropdown: SafetyAndAccessModel,
    var safetyAndAccess: List<SafetyAndAcces>
) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding : SaftyAccessDetailsBottomSheetBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SaftyAccessDetailsBottomSheetBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }

        val saftyAcess: SafetyAndAcces = safetyAndAccess.get(0)
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

    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SaftyAccessDetailsBottomSheetBinding.inflate(inflater)
        return binding.root
    }

}