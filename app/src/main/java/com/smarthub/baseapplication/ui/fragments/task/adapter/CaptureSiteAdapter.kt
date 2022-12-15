package com.smarthub.baseapplication.ui.fragments.task.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CaptureSiteDataItemLayoutBinding
import com.smarthub.baseapplication.utils.Utils

class CaptureSiteAdapter(val context: Context) : Adapter<CaptureSiteViewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaptureSiteViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.capture_site_data_item_layout, parent, false)
        return CaptureSiteViewholder(view)
    }

    override fun onBindViewHolder(holder: CaptureSiteViewholder, position: Int) {
        holder.binding.top.setOnClickListener {
            if(holder.binding.list.visibility == View.GONE){
                holder.binding.arrow.rotation = 180f
                Utils.expand(holder.binding.list)
            }else{
                Utils.collapse(holder.binding.list)
                holder.binding.arrow.rotation = 0f
            }
        }
        holder.binding.list.layoutManager = LinearLayoutManager(context)
        holder.binding.list.adapter = CaptureItemAdapter(context)
    }

    override fun getItemCount(): Int {

        return 5
    }
}

class CaptureSiteViewholder(itemView: View) : ViewHolder(itemView) {
    var binding = CaptureSiteDataItemLayoutBinding.bind(itemView)
}