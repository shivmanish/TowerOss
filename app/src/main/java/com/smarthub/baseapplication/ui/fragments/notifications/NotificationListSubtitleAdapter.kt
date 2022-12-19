package com.smarthub.baseapplication.ui.fragments.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NotificationListSubtitleBinding
import com.smarthub.baseapplication.model.notification.NotificationListItem

class NotificationListSubtitleAdapter(val context: Context) : Adapter<NotificationListSubtitleAdapter.Viewholder>() {

    var list : ArrayList<NotificationListItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_list_subtitle, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.binding.textList.text=list[position].subTitle
        holder.binding.clocktimeTextSubtitle.setText("$position +${list[position].time}")
        holder.binding.dateTextSubtitle.setText("$position +${list[position].Date}")
        holder.binding.actionLayout.visibility= if(list[position].Action) View.VISIBLE else View.GONE

        holder.binding.list.adapter = NotificationListItemAdapter(context)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class Viewholder(item:View) : RecyclerView.ViewHolder(item) {
        var binding = NotificationListSubtitleBinding.bind(item)
    }
}