package com.smarthub.baseapplication.ui.fragments.sitedetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CustomerListItemBinding

class CustomerDataAdapter(var listener: CustomerDataAdapterListener, var array: ArrayList<String>) : Adapter<CustomerDataViewHolder>() {

    fun setData(data: ArrayList<String>) {
        this.array.addAll(data)
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
        return array.size
    }
}

class CustomerDataViewHolder(var itemview: View) : ViewHolder(itemview) {
    var binding = CustomerListItemBinding.bind(itemView)
}

interface CustomerDataAdapterListener{
    fun clickedItem();
}