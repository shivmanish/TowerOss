package com.smarthub.baseapplication.ui.fragments.plandesign.dialouge

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.AC
import com.smarthub.baseapplication.utils.AppLogger


class AcUtilityDialouge(var data: AC) : DialogFragment() {
lateinit var binding: AcUtilityDialougeLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        binding = AcUtilityDialougeLayoutBinding.inflate(inflater)
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
                binding.acMake.setText(it.Make)
                binding.acModal.setText(it.Model)
                binding.acRatingCapacityKw.setText(it.RatingAndCapacity)
                binding.acCabinetsizel.setText(it.CabinetSizeL)
                binding.acCabinetsizeh.setText(it.CabinetSizeH)
                binding.acCabinetsizeb.setText(it.CabinetSizeB)
                binding.acIndoreUnitSizeh.setText(it.IndoorUnitSizeH)
                binding.acIndoreUnitSizeb.setText(it.IndoorUnitSizeB)
                binding.acIndoreUnitSizel.setText(it.IndoorUnitSizeL)
                binding.acOutdoorUnitWeight.setText(it.OutdoorUnitWeight)
                binding.acOverallWeight.setText(it.OverallWeight)
                binding.acOwnerCompany.setText("")
                binding.acUserCompany.setText("")
            }
        }
        catch (e : Exception){
            AppLogger.log(" plan and design utility adapter${e.localizedMessage}")
        }

    }


}