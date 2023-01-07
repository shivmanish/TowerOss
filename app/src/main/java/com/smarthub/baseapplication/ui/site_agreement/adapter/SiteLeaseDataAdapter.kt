package com.smarthub.baseapplication.ui.site_agreement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SiteLeaseListItemBinding
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.Siteacquisition

class SiteLeaseDataAdapter(var listener: SiteLeaseDataAdapterListener, var id:String) : Adapter<SiteLeaseDataViewHolder>() {


    var list = ArrayList<Any>()

    fun setData(data: List<Siteacquisition>) {
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
        this.list.add("loading")

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteLeaseDataViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.site_lease_list_item, parent, false)
        return SiteLeaseDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: SiteLeaseDataViewHolder, position: Int) {
       if(list[position] is Siteacquisition)
       {
           val item = list[position] as Siteacquisition
           holder.binding?.parentRelative?.setOnClickListener {
               listener.clickedItem(item)
       }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class SiteLeaseDataViewHolder(var itemview: View) : ViewHolder(itemview) {
    var binding = SiteLeaseListItemBinding.bind(itemView)
}

interface SiteLeaseDataAdapterListener{
    fun clickedItem(item: Siteacquisition);
}