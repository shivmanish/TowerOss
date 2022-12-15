package com.smarthub.baseapplication.ui.fragments.project

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ProjectItemListBinding
import com.smarthub.baseapplication.model.project.ProjectModelDataItem

class ProjectListAdapter(var context : Context,var listener : ProjectsListAdapterListener) : RecyclerView.Adapter<ProjectListAdapter.ViewHold>() {

    var list : ArrayList<ProjectModelDataItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.project_item_list, parent, false)
        return ViewHold(view,listener)
    }

    fun updateList(list : ArrayList<ProjectModelDataItem>){
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHold(itemView: View,var listener : ProjectsListAdapterListener) : RecyclerView.ViewHolder(itemView){
        var binding = ProjectItemListBinding.bind(itemView)

        fun bindData(data : ProjectModelDataItem){
            binding.name.text = data.Processname
            binding.remark.text = data.Remarks
            binding.processStatus.text = data.Status
            binding.totalTask.text = String.format("%d/%d",0,data.Total_Task)
            binding.processId.apply {
                text = data.id
                backgroundTintList = ColorStateList.valueOf(Color.parseColor(
                    when(data.Processcolor.lowercase()){
                        "red"->"#FF0000"
                        "green"->"#03C83A"
                        "blue"->"#3D4E6D"
                        else->"#3D4E6D"
                    }
                ))
            }
        }

        init {
            binding.btnTask.setOnClickListener {
                listener.showBottomDialog(binding.name.text.toString())
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
      return list.size
    }

    interface ProjectsListAdapterListener{
        fun showBottomDialog(template : String)
    }

}