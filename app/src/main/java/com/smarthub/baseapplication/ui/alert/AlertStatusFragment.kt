package com.smarthub.baseapplication.ui.alert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AlertStatusBinding
import com.smarthub.baseapplication.network.pojo.site_info.GeoConditionModel
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.alert.adapter.AlertimageAdapter
import com.smarthub.baseapplication.ui.alert.dialog.CreateAlertBottomSheet
import com.smarthub.baseapplication.ui.fragments.BaseFragment


class AlertStatusFragment : BaseFragment(),AlertStatusListener, AlertimageAdapter.ItemClickListener {

    lateinit var binding : AlertStatusBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AlertStatusBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.adapter = AlertStatusListAdapter(requireContext(),this@AlertStatusFragment )
        var recyclerListener = view.findViewById<RecyclerView>(R.id.rv_alert_image_list)
        var adapter = AlertimageAdapter(this@AlertStatusFragment)
        recyclerListener.adapter = adapter
        view.findViewById<View>(R.id.attach_card).setOnClickListener {
            adapter.addItem()
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun detailsItemClicked() {
        val bottomSheetDialogFragment = CreateAlertBottomSheet(R.layout.create_alert_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }

    override fun itemClicked() {
        Toast.makeText(requireContext(), "Item Clicked", Toast.LENGTH_SHORT).show()

    }


}


