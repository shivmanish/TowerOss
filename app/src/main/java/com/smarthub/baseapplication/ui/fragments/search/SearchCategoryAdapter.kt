package com.smarthub.baseapplication.ui.fragments.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SearchCategoryListItemBinding

class SearchCategoryAdapter(var context: Context?,var listener : SearchCategoryListener) : RecyclerView.Adapter<SearchCategoryAdapter.ViewHold>() {

    var currentPos = -1
    private var list: ArrayList<String> = ArrayList()
    init {
        list.add("Site UID")
        list.add("Site Name")
        list.add("Site Alternate Name")
        list.add("OPCO UID")
        list.add("OPCO SAP ID")
        list.add("OPCO Site Name")
        list.add("Lat, Long and radius")
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding = SearchCategoryListItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.search_category_list_item, parent, false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.checkText.text= list[position]
        holder.binding.checkText.setCompoundDrawablesWithIntrinsicBounds(0,0,
            if (currentPos == position) R.drawable.btn_select_checked else R.drawable.btn_lang_unchecked,0)
        holder.binding.checkText.setOnClickListener {
            var old = currentPos
            currentPos = position
            listener.selectedCategory(position)
            if (old>=0 && old<list.size)
                notifyItemChanged(old)
            notifyItemChanged(currentPos)
        }
    }

    interface SearchCategoryListener{
        fun selectedCategory(item:Int)
    }
}