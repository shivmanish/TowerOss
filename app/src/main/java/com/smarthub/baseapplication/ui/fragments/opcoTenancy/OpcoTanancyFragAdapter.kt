package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CustomerListItemBinding
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.model.siteInfo.BasicInfoModelItem
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.utils.AppController

class OpcoTanancyFragAdapter(var listener: CustomerDataAdapterListener) :
    Adapter<CustomerDataViewHolder>() {
    var data1 : ArrayList<BasicInfoModelItem>?=null

    var data : List<OpcoDataItem> = ArrayList()

init {
    data1 = AppController.getInstance().siteInfoModel?.item
}
    fun setOpData(data: List<OpcoDataItem>) {
        this.data = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.customer_list_item, parent, false)
        return CustomerDataViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomerDataViewHolder, position: Int) {
        if(data1!=null)
            holder.binding.SiteId.text= "${data1?.get(0)?.Basicinfo?.get(0)?.siteID}"
        holder.binding.textRfiDate.text= "RFI Date: ${data[position].Opcoinfo[0].rfiAcceptanceDate}"
        holder.binding.textRfsDate.text="RFS Date: ${data[position].Opcoinfo[0].rfiAcceptanceDate}"
        holder.binding.cardItem.setOnClickListener {
            listener.clickedItem(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class CustomerDataViewHolder(var itemview: View) : ViewHolder(itemview) {
    var binding = CustomerListItemBinding.bind(itemView)
}

interface CustomerDataAdapterListener{
    fun clickedItem(data : OpcoDataItem)
}