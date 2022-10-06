package com.smarthub.baseapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AtpHeaderTitleBinding
import com.smarthub.baseapplication.model.atp.AtpHeaderTitle

class QatListAdapter(var list: ArrayList<AtpHeaderTitle>) : RecyclerView.Adapter<QatListAdapter.ViewHold>() {

    init {
        list.add(AtpHeaderTitle("Site Id.","CE-MH-MUMB-SC-0123"))
        list.add(AtpHeaderTitle("Site Category","RTP"))
        list.add(AtpHeaderTitle("Site Type","Small Cell"))
        list.add(AtpHeaderTitle("Site Status","Under Construction"))
        list.add(AtpHeaderTitle("Maintenance Zone","Delhi"))
        list.add(AtpHeaderTitle("Site Address","Andra Pradesh"))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.atp_header_title, parent, false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(hold: ViewHold, pos: Int) {
        var item = list[pos]
        hold.binding.title.text = item.title
        hold.binding.subtitle.text = item.sub_title

    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding : AtpHeaderTitleBinding = AtpHeaderTitleBinding.bind(itemView)
    }



}