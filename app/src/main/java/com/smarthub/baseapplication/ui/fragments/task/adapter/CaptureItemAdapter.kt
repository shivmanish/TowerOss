package com.smarthub.baseapplication.ui.fragments.task.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CaptureDataItemBinding
import com.smarthub.baseapplication.model.taskModel.dropdown.CollectionItem

class CaptureItemAdapter(val context: Context, var list : List<CollectionItem>, var parentChecked:Boolean, var statusList:ArrayList<Boolean>, var listener :CaptureItemAdapterListener) : Adapter<CaptureItemAdapter.CaptureItemViewholder>() {

    var checked = ArrayList<Boolean>()

    init {
        if (parentChecked && statusList.size>0)
        {
            checked.clear()
            checked=statusList
        }
        else{
            for (i in list){
                if (parentChecked && statusList.isEmpty()){
                    checked.add(true)

                }
                else{
                    checked.add(false)
                }


            }
        }
//        if (calculateCheckedCount()>0 && parentChecked)
//            listener.onCheckedCountChanged(calculateCheckedCount(),checked)
    }

    class CaptureItemViewholder(itemView: View) : ViewHolder(itemView) {
        var binding : CaptureDataItemBinding=CaptureDataItemBinding.bind(itemView)

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
        if (parentChecked)
            holder.binding.subTitleCheckbox.tag = checked[position]
        holder.binding.subTitleCheckbox.setOnClickListener {
            if (holder.binding.subTitleCheckbox.tag==null)
                holder.binding.subTitleCheckbox.tag = false
            val isChecked = !(holder.binding.subTitleCheckbox.tag as Boolean)
            checked[position] = isChecked
            listener.onCheckedCountChanged(calculateCheckedCount(),checked)
            if(checked[position]){
                holder.binding.subTitleCheckbox.setImageResource(R.drawable.check_selected)
            }else
                holder.binding.subTitleCheckbox.setImageResource(R.drawable.check_unselected)
        }
        if(checked[position]){
            holder.binding.subTitleCheckbox.setImageResource(R.drawable.check_selected)
        }else
            holder.binding.subTitleCheckbox.setImageResource(R.drawable.check_unselected)

    }

    override fun getItemCount(): Int {
        return list.size
    }
}



interface CaptureItemAdapterListener{
    fun onCheckedCountChanged(count : Int,sublistStatus:ArrayList<Boolean>)
}