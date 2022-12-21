package com.smarthub.baseapplication.ui.fragments.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SearchResultItemBinding
import com.smarthub.baseapplication.model.search.SearchList
import com.smarthub.baseapplication.model.search.SearchListItem
import com.smarthub.baseapplication.utils.AppLogger

class SearchResultAdapter(var context: Context?,var listener : SearchResultListener) : RecyclerView.Adapter<SearchResultAdapter.ViewHold>() {

    var list: ArrayList<Any> = ArrayList()

    fun updateList(data: SearchList){
        this.list.clear()
        this.list.addAll(data)

        if (this.list.isEmpty())
            this.list.add("no_data")
        this.list.reverse()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if((list[position] is String) && (list[position]=="no_data")) 0
        else 1
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return if (viewType == 0) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.no_search_list_data, parent, false)
            ViewHold(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.search_result_item, parent, false)
            ItemViewHold(view)
        }
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {

       when(holder){
           is ItemViewHold->{
               var item  = list[position] as SearchListItem
               holder.binding.textName.text =if (item.Siteid!=null) item.Siteid else "null"
               holder.binding.text.text = if (item.id!=null) item.id else "null"
               holder.binding.textLayout.setOnClickListener {
                   listener.onSearchItemSelected(item)
                   list.clear()
                   notifyDataSetChanged()
               }
           }
       }
    }

    interface SearchResultListener{
        fun onSearchItemSelected(item : SearchListItem?)
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    open class ItemViewHold(itemView: View) : ViewHold(itemView){
        var binding : SearchResultItemBinding = SearchResultItemBinding.bind(itemView)
    }

}