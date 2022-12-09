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
    var hashMap = HashMap<String,String>()
    private var list: ArrayList<String> = ArrayList()
    init {
        list.add("name")
        list.add("siteID")
        list.add("siteName")
        list.add("aliasName")
        list.add("OpcoName")
        list.add("OpcoSiteID")
        list.add("LatLongRadius")

        hashMap["name"] = "Name"
        hashMap["siteID"] = "Site UID"
        hashMap["siteName"] = "Site Name"
        hashMap["aliasName"] = "Alias Name"
        hashMap["OpcoName"] = "OPCO Site Name"
        hashMap["OpcoSiteID"] = "OPCO UID"
        hashMap["LatLongRadius"] = ""
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
        holder.binding.checkText.text= hashMap[list[position]]
        holder.binding.checkText.setCompoundDrawablesWithIntrinsicBounds(0,0,
            if (currentPos == position) R.drawable.btn_select_checked else R.drawable.btn_lang_unchecked,0)
        holder.binding.checkText.setOnClickListener {
            var old = currentPos
            currentPos = position
            listener.selectedCategory(list[position])
            if (old>=0 && old<list.size)
                notifyItemChanged(old)
            notifyItemChanged(currentPos)
        }
    }

    interface SearchCategoryListener{
        fun selectedCategory(item:String)
    }
}