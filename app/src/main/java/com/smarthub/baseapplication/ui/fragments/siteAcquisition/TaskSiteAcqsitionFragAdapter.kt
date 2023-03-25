package com.smarthub.baseapplication.ui.fragments.siteAcquisition

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CardLayoutBinding
import com.smarthub.baseapplication.databinding.SiteLeaseListItemBinding
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData

class TaskSiteAcqsitionFragAdapter(var context:Context, var listner:SiteAcqListListener): RecyclerView.Adapter<TaskSiteAcqsitionFragAdapter.ViewHold>() {

    var list = ArrayList<Any>()

    fun setData(data: ArrayList<NewSiteAcquiAllData>?) {
        this.list.clear()
        this.list.addAll(data!!)
        notifyDataSetChanged()
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
        return if (list.isEmpty() || list.get(position)==null)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return when (viewType) {
            1 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
                DataViewHold(view)
            }
            2 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_no_data,parent,false)
                ViewHold(view)
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
                holder.binding.cardTitle.text="Acquisition #${position.plus(1)}"
                holder.itemView.setOnClickListener {
                    listner.clickedItem(item,position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface SiteAcqListListener{
        fun clickedItem(data:NewSiteAcquiAllData,parentIndex:Int)
    }
}