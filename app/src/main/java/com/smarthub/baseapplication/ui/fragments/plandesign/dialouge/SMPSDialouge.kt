package com.smarthub.baseapplication.ui.fragments.plandesign.dialouge

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SmpsDialougeLayoutBinding
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.SMPS


class SMPSDialouge(var data: SMPS) : DialogFragment() {
    lateinit var binding: SmpsDialougeLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = SmpsDialougeLayoutBinding.inflate(inflater)
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
                binding.make.setText(it.Make)
                binding.modal.setText(it.Model)
                binding.RatingCapacityKw.setText(it.RatingAndCapacity)
                binding.cabinetsizel.setText(it.CabinetSizeL)
                binding.cabinetsizeh.setText(it.CabinetSizeH)
                binding.cabinetsizel.setText(it.CabinetSizeL)
                binding.overallWeightKg.setText(it.OverallWeight)
                binding.ownerCompany.setText("")
                binding.userCompany.setText("")
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }


}