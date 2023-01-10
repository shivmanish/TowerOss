package com.smarthub.baseapplication.ui.fragments.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NotificationListTitleBinding
import com.smarthub.baseapplication.model.notification.NotificationListModel
import com.smarthub.baseapplication.model.notification.newData.NotificationNewItem

class NotificationListTitleAdapter(val context: Context, var list : ArrayList<Any> = ArrayList()) : Adapter<NotificationListTitleAdapter.Viewholder>() {

//    fun updateList(list : List<NotificationListModel>){
//        this.list.clear()
//        this.list.addAll(list)
//        notifyDataSetChanged()
//    }

    fun setData(data : List<NotificationNewItem>){
        list.addAll(data)
        notifyDataSetChanged()
    }

//    init {
//        list.add(NotificationListModel("Today",ArrayList()))
//        list.add(NotificationListModel("Today1",ArrayList()))
//        list.add(NotificationListModel("Today2",ArrayList()))
//        list.add(NotificationListModel("Today3",ArrayList()))
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_list_title, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        var item =  list[position]
        if (item is NotificationListModel) {
            holder.binding.textList.text = item.title
            holder.binding.list.adapter = NotificationListSubtitleAdapter(context, item.list)
        }else if (item is NotificationNewItem) {
            holder.binding.textList.text = "${item.UserFirstName} ${item.UserLastName}"
//            holder.binding.list.adapter = NotificationListSubtitleAdapter(context, item.list)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class Viewholder(item:View) : RecyclerView.ViewHolder(item) {
        var binding = NotificationListTitleBinding.bind(item)
    }
}