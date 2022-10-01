package com.smarthub.baseapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.model.SiteDetailDataModel

class SiteDetailAdapter(context: Context, list: ArrayList<SiteDetailDataModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    private val context: Context = context
    var list: ArrayList<SiteDetailDataModel> = list

    private inner class View1ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {
            val recyclerViewModel = list[position]

        }
    }

    private inner class View2ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {
            val recyclerViewModel = list[position]

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        if (viewType == VIEW_TYPE_ONE) {
            return View1ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.site_detail_frag, parent, false)
            )
//        }
        /*return View2ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_view_2, parent, false)
        )*/
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position].viewType === VIEW_TYPE_ONE) {
            (holder as View1ViewHolder).bind(position)
        } else {
            (holder as View2ViewHolder).bind(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }
}