package com.smarthub.baseapplication.ui.fragments.customer_tab.powerload

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerLoadListItemBinding
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter

class PowerLoadAdapter (var listener: ImageAttachmentAdapter.ItemClickListener,var listener1:ItemClickListener) : RecyclerView.Adapter<PowerLoadAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()

//    var type1 = "RF Antena1 - 3G"
/*    var type2 = "Colocation Fee"
    var type3 = "Rental/ Energy Charges"
    var type4 = "Invoice/ Payment Status"
    var type5 = "OPCO Contact Details"*/

    init {
        list.add("RF Antena1 - 3G")
        list.add("Colocation Fee")
        list.add("Rental/ Energy Charges")
        list.add("Invoice/ Payment Status")
        list.add("OPCO Contact Details")
    }

    class ViewHold(itemView: View, listener: ImageAttachmentAdapter.ItemClickListener) : RecyclerView.ViewHolder(itemView) {
        var binding : PowerLoadListItemBinding = PowerLoadListItemBinding.bind(itemView)
        var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitleDropdown.tag = false
            if ((binding.itemTitleDropdown.tag as Boolean)) {
                binding.listItemDropdown?.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.listItemDropdown?.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }

            var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }
        }
    }

    fun addItem() {
        if (list[0] == "no_data") {
            list.removeAt(0)
            list.add(0, "3G RRH - S3058940 - 10-Nov-22")
            notifyDataSetChanged()
        } else {
            list.add(0, "3G RRH - S3058940 - 10-Nov-22")
            notifyItemChanged(0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.power_load_list_item,parent,false)
        return ViewHold(view,listener)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.itemTitleDropdown.setOnClickListener {
            holder.binding.itemTitleDropdown.tag = !(holder.binding.itemTitleDropdown.tag as Boolean)
            if ((holder.binding.itemTitleDropdown.tag as Boolean)) {
                holder.binding.listItemDropdown.setImageResource(R.drawable.ic_arrow_up)
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                holder.binding.listItemDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }

            holder.binding.editListItem.setOnClickListener {
                listener1.EditItemDialouge()
            }
            holder.binding.itemLine.visibility =
                if (holder.binding.itemTitleDropdown.tag as Boolean) View.GONE else View.VISIBLE
            holder.binding.itemCollapse.visibility =
                if (holder.binding.itemTitleDropdown.tag as Boolean) View.VISIBLE else View.GONE
            holder.binding.editListItem.visibility =
                if (holder.binding.itemTitleDropdown.tag as Boolean) View.VISIBLE else View.INVISIBLE
        }
        holder.binding.itemTitleStr.text = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface ItemClickListener{
        fun itemClicked()
        fun EditItemDialouge()
    }

}