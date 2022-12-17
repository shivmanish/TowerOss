package com.smarthub.baseapplication.ui.fragments.search

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SearchResultItemBinding
import com.smarthub.baseapplication.model.search.SearchList
import com.smarthub.baseapplication.model.search.SearchListItem

class SearchResultAdapter(var context: Context?,var listener : SearchResultListener) : RecyclerView.Adapter<SearchResultAdapter.ViewHold>() {

    var searchQatModels: ArrayList<SearchListItem> = ArrayList()

    fun updateList(searchQatModels: SearchList){
        this.searchQatModels = searchQatModels
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return searchQatModels.size
    }

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding : SearchResultItemBinding = SearchResultItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.search_result_item, parent, false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
       holder.binding.textLayout.setOnClickListener {
           listener.onSearchItemSelected(searchQatModels[position])
           searchQatModels.clear()
           notifyDataSetChanged()
       }
    }

    interface SearchResultListener{
        fun onSearchItemSelected(item : SearchListItem?)
    }
}