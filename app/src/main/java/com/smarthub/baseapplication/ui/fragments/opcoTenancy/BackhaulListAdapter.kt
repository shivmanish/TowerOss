package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.TableAdapters.MaterialTableAdapter
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.TableAdapters.PoTableAdapter

class BackhaulListAdapter(var context: Context, var listener: BackhaulListListener, opcodata: OpcoDataItem?) : RecyclerView.Adapter<BackhaulListAdapter.ViewHold>() {

    var list: ArrayList<String> = ArrayList()
    var currentOpened = -1

    var LINK_VIEW_TYPE = 0
    var IDU_VIEW_TYPE = 1
    var ODU_VIEW_TYPE = 2
    var ANTEENA_VIEW_TYPE = 3
    var INSTALLATION_TEAM_VIEW_TYPE = 4
    var MATERIALS_VIEW_TYPE = 5
    var LMC_VIEW_TYPE = 6
    var ATPCHECK_LIST_VIEW_TYPE = 7
    var PO_DETAILS_VIEW_TYPE = 8
    var CONNEVCTED_EQUIPMENT_VIEW_TYPE = 9

    init {
        list.add("Link")
        list.add("IDU")
        list.add("ODU")
        list.add("Antenna")
        list.add("Installation Team")
        list.add("Materials")
        list.add("LMC; Fiber")
        list.add("ATP Checklist")
        list.add("PO Details")
        list.add("Connected Equipment(CE)")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class LinkViewHold(itemView: View) : ViewHold(itemView) {
        var binding: BackhaulLinkListItemBinding = BackhaulLinkListItemBinding.bind(itemView)

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

    class IDUViewHold(itemView: View) : ViewHold(itemView) {
        var binding: BackhaulIduListItemBinding = BackhaulIduListItemBinding.bind(itemView)

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

    class ODUViewHold(itemView: View) : ViewHold(itemView) {
        var binding: BackhaulOduListItemBinding = BackhaulOduListItemBinding.bind(itemView)

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

    class AntennaViewHold(itemView: View) : ViewHold(itemView) {
        var binding: BackhaulAntenaListItemBinding = BackhaulAntenaListItemBinding.bind(itemView)

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

    class InstallationTeamViewHold(itemView: View) : ViewHold(itemView) {
        var binding: BackhaulInstallationTeamListItemBinding =
            BackhaulInstallationTeamListItemBinding.bind(itemView)

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

    class MaterialsViewHold(itemView: View, listener: BackhaulListListener) :
        ViewHold(itemView) {
        var binding: BackhaulConsumableMaterialsListItemBinding = BackhaulConsumableMaterialsListItemBinding.bind(itemView)
        var MaterialTableList : RecyclerView = binding.root.findViewById(R.id.material_table_item)
        var adapter = ImageAttachmentAdapter(object : ImageAttachmentAdapter.ItemClickListener{
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

            val recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }

            binding.imgAdd.setOnClickListener {
                addTableItem("gsfbgksf")
            }
        }
        private fun addTableItem(item:String){
            if (MaterialTableList.adapter!=null && MaterialTableList.adapter is MaterialTableAdapter){
                val adapter = MaterialTableList.adapter as MaterialTableAdapter
                adapter.addItem(item)
            }
        }
    }

    class LMCViewHold(itemView: View) : ViewHold(itemView) {
        var binding: BackhaulLmcListItemBinding = BackhaulLmcListItemBinding.bind(itemView)

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

    class PODetailsViewHold(itemView: View) : ViewHold(itemView) {
        var binding: BackhaulPoDetailsListItemBinding = BackhaulPoDetailsListItemBinding.bind(itemView)
        var PoTableList : RecyclerView = binding.root.findViewById(R.id.Backhaul_PO_table_item)
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
                addTableItem("gsfbgksf")
            }

        }

        private fun addTableItem(item:String){
            if (PoTableList.adapter!=null && PoTableList.adapter is PoTableAdapter){
                val adapter = PoTableList.adapter as PoTableAdapter
                adapter.addItem(item)
            }
        }
    }

    class CONNEVCTEDEQUIPMENTViewHold(itemView: View) : ViewHold(itemView) {
        var binding: BackhaulConnectedEquipmentItemListBinding =
            BackhaulConnectedEquipmentItemListBinding.bind(itemView)
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

    class ATPCHECKViewHold(itemView: View) : ViewHold(itemView) {
        var binding: AtpChecklistBinding = AtpChecklistBinding.bind(itemView)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return when (viewType) {
            LINK_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_link_list_item, parent, false)
                LinkViewHold(view)
            }
            IDU_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_idu_list_item, parent, false)
                IDUViewHold(view)
            }
            ODU_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_odu_list_item, parent, false)
                ODUViewHold(view)
            }
            ANTEENA_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_antena_list_item, parent, false)
                AntennaViewHold(view)
            }
            INSTALLATION_TEAM_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_installation_team_list_item, parent, false)
                InstallationTeamViewHold(view)
            }
            MATERIALS_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_consumable_materials_list_item, parent, false)
                MaterialsViewHold(view, listener)
            }
            LMC_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_lmc_list_item, parent, false)
                LMCViewHold(view)
            }
            ATPCHECK_LIST_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.atp_checklist, parent, false)
                ATPCHECKViewHold(view)
            }
            PO_DETAILS_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_po_details_list_item, parent, false)
                PODetailsViewHold(view)
            }
            CONNEVCTED_EQUIPMENT_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_connected_equipment_item_list, parent, false)
                CONNEVCTEDEQUIPMENTViewHold(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_list_item, parent, false)
                ViewHold(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] == "Link") LINK_VIEW_TYPE
        else if (list[position] == "IDU") IDU_VIEW_TYPE
        else if (list[position] == "ODU") ODU_VIEW_TYPE
        else if (list[position] == "Antenna") ANTEENA_VIEW_TYPE
        else if (list[position] == "Installation Team") INSTALLATION_TEAM_VIEW_TYPE
        else if (list[position] == "Materials") MATERIALS_VIEW_TYPE
        else if (list[position] == "LMC; Fiber") LMC_VIEW_TYPE
        else if (list[position] == "ATP Checklist") ATPCHECK_LIST_VIEW_TYPE
        else if (list[position] == "PO Details") PO_DETAILS_VIEW_TYPE
        else if (list[position] == "Connected Equipment(CE)") CONNEVCTED_EQUIPMENT_VIEW_TYPE
        else 0
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is LinkViewHold) {

            holder.binding.imgEdit.setOnClickListener {
                listener.LinkItemEdit()
            }
            holder.binding.collapsingLayout.setOnClickListener {
                updateList(position)
            }
            if(currentOpened==position){
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                holder.binding.itemLine.visibility=View.GONE
                holder.binding.itemCollapse.visibility=View.VISIBLE
                holder.binding.imgEdit.visibility=View.VISIBLE
            }
            else {
                holder.binding.collapsingLayout.tag=false
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                holder.binding.itemLine.visibility=View.VISIBLE
                holder.binding.itemCollapse.visibility=View.GONE
                holder.binding.imgEdit.visibility=View.GONE
            }
            holder.binding.itemTitleStr.text = list[position]
        }
        else if (holder is IDUViewHold) {
            holder.binding.imgEdit.setOnClickListener {
                listener.IduEditItem()
            }
            holder.binding.collapsingLayout.setOnClickListener {
                updateList(position)
            }
            if(currentOpened==position){
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                holder.binding.itemLine.visibility=View.GONE
                holder.binding.itemCollapse.visibility=View.VISIBLE
                holder.binding.imgEdit.visibility=View.VISIBLE
            }
            else {
                holder.binding.collapsingLayout.tag=false
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                holder.binding.itemLine.visibility=View.VISIBLE
                holder.binding.itemCollapse.visibility=View.GONE
                holder.binding.imgEdit.visibility=View.GONE
            }
            holder.binding.itemTitleStr.text = list[position]

        }
        else if (holder is ODUViewHold) {
            holder.binding.imgEdit.setOnClickListener {
                listener.OduEditItem()
            }
            holder.binding.collapsingLayout.setOnClickListener {
                updateList(position)
            }
            if(currentOpened==position){
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                holder.binding.itemLine.visibility=View.GONE
                holder.binding.itemCollapse.visibility=View.VISIBLE
                holder.binding.imgEdit.visibility=View.VISIBLE
            }
            else {
                holder.binding.collapsingLayout.tag=false
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                holder.binding.itemLine.visibility=View.VISIBLE
                holder.binding.itemCollapse.visibility=View.GONE
                holder.binding.imgEdit.visibility=View.GONE
            }
            holder.binding.itemTitleStr.text = list[position]

        }
        else if (holder is AntennaViewHold) {
            holder.binding.imgEdit.setOnClickListener {
                listener.OduEditItem()
            }
            holder.binding.collapsingLayout.setOnClickListener {
                updateList(position)
            }
            if(currentOpened==position){
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                holder.binding.itemLine.visibility=View.GONE
                holder.binding.itemCollapse.visibility=View.VISIBLE
                holder.binding.imgEdit.visibility=View.VISIBLE
            }
            else {
                holder.binding.collapsingLayout.tag=false
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                holder.binding.itemLine.visibility=View.VISIBLE
                holder.binding.itemCollapse.visibility=View.GONE
                holder.binding.imgEdit.visibility=View.GONE
            }
            holder.binding.itemTitleStr.text = list[position]

        }
        else if (holder is InstallationTeamViewHold) {
            holder.binding.imgEdit.setOnClickListener {
                listener.InstllationItemEdit()
            }
            holder.binding.collapsingLayout.setOnClickListener {
                updateList(position)
            }
            if(currentOpened==position){
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                holder.binding.itemLine.visibility=View.GONE
                holder.binding.itemCollapse.visibility=View.VISIBLE
                holder.binding.imgEdit.visibility=View.VISIBLE
            }
            else {
                holder.binding.collapsingLayout.tag=false
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                holder.binding.itemLine.visibility=View.VISIBLE
                holder.binding.itemCollapse.visibility=View.GONE
                holder.binding.imgEdit.visibility=View.GONE
            }
            holder.binding.itemTitleStr.text = list[position]

        }
        else if (holder is MaterialsViewHold) {
            holder.binding.collapsingLayout.setOnClickListener {
                updateList(position)
            }
            if(currentOpened==position){
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                holder.binding.itemLine.visibility=View.GONE
                holder.binding.itemCollapse.visibility=View.VISIBLE
                holder.binding.imgAdd.visibility=View.VISIBLE
            }
            else {
                holder.binding.collapsingLayout.tag=false
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                holder.binding.itemLine.visibility=View.VISIBLE
                holder.binding.itemCollapse.visibility=View.GONE
                holder.binding.imgAdd.visibility=View.GONE
            }
            holder.binding.itemTitleStr.text = list[position]
            holder.MaterialTableList.adapter = MaterialTableAdapter( context,listener)


        }
        else if (holder is LMCViewHold) {
            holder.binding.imgEdit.setOnClickListener {
                listener.LmcFiberItemEdit()
            }
            holder.binding.collapsingLayout.setOnClickListener {
                updateList(position)
            }
            if(currentOpened==position){
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                holder.binding.itemLine.visibility=View.GONE
                holder.binding.itemCollapse.visibility=View.VISIBLE
                holder.binding.imgEdit.visibility=View.VISIBLE
            }
            else {
                holder.binding.collapsingLayout.tag=false
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                holder.binding.itemLine.visibility=View.VISIBLE
                holder.binding.itemCollapse.visibility=View.GONE
                holder.binding.imgEdit.visibility=View.GONE
            }
            holder.binding.itemTitleStr.text = list[position]

        }
        else if (holder is PODetailsViewHold) {
            holder.binding.collapsingLayout.setOnClickListener {
                updateList(position)
            }
            if(currentOpened==position){
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                holder.binding.itemLine.visibility=View.GONE
                holder.binding.itemCollapse.visibility=View.VISIBLE
                holder.binding.imgAdd.visibility=View.VISIBLE
            }
            else {
                holder.binding.collapsingLayout.tag=false
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                holder.binding.itemLine.visibility=View.VISIBLE
                holder.binding.itemCollapse.visibility=View.GONE
                holder.binding.imgAdd.visibility=View.GONE
            }
            holder.binding.itemTitleStr.text = list[position]
            holder.PoTableList.adapter = PoTableAdapter( context,listener)

        }
        else if (holder is ATPCHECKViewHold) {
            holder.binding.collapsingLayout.setOnClickListener {
                updateList(position)
            }
            if(currentOpened==position){
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                holder.binding.itemLine.visibility=View.GONE
                holder.binding.itemCollapse.visibility=View.VISIBLE
            }
            else {
                holder.binding.collapsingLayout.tag=false
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                holder.binding.itemLine.visibility=View.VISIBLE
                holder.binding.itemCollapse.visibility=View.GONE
            }
            holder.binding.itemTitleStr.text = list[position]

        }
        else if (holder is CONNEVCTEDEQUIPMENTViewHold) {
            holder.binding.imgEdit.setOnClickListener {
                listener.ConnectedEquipmentItemEdit()
            }
            holder.binding.collapsingLayout.setOnClickListener {
                updateList(position)
            }
            if(currentOpened==position){
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                holder.binding.itemLine.visibility=View.GONE
                holder.binding.itemCollapse.visibility=View.VISIBLE
                holder.binding.imgEdit.visibility=View.VISIBLE
            }
            else {
                holder.binding.collapsingLayout.tag=false
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                holder.binding.itemLine.visibility=View.VISIBLE
                holder.binding.itemCollapse.visibility=View.GONE
                holder.binding.imgEdit.visibility=View.GONE
            }
            holder.binding.itemTitleStr.text = list[position]

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

    interface BackhaulListListener {
        fun attachmentItemClicked()
        fun LinkItemEdit()
        fun IduEditItem()
        fun OduEditItem()
        fun AnteenaItemEdit()
        fun InstllationItemEdit()
        fun LmcFiberItemEdit()
        fun ConnectedEquipmentItemEdit()
        fun EditMaterialTableItem(position:Int)
        fun ViewMaterialTableItem(position:Int)
        fun EditPoTableItem(position:Int)
        fun ViewPoTableItem(position:Int)
    }
}