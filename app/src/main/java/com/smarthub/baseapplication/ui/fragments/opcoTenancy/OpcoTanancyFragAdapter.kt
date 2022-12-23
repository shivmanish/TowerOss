package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CustomerListItemBinding
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class OpcoTanancyFragAdapter(var listener: CustomerDataAdapterListener) :
    Adapter<CustomerDataViewHolder>() {
    var data1 = AppController.getInstance().siteInfoModel.item
    var data : List<OpcoDataItem> = ArrayList()

    fun setOpData(data: List<OpcoDataItem>) {
        this.data = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerDataViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.customer_list_item, parent, false)
        return CustomerDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomerDataViewHolder, position: Int) {
        holder.binding.SiteId.text= "${data1?.get(0)?.Basicinfo?.get(0)?.siteID}"
        holder.binding.textRfiDate.text= "RFI Date: ${data[position].Opcoinfo[0].rfiAcceptanceDate}"
        holder.binding.textRfsDate.text="RFS Date: ${data[position].Opcoinfo[0].rfiAcceptanceDate}"
        holder.binding.cardItem.setOnClickListener {
            listener.clickedItem()
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
    fun clickedItem()
}