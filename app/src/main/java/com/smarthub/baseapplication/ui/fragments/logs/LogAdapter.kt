package com.smarthub.baseapplication.ui.fragments.logs

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.LogRecyclerItemViewBinding
import com.smarthub.baseapplication.model.logs.LogsDataInfo

class LogAdapter(var context: Context, var list: ArrayList<LogsDataInfo>) : Adapter<LogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.log_recycler_item_view, parent, false)
        return LogViewHolder(view)
    }
    fun removeData(){
        list.clear()
        notifyDataSetChanged()
    }
    fun addData(listdata: ArrayList<LogsDataInfo>){
        list.clear()
        list.addAll(listdata)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.binding.name.text = list.get(position).Username
        holder.binding.activiy.text = list.get(position).Activity
        holder.binding.message.text = list.get(position).Description
        holder.binding.message.text = list.get(position).Description
        if(position%3==0)
        {
            holder.binding.titleLayout.setBackgroundColor(Color.parseColor("#FFF3CD"))
            holder.binding.activityImageview.background= ContextCompat.getDrawable(holder.itemView.context,R.drawable.circle_3)
            holder.binding.activityImageview.setImageResource(R.drawable.vector__4_);

        }
        if(position%3==1)
        {
            holder.binding.titleLayout.setBackgroundColor(Color.parseColor("#D4EDDA"))
            holder.binding.activityImageview.background= ContextCompat.getDrawable(holder.itemView.context,R.drawable.circle_4)
            holder.binding.activityImageview.setImageResource(R.drawable.vector__5_);
        }
        if(position%3==2)
        {
            holder.binding.titleLayout.setBackgroundColor(Color.parseColor("#FFDEDE"))
            holder.binding.activityImageview.background= ContextCompat.getDrawable(holder.itemView.context,R.drawable.circle_5);
            holder.binding.activityImageview.setImageResource(R.drawable.vector__6_);
        }
        if(position == list.size-1){
            holder.binding.dottedline.visibility = View.GONE
        }else{
            holder.binding.dottedline.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class LogViewHolder(itemView: View) : ViewHolder(itemView) {
    var binding: LogRecyclerItemViewBinding = LogRecyclerItemViewBinding.bind(itemView)
}