package com.smarthub.baseapplication.ui.fragments.task.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CardLayoutBinding
import com.smarthub.baseapplication.databinding.ImgCardViewBinding

class HorizontalTabAdapter(var listener: ItemClickListener) : RecyclerView.Adapter<HorizontalTabAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()

    init {
        list.add("item1")
        list.add("item1")
        list.add("item1")
    }
    fun addItem(){
        list.add("item1")
        notifyItemChanged(list.size.minus(1))
    }
    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : CardLayoutBinding = CardLayoutBinding.bind(itemView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return ViewHold(view)
    }
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.root.setOnClickListener {
            listener.itemClicked()
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }
    interface ItemClickListener{
        fun itemClicked()
    }
}