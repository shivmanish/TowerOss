package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.siteInfo.*
import com.smarthub.baseapplication.network.pojo.site_info.BasicInfoModelDropDown
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tableActionAdapters.TowerPoTableAdapter
class OpcoTssrAdapter(var listener: OpcoTssrLisListener) : RecyclerView.Adapter<OpcoTssrAdapter.ViewHold>() {
    var list : ArrayList<String> = ArrayList()
    var type1 = "RF Feasibility"
    var type2 = "Backhaul Feasibility"
    var type3 = "Equipments"
    var type4 = "Power & MCB"
    var type5 = "Attachments"
    var type6 = "TSSR Executive Info"
    private var data : BasicInfoModelDropDown?=null
    private var fieldData : SiteInfoModel?=null

    fun setData(data : BasicInfoModelDropDown){
        this.data = data
        notifyDataSetChanged()
    }
    fun setValueData(data : SiteInfoModel){
        this.fieldData = data
        notifyDataSetChanged()
    }
    init {
        list.add("RF Feasibility")
        list.add("Backhaul Feasibility")
        list.add("Equipments")
        list.add("Power & MCB")
        list.add("Attachments")
        list.add("TSSR Executive Info")
    }
    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
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
    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : RfItemViewBinding = RfItemViewBinding.bind(itemView)

        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }


        }
    }
    class ViewHold2(itemView: View) : ViewHold(itemView) {
        var binding : BachaulFeasibilityItemViewBinding = BachaulFeasibilityItemViewBinding.bind(itemView)

        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }


        }
    }
    class ViewHold3(itemView: View) : ViewHold(itemView) {
        var binding: EquipmentsInfoViewBinding =
            EquipmentsInfoViewBinding.bind(itemView)
        var poTableList: RecyclerView=binding.root.findViewById(R.id.tower_po_tables)

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
            if (poTableList.adapter!=null && poTableList.adapter is TowerPoTableAdapter){
                var adapter = poTableList.adapter as TowerPoTableAdapter
                adapter.addItem(item)
            }
        }
    }
    class ViewHold4(itemView: View) : ViewHold(itemView) {
        var binding : PowerMsbInfoViewBinding = PowerMsbInfoViewBinding.bind(itemView)
        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
            }


        }
    }

    class ViewHold5(itemView: View, listener: OpcoTssrLisListener) : ViewHold(itemView) {
        var binding : AttachmentListItemBinding = AttachmentListItemBinding.bind(itemView)
        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
            }


        }
    }

   class ViewHold6(itemView: View, listener: OpcoTssrLisListener) : ViewHold(itemView) {
        var binding : TssrInfoViewBinding = TssrInfoViewBinding.bind(itemView)

       init {
           binding.itemTitle.tag = false
           binding.itemTitle.tag = false
           if ((binding.itemTitle.tag as Boolean)) {
               binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
               binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
           } else {
               binding.imgDropdown.setImageResource(R.drawable.down_arrow)
               binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
           }


       }
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
                view = LayoutInflater.from(parent.context).inflate(R.layout.equipments_info_view, parent, false)
                return ViewHold3(view)
            }
            4 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.power_msb_info_view, parent, false)
                return ViewHold4(view)
            }
            5 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.attachment_list_item, parent, false)
                return ViewHold5(view,listener)
            }   6-> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.tssr_info_view, parent, false)
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
                    holder.binding.iconLayout.visibility = View.VISIBLE
                }
                else {
                    holder.binding.itemTitle.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.iconLayout.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitle.text = list[position]

                if (data!=null) {

                }
                if(fieldData!=null && fieldData?.item!!.size>0 && fieldData?.item!![0].Basicinfo.isNotEmpty()){
                    val siteBasicinfo: SiteBasicinfo = fieldData?.item!![0].Basicinfo[0]

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
                    holder.binding.iconLayout.visibility = View.VISIBLE
                }
                else {
                    holder.binding.itemTitle.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.iconLayout.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitle.text = list[position]

                if (data!=null) {

                }
                if(fieldData!=null && fieldData?.item!!.size>0 && fieldData?.item!![0].Basicinfo.isNotEmpty()){
                    val siteBasicinfo: SiteBasicinfo = fieldData?.item!![0].Basicinfo[0]

                }
            }
            is ViewHold3 -> {
                holder.binding.collapsingLayout.setOnClickListener {
                    holder.binding.collapsingLayout.tag = !(holder.binding.collapsingLayout.tag as Boolean)
                    if ((holder.binding.collapsingLayout.tag as Boolean)) {
                        holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                        holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)

                    } else {
                        holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                        holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    }
                    holder.binding.itemLine.visibility = if (holder.binding.collapsingLayout.tag as Boolean) View.GONE else View.VISIBLE
                    holder.binding.itemCollapse.visibility = if (holder.binding.collapsingLayout.tag as Boolean) View.VISIBLE else View.GONE
                    holder.binding.imgAdd.visibility =
                        if (holder.binding.collapsingLayout.tag as Boolean) View.VISIBLE else View.INVISIBLE

                }
                holder.binding.itemTitleStr.text = list[position]
             //   holder.poTableList.adapter=OpcoTssrAdapter(context,listener)
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
                    holder.binding.iconLayout.visibility = View.VISIBLE
                }
                else {
                    holder.binding.itemTitle.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.iconLayout.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitle.text = list[position]
                if(fieldData!=null && fieldData?.item!!.size>0 && fieldData?.item!![0].SafetyAndAccess.isNotEmpty()){
                    val geoCondition: SafetyAndAcces = fieldData?.item!![0].SafetyAndAccess[0]
                }
            }
            is ViewHold5 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.iconLayout.visibility = View.VISIBLE
                }
                else {
                    holder.binding.itemTitle.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.iconLayout.visibility = View.GONE
                }
                holder.binding.itemTitle.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitle.text = list[position]
            }
            is ViewHold6 -> {
                holder.binding.imgEdit.setOnClickListener {
                    listener.requestinfoClicked()
                }
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.iconLayout.visibility = View.VISIBLE
                }
                else {
                    holder.binding.itemTitle.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.iconLayout.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitle.text = list[position]
            }
        }
    }
    var currentOpened = -1
    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView!=null)
            this.recyclerView?.scrollToPosition(position)
    }

    var recyclerView: RecyclerView?=null
    override fun getItemCount(): Int {
        return list.size
    }

    interface OpcoTssrLisListener {
        fun attachmentItemClicked()
        fun detailsItemClicked()
        fun requestinfoClicked()
        fun operationInfoDetailsItemClicked()
        fun geoConditionsDetailsItemClicked()
        fun siteAccessDetailsItemClicked()
    }
}