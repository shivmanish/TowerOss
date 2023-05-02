package com.smarthub.baseapplication.ui.fragments.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.TaskDetailActivity
import com.smarthub.baseapplication.databinding.HomeAlertsListItemBinding
import com.smarthub.baseapplication.databinding.HomeTaskHeaderBinding
import com.smarthub.baseapplication.databinding.HomeTaskListItemBinding
import com.smarthub.baseapplication.model.home.MyTeamTask
import com.smarthub.baseapplication.model.home.alerts.AlertAllData
import com.smarthub.baseapplication.ui.fragments.task.TaskActivity
import com.smarthub.baseapplication.ui.fragments.task.TaskListener
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils


class HomeAlertItemAdapter(var listener: TaskListener, var token:String) : RecyclerView.Adapter<HomeAlertItemAdapter.ViewHold>() {

    var list : ArrayList<Any> = ArrayList()
    var originalList : ArrayList<Any> = ArrayList()

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ListItemViewHold(itemView: View) : ViewHold(itemView) {
        var binding : HomeAlertsListItemBinding = HomeAlertsListItemBinding.bind(itemView)

        fun bindData(data : AlertAllData){
            binding.alertName.text = data.SAIssueType
            binding.senderName.text = data.UserFirstName+ " "+data.UserLastName
//            binding.TaskId.text=data.workorderid
        }
    }

    class HeaderViewHold(itemView: View) : ViewHold(itemView) {
        var binding : HomeTaskHeaderBinding = HomeTaskHeaderBinding.bind(itemView)

        fun bindData(){
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if((list[position] is String) && (list[position]=="loading")) 1
        else if((list[position] is String) && (list[position]=="no_data")) 2
        else 3
    }

    fun updateList(list : List<Any>){
        if(token=="home_navigation" && list.size>=4)
        this.list = ArrayList(list.subList(0,4))
        else
            this.list = ArrayList(list)
        this.originalList = this.list
        notifyDataSetChanged()
    }
    fun filterList(filter : Int){
        var list = this.originalList
        if(token=="home_navigation" && list.size>=4)
            this.list = ArrayList(list.subList(0,4))
        else
            this.list = ArrayList()
        this.list.add("header")
        for (item in list){
            if (item is MyTeamTask){
                val date = item.enddate.substring(0,19)
                val cmp = Utils.compareDate(date)
                if (filter == 0 && cmp > 0)
                    this.list.add(item)
                else if (filter == 1 && cmp == 0)
                    this.list.add(item)
                else if (filter == 2 && cmp < 0)
                    this.list.add(item)
            }
        }
        notifyDataSetChanged()
    }

    fun addItem(item : Any){
        for(i in this.list)
            if (i is String && (i=="loading" || i=="no_data")) this.list.remove(i)
        this.list.add(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return when (viewType) {
            1 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar,parent,false)
                ViewHold(view)
            }
            2 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.no_list_data,parent,false)
                ViewHold(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.home_alerts_list_item,parent,false)
                ListItemViewHold(view)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is ListItemViewHold && list[position] is AlertAllData){
            val item=list[position] as AlertAllData
            holder.bindData(item)
            holder.itemView.setOnClickListener {
//                listener.closeTask(item,token)
            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}