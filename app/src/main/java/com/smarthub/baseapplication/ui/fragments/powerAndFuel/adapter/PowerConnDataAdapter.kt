package com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerConnectionListItemBinding
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.pojo.PowerAndFuel


class PowerConnDataAdapter(var context: Context, var listener: PowerConnectionListListener, var id:String) : RecyclerView.Adapter<PowerConnectionEmptyViewHolder>() {

    var list = ArrayList<Any>()

    fun setData(data: ArrayList<PowerAndFuel>) {
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
        return if (list[position] is PowerAndFuel) 0 else 1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PowerConnectionEmptyViewHolder {
        return if (viewType == 0){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.power_connection_list_item, parent, false)
            return PowerConnectionDataViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar, parent, false)
            PowerConnectionEmptyViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: PowerConnectionEmptyViewHolder, position: Int) {
        when(holder){
            is PowerConnectionDataViewHolder->{
                val item = list[position] as PowerAndFuel
                holder.itemview.setOnClickListener {
                    listener.clickedItem(item)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}
open class PowerConnectionEmptyViewHolder(var itemview: View) : RecyclerView.ViewHolder(itemview)

class PowerConnectionDataViewHolder( itemview: View) : PowerConnectionEmptyViewHolder(itemview) {
    var binding = PowerConnectionListItemBinding.bind(itemView)
}

interface PowerConnectionListListener{
    fun clickedItem(data:PowerAndFuel)
}