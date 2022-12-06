package com.smarthub.baseapplication.ui.fragments.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AtpHeaderTitleBinding
import com.smarthub.baseapplication.databinding.CardItemBinding
import com.smarthub.baseapplication.databinding.LangItemBinding
import com.smarthub.baseapplication.databinding.TaskListItemBinding
import com.smarthub.baseapplication.listeners.QatListListener
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.LangModel
import com.smarthub.baseapplication.model.atp.AtpHeaderStatus
import com.smarthub.baseapplication.model.atp.AtpHeaderTitle
import com.smarthub.baseapplication.model.atp.AtpListItem

class TaskItemAdapter(var listener: itemClickListner) : RecyclerView.Adapter<TaskItemAdapter.ViewHold>() {

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : TaskListItemBinding = TaskListItemBinding.bind(itemView)
        init {
            binding.taskListDropdown.tag=false
            if (binding.taskListDropdown.tag as Boolean){
                binding.taskListDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            }
            else{
                binding.taskListDropdown.setImageResource(R.drawable.ic_arrow_down_black)
               binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.task_list_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.taskListDropdown.setOnClickListener {
            holder.binding.taskListDropdown.tag= !(holder.binding.taskListDropdown.tag as Boolean)
            if (holder.binding.taskListDropdown.tag as Boolean){
                holder.binding.taskListDropdown.setImageResource(R.drawable.ic_arrow_up)
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            }
            else{
                holder.binding.taskListDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }
            holder.binding.itemCollapse.visibility=
                if (holder.binding.taskListDropdown.tag as Boolean) View.VISIBLE else View.GONE
            holder.binding.personTaskItem.setOnClickListener {
                listener.taskAssignDialouge()
            }
//            holder.binding.btnEditCard.visibility=
//                if (holder.binding.taskListDropdown.tag as Boolean) View.VISIBLE else View.GONE


        }

    }

    override fun getItemCount(): Int {
        return 5
    }

    interface itemClickListner {
        fun taskAssignDialouge()
    }
}