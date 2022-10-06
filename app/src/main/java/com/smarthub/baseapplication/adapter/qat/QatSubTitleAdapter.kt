package com.smarthub.baseapplication.adapter.qat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AtpViewType1Binding
import com.smarthub.baseapplication.databinding.AtpViewType2Binding
import com.smarthub.baseapplication.databinding.QatSubTitleViewBinding
import com.smarthub.baseapplication.databinding.QatTitleViewBinding
import com.smarthub.baseapplication.model.atp.AtpCardList
import com.smarthub.baseapplication.model.atp.AtpHeaderStatus
import com.smarthub.baseapplication.model.atp.AtpHeaderTitle
import com.smarthub.baseapplication.model.atp.HeaderList
import com.smarthub.baseapplication.utils.Utils

class QatSubTitleAdapter : RecyclerView.Adapter<QatSubTitleAdapter.ViewHold>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.qat_sub_title_view, parent, false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(hold: ViewHold, pos: Int) {

    }

    override fun getItemCount(): Int {
        return 5
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = QatSubTitleViewBinding.bind(itemView)

        init {
            binding.expansionText.tag = false
            binding.expansionText.setOnClickListener {
                if (it.tag is Boolean && it.tag as Boolean){
                    binding.expansionText.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
                    Utils.collapse(binding.listView)
                }else{
                    binding.expansionText.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_down,0)
                    Utils.expand(binding.listView)
                }
                it.tag = !(it.tag as Boolean)
            }
            Utils
            binding.listView.adapter = QatItemAdapter()
        }
    }

}