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
import com.smarthub.baseapplication.utils.AppLogger

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
        try {
            holder.binding.textName.text = "${item.UserFirstName} ${item.UserLastName}"
            if (item.files.isNotEmpty()){
                holder.filesList.adapter=NotificationFilesListItemAdapter(context,item.files)
            }
        }catch (e:Exception){
            AppLogger.log("somthing went wrong in NotificationListSubtitleAdapter class: ${e.localizedMessage}")
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class Viewholder(item:View) : RecyclerView.ViewHolder(item) {
        var binding = NotificationListSubtitleBinding.bind(item)
        val filesList:RecyclerView=binding.filesList
    }
}