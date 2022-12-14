package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.NativeOnCompleteListener
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CustomerListItemBinding
import com.smarthub.baseapplication.databinding.NocListItemBinding
import com.smarthub.baseapplication.ui.fragments.services_request.ServicesRequestFrqagment


class NocDataAdapter(var listener: NocDataAdapterListener, var array: ArrayList<String>) : RecyclerView.Adapter<NocDataViewHolder>() {

    fun setData(data: ArrayList<String>) {
        this.array.addAll(data)
        notifyDataSetChanged()
    }

    fun updateData(s : String) {
        this.array.add(s)
        notifyItemChanged(array.size.minus(1))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NocDataViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.noc_list_item, parent, false)
        return NocDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: NocDataViewHolder, position: Int) {

        holder.binding?.cardItem?.setOnClickListener {
            listener.clickedItem()
        }
    }

    override fun getItemCount(): Int {
        return array.size
    }
}

class NocDataViewHolder(var itemview: View) : RecyclerView.ViewHolder(itemview) {
    var binding = NocListItemBinding.bind(itemView)
}

interface NocDataAdapterListener{
    fun clickedItem()
}