package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CardLayoutBinding
import com.smarthub.baseapplication.databinding.CustomerListItemBinding
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem

class TaskServicesDataAdapter(var listener: ServicesDataAdapterListener, Id: String) : RecyclerView.Adapter<TaskViewHold>() {

    var list = ArrayList<Any>()
    var id=Id

    fun setData(data: ArrayList<ServiceRequestAllDataItem>) {
        this.list.clear()
        this.list.addAll(data)
        notifyDataSetChanged()
    }
    fun addLoading(){
        this.list.clear()
        this.list.add("loading")
        notifyDataSetChanged()
    }
    override fun getItemViewType(position: Int): Int {
        return if (list[position] is ServiceRequestAllDataItem) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHold {
        return if (viewType == 0){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
            return TaskServiceAdapterViewHold(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar, parent, false)
            TaskViewHold(view)
        }

    }

    override fun onBindViewHolder(holder: TaskViewHold, position: Int) {
        if (holder is TaskServiceAdapterViewHold) {
            val item = list[position] as ServiceRequestAllDataItem
            holder.binding.cardTitle.text = item.id.toString()
            holder.binding.card.setOnClickListener {
                listener.clickedItem(item, id)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
open class TaskViewHold(var itemview: View) : RecyclerView.ViewHolder(itemview)

class TaskServiceAdapterViewHold(itemview: View) : TaskViewHold(itemview) {
    var binding = CardLayoutBinding.bind(itemView)
}