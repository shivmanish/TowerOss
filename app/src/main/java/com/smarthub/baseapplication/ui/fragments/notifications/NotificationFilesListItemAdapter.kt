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

class NotificationFilesListItemAdapter(val context: Context, var list : ArrayList<Any>) : Adapter<NotificationFilesListItemAdapter.Viewholder>() {


    class Viewholder(item:View) : RecyclerView.ViewHolder(item) {
        var binding = NotificationItemBinding.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_item, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        var item = list[position]
//        holder.binding.fileName.text = item.files
    }

    override fun getItemCount(): Int {
        return list.size
    }
}