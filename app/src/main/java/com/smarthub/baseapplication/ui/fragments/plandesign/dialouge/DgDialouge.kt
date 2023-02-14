package com.smarthub.baseapplication.ui.fragments.plandesign.dialouge

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.DG


class DgDialouge(var data: DG) : DialogFragment() {
lateinit var binding: DgDialougeLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        binding = DgDialougeLayoutBinding.inflate(inflater)
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
            data.let {
                binding.dgMake.setText( it.Make)
                binding.dgModal.setText( it.Model)
                binding.dgRatingCapacityKw.setText( it.RatingAndCapacity)
                binding.dgCabinetsizel.setText( it.CabinetSizeL)
                binding.dgCabinetsizeh.setText( it.CabinetSizeH)
                binding.dgCabinetsizeb.setText( it.CabinetSizeB)
                binding.dgOverallWeightKg.setText( it.OverallWeight)
                binding.dgPlatformSize.setText( it.PlatformSize)
                binding.dgFuelType.setText("")
                binding.dgFuelConsumptionPerHour.setText( it.FuelConsumptionPerHour)
            }}catch (e:Exception){
                e.printStackTrace()
            }
    }


}