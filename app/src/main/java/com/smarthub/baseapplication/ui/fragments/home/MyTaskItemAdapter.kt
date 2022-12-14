package com.smarthub.baseapplication.ui.fragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.HomeTaskHeaderBinding
import com.smarthub.baseapplication.databinding.HomeTaskListItemBinding
import com.smarthub.baseapplication.model.home.MyTeamTask
import com.smarthub.baseapplication.utils.AppLogger
import kotlin.collections.ArrayList

class MyTaskItemAdapter : RecyclerView.Adapter<MyTaskItemAdapter.ViewHold>() {

    var list : ArrayList<Any> = ArrayList()

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ListItemViewHold(itemView: View) : ViewHold(itemView) {
        var binding : HomeTaskListItemBinding = HomeTaskListItemBinding.bind(itemView)

        fun bindData(data : MyTeamTask){
            binding.taskName.text = data.Taskname
            binding.taskDueData.text = data.enddate
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
        this.list = ArrayList(list)
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
            holder.bindData(list[position] as MyTeamTask)
        }
        if (holder is HeaderViewHold){
            holder.bindData()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}