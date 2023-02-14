package com.smarthub.baseapplication.ui.fragments.plandesign.dialouge

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BatteryBankDialougeLayoutBinding
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.BatteryBank


class BatteryBankDialouge(var data: BatteryBank) : DialogFragment() {
    lateinit var binding: BatteryBankDialougeLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = BatteryBankDialougeLayoutBinding.inflate(inflater)
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
        binding.canecl.setOnClickListener {
            dialog!!.dismiss()
            dialog!!.cancel()
        }
        binding.canecelText.setOnClickListener {
            dialog!!.dismiss()
            dialog!!.cancel()
        }
        try {
            data!!.let {
                binding.batteryBankMake.setText(it.Make)
                binding.batteryBankModal.setText(it.Model)
                binding.batteryBankRatingCapacityKw.setText(it.RatingAndCapacity)
                binding.batteryBankCabinetsizel.setText(it.CabinetSizeL)
                binding.batteryBankCabinetsizeh.setText(it.CabinetSizeH)
                binding.batteryBankCabinetsizeb.setText(it.CabinetSizeB)
                binding.batteryBankOverallWeightKg.setText(it.OverallWeight)
                binding.batteryBankUserCompany.setText("")
                binding.batteryBankOwnerCompany.setText("")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


}