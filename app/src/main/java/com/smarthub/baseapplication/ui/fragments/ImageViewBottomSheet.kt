package com.smarthub.baseapplication.ui.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.trackermodule.locationpicker.AddresData
import com.example.trackermodule.locationpicker.LocationFetchCallback
import com.example.trackermodule.locationpicker.LocationFetchHelper
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.GeoTagImageViewBinding
import com.smarthub.baseapplication.model.siteIBoard.Attachments
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.Utils


class ImageViewBottomSheet(var item: Attachments) : BaseBottomSheetDialogFragment() {

    lateinit var binding: GeoTagImageViewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = GeoTagImageViewBinding.inflate(inflater)
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.setLayout(width, height)
        dialog!!.window!!.setGravity(Gravity.BOTTOM)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setWindowAnimations(R.style.DialogAnimation)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cancel.setOnClickListener {
            dialog?.dismiss()
        }
        binding.titleText.text = item.title
//        binding.detailText.text = item.detail
        Glide
            .with(this)
            .load(item.fullPath)
            .into(binding.viewImage)
        binding.latLong.text = "Lat/Long :${item.locLatitude}/${item.locLongitude}"
        binding.locality.text = "Place :${item.place} ${Utils.getDateTime(item.created_at.substring(0,19))}"
    }

    override fun getTheme(): Int {
        return R.style.FullDialog
    }

    override fun isCancelable(): Boolean {
        return true
    }
}