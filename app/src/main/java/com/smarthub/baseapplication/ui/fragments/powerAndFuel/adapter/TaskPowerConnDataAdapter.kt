package com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerConnectionListItemBinding
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.NewPowerFuelAllData


class TaskPowerConnDataAdapter(var context: Context, var listener: TaskPowerConnectionListListener) : RecyclerView.Adapter<TaskPowerConnectionEmptyViewHolder>() {

    var list = ArrayList<Any>()

    fun setData(data: ArrayList<NewPowerFuelAllData>?) {
        this.list.clear()
        this.list.addAll(data!!)
        notifyDataSetChanged()
    }
    fun addLoading(){
        this.list.clear()
        this.list.add("loading")
        notifyDataSetChanged()
    }
    init {
        list.add("loading")
    }

    override fun getItemViewType(position: Int): Int {
        return if (list.isEmpty() || list.get(position)==null)
            2
        else if(list[position] is NewPowerFuelAllData)
            1
        else
            3
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskPowerConnectionEmptyViewHolder {
        return when (viewType) {
            1 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.power_connection_list_item,parent,false)
                TaskPowerConnectionDataViewHolder(view)
            }
            2 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_no_data,parent,false)
                TaskPowerConnectionEmptyViewHolder(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar,parent,false)
                TaskPowerConnectionEmptyViewHolder(view)
            }
        }


    }

    override fun onBindViewHolder(holder: TaskPowerConnectionEmptyViewHolder, position: Int) {
        when(holder){
            is TaskPowerConnectionDataViewHolder->{
                val item = list[position] as NewPowerFuelAllData
                holder.itemview.setOnClickListener {
                    listener.clickedItem(item,position)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}
open class TaskPowerConnectionEmptyViewHolder(var itemview: View) : RecyclerView.ViewHolder(itemview)

class TaskPowerConnectionDataViewHolder( itemview: View) : TaskPowerConnectionEmptyViewHolder(itemview) {
    var binding = PowerConnectionListItemBinding.bind(itemView)
}

interface TaskPowerConnectionListListener{
    fun clickedItem(data:NewPowerFuelAllData,parentIndex:Int)
}