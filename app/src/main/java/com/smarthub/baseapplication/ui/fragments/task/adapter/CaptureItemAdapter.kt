package com.smarthub.baseapplication.ui.fragments.task.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CaptureDataItemBinding
import com.smarthub.baseapplication.model.taskModel.dropdown.Tab
import com.smarthub.baseapplication.utils.AppLogger

class CaptureItemAdapter(val context: Context,var list : List<Tab>,var listener :CaptureItemAdapterListener) : Adapter<CaptureItemViewholder>() {

    var checked = ArrayList<Boolean>()

    init {
        for (i in list){
            checked.add(false)
        }
    }

    fun calculateCheckedCount():Int{
        var checkedCount = 0
        for (i in checked)
            if (i) checkedCount++;
        return checkedCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaptureItemViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.capture_data_item, parent, false)
        return CaptureItemViewholder(view)
    }

    override fun onBindViewHolder(holder: CaptureItemViewholder, position: Int) {
        holder.binding.subTitle.text=list[position].name
        holder.binding.subTitleCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            checked[position] = isChecked
            listener.onCheckedCountChanged(calculateCheckedCount())
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class CaptureItemViewholder(itemView: View) : ViewHolder(itemView) {
    var binding : CaptureDataItemBinding=CaptureDataItemBinding.bind(itemView)
}

interface CaptureItemAdapterListener{
    fun onCheckedCountChanged(count : Int)
}