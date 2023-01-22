package com.smarthub.baseapplication.ui.alert.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AlertImgAttachmentBinding
import com.smarthub.baseapplication.databinding.ImgCardViewBinding
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.utils.AppLogger

class AlertImageAdapter(var listener: ImageAttachmentAdapter.ItemClickListener) : RecyclerView.Adapter<AlertImageAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()

    init {
        list.add("addItem")
        list.add("item1")
    }

    fun addItem(){
        list.add("item1")
        AppLogger.log("item added item1")
        notifyItemInserted(list.size.minus(1))
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    class AddItemViewHold(itemView: View) : ViewHold(itemView) {
        var binding : AlertImgAttachmentBinding = AlertImgAttachmentBinding.bind(itemView)
    }

    class ItemViewHold(itemView: View) : ViewHold(itemView) {
        var binding : ImgCardViewBinding = ImgCardViewBinding.bind(itemView)
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] == "addItem" )
            0
        else 1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return if (viewType==0){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.alert_img_attachment,parent,false)
            AddItemViewHold(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.img_card_view,parent,false)
            ItemViewHold(view)
        }
    }
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is AddItemViewHold){
            holder.itemView.setOnClickListener {
                addItem()
            }
        }else if (holder is ItemViewHold){
            holder.itemView.setOnClickListener {
//                Toast.makeText(holder.binding.root.context,"Item clicked",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    interface ItemClickListener{
        fun itemClicked()
        fun itemAdded()
    }
}