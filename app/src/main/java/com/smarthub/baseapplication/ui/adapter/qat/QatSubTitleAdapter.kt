package com.smarthub.baseapplication.ui.adapter.qat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AtpViewType1Binding
import com.smarthub.baseapplication.databinding.AtpViewType2Binding
import com.smarthub.baseapplication.databinding.QatSubTitleViewBinding
import com.smarthub.baseapplication.databinding.QatTitleViewBinding
import com.smarthub.baseapplication.listeners.QatItemListener
import com.smarthub.baseapplication.model.atp.AtpCardList
import com.smarthub.baseapplication.model.atp.AtpHeaderStatus
import com.smarthub.baseapplication.model.atp.AtpHeaderTitle
import com.smarthub.baseapplication.model.atp.HeaderList
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Item
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Subitem
import com.smarthub.baseapplication.utils.Utils

class QatSubTitleAdapter(var listener: QatSubTitleAdapterListener,val list: List<Subitem>) : RecyclerView.Adapter<QatSubTitleAdapter.ViewHold>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.qat_sub_title_view, parent, false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(hold: ViewHold, pos: Int) {
        hold.binding.titleText.text = list[pos].QATSubItem
        hold.binding.titleText.setOnClickListener {
            listener.itemClicked(list[pos])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = QatSubTitleViewBinding.bind(itemView)
        init {
            binding.titleText.tag = false
        }
    }
    interface QatSubTitleAdapterListener{
        fun itemClicked(data : Subitem)
    }

}