package com.smarthub.baseapplication.ui.fragments.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NotificationListSubtitleBinding
import com.smarthub.baseapplication.model.notification.NotificationData
import com.smarthub.baseapplication.model.notification.NotificationListItem
import com.smarthub.baseapplication.model.notification.newData.NotificationNewItem

class NotificationListSubtitleAdapter(val context: Context,var list : ArrayList<NotificationNewItem>) : Adapter<NotificationListSubtitleAdapter.Viewholder>() {
    fun setData(data : List<NotificationNewItem>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_list_subtitle, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item : NotificationNewItem = list[position]
        holder.binding.textName.text = "${item.UserFirstName} ${item.UserLastName}"
//        holder.binding.list.adapter = NotificationListItemAdapter(context,item.sublist)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class Viewholder(item:View) : RecyclerView.ViewHolder(item) {
        var binding = NotificationListSubtitleBinding.bind(item)
    }
}