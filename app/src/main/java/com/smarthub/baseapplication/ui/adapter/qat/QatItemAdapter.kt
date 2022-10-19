package com.smarthub.baseapplication.ui.adapter.qat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AtpViewType1Binding
import com.smarthub.baseapplication.databinding.AtpViewType2Binding
import com.smarthub.baseapplication.databinding.QatItemViewBinding
import com.smarthub.baseapplication.databinding.QatTitleViewBinding
import com.smarthub.baseapplication.listeners.QatItemListener
import com.smarthub.baseapplication.model.atp.AtpCardList
import com.smarthub.baseapplication.model.atp.AtpHeaderStatus
import com.smarthub.baseapplication.model.atp.AtpHeaderTitle
import com.smarthub.baseapplication.model.atp.HeaderList
import com.smarthub.baseapplication.utils.Utils

class QatItemAdapter(var listener: QatItemListener) : RecyclerView.Adapter<QatItemAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()
    init {
        list.add("Pole")
        list.add("Pole Mount")
        list.add("Pole MountFoundation Steel")
        list.add("Hilti Anchor")
        list.add("Earthing")
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.qat_item_view, parent, false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(hold: ViewHold, pos: Int) {
        hold.binding.expansionText.text = list[pos]
        hold.binding.expansionText.setOnClickListener {
            var item = listener.itemClicked()
//            list.add(item)
//            notifyItemChanged(pos)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = QatItemViewBinding.bind(itemView)

    }

}