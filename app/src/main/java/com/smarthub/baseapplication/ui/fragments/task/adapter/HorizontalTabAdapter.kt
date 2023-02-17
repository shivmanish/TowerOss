package com.smarthub.baseapplication.ui.fragments.task.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CardLayoutBinding
import com.smarthub.baseapplication.model.taskModel.dropdown.Tab

class HorizontalTabAdapter(var listener: TaskCardClickListner, var list:ArrayList<Tab>) : RecyclerView.Adapter<HorizontalTabAdapter.ViewHold>() {

//    var list : ArrayList<String> = ArrayList()


//    fun addItem(){
//        list.add("item1")
//        notifyItemChanged(list.size.minus(1))
//    }
    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : CardLayoutBinding = CardLayoutBinding.bind(itemView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return ViewHold(view)
    }
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.cardTitle.text=list[position].name
        if (currentOpened==position){
            holder.binding.card.setCardBackgroundColor(holder.binding.card.context.getColor(R.color.yellow))
            holder.binding.cardTitle.setTextColor(holder.binding.cardTitle.context.getColor(R.color.color1))
        }
        else
        {
            holder.binding.card.setCardBackgroundColor(holder.binding.card.context.getColor(R.color.light_gray))
            holder.binding.cardTitle.setTextColor(holder.binding.cardTitle.context.getColor(R.color.white))
        }
        holder.itemView.setOnClickListener {
            updateList(position)
            listener.TaskCardItemClicked(list[currentOpened])
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }
    var currentOpened = 0
    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) 0 else position
        notifyDataSetChanged()
    }
    interface TaskCardClickListner{
        fun TaskCardItemClicked(selctedCard:Tab)
    }
}