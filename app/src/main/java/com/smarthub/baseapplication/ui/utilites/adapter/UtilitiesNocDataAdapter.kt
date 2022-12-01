package com.smarthub.baseapplication.ui.utilites.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.UtilitesNocListItemBinding
import com.smarthub.baseapplication.databinding.UtilitiesAcListItemBinding
import com.smarthub.baseapplication.databinding.UtilitiesDgListItemBinding
import com.smarthub.baseapplication.databinding.UtilitiesSmpsListItemBinding

class UtilitesNocDataAdapter(var listener: UtilitesNocDataAdapterListener) : Adapter<UtilitesMainDataViewHolder>() {

    var array: ArrayList<String> = ArrayList()
    val type1 = "SMPS"
    val type2 = "DG"
    val type3 = "AC"
    val type4 = "Fire"

    init {
        array.add("SMPS")
        array.add("DG")
        array.add("AC")
        array.add("Fire")
    }

    fun setData(data: ArrayList<String>) {
//        this.array.addAll(data)
//        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (array[position] is String && array[position]==type1) 1
        else if (array[position] is String && array[position]==type2) 2
        else if (array[position] is String && array[position]==type3) 3
        else if (array[position] is String && array[position]==type4) 4
        else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UtilitesMainDataViewHolder {
       if (viewType == 1){
           var view = LayoutInflater.from(parent.context).inflate(R.layout.utilities_smps_list_item, parent, false)
           return UtilitesSMPSViewHolder(view)
       }else if (viewType == 2){
            var view = LayoutInflater.from(parent.context).inflate(R.layout.utilities_dg_list_item, parent, false)
            return UtilitesDGViewHolder(view)
        }else if (viewType == 3){
           var view = LayoutInflater.from(parent.context).inflate(R.layout.utilities_ac_list_item, parent, false)
           return UtilitesACViewHolder(view)
       }else if (viewType == 4){
           var view = LayoutInflater.from(parent.context).inflate(R.layout.utilites_noc_list_item, parent, false)
           return UtilitesFireViewHolder(view)
       }else{
           var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty, parent, false)
           return UtilitesMainDataViewHolder(view)
       }
    }

    override fun onBindViewHolder(holder: UtilitesMainDataViewHolder, position: Int) {

        if (holder is UtilitesSMPSViewHolder){
            holder.binding.parentRelative.setOnClickListener {
                listener.clickedItem()
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

class UtilitesDGViewHolder(itemview: View) : UtilitesMainDataViewHolder(itemview) {
    var binding = UtilitiesDgListItemBinding.bind(itemView)
}

class UtilitesACViewHolder(itemview: View) : UtilitesMainDataViewHolder(itemview) {
    var binding = UtilitiesAcListItemBinding.bind(itemView)
}

class UtilitesFireViewHolder(itemview: View) : UtilitesMainDataViewHolder(itemview) {
    var binding = UtilitesNocListItemBinding.bind(itemView)
}

interface UtilitesNocDataAdapterListener {
    fun clickedItem()
    fun clickedItemAC()
    fun clickedItemDG()
    fun clickedItemSMP()
    fun clickedItemFireExiting()
}