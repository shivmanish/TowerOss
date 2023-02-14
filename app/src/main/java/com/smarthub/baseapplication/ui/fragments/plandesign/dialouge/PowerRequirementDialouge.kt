package com.smarthub.baseapplication.ui.fragments.plandesign.dialouge

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanningAndDesignPowerRequirement
import com.smarthub.baseapplication.utils.AppLogger


class PowerRequirementDialouge(var data: PlanningAndDesignPowerRequirement?) : DialogFragment() {
lateinit var binding: PdEditPowerDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        binding = PdEditPowerDialogBinding.inflate(inflater)
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.setLayout(width, height)
        dialog!!.window!!.setGravity(Gravity.BOTTOM)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setWindowAnimations(R.style.DialogAnimation)
        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.FullDialog
    }

    override fun isCancelable(): Boolean {
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener{
            dialog!!.dismiss()
            dialog!!.cancel()
        }
        binding.canecelText.setOnClickListener{
            dialog!!.dismiss()
            dialog!!.cancel()
        }
        try {
            binding.PowerType.setText(data?.PowerType)
            binding.voltage.setText(data?.Voltage)
            binding.maxTotalPower.setText(data?.MaxTotalPower)
            binding.batteryBackup.setText(data?.BatteryBackup)
            binding.remark.setText(data?.Remark)
        }catch (e:java.lang.Exception){
            AppLogger.log("PlanDesign twrCivil Adapter error : ${e.localizedMessage}")
        }

    }


}