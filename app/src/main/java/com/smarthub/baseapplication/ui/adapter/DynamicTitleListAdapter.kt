package com.smarthub.baseapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.DynamicTitleListBinding
import com.smarthub.baseapplication.ui.dynamic.DynamicTitleList
import com.smarthub.baseapplication.ui.dynamic.TitleItem

class DynamicTitleListAdapter(var data: DynamicTitleList,var listener : DynamicItemListAdapter.DynamicItemListAdapterListener) : RecyclerView.Adapter<DynamicTitleListAdapter.ViewHold>() {

    var currentOpened = -1
    var recyclerView: RecyclerView?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dynamic_title_list, parent, false)
        return ViewHold(view)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        super.onAttachedToRecyclerView(recyclerView)
    }

    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView!=null)
            this.recyclerView?.scrollToPosition(position)
    }

    override fun onBindViewHolder(hold: ViewHold, pos: Int) {
        var item : TitleItem = data.data[pos]
        hold.binding.itemTitle.text = item.title
        hold.binding.itemCollapse.adapter = DynamicItemListAdapter(data.data[pos].listData,listener)
        hold.binding.collapsingLayout.setOnClickListener {
            updateList(pos)
        }
        if (currentOpened == pos) {
            hold.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
            hold.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            hold.binding.itemLine.visibility = View.GONE
            hold.binding.itemCollapse.visibility = View.VISIBLE
            hold.binding.iconLayout.visibility = View.VISIBLE
        } else {
            hold.binding.itemTitle.tag = false
            hold.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
            hold.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            hold.binding.itemLine.visibility = View.VISIBLE
            hold.binding.itemCollapse.visibility = View.GONE
            hold.binding.iconLayout.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return data.data.size
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding : DynamicTitleListBinding = DynamicTitleListBinding.bind(itemView)
        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }
        }
    }
}