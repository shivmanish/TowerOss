package com.smarthub.baseapplication.ui.site_agreement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BuildingDetailsListItemBinding
import com.smarthub.baseapplication.listeners.QatListListener
import com.smarthub.baseapplication.ui.site_agreement.pojo.BuildingStructure


class BuildingStructureTableAdapter(var list : ArrayList<BuildingStructure>, var listener: QatListListener) : RecyclerView.Adapter<BuildingStructureTableAdapter.ViewHold>() {

    init {
        list.add(BuildingStructure("Site Name","Mangolpuri","2","333"))
        list.add(BuildingStructure("Site Name","Mangolpuri","2","333"))
        list.add(BuildingStructure("Site Name","Mangolpuri","2","333"))
        list.add(BuildingStructure("Site Name","Mangolpuri","2","333"))
        list.add(BuildingStructure("Site Name","Mangolpuri","2","333"))
        list.add(BuildingStructure("Site Name","Mangolpuri","2","333"))

    }

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : BuildingDetailsListItemBinding = BuildingDetailsListItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.building_details_list_item,parent,false)
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