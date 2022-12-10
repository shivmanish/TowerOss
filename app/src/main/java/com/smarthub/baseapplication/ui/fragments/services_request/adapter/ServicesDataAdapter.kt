package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CustomerListItemBinding
import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestFrqagment


class ServicesDataAdapter(var listener: ServicesRequestFrqagment, var array: ArrayList<String>) : RecyclerView.Adapter<CustomerDataViewHolder>() {

    fun setData(data: ArrayList<String>) {
        this.array.addAll(data)
        notifyDataSetChanged()
    }

    fun updateData(s : String) {
        this.array.add(s)
        notifyItemChanged(array.size.minus(1))
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

class CustomerDataViewHolder(var itemview: View) : RecyclerView.ViewHolder(itemview) {
    var binding = CustomerListItemBinding.bind(itemView)
}

interface CustomerDataAdapterListener{
    fun clickedItem()
}