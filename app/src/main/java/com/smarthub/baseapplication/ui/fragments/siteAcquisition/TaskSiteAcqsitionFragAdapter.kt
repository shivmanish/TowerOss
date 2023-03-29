package com.smarthub.baseapplication.ui.fragments.siteAcquisition

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AddStartTaskBinding
import com.smarthub.baseapplication.databinding.CardLayoutBinding
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.model.workflow.TaskDataListItem

class TaskSiteAcqsitionFragAdapter(var context:Context, var taskDetails: TaskDataListItem?,var listner:SiteAcqListListener): RecyclerView.Adapter<TaskSiteAcqsitionFragAdapter.ViewHold>() {

    var list = ArrayList<Any>()
    var dataIndex:Int?=null
    fun setData(data: ArrayList<NewSiteAcquiAllData>?) {
        if (taskDetails?.ModuleId!="0"){
            this.list.clear()
            if (data!=null){
                for (item in data){
                    if (item.id.toString()==taskDetails?.ModuleId){
                        this.list.add(item)
                        dataIndex=data.indexOf(item)
                        notifyDataSetChanged()
                        break
                    }
                }
            }
        }
        else{
            this.list.clear()
            this.list.add("AddNew")
            notifyDataSetChanged()
        }
    }
    fun addLoading(){
        this.list.clear()
        this.list.add("loading")
        notifyDataSetChanged()
    }
    init {
        list.add("loading")
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] is String && list[position]=="AddNew")
            2
        else if(list[position] is NewSiteAcquiAllData)
            1
        else
            3
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class DataViewHold(itemView: View):ViewHold(itemView){
        var binding = CardLayoutBinding.bind(itemView)
    }
    class TaskAddNewData(itemView: View):ViewHold(itemView){
        var binding = AddStartTaskBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return when (viewType) {
            1 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
                DataViewHold(view)
            }
            2 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.add_start_task, parent, false)
                TaskAddNewData(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar,parent,false)
                ViewHold(view)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when(holder){
            is DataViewHold->{
                val item = list[position] as NewSiteAcquiAllData
                holder.binding.cardTitle.text="Acquisition #${dataIndex?.plus(1)}"
                holder.itemView.setOnClickListener {
                    if (dataIndex!=null)
                        listner.clickedItem(item,dataIndex!!)
                }
            }
            is TaskAddNewData ->{
                holder.itemView.setOnClickListener {
                    listner.addNew()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface SiteAcqListListener{
        fun clickedItem(data:NewSiteAcquiAllData,parentIndex:Int)
        fun addNew()
    }
}