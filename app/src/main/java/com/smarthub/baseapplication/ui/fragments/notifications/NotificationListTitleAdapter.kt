package com.smarthub.baseapplication.ui.fragments.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NotificationListTitleBinding
import com.smarthub.baseapplication.model.notification.NotificationData
import com.smarthub.baseapplication.model.notification.NotificationListItem
import com.smarthub.baseapplication.model.notification.NotificationListModel

class NotificationListTitleAdapter(val context: Context) : Adapter<NotificationListTitleAdapter.Viewholder>() {

    var list : ArrayList<NotificationListModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_list_title, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

//        holder.binding.textList.text = list[position].title+"$position"
        holder.binding.list.adapter = NotificationListSubtitleAdapter(context)
    }

    override fun getItemCount(): Int {
        return 2
    }

    class Viewholder(item:View) : RecyclerView.ViewHolder(item) {
        var binding = NotificationListTitleBinding.bind(item)
    }
}