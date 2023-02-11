package com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.PowerLoad

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerLoadListItemDialougeBinding
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.PowerLoadData

class PowerLoadEditDialouge(
    contentLayoutId: Int,
    var powerloaddata: PowerLoadData,
    var opcodata: OpcoDataItem?
): BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : PowerLoadListItemDialougeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }
       try{
            binding.MeasuredPoint.setText(powerloaddata.MeasurementPoint)
            binding.MeasuredDate.setText("ApiDataNot")
            binding.PowerType.setText(powerloaddata.PowerType[0])
            binding.LoadCurrent.setText(powerloaddata.LoadCurrent)
            binding.LoadVoltage.setText(powerloaddata.LoadVoltage)
            binding.LoadWattage.setText(powerloaddata.LoadWattage)
            binding.TOCOExcutive.setText("Api Data not avbl")
            binding.OPCOExcutive.setText(powerloaddata.operatorExecutiveName)
            binding.Remark.setText(powerloaddata.remark)

        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = PowerLoadListItemDialougeBinding.inflate(inflater)
        return binding.root
    }
}