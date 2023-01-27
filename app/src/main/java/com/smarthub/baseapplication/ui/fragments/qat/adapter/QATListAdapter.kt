package com.smarthub.baseapplication.ui.fragments.qat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.QatCheckopenItemBinding
import com.smarthub.baseapplication.databinding.QatListItemBinding
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.qatcheck.OpenQatDataModel
import com.smarthub.baseapplication.model.siteInfo.qat.QatCardItem

class QATListAdapter(var context: Context, var listener: QatMainAdapterListener,var Id: String) : RecyclerView.Adapter<QATListAdapter.ViewHold>() {

    var list: ArrayList<QatCardItem> = ArrayList()

    fun setData(data: ArrayList<QatCardItem>) {
        this.list.clear()
        this.list.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : QatListItemBinding = QatListItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.qat_list_item_,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.root.setOnClickListener {
            listener.clickedItem(list.get(position),Id,position)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}