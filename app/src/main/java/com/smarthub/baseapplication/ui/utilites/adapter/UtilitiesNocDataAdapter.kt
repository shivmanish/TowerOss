package com.smarthub.baseapplication.ui.fragments.sitedetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.UtilitesNocListItemBinding
import com.smarthub.baseapplication.ui.utilites.fragment.UtilitiesNocMainTabFragmentDirections

class UtilitesNocDataAdapter(
    var listener: UtilitesNocDataAdapterListener,
    var array: ArrayList<String>
) : Adapter<UtilitesNocDataViewHolder>() {

    fun setData(data: ArrayList<String>) {
        this.array.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UtilitesNocDataViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.utilites_noc_list_item, parent, false)
        var addAC = UtilitesNocListItemBinding.bind(view)

        return UtilitesNocDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: UtilitesNocDataViewHolder, position: Int) {

        holder.binding.parentRelative?.setOnClickListener {
            listener.clickedItem()
            Toast.makeText(it.context, "login", Toast.LENGTH_SHORT).show()
        }
        holder.binding.addDG?.setOnClickListener {
            listener.clickedItemDG()
            Toast.makeText(it.context, "login", Toast.LENGTH_SHORT).show()
        }
        holder.binding.addAC?.setOnClickListener {
            listener.clickedItemAC()
     /*       val action =
                UtilitiesNocMainTabFragmentDirections.actionUtilitiesNocMainTabFragmentToAC1UtilitesFrag()
            Navigation.findNavController(it).navigate(action);*/

        }
        holder.binding.addsmp?.setOnClickListener {
           listener.clickedItemSMP()

        }
        holder.binding.addFireExiting?.setOnClickListener {
            listener.clickedItemFireExiting()
            Toast.makeText(it.context, "login", Toast.LENGTH_SHORT).show()
        }
        holder.binding.addAC?.setOnClickListener {
          listener.clickedItemAC()
        //    Navigation.findNavController(it).navigate(UtilitiesNocMainTabFragmentDirections.actionUtilitiesNocMainTabFragmentToAC1UtilitesFrag())

        }
    }

    override fun getItemCount(): Int {
        return array.size
    }
}

class UtilitesNocDataViewHolder(var itemview: View) : ViewHolder(itemview) {
    var binding = UtilitesNocListItemBinding.bind(itemView)
}

interface UtilitesNocDataAdapterListener {
    fun clickedItem()
    fun clickedItemAC()
    fun clickedItemDG()
    fun clickedItemSMP()
    fun clickedItemFireExiting()
}