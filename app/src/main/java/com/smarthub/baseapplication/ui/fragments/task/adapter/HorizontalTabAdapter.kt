package com.smarthub.baseapplication.ui.fragments.task.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CardLayoutBinding
import com.smarthub.baseapplication.model.taskModel.dropdown.CollectionItem
import com.smarthub.baseapplication.utils.AppLogger

class HorizontalTabAdapter(var listener: TaskCardClickListner, var list:ArrayList<CollectionItem>) : RecyclerView.Adapter<HorizontalTabAdapter.ViewHold>() {


    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : CardLayoutBinding = CardLayoutBinding.bind(itemView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return ViewHold(view)
    }
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.cardTitle.text=list[position].name
//        if (position%2==0)
//            list[position].list = arrayListOf("Data1","Data2","Data3","Data3")
//        else list[position].list = arrayListOf("New1","New2","New3","New4")
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
            AppLogger.log("current selected card data start====>: ")
            AppLogger.log("current selected card data : ${Gson().toJson(list[currentOpened])}")
            AppLogger.log("<======current selected card data end ")
            listener.taskCardItemClicked(list[currentOpened])
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
        fun taskCardItemClicked(selctedCard:CollectionItem)
    }
}