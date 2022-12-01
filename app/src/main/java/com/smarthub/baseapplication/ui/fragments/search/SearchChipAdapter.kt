package com.smarthub.baseapplication.ui.fragments.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SearchChilpLayoutBinding

class SearchChipAdapter(var context: Context?) : RecyclerView.Adapter<SearchChipAdapter.ViewHold>() {

    private var searchQatModels: ArrayList<String> = ArrayList()
    init {
        searchQatModels.add("SC_066593333")
        searchQatModels.add("CSE_0665")
        searchQatModels.add("TSC_6542STFC")
        searchQatModels.add("ZABBSC_6542FC")
        searchQatModels.add("SC_65FC")
        searchQatModels.add("FCC_65FC")
    }

    override fun getItemCount(): Int {
        return searchQatModels.size
    }

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding = SearchChilpLayoutBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.search_chilp_layout, parent, false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.chipItem.text = searchQatModels[position]
    }
}