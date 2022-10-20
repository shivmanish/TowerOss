package com.smarthub.baseapplication.ui.fragments.customer_tab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AtpHeaderTitleBinding
import com.smarthub.baseapplication.databinding.CardItemBinding
import com.smarthub.baseapplication.databinding.CommercialListInvoiceStatusBinding
import com.smarthub.baseapplication.databinding.LangItemBinding
import com.smarthub.baseapplication.listeners.QatListListener
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.LangModel
import com.smarthub.baseapplication.model.atp.AtpHeaderStatus
import com.smarthub.baseapplication.model.atp.AtpHeaderTitle
import com.smarthub.baseapplication.model.atp.AtpListItem

class CustomerInvoiceAdapter : RecyclerView.Adapter<CustomerInvoiceAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()

    init {
        list.add("BIL1234")
        list.add("BIL1235")
        list.add("BIL1236")
    }

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : CommercialListInvoiceStatusBinding = CommercialListInvoiceStatusBinding.bind(itemView)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.commercial_list_invoice_status,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.itemTitle.setOnClickListener {
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
}