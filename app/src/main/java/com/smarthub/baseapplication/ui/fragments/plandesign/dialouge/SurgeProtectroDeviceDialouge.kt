package com.smarthub.baseapplication.ui.fragments.plandesign.dialouge

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.SurgeProtectionDevice
import com.smarthub.baseapplication.utils.AppLogger


class SurgeProtectroDeviceDialouge(var data: SurgeProtectionDevice) : DialogFragment() {
lateinit var binding: SurgeProtectedDeviceDialougeLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        binding = SurgeProtectedDeviceDialougeLayoutBinding.inflate(inflater)
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
                binding.surgeProctetorMake.setText(it.Make)
                binding.surgeProctetorModal.setText(it.Model)
                binding.surgeProctetorRatingCapacityKw.setText("")
                binding.surgeProctetorSpdType.setText("")
                binding.surgeProctetorOwnerCompany.setText("")
                binding.surgeProctetorUserCompany.setText("")
            }
        }catch (e : Exception){
            AppLogger.log(" plan and design utility adapter${e.localizedMessage}")
        }

    }


}