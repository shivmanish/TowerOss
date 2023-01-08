package com.smarthub.baseapplication.ui.utilites.fragment

import android.os.Bundle
import android.service.voice.VoiceInteractionSession.VisibleActivityCallback
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.Ac1TabUtilitiesFragmentBinding
import com.smarthub.baseapplication.databinding.BatteryFragmentBinding
import com.smarthub.baseapplication.databinding.Smps1TabUtilitiesFragmentBinding
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.SMPS
import com.smarthub.baseapplication.model.siteInfo.utilitiesEquip.UtilitieSmp
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.dialog.utils.ConnectedLoadsBottomSheetDialog
import com.smarthub.baseapplication.ui.dialog.utils.RectifierModuleBottomSheetDialog
import com.smarthub.baseapplication.ui.utilites.adapter.SMPSViewRecyclerAdapter
import com.smarthub.baseapplication.ui.utilites.adapter.SmpsUtilityFragAdapter
import com.smarthub.baseapplication.ui.utilites.editdialouge.BatteryEquipmentDialouge
import com.smarthub.baseapplication.ui.utilites.editdialouge.ConsumableMaterialsBottomSheetDialog
import com.smarthub.baseapplication.ui.utilites.editdialouge.InstalationAcceptanceDialouge
import com.smarthub.baseapplication.ui.utilites.editdialouge.PoDetailsBottomSheetDialog
import com.smarthub.baseapplication.utils.Utils

class SMPS1UitilitiesFrag(smpsAllData: UtilitieSmp?, id:String): Fragment(),SmpsUtilityFragAdapter.SmpsInfoListListener {
    lateinit var binding: Smps1TabUtilitiesFragmentBinding
    lateinit var adapter: SmpsUtilityFragAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = Smps1TabUtilitiesFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= SmpsUtilityFragAdapter(requireContext(),this@SMPS1UitilitiesFrag)
        binding.listItem.adapter=adapter
    }


    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"attechments item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun EditInstallationAcceptence() {
        val dalouge = InstalationAcceptanceDialouge()
        dalouge.show(childFragmentManager,"")
        Toast.makeText(requireContext(),"Edit Installation item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun EditEquipmentItem() {
        Toast.makeText(requireContext(),"Edit Equipment item clicked",Toast.LENGTH_SHORT).show()
        val dalouge = BatteryEquipmentDialouge()
        dalouge.show(childFragmentManager,"")
    }

    override fun EditMaintenance() {
        Toast.makeText(requireContext(),"Edit maintenance  item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun editPoClicked(position: Int) {
        Toast.makeText(requireContext(),"Edit po table  item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun viewPoClicked(position: Int) {
        Toast.makeText(requireContext(),"view po table  item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun editRectifireTableItem(position: Int) {
        Toast.makeText(requireContext(),"Edit rectifier table  item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun viewRectifireTableItem(position: Int) {
        Toast.makeText(requireContext(),"View rectifier table  item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun editConnLoadsTableItem(position: Int) {
        Toast.makeText(requireContext(),"Edit Connections Loads table  item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun viewConnLoadsTableItem(position: Int) {
        Toast.makeText(requireContext(),"View Connection Loads table  item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun editConsumMaterialTableItem(position: Int) {
        Toast.makeText(requireContext(),"Edit Consumable Materials table  item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun viewConsumMaterialTableItem(position: Int) {
        Toast.makeText(requireContext(),"View Consumable Materials table  item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun editServiceTableItem(position: Int) {
        Toast.makeText(requireContext(),"Edit Service table  item clicked",Toast.LENGTH_SHORT).show()
    }

    override fun viewServiceTableItem(position: Int) {
        Toast.makeText(requireContext(),"View Service table  item clicked",Toast.LENGTH_SHORT).show()
    }
}