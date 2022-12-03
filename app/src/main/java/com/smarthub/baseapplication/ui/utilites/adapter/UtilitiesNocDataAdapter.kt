package com.smarthub.baseapplication.ui.utilites.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.UtilitesBatteryListItemBinding
import com.smarthub.baseapplication.databinding.UtilitesNocListItemBinding
import com.smarthub.baseapplication.databinding.UtilitesSpdListItemBinding
import com.smarthub.baseapplication.databinding.UtilitiesAcListItemBinding
import com.smarthub.baseapplication.databinding.UtilitiesDgListItemBinding
import com.smarthub.baseapplication.databinding.UtilitiesSmpsListItemBinding

class UtilitesNocDataAdapter(var listener: UtilitesNocDataAdapterListener) : Adapter<UtilitesMainDataViewHolder>() {

    var array: ArrayList<String> = ArrayList()
    val type1 = "SMPS"
    val type2 = "Battery Bank"
    val type3 = "DG"
    val type4 = "AC"
    val type5 = "Fire"
    val type6 = "SPD"

    init {
        array.add("SMPS")
        array.add("Battery Bank")
        array.add("DG")
        array.add("AC")
        array.add("Fire")
        array.add("SPD")
    }

    override fun getItemViewType(position: Int): Int {
        return if (array[position] is String && array[position]==type1) 1
        else if (array[position] is String && array[position]==type2) 2
        else if (array[position] is String && array[position]==type3) 3
        else if (array[position] is String && array[position]==type4) 4
        else if (array[position] is String && array[position]==type5) 5
        else if (array[position] is String && array[position]==type6) 6
        else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UtilitesMainDataViewHolder {
       if (viewType == 1){
           var view = LayoutInflater.from(parent.context).inflate(R.layout.utilities_smps_list_item, parent, false)
           return UtilitesSMPSViewHolder(view)
       }
       else if (viewType == 2) {
           var view = LayoutInflater.from(parent.context).inflate(R.layout.utilites_battery_list_item, parent, false)
           return UtilitesBatteryViewHolder(view)
       }
       else if (viewType == 3){
            var view = LayoutInflater.from(parent.context).inflate(R.layout.utilities_dg_list_item, parent, false)
            return UtilitesDGViewHolder(view)
        }else if (viewType == 4){
           var view = LayoutInflater.from(parent.context).inflate(R.layout.utilities_ac_list_item, parent, false)
           return UtilitesACViewHolder(view)
       }else if (viewType == 5){
           var view = LayoutInflater.from(parent.context).inflate(R.layout.utilites_noc_list_item, parent, false)
           return UtilitesFireViewHolder(view)
       }
       else if (viewType == 6){
           var view = LayoutInflater.from(parent.context).inflate(R.layout.utilites_spd_list_item, parent, false)
           return UtilitesSPDViewHolder(view)
       }else{
           var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty, parent, false)
           return UtilitesMainDataViewHolder(view)
       }
    }

    override fun onBindViewHolder(holder: UtilitesMainDataViewHolder, position: Int) {

        if (holder is UtilitesSMPSViewHolder){
            holder.binding.parentRelative.setOnClickListener {
                listener.clickedItem(position)
                Toast.makeText(it.context, "login", Toast.LENGTH_SHORT).show()
            }
        }
        else if (holder is UtilitesBatteryViewHolder){
            holder.binding.parentRelative.setOnClickListener {
                listener.clickedItem(position)
                Toast.makeText(it.context, "login", Toast.LENGTH_SHORT).show()
            }
        }
        else if (holder is UtilitesACViewHolder){
            holder.binding.parentRelative.setOnClickListener {
                listener.clickedItem(position)
                Toast.makeText(it.context, "login", Toast.LENGTH_SHORT).show()
            }
        }
        else if (holder is UtilitesDGViewHolder){
            holder.binding.parentRelative.setOnClickListener {
                listener.clickedItem(position)
                Toast.makeText(it.context, "login", Toast.LENGTH_SHORT).show()
            }
        }
        else if (holder is UtilitesSPDViewHolder){
            holder.binding.parentRelative.setOnClickListener {
                listener.clickedItem(position)
                Toast.makeText(it.context, "login", Toast.LENGTH_SHORT).show()
            }
        }
        else if (holder is UtilitesFireViewHolder){
            holder.binding.parentRelative.setOnClickListener {
                listener.clickedItem(position)
                Toast.makeText(it.context, "login", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun getItemCount(): Int {
        return array.size
    }
}
open class UtilitesMainDataViewHolder(var itemview: View) : ViewHolder(itemview) {

}

class UtilitesSMPSViewHolder(itemview: View) : UtilitesMainDataViewHolder(itemview) {
    var binding = UtilitiesSmpsListItemBinding.bind(itemView)
}

class UtilitesBatteryViewHolder(itemview: View) : UtilitesMainDataViewHolder(itemview) {
    var binding = UtilitesBatteryListItemBinding.bind(itemView)
}
class UtilitesDGViewHolder(itemview: View) : UtilitesMainDataViewHolder(itemview) {
    var binding = UtilitiesDgListItemBinding.bind(itemView)
}

class UtilitesACViewHolder(itemview: View) : UtilitesMainDataViewHolder(itemview) {
    var binding = UtilitiesAcListItemBinding.bind(itemView)
}

class UtilitesFireViewHolder(itemview: View) : UtilitesMainDataViewHolder(itemview) {
    var binding = UtilitesNocListItemBinding.bind(itemView)
}
class UtilitesSPDViewHolder(itemview: View) : UtilitesMainDataViewHolder(itemview) {
    var binding = UtilitesSpdListItemBinding.bind(itemView)
}

interface UtilitesNocDataAdapterListener {
    fun clickedItem(position:Int)
}