package com.smarthub.baseapplication.ui.site_agreement.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AgreementFragmentBinding
import com.smarthub.baseapplication.listeners.QatListListener
import com.smarthub.baseapplication.model.serviceRequest.acquisitionSurvey.AcquisitionSurveyModel
import com.smarthub.baseapplication.model.serviceRequest.softAqusition.AgreementTerm
import com.smarthub.baseapplication.model.serviceRequest.softAqusition.PropertyOwnerAndPaymentDetail
import com.smarthub.baseapplication.ui.adapter.AtpCardListAdapter
import com.smarthub.baseapplication.ui.dialog.siteinfo.AgrementsDetailsBottomSheet
import com.smarthub.baseapplication.ui.dialog.siteinfo.PropertyOwnerDetailsBottomSheet
import com.smarthub.baseapplication.ui.site_agreement.adapter.AgrementLeaseListAdapter
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class Agreements(val data: AcquisitionSurveyModel? = null, var id: String? = null) :Fragment(), AgrementLeaseListAdapter.AgreementListItemlistner , QatListListener {

    var binding : AgreementFragmentBinding?=null
    lateinit var viewmodel:HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AgreementFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }
       override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.rvAgreementList?.adapter = AgrementLeaseListAdapter(requireContext(),this@Agreements,data!!)
        initViews(view)
    }

    fun initViews(view: View){
//        var b = view.findViewById<View>(R.id.attach_card)
//        b.setOnClickListener {
//
//        }
    }


    override fun attachmentItemClicked() {
//        Toast.makeText(requireContext(),"Item Clicked",Toast.LENGTH_SHORT).show()
    }
    override fun detailsItemClicked(
        agrement: AgreementTerm,
        servicerequirement: AcquisitionSurveyModel
    ) {
       var bottomSheetDialogFragment = AgrementsDetailsBottomSheet(R.layout.agreement_details_botom_sheet,agrement,id,viewmodel,servicerequirement)
        bottomSheetDialogFragment?.show(childFragmentManager,"category")
    }

    override fun propertyOwonertemClicked(
        propertylist: ArrayList<PropertyOwnerAndPaymentDetail>,
        servicerequirement: AcquisitionSurveyModel
    ) {
        var bottomSheetDialogFragment = PropertyOwnerDetailsBottomSheet(R.layout.property_owner_botom_sheet,propertylist,id,viewmodel,servicerequirement)
        bottomSheetDialogFragment?.show(childFragmentManager,"category")

    }

    override fun addNewClicked(adapter: AtpCardListAdapter) {

    }

    override fun itemClicked() {
    }

    override fun cardClicked() {

    }
}