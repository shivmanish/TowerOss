package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CustomerListItemBinding
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.OpcoDataViewHolder
import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestActivity.Companion.Id
import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestFrqagment
import com.smarthub.baseapplication.utils.AppController


class ServicesDataAdapter(var listener: ServicesDataAdapterListener, Id: String?) : RecyclerView.Adapter<ServiceEmptyDataAdapterViewHold>() {

    var list = ArrayList<Any>()
//    var data1 = AppController.getInstance()?.siteInfoModel?.item

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
            var view = LayoutInflater.from(parent.context).inflate(R.layout.customer_list_item, parent, false)
            return ServiceDataAdapterViewHold(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar, parent, false)
            ServiceEmptyDataAdapterViewHold(view)
        }

    }

    override fun onBindViewHolder(holder: ServiceEmptyDataAdapterViewHold, position: Int) {
        if (holder is ServiceDataAdapterViewHold) {
            var item = list[position] as ServiceRequestAllDataItem
            holder.binding.textRfiDate.text = "RFI Date:DataNotFoundFromApi"
            holder.binding.textRfsDate.text = "RFS Date: DataNotFoundFromApi"
            holder.binding?.cardItem?.setOnClickListener {
                listener.clickedItem(item, Id!!)
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