package com.smarthub.baseapplication.ui.fragments.qat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.QatCheckopenItemBinding
import com.smarthub.baseapplication.databinding.QatListItemBinding
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.qatcheck.OpenQatDataModel
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.model.siteInfo.qat.QatCardItem
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.QATMainLaunch
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.OpcoDataViewHolder
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils

class QATListAdapter(var context: Context, var listener: QatMainAdapterListener,var Id: String) : RecyclerView.Adapter<QATListAdapter.ViewHold>() {

    var list: ArrayList<Any> = ArrayList()

    fun addLoading(){
        this.list.clear()
        this.list.add("loading")
        notifyDataSetChanged()
    }

    fun setData(data: ArrayList<QATMainLaunch>) {
        this.list.clear()
        this.list.addAll(data)
        notifyDataSetChanged()
    }

    open class ViewHold(var itemView: View) : RecyclerView.ViewHolder(itemView)

    class ItemViewHold(itemView: View):ViewHold(itemView){
        var binding : QatListItemBinding = QatListItemBinding.bind(itemView)
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] is QATMainLaunch) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return if (viewType == 0) {
            var view =
                LayoutInflater.from(parent.context).inflate(R.layout.qat_list_item_, parent, false)
            return ItemViewHold(view)
        }
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
                holder.binding.textName3.text = item.Instruction
                holder.binding.assignee.text = item.AssignedToUserName
                if (item.isActive=="True"){
                    holder.binding.statusQat.text="Open"
                }
                else{
                    holder.binding.statusQat.text="Closed"
                    holder.binding.statusQat.setTextColor(ContextCompat.getColor(context,R.color.colorRed))
                }
                holder.binding.date.text=Utils.getFormatedDate(item.created_at.substring(0,10),"dd MMM yyyy")
                holder.binding.time.text=Utils.get12hrformate(item.created_at.substring(11,19)).uppercase()
                holder.binding.idTxtCircle.text=item.Category.size.toString()
//        holder.binding.statusQat.text=list[position].
            }catch (e:Exception){
                AppLogger.log("Something Went Wrong in QATListAdapter , Localize Message: ${e.localizedMessage} ")
            }

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}