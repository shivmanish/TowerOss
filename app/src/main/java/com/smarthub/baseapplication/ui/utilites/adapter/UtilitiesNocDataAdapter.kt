package com.smarthub.baseapplication.ui.utilites.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.UtilitesBatteryListItemBinding
import com.smarthub.baseapplication.databinding.UtilitesNocListItemBinding
import com.smarthub.baseapplication.databinding.UtilitesSpdListItemBinding
import com.smarthub.baseapplication.databinding.UtilitiesAcListItemBinding
import com.smarthub.baseapplication.databinding.UtilitiesDgListItemBinding
import com.smarthub.baseapplication.databinding.UtilitiesSmpsListItemBinding
import com.smarthub.baseapplication.model.siteInfo.utilitiesEquip.UtililitiesEquipAllDadaItem
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.pojo.PowerAndFuel

class UtilitesNocDataAdapter(var listener: UtilitesNocDataAdapterListener) : Adapter<UtilitesNocDataAdapter.ViewHolder>() {

    var utilitydatalist: ArrayList<UtililitiesEquipAllDadaItem> = ArrayList()
    var list: ArrayList<String> = ArrayList()
    val type1 = "SMPS"
    val type2 = "Battery Bank"
    val type3 = "DG"
    val type4 = "AC"
    val type5 = "Fire"
    val type6 = "SPD"

    init {
        list.add("SMPS")
        list.add("Battery Bank")
        list.add("DG")
        list.add("AC")
        list.add("Fire")
        list.add("SPD")
    }
    fun setData(data: ArrayList<UtililitiesEquipAllDadaItem>) {
        this.utilitydatalist.clear()
        this.utilitydatalist.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(var itemview: View) : RecyclerView.ViewHolder(itemview) {
        var binding:UtilitiesSmpsListItemBinding = UtilitiesSmpsListItemBinding.bind(itemView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
           val view = LayoutInflater.from(parent.context).inflate(R.layout.utilities_smps_list_item, parent, false)
           return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (list[position]==type1){
            holder.binding.titel.text=list[position]
            holder.binding.cardItem.setOnClickListener {
                listener.clickedItem(position)
            }
        }
        else if (list[position]==type2){
            holder.binding.titel.text=list[position]
            holder.binding.cardItem.setOnClickListener {
                listener.clickedItem(position)
//                Toast.makeText(it.context, "login", Toast.LENGTH_SHORT).show()
            }
        }
        else if (list[position]==type3){
            holder.binding.titel.text=list[position]
            holder.binding.cardItem.setOnClickListener {
                listener.clickedItem(position)
//                Toast.makeText(it.context, "login", Toast.LENGTH_SHORT).show()
            }
        }
        else if (list[position]==type4){
            holder.binding.titel.text=list[position]
            holder.binding.cardItem.setOnClickListener {
                listener.clickedItem(position)
//                Toast.makeText(it.context, "login", Toast.LENGTH_SHORT).show()
            }
        }
        else if (list[position]==type5){
            holder.binding.titel.text=list[position]
            holder.binding.cardItem.setOnClickListener {
                listener.clickedItem(position)
//                Toast.makeText(it.context, "login", Toast.LENGTH_SHORT).show()
            }
        }
        else if (list[position]==type6){
            holder.binding.titel.text=list[position]
            holder.binding.cardItem.setOnClickListener {
                listener.clickedItem(position)
//                Toast.makeText(it.context, "login", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface UtilitesNocDataAdapterListener {
    fun clickedItem(position:Int)
}