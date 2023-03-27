package com.smarthub.baseapplication.ui.fragments.noc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AddStartTaskBinding
import com.smarthub.baseapplication.databinding.CardLayoutBinding
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocCompAllData
import com.smarthub.baseapplication.model.workflow.TaskDataListItem
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils


class TaskNocDataAdapter(var context:Context, var listener: TaskNocDataAdapterListener, var taskDetails: TaskDataListItem?) : RecyclerView.Adapter<nocEmptyDataViewHolder>() {
    var list = ArrayList<Any>()
    var dataIndex:Int?=null
    fun setData(data: ArrayList<NocCompAllData>) {
        if (taskDetails?.ModuleId!="0"){
            this.list.clear()
            for (item in data){
                if (item.id.toString()==taskDetails?.ModuleId){
                    this.list.add(item)
                    dataIndex=data.indexOf(item)
                    notifyDataSetChanged()
                    break
                }
            }
        }
        else{
            this.list.clear()
            this.list.add("AddNew")
            notifyDataSetChanged()
        }

    }
    fun setData(data: NocCompAllData) {
        taskDetails?.ModuleId=data.id.toString()
        this.list.clear()
            this.list.add(data)
            notifyDataSetChanged()

    }
    fun addLoading(){
        this.list.clear()
        this.list.add("loading")
        notifyDataSetChanged()
    }
//    init {
//        list.add("dfshg")
//    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] is NocCompAllData)
            0
        else if (list[position] is String && list[position]=="AddNew")
            1
        else 2
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): nocEmptyDataViewHolder {
        return if (viewType == 0){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
            TaskNocDataViewHolder(view)
        }
        else if (viewType == 1){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.add_start_task, parent, false)
            TaskAddNewData(view)
        }
        else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar, parent, false)
            nocEmptyDataViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: nocEmptyDataViewHolder, position: Int) {
        when (holder){
            is TaskNocDataViewHolder->{
                try {
                    val item = list[position] as NocCompAllData
                    holder.binding.cardTitle.text=String.format(context.resources.getString(R.string.two_string_format),item.id,Utils.getFormatedDate(item.modified_at,"ddMMMyyyy"))
                    holder.itemview.setOnClickListener {
                        listener.clickedItem(item, AppController.getInstance().taskSiteId,dataIndex)
                    }
                }catch (e:java.lang.Exception){
                    AppLogger.log("Noc Fragment error : ${e.localizedMessage}")
                    Toast.makeText(context,"Noc Fragment error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            }
            is TaskAddNewData->{
                holder.itemview.setOnClickListener {
                    listener.addNew()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class TaskNocDataViewHolder( itemview: View) : nocEmptyDataViewHolder(itemview) {
    var binding = CardLayoutBinding.bind(itemView)
}
class TaskAddNewData(itemview: View):nocEmptyDataViewHolder(itemview){
    var binding=AddStartTaskBinding.bind(itemView)
}
interface TaskNocDataAdapterListener{
    fun clickedItem(data:NocCompAllData,id:String,parentIndex:Int?)
    fun addNew()

}