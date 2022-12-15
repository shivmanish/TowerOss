package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.R
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.databinding.CivilInfraListItemBinding
import com.smarthub.baseapplication.databinding.CustomerListItemBinding

class CivilInfraAdapter (var listner: CivilInfraAdapterListner): Adapter<CivilInfraAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()

    var type1 = "Tower"
    var type2 = "Equipment Room"
    var type3= "Earthing"
    init {
        list.add("Tower")
        list.add("Equipment Room")
        list.add("Earthing")
    }

    class ViewHold(view: View) : ViewHolder(view){
        var binding= CivilInfraListItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.civil_infra_list_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (list[position] is String && list[position]==type1) {
            holder.binding.titalStr.setText("${list[position]}")
            holder.itemView.setOnClickListener {
                listner.clickedTowerItem()
            }
        }
        else if (list[position] is String && list[position]==type2) {
            holder.binding.titalStr.setText("${list[position]}")
            holder.itemView.setOnClickListener {
                listner.clickedEquipmentRoomItem()
            }
        }
        else if (list[position] is String && list[position]==type3) {
            holder.binding.titalStr.setText("${list[position]}")
            holder.itemView.setOnClickListener {
                listner.clickedEarthingItem()
            }
        }
        else{
            holder.binding.titalStr.setText("${list[position]}")
            holder.itemView.setOnClickListener {
                listner.clickedTowerItem()
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface CivilInfraAdapterListner{
        fun clickedTowerItem()
        fun clickedPoleItem()
        fun clickedEquipmentRoomItem()
        fun clickedEarthingItem()
    }
}

