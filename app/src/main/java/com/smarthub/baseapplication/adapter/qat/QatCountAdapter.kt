package com.smarthub.baseapplication.adapter.qat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.QatCheckopenItemBinding
import com.smarthub.baseapplication.databinding.QatPunchPointItemBinding
import com.smarthub.baseapplication.databinding.QatSpinnerItemViewBinding
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.qatcheck.OpenQatDataModel

class QatCountAdapter(var listener: QatProfileListener) : RecyclerView.Adapter<QatCountAdapter.ViewHold>() {


    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : QatSpinnerItemViewBinding = QatSpinnerItemViewBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.qat_spinner_item_view,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.root.setOnClickListener {
            listener.itemClicked()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}