package com.smarthub.baseapplication.ui.fragments.qat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AddStartTaskBinding
import com.smarthub.baseapplication.databinding.CardLayoutBinding
import com.smarthub.baseapplication.databinding.QatCheckopenItemBinding
import com.smarthub.baseapplication.databinding.QatListItemBinding
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.qatcheck.OpenQatDataModel
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.model.siteInfo.qat.QatCardItem
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.QATMainLaunch
import com.smarthub.baseapplication.ui.fragments.noc.TaskAddNewData
import com.smarthub.baseapplication.ui.fragments.noc.nocEmptyDataViewHolder
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.OpcoDataViewHolder
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils

class TaskQATListAdapter(var context: Context, var listener: QatTaskAdapterListener, var Id: String) : RecyclerView.Adapter<TaskQATListAdapter.ViewHold>() {

    var list: ArrayList<Any> = ArrayList()

    fun addLoading(){
        this.list.clear()
        this.list.add("loading")
        notifyDataSetChanged()
    }

//    fun addNew(){
//        this.list.clear()
//        this.list.add("AddNew")
//        notifyDataSetChanged()
//    }

    fun setData(data: ArrayList<QATMainLaunch>) {
        this.list.clear()
        this.list.addAll(data)
        notifyDataSetChanged()
    }

    fun setData(qatMainLaunch: QATMainLaunch) {
        this.list.clear()
        this.list.add(qatMainLaunch)
        notifyDataSetChanged()
    }

    open class ViewHold(var itemView: View) : RecyclerView.ViewHolder(itemView)

    class ItemViewHold(itemView: View):ViewHold(itemView){
        var binding : CardLayoutBinding = CardLayoutBinding.bind(itemView)
    }

//    class QatAddNewData(var itemview: View): ViewHold(itemview){
//        var binding= AddStartTaskBinding.bind(itemView)
//    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] is QATMainLaunch) 0
        else if (list[position] is String && list[position]=="AddNew")
            1
        else 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return if (viewType == 0) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
            return ItemViewHold(view)
        }
//        else if (viewType == 1){
//            val view = LayoutInflater.from(parent.context).inflate(R.layout.add_start_task, parent, false)
//            return QatAddNewData(view)
//        }
        else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar, parent, false)
            ViewHold(view)
        }
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {


        if(holder is ItemViewHold){
            try {
                val item=list[position] as QATMainLaunch
                holder.binding.root.setOnClickListener {
                    listener.clickedItem(item,Id,position)
                }
               holder.binding.cardTitle.text = "QAT "+list.get(position)
            //        holder.binding.statusQat.text=list[position].
            }catch (e:Exception){
                AppLogger.log("Something Went Wrong in QATListAdapter , Localize Message: ${e.localizedMessage} ")
            }

        }
//        else if(holder is QatAddNewData){
//            holder.binding.card.setOnClickListener {
//                listener.addNew()
//            }
//        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface QatTaskAdapterListener{
        fun clickedItem(data : QATMainLaunch?, Id :String, index:Int)
        fun addNew()
    }
}