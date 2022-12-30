package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CustomerListItemBinding
import com.smarthub.baseapplication.model.siteInfo.BasicInfoModelItem
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.utils.AppController

class OpcoTanancyFragAdapter(var listener: CustomerDataAdapterListener) : Adapter<OpcoDataViewHolder>() {
    var data1 : ArrayList<BasicInfoModelItem>?=null
    var data = ArrayList<Any>()

    init {
        data1 = AppController.getInstance().siteInfoModel?.item
    }

    fun addLoading(){
        this.data.clear()
        this.data.add("loading")
        notifyDataSetChanged()
    }

    fun setOpData(data: ArrayList<OpcoDataItem>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position] is OpcoDataItem) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpcoDataViewHolder {
        return if (viewType == 0){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.customer_list_item, parent, false)
            OpcoDataItemViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar, parent, false)
            OpcoDataViewHolder(view)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OpcoDataViewHolder, position: Int) {
        if(data1!=null && holder is OpcoDataItemViewHolder) {
            var item = data[position] as OpcoDataItem
            holder.binding.SiteId.text = "${data1?.get(0)?.Basicinfo?.get(0)?.siteID}"
            holder.binding.textRfiDate.text =
                "RFI Date: ${item.Opcoinfo[0].rfiAcceptanceDate}"
            holder.binding.textRfsDate.text =
                "RFS Date: ${item.Opcoinfo[0].rfiAcceptanceDate}"
            holder.binding.cardItem.setOnClickListener {
                listener.clickedItem(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

open class OpcoDataViewHolder(var itemview: View) : ViewHolder(itemview) {

}

class OpcoDataItemViewHolder(itemview: View) : OpcoDataViewHolder(itemview) {
    var binding = CustomerListItemBinding.bind(itemView)
}

interface CustomerDataAdapterListener{
    fun clickedItem(data : OpcoDataItem)
}