package com.smarthub.baseapplication.ui.fragments.sstSbc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AddStartTaskBinding
import com.smarthub.baseapplication.databinding.CardLayoutBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocCompAllData
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcAllData
import com.smarthub.baseapplication.model.workflow.TaskDataListItem
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils


class TaskSstSbcDataAdapter(var context:Context, var listener: TaskSstSbcDataAdapterListener, var taskDetails: TaskDataListItem?) : RecyclerView.Adapter<SstSbcEmptyDataViewHolder>() {
    var list = ArrayList<Any>()
    var dataIndex:Int?=null
    fun setData(data: ArrayList<SstSbcAllData>?) {
        if (data!=null && taskDetails?.ModuleId!="0"){
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
    fun setData(data: SstSbcAllData) {
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
        return if (list[position] is SstSbcAllData)
            0
        else if (list[position] is String && list[position]=="AddNew")
            1
        else 2
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SstSbcEmptyDataViewHolder {
        return if (viewType == 0){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
            TaskSstSbcDataViewHolder(view)
        }
        else if (viewType == 1){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.add_start_task, parent, false)
            TaskAddNewData(view)
        }
        else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar, parent, false)
            SstSbcEmptyDataViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: SstSbcEmptyDataViewHolder, position: Int) {
        when (holder){
            is TaskSstSbcDataViewHolder->{
                try {
                    val item = list[position] as SstSbcAllData
                    if (item.SstSbcTeam!=null && item.SstSbcTeam?.isNotEmpty()==true){
                        if (item.SstSbcTeam?.get(0)?.Type!=null && item.SstSbcTeam?.get(0)?.Type!! > 0)
                            AppPreferences.getInstance().setDropDown(holder.binding.cardTitle, DropDowns.SstSbcType.name,item.SstSbcTeam?.get(0)?.Type.toString())
                        holder.binding.cardTitle.text=String.format(context.resources.getString(R.string.two_string_format),holder.binding.cardTitle.text,Utils.getFormatedDate(item.SstSbcTeam?.get(0)?.PODate,"ddMMMyyyy"))
                    }
                    else
                        holder.binding.cardTitle.text=String.format(context.resources.getString(R.string.two_string_format),"SST/SBC",Utils.getFormatedDate(item.created_at,"ddMMMyyyy"))
                    holder.itemview.setOnClickListener {
                        listener.clickedItem(item, AppController.getInstance().taskSiteId,dataIndex)
                    }
                }catch (e:java.lang.Exception){
                    AppLogger.log("TaskSstSbcDataAdapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"TaskSstSbcDataAdapter error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
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

open class SstSbcEmptyDataViewHolder(var itemview: View) : RecyclerView.ViewHolder(itemview)

class TaskSstSbcDataViewHolder( itemview: View) : SstSbcEmptyDataViewHolder(itemview) {
    var binding = CardLayoutBinding.bind(itemView)
}
class TaskAddNewData(itemview: View):SstSbcEmptyDataViewHolder(itemview){
    var binding=AddStartTaskBinding.bind(itemView)
}
interface TaskSstSbcDataAdapterListener{
    fun clickedItem(data: SstSbcAllData, id:String, parentIndex:Int?)
    fun addNew()

}