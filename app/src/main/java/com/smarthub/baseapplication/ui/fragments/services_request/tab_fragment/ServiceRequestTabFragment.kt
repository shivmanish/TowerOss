package com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpcoInfoFregmentBinding
import com.smarthub.baseapplication.ui.dialog.services_request.BachhualLinkBottomSheet
import com.smarthub.baseapplication.ui.dialog.services_request.EquipmentDetailsBottomSheetDialog
import com.smarthub.baseapplication.ui.dialog.services_request.RadioAntennasBottomSheetDialog
import com.smarthub.baseapplication.ui.dialog.services_request.SRDetailsBottomSheet
import com.smarthub.baseapplication.ui.fragments.opcoInfo.OpcoInfoListAdapter
import com.smarthub.baseapplication.ui.fragments.opcoInfo.OpcoSiteInfoEditDialouge
import com.smarthub.baseapplication.ui.fragments.opcoInfo.OperationsItemsEditDialouge
import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestAdapter
import com.smarthub.baseapplication.ui.utilites.editdialouge.ServicesDetailsBottomSheetDialog
import com.smarthub.baseapplication.viewmodels.SiteInfoViewModel




class ServiceRequestTabFragment : Fragment(), ServicesRequestAdapter.SiteInfoLisListener {
    var siteViewModel : SiteInfoViewModel?=null
    var binding : OpcoInfoFregmentBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = OpcoInfoFregmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listItem?.adapter = ServicesRequestAdapter(this@ServiceRequestTabFragment)
    }

    override fun attachmentItemClicked() {

    }

    override fun detailsItemClicked() {
        val bottomSheetDialogFragment = SRDetailsBottomSheet(R.layout.sr_details_bottom_sheet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }

    override fun operationInfoDetailsItemClicked() {
        val bottomSheetDialogFragment = EquipmentDetailsBottomSheetDialog(R.layout.equipment_bottom_sheet)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }

    override fun geoConditionsDetailsItemClicked() {
        val bottomSheetDialogFragment = RadioAntennasBottomSheetDialog(R.layout.radio_antina_bottom_sheet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }

    override fun siteAccessDetailsItemClicked() {
        val bottomSheetDialogFragment = BachhualLinkBottomSheet(R.layout.backhaul_link_list_item)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }

    /* override fun operationsItemClicked() {
         val bottomSheetDialogFragment = OperationsItemsEditDialouge(R.layout.opco_operations_team_dialouge)
         bottomSheetDialogFragment.show(childFragmentManager,"category")

     }

     override fun opcoSiteInfoItemClicked() {

         val bottomSheetDialogFragment = OpcoSiteInfoEditDialouge(R.layout.opco_info_site_dialouge_layout)
         bottomSheetDialogFragment.show(childFragmentManager,"category")

     }
 */

}