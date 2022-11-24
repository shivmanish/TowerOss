package com.smarthub.baseapplication.ui.adapter.customer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.customer_tab.CustomerInvoiceAdapter

class RfAntinaListAdapter(var listener: ImageAttachmentAdapter.ItemClickListener) : RecyclerView.Adapter<RfAntinaListAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()

    var type1 = "RF Antena1 - 3G"
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
        var binding : RfAntinaListItemBinding = RfAntinaListItemBinding.bind(itemView)
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
        var view = LayoutInflater.from(parent.context).inflate(R.layout.rf_antina_list_item,parent,false)
        return ViewHold(view,listener)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.collapsingLayout.setOnClickListener {
            holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
            if ((holder.binding.itemTitle.tag as Boolean)) {
                holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.down_arrow,0)
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