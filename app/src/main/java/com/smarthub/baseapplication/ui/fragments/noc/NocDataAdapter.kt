package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.NativeOnCompleteListener
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CustomerListItemBinding
import com.smarthub.baseapplication.databinding.NocListItemBinding
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.model.siteInfo.NocAndCompModel.NocAndCompAllDataItem
import com.smarthub.baseapplication.model.siteInfo.NocAndCompModel.NocAndCompModel
import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestFrqagment


class NocDataAdapter(var listener: NocDataAdapterListener, Id: String?) : RecyclerView.Adapter<nocEmptyDataViewHolder>() {
    var list = ArrayList<Any>()
    var id=Id

    fun setData(data: ArrayList<NocAndCompAllDataItem>) {
        this.list.clear()
        this.list.addAll(data)
        notifyDataSetChanged()
    }
    fun addLoading(){
        this.list.clear()
        this.list.add("loading")
        notifyDataSetChanged()
    }
    init {
        list.add("dfshg")
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] is NocAndCompAllDataItem) 0 else 1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): nocEmptyDataViewHolder {
        return if (viewType == 0){
            var view = LayoutInflater.from(parent.context).inflate(R.layout.noc_list_item, parent, false)
            return NocDataViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar, parent, false)
            nocEmptyDataViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: nocEmptyDataViewHolder, position: Int) {
        if (holder is NocDataViewHolder){
            var item = list[position] as NocAndCompAllDataItem
            holder.binding?.cardItem?.setOnClickListener {
                listener.clickedItem(item, id!!)
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}
open class nocEmptyDataViewHolder(var itemview: View) : RecyclerView.ViewHolder(itemview) {}

class NocDataViewHolder( itemview: View) : nocEmptyDataViewHolder(itemview) {
    var binding = NocListItemBinding.bind(itemView)
}

interface NocDataAdapterListener{
    fun clickedItem(data:NocAndCompAllDataItem,id:String)
}