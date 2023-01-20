package com.smarthub.baseapplication.ui.alert.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ChatRecieverBinding
import com.smarthub.baseapplication.databinding.ChatSenderBinding
import com.smarthub.baseapplication.ui.alert.model.chat.ChatModelData

class ChatAdapter(var list : ArrayList<ChatModelData>,var context:Context,var reportedBy : String) : RecyclerView.Adapter<ChatAdapter.ViewHold>() {

    fun updateList(data : ArrayList<ChatModelData>){
        this.list = data
        Toast.makeText(context,"${data.size}",Toast.LENGTH_SHORT).show()
        notifyDataSetChanged()
    }

    fun addData(data : ArrayList<ChatModelData>){
        this.list.addAll(data)
        notifyItemRangeInserted(list.size.minus(data.size),data.size)
    }

    open class ViewHold(itemView : View) :RecyclerView.ViewHolder(itemView)

    class RecieverViewHold(itemView : View) :ViewHold(itemView){
        var binding = ChatRecieverBinding.bind(itemView)
    }
    class SenderViewHold(itemView : View) :ViewHold(itemView){
        var binding = ChatSenderBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {

        return if (viewType == 0)
            RecieverViewHold(LayoutInflater.from(context).inflate(R.layout.chat_reciever,parent,false))
        else SenderViewHold(LayoutInflater.from(context).inflate(R.layout.chat_sender,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        var item =  list[position]
        if (holder is RecieverViewHold){
            holder.binding.textMsg.text = item.message
        }else if(holder is SenderViewHold){
            holder.binding.textMsg.text = item.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].ReportedBy == reportedBy)
            1
        else 0
    }
    override fun getItemCount(): Int {
        return list.size
    }
}