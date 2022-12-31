package com.smarthub.baseapplication.ui.fragments.powerConnection.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NocListItemBinding
import com.smarthub.baseapplication.databinding.PowerConnectionListItemBinding
import com.smarthub.baseapplication.ui.fragments.powerConnection.pojo.PowerAndFuel


class PowerConnDataAdapter(var listener: PowerConnDataDataDataAdapterListener, var array: ArrayList<PowerAndFuel>) : RecyclerView.Adapter<PowerConnDataDataViewHolder>() {

    fun setData(data: ArrayList<PowerAndFuel>) {
        this.array.addAll(data)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PowerConnDataDataViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.power_connection_list_item, parent, false)
        return PowerConnDataDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: PowerConnDataDataViewHolder, position: Int) {

        holder.binding?.cardItem?.setOnClickListener {
            listener.clickedItem(array.get(position))
        }
    }

    override fun getItemCount(): Int {
        return array.size
    }
}

class PowerConnDataDataViewHolder(var itemview: View) : RecyclerView.ViewHolder(itemview) {
    var binding = PowerConnectionListItemBinding.bind(itemView)
}

interface PowerConnDataDataDataAdapterListener{
    fun clickedItem(data:PowerAndFuel)
}