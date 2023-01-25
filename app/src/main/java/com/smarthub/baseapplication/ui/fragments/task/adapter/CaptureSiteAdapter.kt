package com.smarthub.baseapplication.ui.fragments.task.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CaptureSiteDataItemLayoutBinding
import com.smarthub.baseapplication.model.taskModel.dropdown.TaskDropDownModel

class CaptureSiteAdapter(val context: Context,var list : TaskDropDownModel) : Adapter<CaptureSiteViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaptureSiteViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.capture_site_data_item_layout, parent, false)
        return CaptureSiteViewholder(view)
    }

    override fun onBindViewHolder(holder: CaptureSiteViewholder, position: Int) {
        if (currentOpened == position) {
            holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
            holder.binding.itemLine.visibility = View.GONE
            holder.binding.itemCollapse.visibility = View.VISIBLE
        }
        else {
            holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
            holder.binding.itemLine.visibility = View.VISIBLE
            holder.binding.itemCollapse.visibility = View.GONE
        }
        holder.binding.collapsingLayout.setOnClickListener {
            updateList(position)
        }

        val sublistAdapter=CaptureItemAdapter(context,list[position].tabs,holder.binding.titleCheckbox)
        holder.binding.titleStr.text = list[position].name
        holder.binding.list.adapter = sublistAdapter
    }

    override fun getItemCount(): Int {
        return list.size
    }
    var currentOpened = -1
    var currentSelected = -1

    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) -1 else position
        notifyDataSetChanged()
    }
}

class CaptureSiteViewholder(itemView: View) : ViewHolder(itemView) {
    var binding = CaptureSiteDataItemLayoutBinding.bind(itemView)
}