package com.smarthub.baseapplication.ui.fragments.task.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CaptureDataItemBinding

class CaptureItemAdapter(val context: Context, sublist:ArrayList<String>) : Adapter<CaptureItemViewholder>() {
    var list : ArrayList<String> = ArrayList()

    init {
        list.clear()
        list.addAll(sublist)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaptureItemViewholder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.capture_data_item, parent, false)
        return CaptureItemViewholder(view)
    }

    override fun onBindViewHolder(holder: CaptureItemViewholder, position: Int) {
        holder.binding.subTitle.text=list[position]
    }

    override fun getItemCount(): Int {

        return list.size
    }
}

class CaptureItemViewholder(itemView: View) : ViewHolder(itemView) {
    var binding : CaptureDataItemBinding=CaptureDataItemBinding.bind(itemView)
}