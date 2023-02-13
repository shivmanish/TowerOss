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

    var list : ArrayList<Any> = ArrayList()

    fun addItem(item : Any){
        for(i in this.list)
            if (i is String && (i=="loading" || i=="no_data")) this.list.remove(i)
        this.list.add(item)
        notifyDataSetChanged()
    }

    fun addLoading(item : Any){
        this.list.clear()
        this.list.add(item)
        notifyDataSetChanged()
    }

    fun addNoData(item : Any){
        this.list.clear()
        this.list.add(item)
        notifyDataSetChanged()
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun updateList(list : ArrayList<Any>){
        this.list = list
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
                val view = LayoutInflater.from(parent.context).inflate(R.layout.project_item_list,parent,false)
                ViewHoldItem(view,listener)
            }
        }
    }

    class ViewHoldItem(itemView: View,var listener : ProjectsListAdapterListener) : ViewHold(itemView){
        var binding = ProjectItemListBinding.bind(itemView)

        fun bindData(data : ProjectModelDataItem){
            var rnds=(20..80).random()
            binding.name.text = data.Projectname
            binding.remark.text = data.Remarks
            binding.processStatus.text = data.Currentstatus
            binding.totalTask.text = String.format("%d/%d",data.Total_Closed,data.Total_Task)
            binding.totalSla.text=String.format("%d/%d",0,data.Total_SLA)
            binding.progressbarPredict.progress=rnds
            binding.progressStatus.text=rnds.toString()
            binding.siteName.text=data.sitename
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

//        init {
//            binding.btnTask.setOnClickListener {
//                listener.showBottomDialog(data.)
//            }
//        }
    }

    override fun getItemViewType(position: Int): Int {
        return if((list[position] is String) && (list[position]=="loading")) 0
        else if((list[position] is String) && (list[position]=="no_data")) 1
        else 2
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is ViewHoldItem && list[position] is ProjectModelDataItem){
            var data=list[position] as ProjectModelDataItem
            holder.bindData(data)
            holder.binding.processId.text=(position+1).toString()
            holder.binding.btnTask.setOnClickListener {
                listener.showBottomDialog(data.Processname)
            }
        }
    }

    override fun getItemCount(): Int {
      return list.size
    }

    interface ProjectsListAdapterListener{
        fun showBottomDialog(template : String)
    }

}