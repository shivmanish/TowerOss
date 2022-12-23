package com.smarthub.baseapplication.ui.site_agreement.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AgreementFragmentBinding
import com.smarthub.baseapplication.listeners.QatListListener
import com.smarthub.baseapplication.ui.adapter.AtpCardListAdapter
import com.smarthub.baseapplication.ui.dialog.siteinfo.AgrementsDetailsBottomSheet
import com.smarthub.baseapplication.ui.site_agreement.adapter.AgrementLeaseListAdapter

class Agreements :Fragment(), AgrementLeaseListAdapter.AgreementListItemlistner , QatListListener {

    var binding : AgreementFragmentBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AgreementFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }
       override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.rvAgreementList?.adapter = AgrementLeaseListAdapter(this@Agreements)
        initViews(view)
    }

    fun initViews(view: View){
//        var b = view.findViewById<View>(R.id.attach_card)
//        b.setOnClickListener {
//
//        }
    }


    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"Item Clicked",Toast.LENGTH_SHORT).show()
    }
    override fun detailsItemClicked() {
       var bottomSheetDialogFragment = AgrementsDetailsBottomSheet(R.layout.agreement_details_botom_sheet)
        bottomSheetDialogFragment?.show(childFragmentManager,"category")
    }

    override fun addNewClicked(adapter: AtpCardListAdapter) {
        TODO("Not yet implemented")
    }

    override fun itemClicked() {
        TODO("Not yet implemented")
    }

    override fun cardClicked() {
        TODO("Not yet implemented")
    }
}