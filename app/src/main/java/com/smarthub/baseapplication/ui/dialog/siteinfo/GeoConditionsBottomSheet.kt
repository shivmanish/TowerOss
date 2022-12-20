package com.smarthub.baseapplication.ui.dialog.siteinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.GeoConditionsDetailsBottomSheetBinding
import com.smarthub.baseapplication.model.siteInfo.GeoCondition
import com.smarthub.baseapplication.network.pojo.site_info.GeoConditionModel

class GeoConditionsBottomSheet(
    contentLayoutId: Int,
    var dropdownData: GeoConditionModel,
    var geoCondition: List<GeoCondition>
) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding: GeoConditionsDetailsBottomSheetBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = GeoConditionsDetailsBottomSheetBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        var geoCondition = geoCondition.get(0)
        binding.potentioalThreatSpinner.setSpinnerData(
            dropdownData.potentialthreat.data,
            geoCondition.Potentialthreat
        )
        binding.textAltitude.setText(geoCondition.Altitude)
        binding.windZoneSpinner.setSpinnerData(
            dropdownData.windzone.data,
            geoCondition.Windzone
        )
        binding.seismecZoneSpinner.setSpinnerData(
            dropdownData.seismiczone.data, geoCondition.Seismiczone
        )
        binding.floodZoneSpinner.setSpinnerData(
            dropdownData.floodzone.data, geoCondition.Floodzone
        )
        binding.textTempZone.setText(geoCondition.TempratureZone)
        binding.terrainTypeSpinner.setSpinnerData(
            dropdownData.terraintype.data, geoCondition.Terraintype
        )

    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GeoConditionsDetailsBottomSheetBinding.inflate(inflater)
        return binding.root
    }


}