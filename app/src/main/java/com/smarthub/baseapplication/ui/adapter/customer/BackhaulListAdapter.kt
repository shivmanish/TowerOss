package com.smarthub.baseapplication.ui.adapter.customer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter

class BackhaulListAdapter(var listener: ImageAttachmentAdapter.ItemClickListener) : RecyclerView.Adapter<BackhaulListAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()

    var type1 = "Link"
    var type2 = "IDU"
    var type3 = "ODU"
    var type4 = "Anteena"
    var type5 = "Installation Team"
    var type6 = "Consumable Materials"
    var type7 = "LMC; Fiber"
    var type8 = "ATP Checklist"
    var type9 = "PO Details"

    init {
        list.add("Link")
        list.add("IDU")
        list.add("ODU")
        list.add("Anteena")
        list.add("Installation Team")
        list.add(" Materials")
        list.add("LMC; Fiber")
        list.add("ATP Checklist")
        list.add("PO Details")
    }

    class ViewHold(itemView: View, listener: ImageAttachmentAdapter.ItemClickListener) : RecyclerView.ViewHolder(itemView) {
        var binding : BackhaulListItemBinding = BackhaulListItemBinding.bind(itemView)
        var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.down_arrow,0)
            }

            var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_list_item,parent,false)
        return ViewHold(view,listener)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.collapsingLayout.setOnClickListener {
            holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
            if ((holder.binding.itemTitle.tag as Boolean)) {
                holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
            }

            holder.binding.itemLine.visibility =
                if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
            holder.binding.itemCollapse.visibility =
                if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
        }
        holder.binding.itemTitle.text = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface ItemClickListener{
        fun itemClicked()
    }
}