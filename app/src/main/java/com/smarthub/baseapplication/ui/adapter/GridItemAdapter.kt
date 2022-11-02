package com.smarthub.baseapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AtpHeaderTitleBinding
import com.smarthub.baseapplication.databinding.CardItemBinding
import com.smarthub.baseapplication.databinding.LangItemBinding
import com.smarthub.baseapplication.listeners.QatListListener
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.LangModel
import com.smarthub.baseapplication.model.atp.AtpHeaderStatus
import com.smarthub.baseapplication.model.atp.AtpHeaderTitle
import com.smarthub.baseapplication.model.atp.AtpListItem

class GridItemAdapter(var list : ArrayList<AtpHeaderStatus>, var listener: QatListListener) : RecyclerView.Adapter<GridItemAdapter.ViewHold>() {

    init {
        list.add(AtpHeaderStatus("item1","item2"))
        list.add(AtpHeaderStatus("item1","item2"))
        list.add(AtpHeaderStatus("item1","item2"))
        list.add(AtpHeaderStatus("item1","item2"))
        list.add(AtpHeaderStatus("item1","item2"))
        list.add(AtpHeaderStatus("item1","item2"))
        list.add(AtpHeaderStatus("item1","item2"))
        list.add(AtpHeaderStatus("item1","item2"))
        list.add(AtpHeaderStatus("item1","item2"))
    }

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : AtpHeaderTitleBinding = AtpHeaderTitleBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.grid_image_view,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.root.setOnClickListener {
            listener.cardClicked()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}