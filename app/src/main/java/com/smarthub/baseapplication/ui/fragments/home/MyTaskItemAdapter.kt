package com.smarthub.baseapplication.ui.fragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.HomeTaskListItemBinding
import com.smarthub.baseapplication.model.home.MyTeamTask
import com.smarthub.baseapplication.utils.AppLogger
import okhttp3.internal.notifyAll

class MyTaskItemAdapter : RecyclerView.Adapter<MyTaskItemAdapter.ViewHold>() {

    var list : ArrayList<MyTeamTask> = ArrayList()

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : HomeTaskListItemBinding = HomeTaskListItemBinding.bind(itemView)

        fun bindData(data : MyTeamTask){
            binding.taskName.text = data.Taskname
            binding.taskDueData.text = data.enddate
        }
    }

    fun updateList(list : List<MyTeamTask>){
        AppLogger.log("updateList : data ${list.size}")
        this.list = ArrayList(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.home_task_list_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}