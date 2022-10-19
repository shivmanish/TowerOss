package com.smarthub.baseapplication.ui.adapter.qat

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.listeners.AddImageListener


class GADynamicAdapter(var list: ArrayList<Any>,var listener: AddImageListener) :
    RecyclerView.Adapter<GADynamicAdapter.ViewHold>() {

    open class ViewHold(var view: View) : RecyclerView.ViewHolder(view)

    class SpinerViewHold(view: View) : ViewHold(view) {
//        var spinerBinding = DynamicSpinerBinding.bind(view)
    }

    class TextFieldViewHold(view: View) : ViewHold(view) {
        //    var dynamicTextfieldLayoutBinding = DynamicTextfieldLayoutBinding.bind(view)
    }

    class MainScreenHold(view: View, var listener: AddImageListener) : ViewHold(view) {
//        var Adapter= AtpAddImageListAdapter(listener)
//        var addimge=view.findViewById<RecyclerView>(R.id.rvImageAttachment)
//
//        init{
//
//
//            /*var list : ArrayList<Any>, var listener: AddImageListener*/
//           addimge.adapter=Adapter
//        }

    }

    override fun getItemViewType(position: Int): Int {
        if (list[position] is String && (list[position] as String) == "spiner_item")
            return 1
        if (list[position] is String && (list[position] as String) == "text_filed_items")
            return 2
        if (list[position] is String && (list[position] as String) == "main_layout")
            return 3

        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {

        return when (viewType) {
            1 -> {
                var view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.dynamic_spiner, parent, false)
                SpinerViewHold(view)
            }
            2 -> {
                var view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.dynamic_textfield_layout, parent, false)
                TextFieldViewHold(view)
            }
            3 -> {
                var view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.rv_ga_list_layout, parent, false)
                MainScreenHold(view, listener)
            }
            else -> {
                var view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.pro_single_item_view, parent, false)
                return ViewHold(view)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is SpinerViewHold) {
            Log.d("status", "SpinerViewHold")
        } else if (holder is TextFieldViewHold) {
            Log.d("status", "TextFieldViewHold")
        }
        else if (holder is MainScreenHold) {

//            Log.d("status", "MainScreenHold")
//            Log.d(
//                "status",
//                holder.view.findViewById<RecyclerView>(R.id.rvImageAttachment).toString()
//
//            )
//            holder.Adapter.UpdateList(ArrayList())

        } else {
            Log.d("status", "default")
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

