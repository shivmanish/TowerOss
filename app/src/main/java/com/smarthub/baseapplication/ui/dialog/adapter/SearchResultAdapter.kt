package com.smarthub.baseapplication.ui.dialog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SearchResultItemBinding
import com.smarthub.baseapplication.model.search.SearchList
import com.smarthub.baseapplication.model.search.SearchListItem

class SearchResultAdapter(var context: Context?,var listener : SearchResultListener) : RecyclerView.Adapter<SearchResultAdapter.ViewHold>() {

    private var searchQatModels: ArrayList<SearchListItem> = ArrayList()
    var maxCount = 0
    var checkedPosition = -1

    fun showAll(){
        maxCount = searchQatModels.size
        notifyDataSetChanged()
    }

    fun updateList(searchQatModels: SearchList){
        this.searchQatModels = searchQatModels
        notifyDataSetChanged()
        maxCount = if (searchQatModels.size>5) 5 else searchQatModels.size
    }
    override fun getItemCount(): Int {
        return maxCount
    }

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding : SearchResultItemBinding = SearchResultItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.search_result_item, parent, false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.checkBox.isChecked = (checkedPosition == position)
        holder.binding.checkBox.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                var previous = checkedPosition
                checkedPosition = position
                notifyItemChanged(previous)
                listener.onItemSelected(searchQatModels[position])
            }else if (checkedPosition == position)
                listener.onItemSelected(null)
        }
    }

    interface SearchResultListener{
        fun onItemSelected(item : SearchListItem?)
    }
}