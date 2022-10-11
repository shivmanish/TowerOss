package com.smarthub.baseapplication.adapter.qat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.QatCheckopenItemBinding
import com.smarthub.baseapplication.databinding.QatPunchPointItemBinding
import com.smarthub.baseapplication.databinding.QatSpinnerItemViewBinding
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.qatcheck.OpenQatDataModel

class QatSpinnerItemAdapter(var listener: QatProfileListener) : RecyclerView.Adapter<QatSpinnerItemAdapter.ViewHold>() {


    var list = ArrayList<String>()
    init {
        list.add("6.5 m")
        list.add("Not OK")
    }

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
        holder.binding.firstName.text = "${list[position]}"
    }

    override fun getItemCount(): Int {
        return list.size

    }
}