package com.smarthub.baseapplication.ui.fragments.sitedetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CustomerListItemBinding
import com.smarthub.baseapplication.databinding.SiteLeaseListItemBinding

class SiteLeaseDataAdapter(var list : ArrayList<Any>) : Adapter<SiteLeaseDataViewHolder>() {

    fun addItem(item : Any){
        list.add(item)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteLeaseDataViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.site_lease_list_item, parent, false)
        return SiteLeaseDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: SiteLeaseDataViewHolder, position: Int) {

    /*    holder.binding?.parentRelative?.setOnClickListener {
            list.clickedItem()
        }*/
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class SiteLeaseDataViewHolder(var itemview: View) : ViewHolder(itemview) {
    var binding = SiteLeaseListItemBinding.bind(itemView)
}

interface SiteLeaseDataAdapterListener{
    fun clickedItem();
}