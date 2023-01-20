package com.smarthub.baseapplication.ui.adapter.spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SearchResultItemBinding
import com.smarthub.baseapplication.model.search.*
import com.smarthub.baseapplication.ui.fragments.search.SearchResultAdapter

class SearchArrayAdapter(context: Context?,var list: ArrayList<Any?>,var listener : SearchResultAdapter.SearchResultListener) : ArrayAdapter<Any?>(context!!, 0, list) {
    
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView!!, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView!!, parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v = LayoutInflater.from(context).inflate(R.layout.custom_spinner_view, parent, false)

        var binding : SearchResultItemBinding = SearchResultItemBinding.bind(v)
        if (list[position] is SearchListItem) {
            val item = list[position] as SearchListItem
            binding.textName.text = item.name
            binding.text.text = item.id
            binding.textLayout.setOnClickListener {
                listener.onSearchItemSelected(item)
                list.clear()
                notifyDataSetChanged()
            }
        }
        else  if (list[position] is SearchSiteIdItem) {
            val item = list[position] as SearchSiteIdItem
            binding.textName.text = item.siteID
            binding.text.text = item.id
            binding.textLayout.setOnClickListener {
                listener.onSearchItemSelected(item)
                list.clear()
                notifyDataSetChanged()
            }
        }
        else  if (list[position] is SearchSiteNameItem) {
            val item = list[position] as SearchSiteNameItem
            binding.textName.text = item.siteName
            binding.text.text = item.id
            binding.textLayout.setOnClickListener {
                listener.onSearchItemSelected(item)
                list.clear()
                notifyDataSetChanged()
            }
        }
        else  if (list[position] is SearchAliasNameItem) {
            val item = list[position] as SearchAliasNameItem
            binding.textName.text = item.aliasName
            binding.text.text = item.id
            binding.textLayout.setOnClickListener {
                listener.onSearchItemSelected(item)
                list.clear()
                notifyDataSetChanged()
            }
        }
        else  if (list[position] is SearchSiteOpcoName) {
            val item = list[position] as SearchSiteOpcoName
            binding.textName.text = item.OpcoName
            binding.text.text = item.id
            binding.textLayout.setOnClickListener {
                listener.onSearchItemSelected(item)
                list.clear()
                notifyDataSetChanged()
            }
        }
        else  if (list[position] is SearchSiteOpcoSiteId) {
            val item = list[position] as SearchSiteOpcoSiteId
            binding.textName.text = item.OpcoSiteID
            binding.text.text = item.id
            binding.textLayout.setOnClickListener {
                listener.onSearchItemSelected(item)
                list.clear()
                notifyDataSetChanged()
            }
        }
        return binding.root
    }
}