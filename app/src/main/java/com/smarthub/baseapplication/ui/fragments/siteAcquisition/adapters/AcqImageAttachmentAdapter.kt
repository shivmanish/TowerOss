package com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ImgCardViewBinding
import com.smarthub.baseapplication.model.siteIBoard.Attachments
import com.smarthub.baseapplication.utils.AppLogger

class AcqImageAttachmentAdapter(var context:Context,var list:ArrayList<Attachments>,var listener: ItemClickListener) : RecyclerView.Adapter<AcqImageAttachmentAdapter.ViewHold>() {

    fun addItem(){
//        list.add("item1")
//        notifyItemChanged(list.size.plus(1))
    }
    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : ImgCardViewBinding = ImgCardViewBinding.bind(itemView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.img_card_view,parent,false)
        return ViewHold(view)
    }
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        val attachmentsItem:Attachments=list[position]
        AppLogger.log("image full path : ${attachmentsItem.fullPath}")
        holder.itemView.setOnClickListener {
            listener.itemClicked()
        }
        Glide.with(context).
        load(attachmentsItem.fullPath)
            .into(holder.binding.attachments)

    }
    override fun getItemCount(): Int {
        return list.size
    }
    interface ItemClickListener{
        fun itemClicked()
    }
}