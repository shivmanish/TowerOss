package com.smarthub.baseapplication.ui.fragments.noc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CardLayoutBinding
import com.smarthub.baseapplication.databinding.RfAntennaFragAdapterBinding
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocCompAllData
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils


class TaskNocDataAdapter(var context:Context, var listener: NocDataAdapterListener, var Id: String?) : RecyclerView.Adapter<nocEmptyDataViewHolder>() {
    var list = ArrayList<Any>()

    fun setData(data: ArrayList<NocCompAllData>) {
        this.list.clear()
        this.list.addAll(data)
        notifyDataSetChanged()
    }
    fun addLoading(){
        this.list.clear()
        this.list.add("loading")
        notifyDataSetChanged()
    }
    init {
        list.add("dfshg")
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] is NocCompAllData)
            0
        else 1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): nocEmptyDataViewHolder {
        return if (viewType == 0){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
            TaskNocDataViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar, parent, false)
            nocEmptyDataViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: nocEmptyDataViewHolder, position: Int) {
        if (holder is TaskNocDataViewHolder){
            try {
                val item = list[position] as NocCompAllData
                holder.binding.cardTitle.text=String.format(context.resources.getString(R.string.two_string_format),item.id,Utils.getFormatedDate(item.modified_at.substring(0,10),"ddMMMyyyy"))
                holder.itemview.setOnClickListener {
                    listener.clickedItem(item, Id!!,position)
                }
            }catch (e:java.lang.Exception){
                AppLogger.log("Noc Fragment error : ${e.localizedMessage}")
                Toast.makeText(context,"Noc Fragment error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
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