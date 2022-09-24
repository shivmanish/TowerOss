package com.smarthub.baseapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ProSingleItemViewBinding

class ProfileListItemAdapter : RecyclerView.Adapter<ProfileListItemAdapter.ViewHold>() {

    var list : ArrayList<Any> = ArrayList()

    init {
        list.add("single_item")
        list.add("default")
        list.add("double_item")
        list.add("single_item")
    }

    open class ViewHold(var view : View) : RecyclerView.ViewHolder(view) {

    }

    class SingleViewHold(view : View) : ViewHold(view) {
        var proSingleItemViewBinding = ProSingleItemViewBinding.bind(view)
    }

    class DoubleViewHold(view : View) : ViewHold(view) {
//        var proSingleItemViewBinding = ProSingleItemViewBinding.bind(view)
    }

    override fun getItemViewType(position: Int): Int {
        if (list[position] is String && (list[position] as String) == "single_item")
            return 1
        if (list[position] is String && (list[position] as String) == "double_item")
            return 2

        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return when (viewType) {
            1 -> {
                var view  : View = LayoutInflater.from(parent.context).inflate(R.layout.pro_single_item_view,parent,false)
                SingleViewHold(view)
            }
            2 -> {
                var view  : View = LayoutInflater.from(parent.context).inflate(R.layout.pro_double_item_view,parent,false)
                DoubleViewHold(view)
            }
            else -> {
                var view  : View = LayoutInflater.from(parent.context).inflate(R.layout.pro_single_item_view,parent,false)
                return ViewHold(view)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is SingleViewHold){
            Log.d("status","SingleViewHold")
        }else if (holder is DoubleViewHold){
            Log.d("status","DoubleViewHold")
        }else{
            Log.d("status","default")
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}