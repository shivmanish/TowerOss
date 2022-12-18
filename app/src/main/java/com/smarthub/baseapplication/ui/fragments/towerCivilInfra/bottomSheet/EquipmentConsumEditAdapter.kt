package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.EquipmentConsumableEditDialougeBinding
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter

class EquipmentConsumEditAdapter (contentLayoutId: Int) : BottomSheetDialogFragment(contentLayoutId),
    ImageAttachmentAdapter.ItemClickListener {

    lateinit var binding: EquipmentConsumableEditDialougeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EquipmentConsumableEditDialougeBinding.bind(view)
        binding.canecl.setOnClickListener {
            dismiss()
        }
        var attacmentsItem: RecyclerView = binding.root.findViewById(R.id.list_item)
        var adapter = ImageAttachmentAdapter(this@EquipmentConsumEditAdapter)
        attacmentsItem.adapter=adapter
        view.findViewById<View>(R.id.attach_card).setOnClickListener{
            adapter.addItem()
        }
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = EquipmentConsumableEditDialougeBinding.inflate(inflater)
        return binding.root
    }

    override fun itemClicked() {
        Toast.makeText(requireContext(),"Item Clicked", Toast.LENGTH_SHORT).show()
    }


}