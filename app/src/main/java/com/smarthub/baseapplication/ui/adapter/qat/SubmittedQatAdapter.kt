package com.smarthub.baseapplication.ui.adapter.qat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.QatChecksubmittedItemBinding
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.qatcheck.OpenQatDataModel


class SubmittedQatAdapter(var list : ArrayList<OpenQatDataModel>,var listener : QatProfileListener) : RecyclerView.Adapter<SubmittedQatAdapter.ViewHold>() {

    init {
        list.add(OpenQatDataModel("item1","item2","item2","item2","item2","item2"))
        list.add(OpenQatDataModel("item1","item2","item2","item2","item2","item2"))
        list.add(OpenQatDataModel("item1","item2","item2","item2","item2","item2"))
        list.add(OpenQatDataModel("item1","item2","item2","item2","item2","item2"))

    }

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : QatChecksubmittedItemBinding = QatChecksubmittedItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.qat_checksubmitted_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.idBtnLessmore.setOnClickListener {
            if (holder.binding.idLessMore.visibility === View.VISIBLE) {
                TransitionManager.beginDelayedTransition(holder.binding.idCardview, AutoTransition())
                holder.binding.idLessMore.visibility = View.GONE
                holder.binding.idBtnLessmore.setImageResource(R.drawable.ic_baseline_expand_more)
            } else {
                TransitionManager.beginDelayedTransition(holder.binding.idCardview, AutoTransition())
                holder.binding.idLessMore.visibility = View.VISIBLE
                holder.binding.idBtnLessmore.setImageResource(R.drawable.ic_baseline_expand_less)
            }
        }
        holder.binding.layoutTop.setOnClickListener {
            listener.itemClicked()
        }
        holder.binding.idCardview.setOnClickListener {
            listener.itemClicked()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}