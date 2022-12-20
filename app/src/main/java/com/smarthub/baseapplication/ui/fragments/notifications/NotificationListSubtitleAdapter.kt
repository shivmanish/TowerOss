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

class NotificationListSubtitleAdapter(val context: Context,var list : ArrayList<NotificationListItem>) : Adapter<NotificationListSubtitleAdapter.Viewholder>() {

    init {
        list.add(NotificationListItem("Ajay", "5 min ago", "10:00 am", "13-Dec-22", false, ArrayList()))
        list.add(NotificationListItem("Ankit", "5 min ago", "10:00 am", "13-Dec-22", false, ArrayList()))
        list.add(NotificationListItem("Manish", "5 min ago", "10:00 am", "13-Dec-22", false, ArrayList()))
        list.add(NotificationListItem("Sandeep", "5 min ago", "10:00 am", "13-Dec-22", false, ArrayList()))
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_list_subtitle, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        var item = list[position]
        holder.binding.list.adapter = NotificationListItemAdapter(context,item.sublist)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class Viewholder(item:View) : RecyclerView.ViewHolder(item) {
        var binding = NotificationListSubtitleBinding.bind(item)
    }
}