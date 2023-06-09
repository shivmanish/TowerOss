package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CustomerListItemBinding
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem

class ServicesDataAdapter(var listener: ServicesDataAdapterListener, Id: String) : RecyclerView.Adapter<ServiceEmptyDataAdapterViewHold>() {

    var list = ArrayList<Any>()
    var id=Id

    fun setData(data: ArrayList<ServiceRequestAllDataItem>) {
        this.list.clear()
        this.list.addAll(data)
        notifyDataSetChanged()
    }
    fun addLoading(){
        this.list.clear()
        this.list.add("loading")
        notifyDataSetChanged()
    }
    override fun getItemViewType(position: Int): Int {
        return if (list[position] is ServiceRequestAllDataItem) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceEmptyDataAdapterViewHold {
        return if (viewType == 0){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.customer_list_item, parent, false)
            return ServiceDataAdapterViewHold(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar, parent, false)
            ServiceEmptyDataAdapterViewHold(view)
        }

    }

    override fun onBindViewHolder(holder: ServiceEmptyDataAdapterViewHold, position: Int) {
        if (holder is ServiceDataAdapterViewHold) {
            val item = list[position] as ServiceRequestAllDataItem
            holder.binding.textRfiDate.text = ""
            holder.binding.textRfsDate.text = ""
            holder.binding.cardItem.setOnClickListener {
                listener.clickedItem(item, id)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
open class ServiceEmptyDataAdapterViewHold(var itemview: View) : RecyclerView.ViewHolder(itemview) {}

class ServiceDataAdapterViewHold(itemview: View) : ServiceEmptyDataAdapterViewHold(itemview) {
    var binding = CustomerListItemBinding.bind(itemView)
}

interface ServicesDataAdapterListener{
    fun clickedItem(data : ServiceRequestAllDataItem, Id :String)
}