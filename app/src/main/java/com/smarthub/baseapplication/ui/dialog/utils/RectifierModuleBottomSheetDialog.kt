package com.smarthub.baseapplication.ui.dialog.utils

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AdditinalAccessoresBottomSheetBinding
import com.smarthub.baseapplication.databinding.DgEqipmentDialogLayoutBinding
import com.smarthub.baseapplication.databinding.RectifierModuleBottomSheetBinding


class RectifierModuleBottomSheetDialog : DialogFragment() {
    lateinit var binding: RectifierModuleBottomSheetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = RectifierModuleBottomSheetBinding.inflate(inflater)
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
//        binding.canecelText.setOnClickListener{
//            dialog!!.dismiss()
//            dialog!!.cancel()
//        }
    }


}