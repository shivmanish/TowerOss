package com.smarthub.baseapplication.ui.fragments.plandesign.dialouge

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.DCDB
import com.smarthub.baseapplication.utils.AppLogger


class DcdbDialouge(var data: DCDB) : DialogFragment() {
lateinit var binding: DcdbDialougeLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        binding = DcdbDialougeLayoutBinding.inflate(inflater)
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
                binding.dcdbMake.setText(it.Make)
                binding.dcdbModal.setText(it.Model)
                binding.dcdbRatingCapacityKw.setText(it.RatingAndCapacity)
                binding.dcdbUnitSizeb.setText(it.UnitSizeB)
                binding.dcdbUnitSizeh.setText(it.UnitSizeH)
                binding.dcdbUnitSizel.setText(it.UnitSizeL)
                binding.dcdbUnitWeight.setText(it.UnitWeight)
                binding.dcdbRatingCapacity.setText(it.RatingAndCapacity)
                binding.dcdbOwnerCompany.setText("")
                binding.dcdbRemarks.setText("")
                binding.dcdbUserCompany.setText("")
            }
        }catch (e : Exception){
            AppLogger.log(" plan and design utility adapter${e.localizedMessage}")
        }

    }


}