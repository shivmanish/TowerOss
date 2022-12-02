package com.smarthub.baseapplication.ui.site_lease_acquisition.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PoItemListBinding
import com.smarthub.baseapplication.listeners.QatListListener
import com.smarthub.baseapplication.ui.site_lease_acquisition.pojo.AddPoDetails


class PoDetailsAdapter(var list : ArrayList<AddPoDetails>, var listener: QatListListener) : RecyclerView.Adapter<PoDetailsAdapter.ViewHold>() {

    init {
        list.add(AddPoDetails("Site Name","Mangolpuri","2","333"))
        list.add(AddPoDetails("Site Name","Mangolpuri","2","333"))
        list.add(AddPoDetails("Site Name","Mangolpuri","2","333"))
        list.add(AddPoDetails("Site Name","Mangolpuri","2","333"))
        list.add(AddPoDetails("Site Name","Mangolpuri","2","333"))
        list.add(AddPoDetails("Site Name","Mangolpuri","2","333"))

    }

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : PoItemListBinding = PoItemListBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.po_item_list,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
//        holder.binding.txtItemCode.text = list[position].title
//        holder.binding.subtitle.text = list[position].sub_title
        holder.binding.root.setOnClickListener {
            listener.itemClicked()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}