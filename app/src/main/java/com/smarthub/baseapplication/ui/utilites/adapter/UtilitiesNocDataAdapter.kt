package com.smarthub.baseapplication.ui.fragments.sitedetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SiteLeaseListItemBinding
import com.smarthub.baseapplication.databinding.UtilitesNocListItemBinding

class UtilitesNocDataAdapter(var listener: UtilitesNocDataAdapterListener, var array: ArrayList<String>) : Adapter<UtilitesNocDataViewHolder>() {

    fun setData(data: ArrayList<String>) {
        this.array.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UtilitesNocDataViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.utilites_noc_list_item, parent, false)
        return UtilitesNocDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: UtilitesNocDataViewHolder, position: Int) {

        holder.binding?.parentRelative?.setOnClickListener {
            listener.clickedItem()
        }
    }

    override fun getItemCount(): Int {
        return array.size
    }
}

class UtilitesNocDataViewHolder(var itemview: View) : ViewHolder(itemview) {
    var binding = UtilitesNocListItemBinding.bind(itemView)
}

interface UtilitesNocDataAdapterListener{
    fun clickedItem();
}