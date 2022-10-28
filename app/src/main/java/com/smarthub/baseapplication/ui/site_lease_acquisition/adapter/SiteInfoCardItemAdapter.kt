package com.smarthub.baseapplication.ui.fragments.sitedetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AtpHeaderTitleBinding
import com.smarthub.baseapplication.databinding.CardItemBinding
import com.smarthub.baseapplication.databinding.InfoCardItemBinding
import com.smarthub.baseapplication.databinding.LangItemBinding
import com.smarthub.baseapplication.listeners.QatListListener
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.LangModel
import com.smarthub.baseapplication.model.atp.AtpHeaderStatus
import com.smarthub.baseapplication.model.atp.AtpHeaderTitle
import com.smarthub.baseapplication.model.atp.AtpListItem

class SiteLeaseInfoCardAdapter() : RecyclerView.Adapter<SiteLeaseInfoCardAdapter.ViewHold>() {

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