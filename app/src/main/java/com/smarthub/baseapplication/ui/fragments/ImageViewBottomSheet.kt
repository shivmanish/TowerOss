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

    override fun onDestroy() {
        super.onDestroy()
        locca?.locationFetchCallback = null
    }
    var locca : LocationFetchHelper?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cancel.setOnClickListener {
            dialog?.dismiss()
        }
        locca = LocationFetchHelper(requireContext()) { addresData ->
            Toast.makeText(requireContext(), "latlong address is called ${addresData!!.lattitude} and ${addresData.Locality}", Toast.LENGTH_SHORT)
            binding.textLattitude.text = addresData.lattitude
            binding.textLongitude.text = addresData.longitude
            binding.textLocality.text = addresData.Locality
        }
        Glide
            .with(this)
            .load(item.fullPath)
            .into(binding.viewImage)
    }

    override fun getTheme(): Int {
        return R.style.FullDialog
    }

    override fun isCancelable(): Boolean {
        return true
    }
}