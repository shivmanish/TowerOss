package com.smarthub.baseapplication.ui.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R

class TaskAdapter :Adapter<TaskViewModel>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewModel {
        val inflater = LayoutInflater.from(parent.context)
        val view  = inflater.inflate(R.layout.project_task_list_item,parent,false)
        return TaskViewModel(view)
    }

    override fun onBindViewHolder(holder: TaskViewModel, position: Int) {
    }

    override fun getItemCount(): Int {
        return 5
    }
}
class TaskViewModel(itemView: View) : ViewHolder(itemView) {

}