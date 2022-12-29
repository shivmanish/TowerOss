package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AcquisitionPoTableBinding
import com.smarthub.baseapplication.databinding.AcquisitionPropertyOwnerTableBinding
import com.smarthub.baseapplication.databinding.AcquisitionSurveyBoundryDetailsItemBinding
import com.smarthub.baseapplication.databinding.AcquisitionSurveyBuildingDetalisItemBinding
import com.smarthub.baseapplication.databinding.ServiceRequestTeamvendorAttachmentBinding
import com.smarthub.baseapplication.model.serviceRequest.AcquisitionSurvey.ASAquisitionSurvey
import com.smarthub.baseapplication.model.serviceRequest.AcquisitionSurvey.ASAquisitionSurveyBuildingDetail
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.services_request.tableAdapters.BoundryDetailsTableAdapter
import com.smarthub.baseapplication.ui.fragments.services_request.tableAdapters.PoDetailsTableAdapter
import com.smarthub.baseapplication.ui.fragments.services_request.tableAdapters.PropertyOwnerTableAdapter

class AcquisitionSurveyFragAdapter (var context : Context, var listener: AcquisitionSurveyListItemListner, serviceRequestAllData: ServiceRequestAllDataItem?) : RecyclerView.Adapter<AcquisitionSurveyFragAdapter.ViewHold>() {

    var currentOpened = -1
    private var AcquisitionSurveyData:ASAquisitionSurvey?=null
    private var BuildingDetailData: ASAquisitionSurveyBuildingDetail?=null
    var list: ArrayList<String> = ArrayList()
    var type1="Building Details"
    var type2="Boundary Structures Details"
    var type3="Property Owner's Details"
    var type4="PO Details"
    var type5="Attachments"

    init {
        list.add("Building Details")
        list.add("Boundary Structures Details")
        list.add("Property Owner's Details")
        list.add("PO Details")
        list.add("Attachments")
        AcquisitionSurveyData=serviceRequestAllData?.ASAcquitionSurvey?.get(0)
        BuildingDetailData=AcquisitionSurveyData?.ASAquisitionSurveyBuildingDetail?.get(0)
    }
    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    class BuildingDetailsViewHold(itemView: View) : ViewHold(itemView) {
        var binding: AcquisitionSurveyBuildingDetalisItemBinding = AcquisitionSurveyBuildingDetalisItemBinding.bind(itemView)

        init {

            binding.collapsingLayout.tag = false

            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }


        }
    }
    class BoundryDetailsViewHold(itemView: View) : ViewHold(itemView){
        var binding: AcquisitionSurveyBoundryDetailsItemBinding = AcquisitionSurveyBoundryDetailsItemBinding.bind(itemView)
        var BoundryTableList: RecyclerView=binding.acquisitionSurveyBoundryTableItem

        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }
            binding.imgAdd.setOnClickListener {
                addTableItem("dfsdh")
            }
        }
        private fun addTableItem(item:String){
            if (BoundryTableList.adapter!=null && BoundryTableList.adapter is BoundryDetailsTableAdapter){
                var adapter = BoundryTableList.adapter as BoundryDetailsTableAdapter
                adapter.addItem(item)
            }
        }
    }
    class PropertyOwnerDetailsViewHold(itemView: View) : ViewHold(itemView){
        var binding: AcquisitionPropertyOwnerTableBinding = AcquisitionPropertyOwnerTableBinding.bind(itemView)
        var PropertyOwnerTableList: RecyclerView=binding.acquisitionPropertyOwnerTableItem

        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }
            binding.imgAdd.setOnClickListener {
                addTableItem("dfsdh")
            }
        }
        private fun addTableItem(item:String){
            if (PropertyOwnerTableList.adapter!=null && PropertyOwnerTableList.adapter is PropertyOwnerTableAdapter){
                var adapter = PropertyOwnerTableList.adapter as PropertyOwnerTableAdapter
                adapter.addItem(item)
            }
        }
    }
    class PoDetailsViewHold(itemView: View) : ViewHold(itemView){
        var binding: AcquisitionPoTableBinding = AcquisitionPoTableBinding.bind(itemView)
        var PoTableList: RecyclerView=binding.acquisitionPoTableItem

        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }
            binding.imgAdd.setOnClickListener {
                addTableItem("dfsdh")
            }
        }
        private fun addTableItem(item:String){
            if (PoTableList.adapter!=null && PoTableList.adapter is PoDetailsTableAdapter){
                var adapter = PoTableList.adapter as PoDetailsTableAdapter
                adapter.addItem(item)
            }
        }
    }

    class AttachmentViewHold(itemView: View, listener: AcquisitionSurveyListItemListner) : ViewHold(itemView) {
        var binding: ServiceRequestTeamvendorAttachmentBinding = ServiceRequestTeamvendorAttachmentBinding.bind(itemView)
        var adapter =  ImageAttachmentAdapter(object : ImageAttachmentAdapter.ItemClickListener{
            override fun itemClicked() {
                listener.attachmentItemClicked()
            }
        })
        init {
            binding.collapsingLayout.tag = false

            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }

            var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {

        return when (viewType) {
            1 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.acquisition_survey_building_detalis_item, parent, false)
                BuildingDetailsViewHold(view)
            }
            2 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.acquisition_survey_boundry_details_item, parent, false)
                BoundryDetailsViewHold(view)
            }
            3 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.acquisition_property_owner_table, parent, false)
                PropertyOwnerDetailsViewHold(view)
            }
            4 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.acquisition_po_table, parent, false)
                PoDetailsViewHold(view)
            }
            5 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.service_request_teamvendor_attachment, parent, false)
                AttachmentViewHold(view,listener)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.acquisition_survey_building_detalis_item, parent, false)
                ViewHold(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (list[position] is String && list[position]==type1)
            return 1
        else if (list[position] is String && list[position]==type2)
            return 2
        else if (list[position] is String && list[position]==type3)
            return 3
        else if (list[position] is String && list[position]==type4)
            return 4
        else if (list[position] is String && list[position]==type5)
            return 5
        else return 0
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when(holder){
            is BuildingDetailsViewHold->{
                holder.binding.imgEdit.setOnClickListener {
                    listener.EditBuildingdetailsItemClicked()
                }
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgEdit.visibility = View.VISIBLE
                }
                else {
                    holder.binding.collapsingLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.imgEdit.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
                if(BuildingDetailData!=null){
                    holder.binding.BuildingLatLong.text="Data not found"
                    holder.binding.BuildingType.text="Data not found"
                    holder.binding.GateFence.text="Data not found"
                    holder.binding.SiteAccessArea.text="Data not found"
                    holder.binding.BuildingHeight.text="Data not found"
                    holder.binding.NoOfFloor.text="Data not found"
                    holder.binding.TypicalFloorArea.text="Data not found"
                    holder.binding.YearOfConstruction.text="Data not found"
                    holder.binding.PropertyOwnership.text="Data not found"
                    holder.binding.PropertyOfferForAcquisition.text="Data not found"
                    holder.binding.AcquisitionOfferType.text="Data not found"
                    holder.binding.OverallFeasibility.text=BuildingDetailData?.ASOverallFeasiblity
                    holder.binding.FiberLMCLaying.text=BuildingDetailData?.ASFibreLmcLaying
                    holder.binding.TowerPoleInstallation.text=BuildingDetailData?.ASTowerPoleInstallation
                    holder.binding.EBsupplyBuildingMeter.text=BuildingDetailData?.ASEBSupplyThroughBuildingMeter
                    holder.binding.EquipmentRoom.text=BuildingDetailData?.ASEquipmentRoom
                    holder.binding.RequiredAreaAvailable.text=BuildingDetailData?.ASRequiredAreaAvailable
                    holder.binding.OverallFeasibillity.text=BuildingDetailData?.ASOverallFeasiblity
                    holder.binding.Availability.text=BuildingDetailData?.ASAvailablityofStatuaryPermission
                    holder.binding.SurveyDate.text=BuildingDetailData?.ASSurveyDate
                    holder.binding.OfficeAddress.text="Data Not found"
                    holder.binding.Remmarks.text="Data Not found"
                }
            }
            is BoundryDetailsViewHold->{
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgAdd.visibility = View.VISIBLE
                }
                else {
                    holder.binding.itemTitleStr.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.imgAdd.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
                holder.BoundryTableList.adapter=BoundryDetailsTableAdapter(context,listener,AcquisitionSurveyData?.ASBoundryStructureDetail!!)
            }
            is PropertyOwnerDetailsViewHold->{
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgAdd.visibility = View.VISIBLE
                }
                else {
                    holder.binding.itemTitleStr.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.imgAdd.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
                holder.PropertyOwnerTableList.adapter=PropertyOwnerTableAdapter(context,listener,AcquisitionSurveyData?.ASPropertyOwnerDetail!!)
            }
            is PoDetailsViewHold->{
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgAdd.visibility = View.VISIBLE
                }
                else {
                    holder.binding.itemTitleStr.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.imgAdd.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
                holder.PoTableList.adapter=PoDetailsTableAdapter(context,listener)
            }
            is AttachmentViewHold->{
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                }
                else {
                    holder.binding.collapsingLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
    var recyclerView: RecyclerView?=null
    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView!=null)
            this.recyclerView?.scrollToPosition(position)
    }


    interface AcquisitionSurveyListItemListner {
        fun attachmentItemClicked()
        fun EditBuildingdetailsItemClicked()
        fun editBoundryDetailsClicked(position:Int)
        fun viewBoundryDetailsClicked(position:Int)
        fun editPropertyOwnerDetailsClicked(position:Int)
        fun viewPropertyOwnerDetailsClicked(position:Int)
        fun editPoDetailsClicked(position:Int)
        fun viewPoDetailsClicked(position:Int)
    }
}