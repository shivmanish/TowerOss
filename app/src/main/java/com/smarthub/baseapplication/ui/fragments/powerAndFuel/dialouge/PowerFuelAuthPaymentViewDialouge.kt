package com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.EquipmentRoomPoViewDialougeBinding
import com.smarthub.baseapplication.databinding.PowerFuelPaymentDialougeBinding
import com.smarthub.baseapplication.databinding.TowerPoViewDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelAuthorityPayments
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelPODetail
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilPODetail
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class PowerFuelAuthPaymentViewDialouge (contentLayoutId: Int, var data: PowerFuelAuthorityPayments) : BottomSheetDialogFragment(contentLayoutId){

    lateinit var binding: PowerFuelPaymentDialougeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }
        binding.PaymentType.text=data.PaymentType
        binding.Amount.text=data.Amount
        binding.remark.text=data.Remark
        if (data.PaymentStatus.isNotEmpty())
            AppPreferences.getInstance().setDropDown(binding.PaymentStatus, DropDowns.PaymentStatus.name,data.PaymentStatus.get(0).toString())

        binding.DueDate.text=Utils.getFormatedDate(data.DueDate.substring(0,10),"dd-MMM-yyyy")
        binding.StatusDate.text=Utils.getFormatedDate(data.StatusDate.substring(0,10),"dd-MMM-yyyy")
        binding.DemandReceiptDate.text=Utils.getFormatedDate(data.DemandReceiptDate.substring(0,10),"dd-MMM-yyyy")

    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = PowerFuelPaymentDialougeBinding.inflate(inflater)
        return binding.root
    }



}