package com.smarthub.baseapplication.ui.alert.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AlertImgAttachmentBinding
import com.smarthub.baseapplication.databinding.ImgCardViewBinding

class AlertimageAdapter(var listener: ItemClickListener) : RecyclerView.Adapter<AlertimageAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()

    init {
        list.add("item1")
        list.add("item1")
    }
    fun addItem(){
        list.add("item1")
        notifyItemChanged(list.size.minus(1))
    }
    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : AlertImgAttachmentBinding = AlertImgAttachmentBinding.bind(itemView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.alert_img_attachment,parent,false)
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