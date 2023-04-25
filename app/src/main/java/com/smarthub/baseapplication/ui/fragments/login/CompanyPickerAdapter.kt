package com.smarthub.baseapplication.ui.fragments.login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CompanyItemViewBinding
import com.smarthub.baseapplication.databinding.SearchChilpLayoutBinding
import com.smarthub.baseapplication.model.profile.CompanyData
import com.smarthub.baseapplication.model.search.SearchHistoryList
import com.smarthub.baseapplication.model.search.SearchList
import com.smarthub.baseapplication.model.search.SearchListItem

class CompanyPickerAdapter(var context: Context?, var listner: CompanyPickerAdapterListner) : RecyclerView.Adapter<CompanyPickerAdapter.ViewHold>() {

    var currentSelected = 0
    private var searchQatModels: List<CompanyData?> = ArrayList()

    fun updateList(list: List<CompanyData?>){
        searchQatModels=list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return searchQatModels.size
    }

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding = CompanyItemViewBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.company_item_view, parent, false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        val item = searchQatModels[position]
        holder.binding.company.text = item?.ownername
        holder.binding.company.setCompoundDrawablesWithIntrinsicBounds(if (currentSelected == position) R.drawable.btn_select_checked else R.drawable.btn_lang_unchecked,
            0, 0,0)
        holder.binding.company.setOnClickListener {
            val old = currentSelected
            currentSelected = position
            listner.clickedSearchHistoryItem(searchQatModels[position])
            if (old>=0 && old<searchQatModels.size)
                notifyItemChanged(old)
            notifyItemChanged(currentSelected)
        }
    }

    interface CompanyPickerAdapterListner{
        fun clickedSearchHistoryItem(historyItem: CompanyData?)
    }
}