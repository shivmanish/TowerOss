package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.*
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.services_request.tableAdapters.*
import com.smarthub.baseapplication.utils.AppLogger

class OpcoTssrAdapter(var context : Context, var listener: OpcoTssrLisListener,serviceRequestAllData: ServiceRequestAllDataItem?) : RecyclerView.Adapter<OpcoTssrAdapter.ViewHold>() {
    var list : ArrayList<String> = ArrayList()
    private var opcoTssrdata: OpcoTSSR?=null
    private var RfFeasibility: RFFeasibility?=null
    private var BackhaulFeasibility: BackhaulFeasibility?=null
    private var PowerMcb: PowerRequirement?=null
    private var tSSRExecutiveInfo: TSSRExecutiveInfo?=null
    var type1 = "RF Feasibility"
    var type2 = "Backhaul Feasibility"
    var type3 = "Equipments"
    var type4 = "Power & MCB"
    var type5 = "TSSR Executive Info"
    var type6 = "Attachments"


    init {
        list.add("RF Feasibility")
        list.add("Backhaul Feasibility")
        list.add("Equipments")
        list.add("Power & MCB")
        list.add("TSSR Executive Info")
        list.add("Attachments")
        try {
            opcoTssrdata=serviceRequestAllData?.OpcoTSSR?.get(0)
            RfFeasibility=opcoTssrdata?.RFFeasibility?.get(0)
            BackhaulFeasibility=opcoTssrdata?.BackHaulFeasibility?.get(0)
            PowerMcb=opcoTssrdata?.PowerAndMCB?.get(0)?.PowerRequirements?.get(0)
            tSSRExecutiveInfo=opcoTssrdata?.TSSRExecutiveInfo?.get(0)
        }catch (e:java.lang.Exception){
            Toast.makeText(context,"error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
        }
    }
    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : RfItemViewBinding = RfItemViewBinding.bind(itemView)
        var SectorTableList: RecyclerView=binding.sectorCellsTableItem
        init {
            binding.collapsingLayout.tag = false

            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }
            binding.addItems.setOnClickListener {
                addTableItem("dfsdh")
            }
        }
        private fun addTableItem(item:String){
            if (SectorTableList.adapter!=null && SectorTableList.adapter is SecotrsCellsDetailsTableAdapter){
                var adapter = SectorTableList.adapter as SecotrsCellsDetailsTableAdapter
                adapter.addItem(item)
            }
        }
    }
    class ViewHold2(itemView: View) : ViewHold(itemView) {
        var binding : BachaulFeasibilityItemViewBinding = BachaulFeasibilityItemViewBinding.bind(itemView)
        var MicrowaveTableList: RecyclerView=binding.MicrowaveTableItem
        var FiberTableList: RecyclerView=binding.FiberTableItem
        init {
            binding.collapsingLayout.tag = false

            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }
            binding.addMicroWaveItems.setOnClickListener{
                addMicrowaveTableItem("dfghfgh")
            }
            binding.addFiberItems.setOnClickListener{
                addFiberTableItem("dfghfgh")
            }
        }
        private fun addMicrowaveTableItem(item:String){
            if (MicrowaveTableList.adapter!=null && MicrowaveTableList.adapter is BackhaulMicrowaveTableAdapter){
                var adapter = MicrowaveTableList.adapter as BackhaulMicrowaveTableAdapter
                adapter.addItem(item)
            }
        }
        private fun addFiberTableItem(item:String){
            if (FiberTableList.adapter!=null && FiberTableList.adapter is BackhaulFiberTableAdapter){
                var adapter = FiberTableList.adapter as BackhaulFiberTableAdapter
                adapter.addItem(item)
            }
        }
    }
    class ViewHold3(itemView: View) : ViewHold(itemView) {
        var binding: OpcotssrEquipmentTableBinding = OpcotssrEquipmentTableBinding.bind(itemView)
        var tssrEquipmentTableList: RecyclerView=binding.opcotssrEquipmentTables

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
            if (tssrEquipmentTableList.adapter!=null && tssrEquipmentTableList.adapter is tssrEquipmentTableAdapter){
                var adapter = tssrEquipmentTableList.adapter as tssrEquipmentTableAdapter
                adapter.addItem(item)
            }
        }
    }
    class ViewHold4(itemView: View) : ViewHold(itemView) {
        var binding : OpcotssrPowermcbBinding = OpcotssrPowermcbBinding.bind(itemView)
        var tssrPowerMcbTableList: RecyclerView=binding.opcotssrPowermcbTableItem
        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }

            binding.addItems.setOnClickListener {
                addTableItem("dfsdh")
            }
        }
        private fun addTableItem(item:String){
            if (tssrPowerMcbTableList.adapter!=null && tssrPowerMcbTableList.adapter is tssrPowerMcbTableAdapter){
                var adapter = tssrPowerMcbTableList.adapter as tssrPowerMcbTableAdapter
                adapter.addItem(item)
            }
        }
    }
    class ViewHold5(itemView: View) : ViewHold(itemView) {
        var binding : TssrInfoViewBinding = TssrInfoViewBinding.bind(itemView)
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
    class ViewHold6(itemView: View, listener: OpcoTssrLisListener) : ViewHold(itemView) {
        var binding : OpcotssrAttachmentsBinding = OpcotssrAttachmentsBinding.bind(itemView)
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
        else if (list[position] is String && list[position]==type6)
            return 6
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.rf_item_view, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.bachaul_feasibility_item_view, parent, false)
                return ViewHold2(view)
            }
            3 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.opcotssr_equipment_table, parent, false)
                return ViewHold3(view)
            }
            4 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.opcotssr_powermcb, parent, false)
                return ViewHold4(view)
            }
            5-> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.tssr_info_view, parent, false)
                return ViewHold5(view)
            }

            6 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.opcotssr_attachments, parent, false)
                return ViewHold6(view,listener)
            }
        }
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when (holder) {

            is ViewHold1 -> {
                holder.binding.imgEdit.setOnClickListener {
                    listener.detailsItemClicked()
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
               try {
                   holder.binding.TechnologyRfFeasibility.text=RfFeasibility?.Technology
                   holder.binding.SectorCount.text=RfFeasibility?.SectorCount
                   holder.binding.RRUCountRfFeasibility.text=RfFeasibility?.RRUCount
                   holder.binding.OfSetPoleRead.text=RfFeasibility?.OffSetPoleReqd
                   holder.binding.RemarkRfFeasibility.text=RfFeasibility?.RFFeasibiltyRemarks

                   holder.SectorTableList.adapter=SecotrsCellsDetailsTableAdapter(context,listener,RfFeasibility?.SectorsOrCellDetails!!)

               }catch (e:java.lang.Exception){
                   AppLogger.log("error : ${e.localizedMessage}")
                   Toast.makeText(context,"error :${e.localizedMessage}",Toast.LENGTH_LONG).show()

               }
            }
            is ViewHold2 -> {
                holder.binding.imgEdit.setOnClickListener {
                    listener.requestinfoClicked()
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
                if(BackhaulFeasibility!=null){
                    holder.binding.BackhaulNodeType.text=BackhaulFeasibility?.BackHaulNodeType
                    holder.binding.OffsetPoleRequired.text=BackhaulFeasibility?.OffSetPoleRequired
                }
                holder.MicrowaveTableList.adapter=BackhaulMicrowaveTableAdapter(context,listener,BackhaulFeasibility?.MicrowaveOrUBR!!)
                holder.FiberTableList.adapter=BackhaulFiberTableAdapter(context,listener,BackhaulFeasibility?.Fiber!!)
            }
            is ViewHold3 -> {
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
                holder.tssrEquipmentTableList.adapter=tssrEquipmentTableAdapter(context,listener,opcoTssrdata?.Equipments?.get(0)?.OpcoTSSREquipmentTable!!)
            }
            is ViewHold4 -> {
                holder.binding.imgEdit.setOnClickListener() {
                    listener.siteAccessDetailsItemClicked()
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
                if(PowerMcb!=null){
                    holder.binding.InputVoltage.text=PowerMcb?.InputVoltage
                    holder.binding.MaxTotalPower.text=PowerMcb?.MaxTotalPower
                    holder.binding.inputType.text=PowerMcb?.InputType
                    holder.binding.batterBackupRequired.text=PowerMcb?.BatteryBackupRequired
                }
                holder.tssrPowerMcbTableList.adapter=tssrPowerMcbTableAdapter(context,listener,opcoTssrdata?.PowerAndMCB?.get(0)?.MCBRequirements!!)
            }
            is ViewHold5 -> {
                holder.binding.imgEdit.setOnClickListener() {
                    listener.siteAccessDetailsItemClicked()
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
                if(tSSRExecutiveInfo!=null){
                    holder.binding.ExecutiveName.text=tSSRExecutiveInfo?.ExecutiveName
                    holder.binding.EmailId.text=tSSRExecutiveInfo?.EmailID
                    holder.binding.phoneNumber.text=tSSRExecutiveInfo?.PhoneNumber
                    holder.binding.surveyDate.text=tSSRExecutiveInfo?.SurveyDate
                    holder.binding.remarks.text="Data Not Found"
                }
            }
            is ViewHold6 -> {
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
    var currentOpened = -1
    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView!=null)
            this.recyclerView?.scrollToPosition(position)
    }

    interface OpcoTssrLisListener {
        fun attachmentItemClicked()
        fun detailsItemClicked()
        fun requestinfoClicked()
        fun operationInfoDetailsItemClicked()
        fun geoConditionsDetailsItemClicked()
        fun siteAccessDetailsItemClicked()
        fun editSectorCellsDetailsClicked(position:Int)
        fun viewSectorCellsDetailsClicked(position:Int)
        fun editBackhaulMicrowaveClicked(position:Int)
        fun viewBackhaulMicrowaveClicked(position:Int)
        fun editBackhaulFiberClicked(position:Int)
        fun viewBackhaulFiberClicked(position:Int)
        fun editEquipmentClicked(position:Int)
        fun viewEquipmentClicked(position:Int)
        fun editPowerMcbClicked(position:Int)
        fun viewPowerMcbClicked(position:Int)
    }
}