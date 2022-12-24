package com.smarthub.baseapplication.ui.adapter.customer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.customer_tab.CustomerInvoiceAdapter

class RfAntinaListAdapter(var listener:  RfAnteenaItemClickListener) : RecyclerView.Adapter<RfAntinaListAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()



    init {
        list.add("RF Antena1 - 3G")

    }

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : RfAntinaListItemBinding = RfAntinaListItemBinding.bind(itemView)
        init {
            binding.itemTitleDropdown.tag = false
            if ((binding.itemTitleDropdown.tag as Boolean)) {
                binding.listItemDropdown?.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.listItemDropdown?.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }


//
//            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
//                adapter.addItem()
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.rf_antina_list_item,parent,false)
        return ViewHold(view)
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
                listener.editModeCliked()
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

    interface RfAnteenaItemClickListener{
        fun attachmentItemClicked()
        fun editModeCliked()
    }
}