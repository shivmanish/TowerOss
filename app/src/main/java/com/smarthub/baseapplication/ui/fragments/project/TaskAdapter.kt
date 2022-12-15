package com.smarthub.baseapplication.ui.fragments.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.model.project.TaskModelClass

class TaskAdapter :Adapter<TaskViewModel>(){

    var list = ArrayList<TaskModelClass>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewModel {
        val inflater = LayoutInflater.from(parent.context)
        val view  = inflater.inflate(R.layout.project_task_list_item,parent,false)
        return TaskViewModel(view)
    }

    fun updateList(list : ArrayList<TaskModelClass>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TaskViewModel, position: Int) {
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
class TaskViewModel(itemView: View) : ViewHolder(itemView) {

}