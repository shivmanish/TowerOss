package com.smarthub.baseapplication.ui.fragments.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.HomeTaskHeaderBinding
import com.smarthub.baseapplication.databinding.HomeTaskListItemBinding
import com.smarthub.baseapplication.databinding.ProjectTaskListItemBinding
import com.smarthub.baseapplication.model.home.MyTeamTask
import com.smarthub.baseapplication.model.project.TaskModelClass
import com.smarthub.baseapplication.model.project.TaskModelData
import com.smarthub.baseapplication.ui.fragments.home.MyTaskItemAdapter

class TaskAdapter : Adapter<TaskAdapter.ViewHold>() {

    var list : ArrayList<Any> = ArrayList()

    open class ViewHold(itemView: View) : ViewHolder(itemView)

    class ListItemViewHold(itemView: View) : ViewHold(itemView) {
        var binding : ProjectTaskListItemBinding = ProjectTaskListItemBinding.bind(itemView)

        fun bindData(data : TaskModelClass){
            binding.titleText.text=data.Taskname
            binding.sla.text=data.SLA
            Glide
                .with(binding.picture)
                .load(data.pictures)
                .placeholder(R.drawable.ic_menu_picture)
                .into(binding.picture)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if((list[position] is String) && (list[position]=="loading")) 0
        else if((list[position] is String) && (list[position]=="no_data")) 1
        else 2
    }

    fun updateList(list : ArrayList<Any>){
        this.list = list
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
                val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar,parent,false)
                ViewHold(view)
            }
            1 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.no_task_list_data,parent,false)
                ViewHold(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.project_task_list_item,parent,false)
                ListItemViewHold(view)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is ListItemViewHold && list[position] is TaskModelClass){
            holder.bindData(list[position] as TaskModelClass)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}