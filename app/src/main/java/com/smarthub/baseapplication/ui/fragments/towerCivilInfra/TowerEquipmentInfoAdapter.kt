package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tableActionAdapters.EarthingConsumabletableAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tableActionAdapters.EarthingPoTableAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tableActionAdapters.EquipmentConsumableTableAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tableActionAdapters.EquipmentPoTableAdapter

class TowerEquipmentInfoAdapter(var context: Context, var listner: TowerEquipmentInfoAdapter.TowerPoleListListener): RecyclerView.Adapter<TowerEquipmentInfoAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()

    var type1 = "Equipment Room"
    var type2 = "Installation & Acceptence"
    var type3 = "PO"
    var type4 = "Consumables"
    var type5 = "Attachment"
    init {
        list.add("Equipment Room")
        list.add("Installation & Acceptence")
        list.add("PO")
        list.add("Consumables")
        list.add("Attachment")
    }
    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : PoleEquipmentRoomListBinding = PoleEquipmentRoomListBinding.bind(itemView)

        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }


        }
    }
    class ViewHold2(itemView: View) : ViewHold(itemView) {
        var binding : PoleInstallationAcceptenceBinding = PoleInstallationAcceptenceBinding.bind(itemView)

        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }
        }
    }
    class ViewHold3(itemView: View) : ViewHold(itemView) {
        var binding: PolePoItemBinding =
            PolePoItemBinding.bind(itemView)
        var poTableList: RecyclerView=binding.root.findViewById(R.id.po_tables)
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
            if (poTableList.adapter!=null && poTableList.adapter is EquipmentPoTableAdapter){
                var adapter = poTableList.adapter as EquipmentPoTableAdapter
                adapter.addItem(item)
            }
        }
    }
    class ViewHold4(itemView: View) : ViewHold(itemView) {
        var binding: PoleConsumableItemBinding =
            PoleConsumableItemBinding.bind(itemView)

        var EquipmentConsumableTableList : RecyclerView = binding.root.findViewById(R.id.equipment_consumable_table)
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
            if (EquipmentConsumableTableList.adapter!=null && EquipmentConsumableTableList.adapter is EquipmentConsumableTableAdapter){
                var adapter = EquipmentConsumableTableList.adapter as EquipmentConsumableTableAdapter
                adapter.addItem(item)
            }
        }
    }
    class ViewHold5(itemView: View,listener: TowerEquipmentInfoAdapter.TowerPoleListListener) :ViewHold(itemView) {
        var binding: PoleAttachmentBinding = PoleAttachmentBinding.bind(itemView)

        var adapter =  ImageAttachmentAdapter(object : ImageAttachmentAdapter.ItemClickListener{
            override fun itemClicked() {
                listener.attachmentItemClicked()
            }
        })

        init {
            binding.imgDropdown.tag = false
            binding.imgDropdown.tag = false
            if ((binding.imgDropdown.tag as Boolean)) {
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
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.pole_equipment_room_list, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.pole_installation_acceptence, parent, false)
                return ViewHold2(view)
            }
            3 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.pole_po_item, parent, false)
                return ViewHold3(view)
            }
            4 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.pole_consumable_item, parent, false)
                return ViewHold4(view)
            }
            5 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.pole_attachment, parent, false)
                return ViewHold5(view,listner)
            }


        }
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when (holder) {
            is ViewHold1 -> {
                holder.binding.imgDropdown.setOnClickListener {
                    holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                    if ((holder.binding.itemTitle.tag as Boolean)) {
                        holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                        holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)

                    } else {
                        holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                        holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    }
                    holder.binding.itemLine.visibility = if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                    holder.binding.imgEdit.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.INVISIBLE
                    holder.binding.imgEdit.setOnClickListener {
                        listner.EditTowerItem()
                    }

                    holder.binding.itemCollapse.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                    holder.binding.imgEdit.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.INVISIBLE

                }
                holder.binding.itemTitleStr.text = list[position]

            }
            is ViewHold2 -> {
                holder.binding.imgDropdown.setOnClickListener {
                    holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                    if ((holder.binding.itemTitle.tag as Boolean)) {
                        holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                        holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)

                    } else {
                        holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                        holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    }
                    holder.binding.itemLine.visibility = if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                    holder.binding.imgEdit.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.INVISIBLE
                    holder.binding.imgEdit.setOnClickListener {
                        listner.EditInstallationAcceptence()
                    }

                    holder.binding.itemCollapse.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                    holder.binding.imgEdit.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.INVISIBLE

                }
                holder.binding.itemTitleStr.text = list[position]
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
                holder.poTableList.adapter=EquipmentPoTableAdapter(context,listner)
            }
            is ViewHold4 -> {
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
                holder.EquipmentConsumableTableList.adapter=EquipmentConsumableTableAdapter(context,listner)
            }
            is ViewHold5 -> {
                holder.binding.imgDropdown.setOnClickListener {
                    holder.binding.imgDropdown.tag = !(holder.binding.imgDropdown.tag as Boolean)
                    if ((holder.binding.imgDropdown.tag as Boolean)) {
                        holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                        holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    } else {
                        holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                        holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    }
                    holder.binding.itemLine.visibility =
                        if (holder.binding.imgDropdown.tag as Boolean) View.GONE else View.VISIBLE
                    holder.binding.iconLayout.visibility =
                        if (holder.binding.imgDropdown.tag as Boolean) View.VISIBLE else View.GONE

                    holder.binding.itemCollapse.visibility =
                        if (holder.binding.imgDropdown.tag as Boolean) View.VISIBLE else View.GONE

                }
                holder.binding.itemTitleStr.text = list[position]
            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface TowerPoleListListener {
        fun attachmentItemClicked()
        fun EditInstallationAcceptence()
        fun EditTowerItem()
        fun editPoClicked(position:Int)
        fun viewPoClicked(position:Int)
        fun editConsumableClicked(position:Int)
        fun viewConsumableClicked(position:Int)
    }
}