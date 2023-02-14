package com.smarthub.baseapplication.ui.fragments.plandesign.dialouge

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.FireExtinguisher
import com.smarthub.baseapplication.utils.AppLogger


class FireDialouge(var data: FireExtinguisher) : DialogFragment() {
lateinit var binding: FireDialougeLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        binding = FireDialougeLayoutBinding.inflate(inflater)
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
            data!!.let {
                binding.fireMake.setText(it.Make)
                binding.fireModal.setText(it.Model)
                binding.fireRatingCapacityKw.setText("")
                binding.fireCabinetsizel.setText(it.CabinetSizeL)
                binding.fireCabinetsizeh.setText(it.CabinetSizeH)
                binding.fireCabinetsizeb.setText(it.CabinetSizeB)
                binding.fireUnitSizeh.setText(it.UnitSizeH)
                binding.fireUnitSizeb.setText(it.UnitSizeB)
                binding.fireUnitSizel.setText(it.UnitSizeL)
                binding.fireUnitWeight.setText("")
                binding.fireExtinguisherType.setText("")
                binding.fireFuelConsuptionHour.setText(it.FuelConsumptionPerHour)
                binding.fireOwnerCompany.setText("")
                binding.fireUserCompany.setText("")
            }
        }catch (e : Exception){
            AppLogger.log(" plan and design utility adapter${e.localizedMessage}")
        }

    }


}