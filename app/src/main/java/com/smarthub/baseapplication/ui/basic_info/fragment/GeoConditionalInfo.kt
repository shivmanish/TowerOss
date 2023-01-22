package com.smarthub.baseapplication.ui.basic_info.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.GeoConditionalInfoFragmentBinding
import com.smarthub.baseapplication.network.pojo.site_info.GeoConditionModel
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter

class GeoConditionalInfo : Fragment(), ImageAttachmentAdapter.ItemClickListener {
    var data: GeoConditionModel? = null
    var binding: GeoConditionalInfoFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = GeoConditionalInfoFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var recyclerListener = view.findViewById<RecyclerView>(R.id.list_item)
        var adapter = ImageAttachmentAdapter(this@GeoConditionalInfo)
        recyclerListener.adapter = adapter
        data = requireArguments().getSerializable("data") as GeoConditionModel?
        view.findViewById<View>(R.id.attach_card).setOnClickListener {
            adapter.addItem()
        }
        initViews(view)
    }

    fun initViews(view: View) {
        binding!!.seismecZoneSpinner.setSpinnerData(data!!.seismiczone.data)
        binding!!.windZoneSpinner.setSpinnerData(data!!.windzone.data)
        binding!!.potentioalThreatSpinner.setSpinnerData(data!!.potentialthreat.data)
        binding!!.floodZoneSpinner.setSpinnerData(data!!.floodzone.data)
        binding!!.terrainTypeSpinner.setSpinnerData(data!!.terraintype.data)


    }

    override fun itemClicked() {
//        Toast.makeText(requireContext(), "Item Clicked", Toast.LENGTH_SHORT).show()
    }
}