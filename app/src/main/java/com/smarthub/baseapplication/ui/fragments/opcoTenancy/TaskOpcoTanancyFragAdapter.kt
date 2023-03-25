package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CardLayoutBinding
import com.smarthub.baseapplication.databinding.CustomerListItemBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.OpcoTenencyAllData
import com.smarthub.baseapplication.model.siteInfo.BasicInfoModelItem
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class TaskOpcoTanancyFragAdapter(var context:Context,var listener: TaskCustomerDataAdapterListener) : Adapter<TaskOpcoDataViewHolder>() {
    var data = ArrayList<Any>()



    fun addLoading(){
        this.data.clear()
        this.data.add("loading")
        notifyDataSetChanged()
    }

    fun setOpData(data: ArrayList<OpcoTenencyAllData>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position] is OpcoTenencyAllData) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskOpcoDataViewHolder {
        return if (viewType == 0){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
            TaskOpcoDataItemViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar, parent, false)
            TaskOpcoDataViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: TaskOpcoDataViewHolder, position: Int) {
        if(holder is TaskOpcoDataItemViewHolder) {
            val item = data[position] as OpcoTenencyAllData

holder.binding.cardTitle.text = "OPCO "+item.id
            holder.itemview.setOnClickListener {
                listener.clickedItem(item,position)
                AppLogger.log("clicked card data : ===> ${Gson().toJson(item.Opcoinfo.get(0))}")
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

open class TaskOpcoDataViewHolder(var itemview: View) : ViewHolder(itemview) {}

class TaskOpcoDataItemViewHolder(itemview: View) : TaskOpcoDataViewHolder(itemview) {
    var binding = CardLayoutBinding.bind(itemView)
}

interface TaskCustomerDataAdapterListener{
    fun clickedItem(data : OpcoTenencyAllData,parentIndex:Int)
}