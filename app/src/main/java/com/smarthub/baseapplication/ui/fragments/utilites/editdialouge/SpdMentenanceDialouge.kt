package com.smarthub.baseapplication.ui.utilites.editdialouge

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AcMentenanceDialougeLayoutBinding
import com.smarthub.baseapplication.databinding.BatteryEquipmentDialougeLayoutBinding
import com.smarthub.baseapplication.databinding.OpcoInfoSiteDialougeLayoutBinding
import com.smarthub.baseapplication.databinding.RfEquipmentDialougeLayoutBinding
import com.smarthub.baseapplication.databinding.SiteInfoBasicDetailsDialougeLayoutBinding


class SpdMentenanceDialouge : DialogFragment() {
lateinit var binding: AcMentenanceDialougeLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         super.onCreateView(inflater, container, savedInstanceState)
        binding = AcMentenanceDialougeLayoutBinding.inflate(inflater)
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.setLayout(width, height)
        dialog!!.window!!.setGravity( Gravity.BOTTOM)
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
    }


}