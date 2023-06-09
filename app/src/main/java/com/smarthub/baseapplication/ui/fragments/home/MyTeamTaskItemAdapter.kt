package com.smarthub.baseapplication.ui.fragments.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.TaskDetailActivity
import com.smarthub.baseapplication.databinding.HomeTaskHeaderBinding
import com.smarthub.baseapplication.databinding.HomeTaskListItemBinding
import com.smarthub.baseapplication.model.home.MyTeamTask
import com.smarthub.baseapplication.ui.fragments.task.TaskActivity
import com.smarthub.baseapplication.ui.fragments.task.TaskListener
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils


class MyTeamTaskItemAdapter(var listener: TaskListener, var token:String,var context: Context) : RecyclerView.Adapter<MyTeamTaskItemAdapter.ViewHold>() {

    var list : ArrayList<Any> = ArrayList()
    var overDue:Int ?=0
    var nowDue:Int ?=0
    var nextDue:Int ?=0

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ListItemViewHold(itemView: View) : ViewHold(itemView) {
        var binding : HomeTaskListItemBinding = HomeTaskListItemBinding.bind(itemView)

        fun bindData(data : MyTeamTask){
            binding.taskName.text = data.Taskname
            binding.taskDueData.text = data.enddate
            binding.TaskId.text=data.workorderid
        }
    }

    class HeaderViewHold(itemView: View) : ViewHold(itemView) {
        var binding : HomeTaskHeaderBinding = HomeTaskHeaderBinding.bind(itemView)

        fun bindData(){
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if((list[position] is String) && (list[position]=="header")) 0
        else if((list[position] is String) && (list[position]=="loading")) 1
        else if((list[position] is String) && (list[position]=="no_data")) 2
        else 3
    }

    fun updateList(list : List<Any>){
        if(token=="home_navigation" && list.size>=4)
        this.list = ArrayList(list.subList(0,4))
        else
            this.list = ArrayList(list)
        overDue=0
        nowDue=0
        nextDue=0
        for (item in list){
            if (item is MyTeamTask){
                val date = item.enddate.substring(0,19)
                val cmp = Utils.compareDate(date)
                when {
                    cmp > 0 -> {
                        overDue=overDue!!+1
                    }
                    cmp == 0 -> {
                        nowDue=nowDue!!+1
                    }
                    else -> {
                        nextDue=nextDue!!+1
                    }
                }
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
            0 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.home_task_header,parent,false)
                HeaderViewHold(view)
            }
            1 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar,parent,false)
                ViewHold(view)
            }
            2 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.no_list_data,parent,false)
                ViewHold(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.home_task_list_item,parent,false)
                ListItemViewHold(view)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is ListItemViewHold && list[position] is MyTeamTask){
            val item=list[position] as MyTeamTask
            holder.bindData(item)
            val date = item.enddate.substring(0,19)
            val cmp = Utils.compareDate(date)
            when {
                cmp > 0 -> {
                    holder.binding.IndicatorLine.setBackgroundResource(R.color.colorRed)
                }
                cmp == 0 -> {
                    holder.binding.IndicatorLine.setBackgroundResource(R.color.yellow)
                }
                else -> {
                    holder.binding.IndicatorLine.setBackgroundResource(R.color.blue)
                }
            }
            holder.itemView.setOnClickListener {
                listener.closeTask(item,token)
            }
            holder.binding.taskClose.text= String.format(context.resources.getString(R.string.two_string_format_space),item.First_Name,item.Last_Name)
            if(item.Auto=="True"){
                holder.binding.editTaskItem.text="A"
                holder.binding.btnEdit.visibility=View.GONE
            }
            else{
                holder.binding.editTaskItem.text="M"
                holder.binding.btnEdit.visibility=View.VISIBLE
            }
            holder.binding.personTaskItem.setOnClickListener {
                if(item.Auto=="True"){
                    AppLogger.log("before updated Data for task Assign : $item")
                    listener.assignTask(item)
                }

            }
            holder.binding.btnEdit.setOnClickListener {
                if(item.Auto!="True"){
                    AppLogger.log("before updated Data for task Assign : $item")
                    val intent = Intent(holder.itemView.context, TaskActivity::class.java)
                    intent.putExtra("data",Gson().toJson(item))
                    intent.putExtra("title","Edit Task")
                    intent.putExtra("Distance", item.Distance)
                    intent.putExtra("NotificationSettingGeoFencing", item.NotificationSettingGeoFencing)
                    intent.putExtra("NotificationSettingGeoTracking", item.NotificationSettingGeoTracking)
                    holder.itemView.context.startActivity(intent)
                }

            }
        }
        if (holder is HeaderViewHold){
            holder.bindData()
            holder.binding.overDue.text=overDue.toString()
            holder.binding.nowDue.text=nowDue.toString()
            holder.binding.nextDue.text=nextDue.toString()
            holder.binding.cardAdd.setOnClickListener {
                val intent = Intent(holder.binding.cardAdd.context, TaskActivity::class.java)
                holder.binding.cardAdd.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}