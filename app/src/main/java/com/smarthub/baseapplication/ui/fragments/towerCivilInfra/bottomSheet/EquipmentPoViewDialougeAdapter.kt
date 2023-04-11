package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TowerPoViewDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilPODetail
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class EquipmentPoViewDialougeAdapter (contentLayoutId: Int,var data:TwrCivilPODetail) : BottomSheetDialogFragment(contentLayoutId),
    ImageAttachmentAdapter.ItemClickListener {

    lateinit var binding: TowerPoViewDialougeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }
        val attacmentsItem: RecyclerView = binding.root.findViewById(R.id.list_item)
        val adapter = ImageAttachmentAdapter(this@EquipmentPoViewDialougeAdapter)
        attacmentsItem.adapter=adapter
        view.findViewById<View>(R.id.attach_card).setOnClickListener{
            adapter.addItem()
        }
        binding.poAmount.text=data.POAmount
        binding.poItems.text=data.POItem
        binding.vendorCode.text=data.VendorCode
        binding.poNumber.text=data.PONumber
        binding.poAmount.text=data.POAmount
        binding.poLineNumber.text=data.POLineNo.toString()
        binding.remark.text=data.remark
        if (data.VendorCompany?.isNotEmpty()==true)
            AppPreferences.getInstance().setDropDown(binding.vendorName, DropDowns.VendorCompany.name,data.VendorCompany?.get(0).toString())

        binding.poDate.text= Utils.getFormatedDate(data.PODate,"dd-MMM-yyyy")
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = TowerPoViewDialougeBinding.inflate(inflater)
        return binding.root
    }

    override fun itemClicked() {
        Toast.makeText(requireContext(),"Item Clicked", Toast.LENGTH_SHORT).show()
    }


}