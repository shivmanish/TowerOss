package com.smarthub.baseapplication.ui.fragments.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SearchResultItemBinding
import com.smarthub.baseapplication.model.search.*
import com.smarthub.baseapplication.utils.AppLogger

class SearchResultAdapter(var context: Context?,var listener : SearchResultListener) : RecyclerView.Adapter<SearchResultAdapter.ViewHold>() {

    var list: ArrayList<Any> = ArrayList()

    fun updateList(data: List<Any>){
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
    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView){}

    open class ItemViewHold(itemView: View) : ViewHold(itemView){
        var binding : SearchResultItemBinding = SearchResultItemBinding.bind(itemView)
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
               AppLogger.log("item :${Gson().toJson(list[position])}")
               if (list[position] is SearchListItem) {
                   val item = list[position] as SearchListItem
                   holder.binding.textName.text = item.name
                   holder.binding.text.text = item.id
                   holder.binding.textLayout.setOnClickListener {
                       listener.onSearchItemSelected(item)
                       list.clear()
                       notifyDataSetChanged()
                   }
               }
               else  if (list[position] is SearchSiteIdItem) {
                   val item = list[position] as SearchSiteIdItem
                   holder.binding.textName.text = item.siteID
                   holder.binding.text.text = item.id
                   holder.binding.textLayout.setOnClickListener {
                       listener.onSearchItemSelected(item)
                       list.clear()
                       notifyDataSetChanged()
                   }
               }
               else  if (list[position] is SearchSiteNameItem) {
                   val item = list[position] as SearchSiteNameItem
                   holder.binding.textName.text = item.siteName
                   holder.binding.text.text = item.id
                   holder.binding.textLayout.setOnClickListener {
                       listener.onSearchItemSelected(item)
                       list.clear()
                       notifyDataSetChanged()
                   }
               }
               else  if (list[position] is SearchAliasNameItem) {
                   val item = list[position] as SearchAliasNameItem
                   holder.binding.textName.text = item.aliasName
                   holder.binding.text.text = item.id
                   holder.binding.textLayout.setOnClickListener {
                       listener.onSearchItemSelected(item)
                       list.clear()
                       notifyDataSetChanged()
                   }
               }
               else  if (list[position] is SearchSiteOpcoName) {
                   val item = list[position] as SearchSiteOpcoName
                   holder.binding.textName.text = item.OpcoName
                   holder.binding.text.text = item.id
                   holder.binding.textLayout.setOnClickListener {
                       listener.onSearchItemSelected(item)
                       list.clear()
                       notifyDataSetChanged()
                   }
               }
               else  if (list[position] is SearchSiteOpcoSiteId) {
                   val item = list[position] as SearchSiteOpcoSiteId
                   holder.binding.textName.text = item.OpcoSiteID
                   holder.binding.text.text = item.id
                   holder.binding.textLayout.setOnClickListener {
                       listener.onSearchItemSelected(item)
                       list.clear()
                       notifyDataSetChanged()
                   }
               }
           }
       }
    }

    interface SearchResultListener{
        fun onSearchItemSelected(item : Any?)
    }



}