package com.smarthub.baseapplication.ui.adapter.qat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AtpViewType1Binding
import com.smarthub.baseapplication.databinding.AtpViewType2Binding
import com.smarthub.baseapplication.databinding.QatTitleViewBinding
import com.smarthub.baseapplication.listeners.QatItemListener
import com.smarthub.baseapplication.model.atp.AtpCardList
import com.smarthub.baseapplication.model.atp.AtpHeaderStatus
import com.smarthub.baseapplication.model.atp.AtpHeaderTitle
import com.smarthub.baseapplication.model.atp.HeaderList
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Item
import com.smarthub.baseapplication.utils.Utils

class QatTitleAdapter(var listener: QatItemListener, val list: List<Item>) : RecyclerView.Adapter<QatTitleAdapter.ViewHold>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.qat_title_view, parent, false)
        return ViewHold(view,listener)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.itemTitle.text = list[position].QATItem
        holder.binding.listView.adapter = QatSubTitleAdapter(listener,list[position].subitem)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    open class ViewHold(itemView: View,listener: QatItemListener) : RecyclerView.ViewHolder(itemView){
        val binding = QatTitleViewBinding.bind(itemView)
        init {
            binding.collapsingLayout.tag = false
            binding.collapsingLayout.setOnClickListener {
                if (it.tag is Boolean && it.tag as Boolean){
                    Utils.collapse(binding.listView)
                    binding.imgDropdown.setImageResource(R.drawable.down_arrow,)
               binding.root.isSelected = false
                }else{
                    Utils.expand(binding.listView)
                    binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up_faq)
                    binding.root.isSelected = true
                }
                it.tag = !(it.tag as Boolean)
            }
//            Utils

        }
    }


}