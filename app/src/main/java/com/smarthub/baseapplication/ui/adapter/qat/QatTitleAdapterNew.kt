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
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Subitem
import com.smarthub.baseapplication.utils.Utils

class QatTitleAdapterNew(var listener: QatItemListener) : RecyclerView.Adapter<QatTitleAdapterNew.ViewHold>() {

    var list : ArrayList<String> = ArrayList()
    init {
        list.add("Electircal / Civil Material")
        list.add("Electircal / Civil Construction")
        list.add("Earthing Department")
        list.add("Pole Construction")
        list.add("Others")
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.qat_title_view, parent, false)
        return ViewHold(view,listener)
    }

    override fun onBindViewHolder(hold: ViewHold, pos: Int) {
        hold.binding.itemTitle.text = "${list[pos]}"
    }

    override fun getItemCount(): Int {
        return list.size
    }

    open class ViewHold(itemView: View,listener: QatItemListener) : RecyclerView.ViewHolder(itemView){
        val binding = QatTitleViewBinding.bind(itemView)
        init {
            binding.itemTitle.tag = false
            binding.itemTitle.setOnClickListener {
                if (it.tag is Boolean && it.tag as Boolean){
                    Utils.collapse(binding.listView)
                    binding.itemTitle.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
               binding.root.isSelected = false
                }else{
                    Utils.expand(binding.listView)
                    binding.itemTitle.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_down,0)
                    binding.root.isSelected = true
                }
                it.tag = !(it.tag as Boolean)
            }
//            Utils
            binding.listView.adapter = QatSubTitleAdapter(listener)
        }
    }

}