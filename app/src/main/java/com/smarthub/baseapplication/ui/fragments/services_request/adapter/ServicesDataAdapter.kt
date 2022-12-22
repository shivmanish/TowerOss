package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CustomerListItemBinding
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestFrqagment


class ServicesDataAdapter(var listener: ServicesRequestFrqagment) : RecyclerView.Adapter<CustomerDataViewHolder>() {

    var list = ArrayList<ServiceRequestAllDataItem>()

    fun setData(data: ArrayList<ServiceRequestAllDataItem>) {
        this.list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerDataViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.customer_list_item, parent, false)
        return CustomerDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomerDataViewHolder, position: Int) {

        holder.binding?.cardItem?.setOnClickListener {
            listener.clickedItem(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class CustomerDataViewHolder(var itemview: View) : RecyclerView.ViewHolder(itemview) {
    var binding = CustomerListItemBinding.bind(itemView)
}

interface ServicesDataAdapterListener{
    fun clickedItem(data : ServiceRequestAllDataItem)
}