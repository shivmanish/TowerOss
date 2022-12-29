package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CustomerListItemBinding
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestActivity.Companion.Id
import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestFrqagment
import com.smarthub.baseapplication.utils.AppController


class ServicesDataAdapter(var listener: ServicesRequestFrqagment, Id: String?) : RecyclerView.Adapter<CustomerDataViewHolder>() {

    var list = ArrayList<ServiceRequestAllDataItem>()
    var data1 = AppController.getInstance()?.siteInfoModel?.item

    fun setData(data: ArrayList<ServiceRequestAllDataItem>) {
        this.list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerDataViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.customer_list_item, parent, false)
        return CustomerDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomerDataViewHolder, position: Int) {
        holder.binding.SiteId.text= "${data1?.get(0)?.Basicinfo?.get(0)?.siteID}"
        holder.binding.textRfiDate.text= "RFI Date:DataNotFoundFromApi"
        holder.binding.textRfsDate.text="RFS Date: DataNotFoundFromApi"
        holder.binding?.cardItem?.setOnClickListener {
            listener.clickedItem(list[position],Id!!)
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
    fun clickedItem(data : ServiceRequestAllDataItem, Id :String)
}