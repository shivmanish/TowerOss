package com.smarthub.baseapplication.ui.fragments.task.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CaptureDataItemBinding
import com.smarthub.baseapplication.model.taskModel.dropdown.Tab

class CaptureItemAdapter(val context: Context,var list : List<Tab>,var parentCheck :CheckBox) : Adapter<CaptureItemViewholder>() {

    init {
        parentCheck.setOnCheckedChangeListener { buttonView, isChecked ->

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaptureItemViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.capture_data_item, parent, false)
        return CaptureItemViewholder(view)
    }

    override fun onBindViewHolder(holder: CaptureItemViewholder, position: Int) {
        holder.binding.subTitle.text=list[position].name
        holder.binding.subTitleCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class CaptureItemViewholder(itemView: View) : ViewHolder(itemView) {
    var binding : CaptureDataItemBinding=CaptureDataItemBinding.bind(itemView)
}