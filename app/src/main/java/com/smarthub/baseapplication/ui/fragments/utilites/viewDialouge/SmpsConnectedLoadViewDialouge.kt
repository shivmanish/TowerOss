package com.smarthub.baseapplication.ui.fragments.utilites.viewDialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.UtilitySmpsconnectedLoadViewDialougeBinding
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityConnectedLoad
import com.smarthub.baseapplication.utils.Utils

class SmpsConnectedLoadViewDialouge (contentLayoutId: Int, var data: UtilityConnectedLoad) : BottomSheetDialogFragment(contentLayoutId){

    lateinit var binding: UtilitySmpsconnectedLoadViewDialougeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = UtilitySmpsconnectedLoadViewDialougeBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }
        binding.McbNumber.text=data.MCBNumber.toString()
        binding.ConnectedEquipment.text=data.ConnectedEquipment
        binding.McbRating.text=data.RatingAmp
        binding.ActualReading.text=data.ActualReading
        binding.remark.text=data.Remark
        binding.InstallationDate.text=Utils.getFormatedDate(data.InstallationDate,"dd-MMM-yyyy")


    }

    override fun getTheme() = R.style.NewDialogTask




}