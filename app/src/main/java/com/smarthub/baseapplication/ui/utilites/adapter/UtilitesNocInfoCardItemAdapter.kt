package com.smarthub.baseapplication.ui.fragments.sitedetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.InfoCardItemBinding
import com.smarthub.baseapplication.model.atp.AtpHeaderStatus

class UtilitesNocInfoCardAdapter() : RecyclerView.Adapter<UtilitesNocInfoCardAdapter.ViewHold>() {

    var list : ArrayList<AtpHeaderStatus> = ArrayList()

    init {
        list.add(AtpHeaderStatus("item1","item2"))
        list.add(AtpHeaderStatus("item1","item2"))
        list.add(AtpHeaderStatus("item1","item2"))
    }

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : InfoCardItemBinding = InfoCardItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.info_card_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {


    }

    override fun getItemCount(): Int {
        return list.size
    }
}