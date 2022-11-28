package com.smarthub.baseapplication.ui.fragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AtpHeaderTitleBinding
import com.smarthub.baseapplication.databinding.CardItemBinding
import com.smarthub.baseapplication.databinding.HomeTaskListItemBinding
import com.smarthub.baseapplication.databinding.LangItemBinding
import com.smarthub.baseapplication.databinding.TaskListItemBinding
import com.smarthub.baseapplication.listeners.QatListListener
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.LangModel
import com.smarthub.baseapplication.model.atp.AtpHeaderStatus
import com.smarthub.baseapplication.model.atp.AtpHeaderTitle
import com.smarthub.baseapplication.model.atp.AtpListItem

class TaskHomeItemAdapter : RecyclerView.Adapter<TaskHomeItemAdapter.ViewHold>() {

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : HomeTaskListItemBinding = HomeTaskListItemBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.home_task_list_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {


    }

    override fun getItemCount(): Int {
        return 5
    }
}