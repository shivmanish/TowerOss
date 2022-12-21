package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CustomerListItemBinding
import com.smarthub.baseapplication.model.opco_tenancy.OpcoCardList
import com.smarthub.baseapplication.model.opco_tenancy.OpcoCardListItem

class CustomerDataAdapter(var listener: CustomerDataAdapterListener) :
    Adapter<CustomerDataViewHolder>() {

    var list : ArrayList<OpcoCardListItem> = ArrayList()
    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: OpcoCardList) {
        this.list.addAll(data)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerDataViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.customer_list_item, parent, false)
        return CustomerDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomerDataViewHolder, position: Int) {

        holder.binding?.cardItem?.setOnClickListener {
            listener.clickedItem()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class CustomerDataViewHolder(var itemview: View) : ViewHolder(itemview) {
    var binding = CustomerListItemBinding.bind(itemView)
}

interface CustomerDataAdapterListener{
    fun clickedItem()
}