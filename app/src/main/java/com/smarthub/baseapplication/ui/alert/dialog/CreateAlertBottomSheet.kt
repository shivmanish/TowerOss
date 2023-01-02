package com.smarthub.baseapplication.ui.alert.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CreateAlertDialogBinding
import com.smarthub.baseapplication.network.pojo.site_info.GeoConditionModel
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.alert.AlertStatusListAdapter
import com.smarthub.baseapplication.ui.alert.AlertStatusListener
import com.smarthub.baseapplication.ui.alert.adapter.AlertimageAdapter
import java.util.Objects


class CreateAlertBottomSheet (contentLayoutId: Int): BottomSheetDialogFragment(contentLayoutId) ,
    AlertimageAdapter.ItemClickListener {
    lateinit var binding : CreateAlertDialogBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }
    }
    override fun getTheme() = R.style.NewDialogTask
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CreateAlertDialogBinding.inflate(inflater)
               var recyclerListener = view?.findViewById<RecyclerView>(R.id.rv_alert_image_list)

               var adapter = AlertimageAdapter(this@CreateAlertBottomSheet)
             adapter.addItem()
              recyclerListener?.adapter = adapter
               view?.findViewById<View>(R.id.attach_card)?.setOnClickListener {
                   adapter.addItem()
               }
        return binding.root
    }

    override fun itemClicked() {

    }
}