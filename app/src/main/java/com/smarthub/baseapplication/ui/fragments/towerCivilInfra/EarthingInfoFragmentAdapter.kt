package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter

class EarthingInfoFragmentAdapter(var context: Context,var listner: TowerEarthingListListener): RecyclerView.Adapter<EarthingInfoFragmentAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()

    var type1 = "Earthing"
    var type2 = "Installation & Acceptence"
    var type3 = "PO"
    var type4 = "Consumables"
    var type5 = "Attachment"
    init {
        list.add("Earthing")
        list.add("Installation & Acceptence")
        list.add("PO")
        list.add("Consumables")
        list.add("Attachment")
    }
    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : TowerEartrhingItemBinding = TowerEartrhingItemBinding.bind(itemView)

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
        var binding : EarthingInstallationAcceptenceBinding = EarthingInstallationAcceptenceBinding.bind(itemView)

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
        var binding: EarthingPoItemsBinding = EarthingPoItemsBinding.bind(itemView)
        var rvTableList : RecyclerView = binding.root.findViewById(R.id.rv_tables)

        //   var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.imgDropdown.tag = false
            if ((binding.imgDropdown.tag as Boolean)) {
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
            if (rvTableList.adapter!=null && rvTableList.adapter is EarthingPoTableAdapter){
                var adapter = rvTableList.adapter as EarthingPoTableAdapter
                adapter.addItem(item)
            }
        }
    }
    class ViewHold4(itemView: View) : ViewHold(itemView) {
        var binding: EarthingConsumableItemBinding =
            EarthingConsumableItemBinding.bind(itemView)

        //   var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.imgDropdown.tag = false
            if ((binding.imgDropdown.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }
        }
    }
    class ViewHold5(itemView: View,listener: TowerEarthingListListener) :
        ViewHold(itemView) {
        var binding: EarthingAttachmentsBinding = EarthingAttachmentsBinding.bind(itemView)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.tower_eartrhing_item, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.earthing_installation_acceptence, parent, false)
                return ViewHold2(view)
            }
            3 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.earthing_po_items, parent, false)
                return ViewHold3(view)
            }
            4 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.earthing_consumable_item, parent, false)
                return ViewHold4(view)
            }
            5 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.earthing_attachments, parent, false)
                return ViewHold5(view, listner)
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
                    listner.EditEarthingItem()
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
                holder.binding.imgDropdown.setOnClickListener {
                    holder.binding.imgDropdown.tag = !(holder.binding.imgDropdown.tag as Boolean)
                    if ((holder.binding.imgDropdown.tag as Boolean)) {
                        holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                        holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)

                    } else {
                        holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                        holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    }
                    holder.binding.itemLine.visibility = if (holder.binding.imgDropdown.tag as Boolean) View.GONE else View.VISIBLE
                    holder.binding.itemCollapse.visibility = if (holder.binding.imgDropdown.tag as Boolean) View.VISIBLE else View.GONE
                    holder.binding.iconLayout.visibility =
                        if (holder.binding.imgDropdown.tag as Boolean) View.VISIBLE else View.INVISIBLE

                }
                holder.binding.itemTitleStr.text = list[position]
                holder.rvTableList.adapter = EarthingPoTableAdapter( context,listner)
//                holder.binding.imgAdd.setOnClickListener {
//                    addTableItem("gsfbgksf")
//                }
//                holder.addTableItem("itegbjdks")
            }
            is ViewHold4 -> {
                holder.binding.imgDropdown.setOnClickListener {
                    holder.binding.imgDropdown.tag = !(holder.binding.imgDropdown.tag as Boolean)
                    if ((holder.binding.imgDropdown.tag as Boolean)) {
                        holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                        holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)

                    } else {
                        holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                        holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    }
                    holder.binding.itemLine.visibility = if (holder.binding.imgDropdown.tag as Boolean) View.GONE else View.VISIBLE
                    holder.binding.itemCollapse.visibility = if (holder.binding.imgDropdown.tag as Boolean) View.VISIBLE else View.GONE
                    holder.binding.iconLayout.visibility =
                        if (holder.binding.imgDropdown.tag as Boolean) View.VISIBLE else View.INVISIBLE

                }
                holder.binding.itemTitleStr.text = list[position]
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

    interface TowerEarthingListListener {
        fun attachmentItemClicked()
        fun EditInstallationAcceptence()
        fun EditEarthingItem()
        fun editClicked(position:Int)
        fun viewClicked(position:Int)
    }
}