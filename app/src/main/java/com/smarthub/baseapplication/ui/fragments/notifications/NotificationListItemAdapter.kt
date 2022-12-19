package com.smarthub.baseapplication.ui.fragments.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NotificationItemBinding
import com.smarthub.baseapplication.model.notification.NotificationData
import com.smarthub.baseapplication.model.notification.NotificationListItem

class NotificationListItemAdapter(val context: Context) : Adapter<NotificationListItemAdapter.Viewholder>() {

    class Viewholder(item:View) : RecyclerView.ViewHolder(item) {
        var binding = NotificationItemBinding.bind(item)
    }

    var list : ArrayList<NotificationData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_item, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        if(list[position].files=="yes"){
            holder.binding.fileLayout.visibility=View.VISIBLE
        }
        else holder.binding.fileLayout.visibility=View.GONE

    }

    override fun getItemCount(): Int {
        return list.size
    }
}