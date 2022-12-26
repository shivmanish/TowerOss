package com.smarthub.baseapplication.ui.fragments.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentSearchTaskBinding
import com.smarthub.baseapplication.ui.dialog.services_request.EquipmentDetailsBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.opcoInfo.OperationsItemsEditDialouge
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskAdapter
import com.smarthub.baseapplication.ui.fragments.task.editdialog.OPCOSiteInfoEdit
import com.smarthub.baseapplication.ui.fragments.task.editdialog.SiteInfoEditBottomSheet
import com.smarthub.baseapplication.viewmodels.MainViewModel

class TaskSearchTabFragment : Fragment(), TaskAdapter.TaskLisListener {

    private lateinit var binding: FragmentSearchTaskBinding
    private lateinit var mainViewModel:MainViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        mainViewModel.isActionBarHide(false)
        binding = FragmentSearchTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listItem.adapter = TaskAdapter(requireContext(),this@TaskSearchTabFragment)


    }



    override fun attachmentItemClicked() {

    }

    override fun detailsItemClicked() {
        val bottomSheetDialogFragment = SiteInfoEditBottomSheet(R.layout.task_site_info_dialouge_layout)
        bottomSheetDialogFragment.show(childFragmentManager,"category")


    }

    override fun requestinfoClicked() {
        val bottomSheetDialogFragment = OPCOSiteInfoEdit(R.layout.opco_info_site_dialouge_layout)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }

    override fun operationInfoDetailsItemClicked() {

    }

    override fun geoConditionsDetailsItemClicked() {

    }

    override fun siteAccessDetailsItemClicked() {
//OperationsItemsEditDialouge
        val bottomSheetDialogFragment = OperationsItemsEditDialouge(R.layout.opco_operations_team_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }

    override fun EditInstallationAcceptence() {

    }

    override fun EditTowerItem() {
        val bottomSheetDialogFragment = EquipmentDetailsBottomSheetDialog(R.layout.equipment_room_dialouge_layout)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }

    override fun editPoClicked(position: Int) {

    }

    override fun viewPoClicked(position: Int) {
        val bottomSheetDialogFragment = EquipmentDetailsBottomSheetDialog(R.layout.equipment_view_bottom_sheet)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }

    override fun editConsumableClicked(position: Int) {

    }

    override fun viewConsumableClicked(position: Int) {

    }

    override fun editOffsetClicked(position: Int) {

    }

    override fun viewOffsetClicked(position: Int) {

    }

}